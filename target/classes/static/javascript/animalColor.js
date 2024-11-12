document.addEventListener('DOMContentLoaded', () => {
    const searchButton = document.getElementById('searchButton');

    // Butona tıklanınca çalışan fonksiyon
    searchButton.addEventListener('click', () => {
        const color = document.getElementById('speciesInput').value;

        // Fetch ile backend'e GET isteği gönderiyoruz
        fetch(`http://localhost:8080/animal/color/${color}`)
            .then(response => response.json())
            .then(data => {
                const resultContainer = document.getElementById('result-container');
                resultContainer.innerHTML = '';  // Önceki sonuçları temizliyoruz

                if (data.length === 0) {
                    resultContainer.innerHTML = '<p>No animals found.</p>';
                } else {

                    // Eğer veri varsa tabloya dönüştürüp göster
                    const table = document.createElement('table');
                    const header = document.createElement('tr');
                    header.innerHTML = `
                        <th>Name</th>
                        <th>Species</th>
                        <th>Age</th>
                        <th>Color</th>
                    `;
                    table.appendChild(header);

                    data.forEach(animal => {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td>${animal.name}</td>
                            <td>${animal.species}</td>
                            <td>${animal.age}</td>
                            <td>${animal.color}</td>
                        `;
                        table.appendChild(row);
                    });

                    resultContainer.appendChild(table);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('resultContainer').innerHTML = '<p>An error occurred. Please try again.</p>';
            });
    });
});
