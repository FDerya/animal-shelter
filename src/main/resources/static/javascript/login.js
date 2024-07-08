document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('login-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        const loginUrl = "http://localhost:8080/auth/login";

        const response = await fetch(loginUrl, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        const messageDiv = document.getElementById('message');

        if (response.ok) {
            // Mock token storage as the API does not return a token
            const mockToken = "mockToken123";
            sessionStorage.setItem('token', mockToken);
            alert("Login successful. Redirecting to homepage...");
            window.location.href = 'homePage.html';
        } else if (response.status === 401) {
            messageDiv.textContent = "Login failed. If you do not have an account, please register.";
        } else {
            messageDiv.textContent = "Login failed. Please try again.";
        }
    });
});
