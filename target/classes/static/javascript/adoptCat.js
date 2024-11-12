document.addEventListener('DOMContentLoaded', () => {
    // Retrieve the animal ID from the URL parameters
    const urlParams = new URLSearchParams(window.location.search);
    const animalId = urlParams.get('animalId');

    // Retrieve the token from sessionStorage
    const token = sessionStorage.getItem('token');

    let userID;
    let userEmail;

    if (token) {
        try {
            // Decode the JWT token payload
            const payload = JSON.parse(atob(token.split('.')[1]));
            userEmail = payload.email;
            userID = payload.idUser;

            console.log('JWT Payload:', payload);

            // Set the user email and animal ID in the form
            document.getElementById('user').value = userEmail;
            document.getElementById('animal').value = animalId;
        } catch (error) {
            console.error('Failed to decode token:', error);
        }
    } else {
        console.error('Token not found in sessionStorage');
    }

    document.getElementById('adoption-form').addEventListener('submit', async (e) => {
        e.preventDefault();

        // Collect form data
        const requestDate = document.getElementById('requestDate').value;
        const status = document.getElementById('status').value;

        // Prepare the adoption request object
        const adoptionRequest = {
            user: {idUser: userID, email: userEmail},
            animal: {idAnimal: animalId},
            requestDate: requestDate,
            status: status
        };

        try {
            // Send the adoption request to the server
            const response = await fetch('http://localhost:8080/adoption_request/adopt', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(adoptionRequest)
            });

            // Handle the server response
            if (response.ok) {
                alert('Adoption request submitted successfully!');
                window.location.href = 'katten.html';
            } else {
                alert('Failed to submit adoption request.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred while submitting the adoption request.');
        }
    });
});
