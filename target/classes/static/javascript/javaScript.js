// DOMContentLoaded ile sayfa tamamen yüklendikten sonra çalışacak olan kodlar
document.addEventListener('DOMContentLoaded', () => {

    // Bir GET isteği yapıp sonuçları tabloya yazdıran fonksiyon
    function getDataAndDisplay(url, containerId) {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                const container = document.getElementById(containerId);
                container.innerHTML = '';  // Önceki sonuçları temizle

                if (data.length === 0) {
                    container.innerHTML = '<p>No data found.</p>';
                } else {
                    // Eğer veri varsa tablo şeklinde göster
                    const table = document.createElement('table');
                    const header = document.createElement('tr');
                    header.innerHTML = `
                        <th>ID</th>
                        <th>Name</th>
                        <th>Species</th>
                        <th>Age</th>
                        <th>Color</th>
                    `;
                    table.appendChild(header);

                    data.forEach(item => {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td>${item.idAnimal}</td>
                            <td>${item.name}</td>
                            <td>${item.species}</td>
                            <td>${item.age}</td>
                            <td>${item.color}</td>
                        `;
                        table.appendChild(row);
                    });

                    container.appendChild(table);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById(containerId).innerHTML = '<p>An error occurred. Please try again.</p>';
            });
    }

    // POST veya PUT isteği yapan genel bir fonksiyon
    function sendData(url, method, data, callback) {
        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(result => {
                console.log('Success:', result);
                if (callback) callback(result);  // Eğer bir callback varsa onu çalıştır
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    // DELETE isteği yapan fonksiyon
    function deleteData(url, id, callback) {
        fetch(`${url}/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                if (callback) callback();  // Eğer bir callback varsa onu çalıştır
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }


    //
    // // Bir animal in rengine gore filtreleme methodu
    //
    // document.getElementById('searchButton').addEventListener('click', function() {
    //     const color = document.getElementById('colorInput').value;  // colorInput'u kullanıyoruz
    //     getDataAndDisplay(`http://localhost:8080/animal/color/${color}`, 'result-container');
    // });
    //
    //
    // // Örnek Kullanım (Sayfanın sonuna, spesifik kullanımlar için eklenebilir):
    //
    // // 1. Hayvan türüne göre verileri çek ve göster (GET)
    // document.getElementById('searchButton').addEventListener('click', function () {
    //     const species = document.getElementById('speciesInput').value;
    //     getDataAndDisplay(`http://localhost:8080/animal/type?species=${species}`, 'result-container');
    // });




    // deneme 1
    document.getElementById('searchButton').addEventListener('click', function () {
        const userId = document.getElementById('speciesInput').value;

        if (!userId) {
            document.getElementById('result-container').textContent = 'Please enter a valid user ID.';
            return;
        }

        fetch(`http://localhost:8080/user/email/{idUser}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('User not found');
                }
                return response.text();
            })
            .then(data => {
                document.getElementById('result-container').textContent = data;
            })
            .catch(error => {
                document.getElementById('result-container').textContent = 'Error: ' + error.message;
            });
    });

    // deneme 2
    document.getElementById('searchButton').addEventListener('click', function () {
        const userId = document.getElementById('speciesInput').value;

        fetch(`http://localhost:8080/adoption_request/${userId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('User not found');
                }
                return response.text();
            })
            .then(data => {
                document.getElementById('result-container').textContent = data;
            })
            .catch(error => {
                document.getElementById('result-container').textContent = 'Error: ' + error.message;
            });
    });


    // deneme 3
    document.getElementById('searchButton').addEventListener('click', function () {
        const idAnimal = document.getElementById('speciesInput').value;

        fetch(`http://localhost:8080/animal/status/${idAnimal}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Animal not found');
                }
                return response.text();
            })
            .then(data => {
                document.getElementById('result-container').textContent = data;
            })
            .catch(error => {
                document.getElementById('result-container').textContent = 'Error: ' + error.message;
            });
    });
    //
    //
    // // 2. Formu göndererek yeni hayvan ekle (POST)
    //
    // handleFormSubmit('animalForm', 'http://localhost:8080/animal', 'POST');
    //
    // // 3. Hayvanı id ile sil (DELETE)
    // document.getElementById('deleteButton').addEventListener('click', function () {
    //     const id = document.getElementById('idInput').value;
    //     deleteData('http://localhost:8080/animal', id, () => {
    //         alert('Animal deleted successfully!');
    //     });
    // });

});
