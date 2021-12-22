//Globals
const genresList = document.getElementById('form__genres');
let genres = [];


//Attach Events
document.addEventListener('DOMContentLoaded', initApp);

// basic logic
function printGenres({genreId, genreName}){
    const div = document.createElement('div');

    const input = document.createElement('input');
    input.dataset.id = genreId;
    input.type = 'checkbox';
    input.id = 'genre';
    input.className = 'input__label';
    div.appendChild(input);
    const label = document.createElement('label');
    label.for = 'genre';
    label.className = 'label__checkbox';
    label.innerText = `${genreName}`;
    div.appendChild(label);

    genresList.appendChild(div);
}

//Event logic
function initApp(){
    getGenres().then(value => {
        console.log(value)
        genres = value;
        genres.forEach(genre => printGenres(genre))
    })
}

//async logic
async function getGenres(){
    const response = await fetch('http://localhost:8081/lib/controller?command=send_genres');
    const data = await response.json();

    return data;
}

async function sendBooks(){
    const response = await fetch('http//');
}