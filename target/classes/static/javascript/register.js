document.addEventListener('DOMContentLoaded', () => {
    // Add event listener for form submission
    document.getElementById('register-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        // Get form values
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        // Define the registration URL
        const registerUrl = "http://localhost:8080/auth/register";

        try {
            // Send the registration request to the server
            const response = await fetch(registerUrl, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({name, email, password})
            });

            // Get the message div for displaying messages
            const messageDiv = document.getElementById('message');

            // Handle the server response
            if (response.status === 201) {
                alert("Registration successful. Redirecting to login page...");
                window.location.href = 'login.html';
            } else {
                const result = await response.json();
                messageDiv.textContent = `Registration failed: ${result.message}`;
            }
        } catch (error) {
            console.error('Error:', error);
            const messageDiv = document.getElementById('message');
            messageDiv.textContent = "An error occurred while registering. Please try again.";
        }
    });
});
