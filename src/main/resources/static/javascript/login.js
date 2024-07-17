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
            const data = await response.json();
            const token = data.token;
            const role = data.role;

            sessionStorage.setItem('token', token);
            alert("Login successful. Redirecting to appropriate page...");

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
    });
});
