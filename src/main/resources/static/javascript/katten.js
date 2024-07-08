document.addEventListener('DOMContentLoaded', async () => {
    const catsListDiv = document.getElementById('cats-list');

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

    function getRandomImage() {
        const index = Math.floor(Math.random() * images.length);
        return images[index];
    }

    try {
        const response = await fetch('http://localhost:8080/animal/getAllCats');
        if (!response.ok) {
            throw new Error('Failed to fetch cats data');
        }

        const cats = await response.json();
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

            catDiv.appendChild(catImage);
            catDiv.appendChild(catName);
            catDiv.appendChild(catAge);
            catDiv.appendChild(catDescription);

            catsListDiv.appendChild(catDiv);
        });
    } catch (error) {
        catsListDiv.innerHTML = `<p>Error loading cats: ${error.message}</p>`;
    }
});
