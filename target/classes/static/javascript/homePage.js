document.addEventListener('DOMContentLoaded', () => {
    // Retrieve the login and logout buttons
    const loginButton = document.getElementById('login-button');
    const logoutButton = document.getElementById('logout-button');

    // Check if a token is stored in sessionStorage
    const token = sessionStorage.getItem('token');

    // Toggle the display of login and logout buttons based on token presence
    if (token) {
        loginButton.style.display = 'none';
        logoutButton.style.display = 'inline-block';
    } else {
        loginButton.style.display = 'inline-block';
        logoutButton.style.display = 'none';
    }

    // Event listener for login button click
    loginButton.addEventListener('click', () => {
        window.location.href = 'login.html'; // Redirect to the login page
    });

    // Event listener for logout button click
    logoutButton.addEventListener('click', () => {
        sessionStorage.removeItem('token'); // Remove the token from sessionStorage
        alert('You have been logged out.'); // Show logout alert
        window.location.reload(); // Reload the page to reflect the changes
    });
});
