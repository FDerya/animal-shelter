document.addEventListener('DOMContentLoaded', () => {
    // Add event listener for form submission
    document.getElementById('login-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        // Get email and password from the form
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        // Define the login URL
        const loginUrl = "http://localhost:8080/auth/login";

        try {
            // Send the login request to the server
            const response = await fetch(loginUrl, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({email, password})
            });

            // Get the message div for displaying messages
            const messageDiv = document.getElementById('message');

            // Handle the server response
            if (response.ok) {
                const data = await response.json();
                const token = data.token;
                const role = data.role;

                // Store the token in sessionStorage
                sessionStorage.setItem('token', token);
                alert("Login successful. Redirecting to appropriate page...");

                // Redirect based on user role
                if (role === 'admin') {
                    window.location.href = 'admin.html';
                } else {
                    window.location.href = 'homePage.html';
                }
            } else if (response.status === 401) {
                messageDiv.textContent = "Login failed. If you do not have an account, please register.";
            } else {
                messageDiv.textContent = "Login failed. Please try again.";
            }
        } catch (error) {
            console.error('Error:', error);
            const messageDiv = document.getElementById('message');
            messageDiv.textContent = "An error occurred while logging in. Please try again.";
        }
    });
});
