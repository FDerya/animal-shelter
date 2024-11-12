document.addEventListener("DOMContentLoaded", function() {
    fetchAnimals();
});

// Fetch animals from the server
function fetchAnimals() {
    fetch('http://localhost:8080/animal')
        .then(response => response.json())
        .then(data => displayAnimals(data))
        .catch(error => console.error('Error fetching animals:', error));
}

// Display animals on the page
function displayAnimals(animals) {
    const animalList = document.getElementById('animal-list');

    animals.forEach(animal => {
        const animalCard = document.createElement('div');
        animalCard.classList.add('animal-card');

        const animalInfo = `
            <div class="animal-header">
                <h2>${animal.name}</h2>
                <div class="action-buttons">
                    <button class="edit-button" onclick="editAnimal(${animal.idAnimal})">Edit</button>
                    <button class="delete-button" onclick="deleteAnimal(${animal.idAnimal}, '${animal.name}')">Delete</button>
                </div>
            </div>
            <p><strong>Species:</strong> ${animal.species}</p>
            <p><strong>Age:</strong> ${animal.age}</p>
            <p><strong>Gender:</strong> ${animal.gender}</p>
            <p><strong>Description:</strong> ${animal.description}</p>
            <p><strong>Status:</strong> ${animal.status}</p>
        `;

        animalCard.innerHTML = animalInfo;
        animalList.appendChild(animalCard);
    });
}

// Delete an animal
function deleteAnimal(id, name) {
    if (confirm(`Are you sure you want to delete ${name}?`)) {
        fetch(`http://localhost:8080/animal/delete/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert(`${name} has been deleted.`);
                    location.reload(); // Reload the page to reflect the changes
                } else {
                    alert('Failed to delete the animal.');
                }
            })
            .catch(error => console.error('Error deleting animal:', error));
    }
}

// Edit an animal
function editAnimal(id) {
    fetch(`http://localhost:8080/animal/${id}`)
        .then(response => response.json())
        .then(animal => {
            document.getElementById('edit-id').value = animal.idAnimal;
            document.getElementById('edit-name').value = animal.name;
            document.getElementById('edit-species').value = animal.species;
            document.getElementById('edit-age').value = animal.age;
            document.getElementById('edit-gender').value = animal.gender;
            document.getElementById('edit-description').value = animal.description;
            document.getElementById('edit-status').value = animal.status;
            openModal();
        })
        .catch(error => console.error('Error fetching animal details:', error));
}

// Open the modal for editing
function openModal() {
    document.getElementById('edit-modal').style.display = 'block';
}

// Close the modal
function closeModal() {
    document.getElementById('edit-modal').style.display = 'none';
}

// Update an animal
function updateAnimal() {
    const id = document.getElementById('edit-id').value;
    const animal = {
        idAnimal: id,
        name: document.getElementById('edit-name').value,
        species: document.getElementById('edit-species').value,
        age: document.getElementById('edit-age').value,
        gender: document.getElementById('edit-gender').value,
        description: document.getElementById('edit-description').value,
        status: document.getElementById('edit-status').value
    };

    fetch(`http://localhost:8080/animal/edit/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(animal)
    })
        .then(response => {
            if (response.ok) {
                alert(`${animal.name} has been updated.`);
                closeModal();
                location.reload(); // Reload the page to reflect the changes
            } else {
                alert('Failed to update the animal.');
            }
        })
        .catch(error => console.error('Error updating animal:', error));
}
