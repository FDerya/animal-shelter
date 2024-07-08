document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('register-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        const registerUrl = "http://localhost:8080/auth/register";

        const response = await fetch(registerUrl, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, email, password })
        });

        const messageDiv = document.getElementById('message');

        if (response.status === 201) {
            alert("Registration successful. Redirecting to login page...");
            window.location.href = 'login.html';
        } else {
            const result = await response.json();
            alert(`Registration failed: ${result}`);
        }
    });
});
