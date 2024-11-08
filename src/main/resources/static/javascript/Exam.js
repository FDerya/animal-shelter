document.getElementById('searchButton').addEventListener('click', function () {
    const count = document.getElementById('speciesInput').value;

        fetch(`http://meowfacts.herokuapp.com/?count=${count}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Animal not found');
                }
                return response.json();  // JSON format
            })
            .then(data => {
                // JSON'dan yazdir
                document.getElementById('result-container').innerHTML = `
                <h3>Animal Information</h3>
                <p><strong>ID:</strong> ${data.id}</p>
                <p><strong>Name:</strong> ${data.name}</p>
                <p><strong>Species:</strong> ${data.species}</p>
                <p><strong>Age:</strong> ${data.age}</p>
                <p><strong>Health Status:</strong> ${data.healthStatus}</p>
            `;
            })
            .catch(error => {
                document.getElementById('result-container').textContent = 'Error: ' + error.message;
            });
    });
