
// deneme 1 animal id ye gore information
// id ye dikkat et idUser gibi
document.getElementById('searchButton').addEventListener('click', function () {
    const id = document.getElementById('speciesInput').value;

    // Eğer idAnimal boşsa, hata mesajı göster
    if (!id) {
        document.getElementById('result-container').textContent = 'Please enter a valid animal ID.';
        return;
    }

    // URL'de idAnimal'ı doğru şekilde kullanıyoruz
    fetch(`http://localhost:8080/animal/info/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Animal not found');
            }
            return response.json();  // JSON format
        })
        .then(data => {

        })
        .catch(error => {
            document.getElementById('result-container').textContent = 'Error: ' + error.message;
        });
});

