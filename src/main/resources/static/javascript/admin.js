document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('add-animal-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const species = document.getElementById('species').value;
        const age = document.getElementById('age').value;
        const gender = document.getElementById('gender').value;
        const description = document.getElementById('description').value;

        const animalData = {
            name,
            species,
            age,
            gender,
            description,
            status: 'available' // default status
        };

        const response = await fetch('http://localhost:8080/animal/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + sessionStorage.getItem('token') // Assuming the token is stored in sessionStorage
            },
            body: JSON.stringify(animalData)
        });

        const messageDiv = document.getElementById('message');

        if (response.ok) {
            messageDiv.textContent = "Animal added successfully!";
        } else {
            messageDiv.textContent = "Failed to add animal. Please try again.";
        }
    });
});
