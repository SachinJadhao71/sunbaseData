<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form id="loginForm">
        <label for="login_id">Login ID:</label>
        <input type="text" id="login_id" name="login_id" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <button type="submit">Login</button>
    </form>

    <script>
        document.getElementById('loginForm').addEventListener('submit', function (event) {
            event.preventDefault();
            authenticateUser();
        });

        function authenticateUser() {
            var loginData = {
                login_id: document.getElementById('login_id').value,
                password: document.getElementById('password').value,
            };

            fetch('https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginData),
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Authentication failed');
                }
                return response.json();
            })
            .then(data => {
                // Save the token for future API calls (You can store it in a variable or in local storage)
                var token = data.token;
                console.log('Token:', token);

                // Redirect to the customer list page
                window.location.href = 'customer_list.html';
            })
            .catch(error => {
                console.error('Error authenticating user:', error);
                alert('Authentication failed. Please check your login credentials.');
            });
        }
    </script>
</body>
</html>
