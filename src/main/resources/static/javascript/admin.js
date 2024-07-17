document.addEventListener('DOMContentLoaded', () => {
    // Event listener for the animal form submission
    document.getElementById('add-animal-form').addEventListener('submit', async (event) => {
        event.preventDefault();

        // Collect form data
        const name = document.getElementById('name').value;
        const species = document.getElementById('species').value;
        const age = document.getElementById('age').value;
        const gender = document.getElementById('gender').value;
        const description = document.getElementById('description').value;

        // Prepare the animal data object
        const animalData = {
            name,
            species,
            age,
            gender,
            description,
            status: 'available' // default status
        };

        // Send the data to the server
        const response = await fetch('http://localhost:8080/animal/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + sessionStorage.getItem('token')
            },
            body: JSON.stringify(animalData)
        });

        const messageDiv = document.getElementById('message');

        // Display a message based on the server response
        if (response.ok) {
            messageDiv.textContent = "Animal added successfully!";
        } else {
            messageDiv.textContent = "Failed to add animal. Please try again.";
        }
    });
});


