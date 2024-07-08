document.addEventListener('DOMContentLoaded', async () => {
    const dogsListDiv = document.getElementById('dogs-list');

    const images = [
        '/static/png/dogs/dog1.png',
        '/static/png/dogs/dog2.png',
        '/static/png/dogs/dog3.png',
        '/static/png/dogs/dog4.png',
        '/static/png/dogs/dog5.png'

    ];

    function getRandomImage() {
        const index = Math.floor(Math.random() * images.length);
        return images[index];
    }

    try {const response = await fetch('http://localhost:8080/animal/getAllDogs');
        if (!response.ok) {
            throw new Error('Failed to fetch dogs data');
        }
        const dogs = await response.json();
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

            dogDiv.appendChild(dogImage);
            dogDiv.appendChild(dogName);
            dogDiv.appendChild(dogAge);
            dogDiv.appendChild(dogDescription);

            dogsListDiv.appendChild(dogDiv);
        });
    } catch (error) {
        dogsListDiv.innerHTML = `<p>Error loading dogs: ${error.message}</p>`;
    }
});
