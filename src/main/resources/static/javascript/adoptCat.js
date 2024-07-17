document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const animalId = urlParams.get('animalId');

    const token = sessionStorage.getItem('token');

    let userID;
    let userEmail;

    if (token) {
        // Decode the JWT token payload
        const payload = JSON.parse(atob(token.split('.')[1]));
        userEmail = payload.email;
        userID = payload.idUser;


        console.log('JWT Payload:', payload);

        document.getElementById('user').value = userEmail;
        document.getElementById('animal').value = animalId;

    } else {
        console.error('Token not found in sessionStorage');
    }

    document.getElementById('adoption-form').addEventListener('submit', async (e) => {
        e.preventDefault();

        const requestDate = document.getElementById('requestDate').value;
        const status = document.getElementById('status').value;

        const adoptionRequest = {
            user: { idUser: userID, email: userEmail },
            animal: { idAnimal: animalId },
            requestDate: requestDate,
            status: status
        };
        // mysql
        try {
            const response = await fetch('http://localhost:8080/adoption_request/adopt', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(adoptionRequest)
            });

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
