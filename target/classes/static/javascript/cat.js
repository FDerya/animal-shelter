document.getElementById('button').addEventListener('click', getRandomStudent);


async function fetch() {
    const response = await fetch('http://meowfacts.herokuapp.com/?count=3 ');
    const information = await response.json();
    return students;
}