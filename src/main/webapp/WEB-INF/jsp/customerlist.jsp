<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h1>Customer List</h1>
    <table id="customerTable">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Customer data will be populated here -->
        </tbody>
    </table>

    <script>
        // Load customer list on page load
        window.onload = function () {
            getCustomerList();
        };

        function getCustomerList() {
            var token = 'REPLACE_WITH_BEARER_TOKEN'; // Replace with the actual bearer token received during login

            fetch('https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list', {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + token,
                },
            })
            .then(response => response.json())
            .then(data => {
                populateCustomerList(data);
            })
            .catch(error => {
                console.error('Error fetching customer list:', error);
            });
        }

        function populateCustomerList(customers) {
            var customerTable = document.getElementById('customerTable');
            var tbody = customerTable.getElementsByTagName('tbody')[0];
            tbody.innerHTML = ''; // Clear previous data

            customers.forEach(customer => {
                var row = '<tr>';
                row += '<td>' + customer.first_name + '</td>';
                row += '<td>' + customer.last_name + '</td>';
                row += '<td>' + customer.email + '</td>';
                row += '<td>' + customer.phone + '</td>';
                row += '<td><button onclick="editCustomer(\'' + customer.uuid + '\')">Edit</button>';
                row += ' <button onclick="deleteCustomer(\'' + customer.uuid + '\')">Delete</button></td>';
                row += '</tr>';
                tbody.innerHTML += row;
            });
        }

        function editCustomer(uuid) {
            // Redirect to the edit customer page with the UUID as a parameter
            window.location.href = 'edit_customer.html?uuid=' + uuid;
        }

        function deleteCustomer(uuid) {
            var token = 'REPLACE_WITH_BEARER_TOKEN'; // Replace with the actual bearer token received during login

            fetch('https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp', {
                method: 'POST',
                headers: {
                    'Authorization': 'Bearer ' + token,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    cmd: 'delete',
                    uuid: uuid,
                }),
            })
            .then(response => {
                if (response.ok) {
                    // Refresh customer list after successful deletion
                    getCustomerList();
                } else {
                    throw new Error('Failed to delete customer');
                }
            })
            .catch(error => {
                console.error('Error deleting customer:', error);
            });
        }
    </script>
</body>
</html>
