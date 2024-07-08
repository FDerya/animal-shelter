document.addEventListener('DOMContentLoaded', () => {
    const loginButton = document.getElementById('login-button');
    const logoutButton = document.getElementById('logout-button');

    const token = sessionStorage.getItem('token');
    if (token) {
        loginButton.style.display = 'none';
        logoutButton.style.display = 'inline-block';
    } else {
        loginButton.style.display = 'inline-block';
        logoutButton.style.display = 'none';
    }

    loginButton.addEventListener('click', () => {
        window.location.href = 'login.html';
    });

    logoutButton.addEventListener('click', () => {
        sessionStorage.removeItem('token');
        alert('You have been logged out.');
        window.location.reload();
    });
});
