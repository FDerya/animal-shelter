document.addEventListener('DOMContentLoaded', async () => {
    const dogsListDiv = document.getElementById('dogs-list');

    // Array of dog images
    const images = [
        '/static/png/dogs/dog1.png',
        '/static/png/dogs/dog2.png',
        '/static/png/dogs/dog3.png',
        '/static/png/dogs/dog4.png',
        '/static/png/dogs/dog5.png'

    ];

    // Function to get a random image from the array
    function getRandomImage() {
        const index = Math.floor(Math.random() * images.length);
        return images[index];
    }

    try {
        console.log('Fetching dogs data...');
        // Fetch dogs data from the server
        const response = await fetch('http://localhost:8080/animal/getAllDogs');

        if (!response.ok) {
            throw new Error('Failed to fetch dogs data');
        }

        const dogs = await response.json();
        console.log('Dogs data:', dogs);

        // Iterate over the dogs data and create HTML elements for each dog
        dogs.forEach(dog => {
            const dogDiv = document.createElement('div');
            dogDiv.classList.add('dog');

            const dogImage = document.createElement('img');
            dogImage.src = getRandomImage();
            dogImage.alt = `Image of ${dog.name}`;
            dogImage.classList.add('dog-image');

            const dogName = document.createElement('h2');
            dogName.textContent = dog.name;

            const dogDescription = document.createElement('p');
            dogDescription.textContent = `Personality: ${dog.description}`;

            const dogAge = document.createElement('p');
            dogAge.textContent = `Age: ${dog.age}`;

            const adoptButton = document.createElement('button');
            adoptButton.textContent = 'Adopt';
            adoptButton.classList.add('adopt-button');
            adoptButton.onclick = () => {
                window.location.href = `adoptDog.html?animalId=${dog.idAnimal}`;
            };

            // Append the created elements to the dogDiv
            dogDiv.appendChild(dogImage);
            dogDiv.appendChild(dogName);
            dogDiv.appendChild(dogAge);
            dogDiv.appendChild(dogDescription);
            dogDiv.appendChild(adoptButton);

            // Append the dogDiv to the dogsListDiv
            dogsListDiv.appendChild(dogDiv);
        });
    } catch (error) {
        console.error('Error loading dogs:', error);
        dogsListDiv.innerHTML = `<p>Error loading dogs: ${error.message}</p>`;
    }
});
