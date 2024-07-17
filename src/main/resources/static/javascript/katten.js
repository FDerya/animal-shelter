document.addEventListener('DOMContentLoaded', async () => {
    // The div where the list of cats will be displayed
    const catsListDiv = document.getElementById('cats-list');

    // Array of cat images
    const images = [
        '/static/png/cats/cat1.png',
        '/static/png/cats/cat2.png',
        '/static/png/cats/cat3.png',
        '/static/png/cats/cat4.png',
        '/static/png/cats/cat5.png',
        '/static/png/cats/cat6.png',
        '/static/png/cats/cat7.png',
        '/static/png/cats/cat8.png',
        '/static/png/cats/cat9.png'
    ];

    // Function to get a random image from the array
    function getRandomImage() {
        const index = Math.floor(Math.random() * images.length);
        return images[index];
    }

    try {
        // Fetch cats data from the server
        const response = await fetch('http://localhost:8080/animal/getAllCats');
        if (!response.ok) {
            throw new Error('Failed to fetch cats data');
        }

        const cats = await response.json();

        // Iterate over the cats data and create HTML elements for each cat
        cats.forEach(cat => {
            const catDiv = document.createElement('div');
            catDiv.classList.add('cat');

            const catImage = document.createElement('img');
            catImage.src = getRandomImage();
            catImage.alt = `Image of ${cat.name}`;
            catImage.classList.add('cat-image');

            const catName = document.createElement('h2');
            catName.textContent = cat.name;

            const catDescription = document.createElement('p');
            catDescription.textContent = `Personality: ${cat.description}`;

            const catAge = document.createElement('p');
            catAge.textContent = `Age: ${cat.age}`;

            const adoptButton = document.createElement('button');
            adoptButton.textContent = 'Adopt';
            adoptButton.classList.add('adopt-button');
            adoptButton.onclick = () => {
                window.location.href = `adoptCat.html?animalId=${cat.idAnimal}`;
            };


            // Append the created elements to the catDiv
            catDiv.appendChild(catImage);
            catDiv.appendChild(catName);
            catDiv.appendChild(catAge);
            catDiv.appendChild(catDescription);
            catDiv.appendChild(adoptButton);

            // Append the catDiv to the catsListDiv
            catsListDiv.appendChild(catDiv);
        });
    } catch (error) {
        catsListDiv.innerHTML = `<p>Error loading cats: ${error.message}</p>`;
    }
});
