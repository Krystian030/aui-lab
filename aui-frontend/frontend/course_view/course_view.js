import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayCourse();
    // fetchAndDisplayCharacters();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayCharacters() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCharacters(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/users/' + getParameterByName('course') + '/characters', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display characters.
 *
 * @param {{characters: {id: number, name:string}[]}} characters
 */
function displayCharacters(characters) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    characters.characters.forEach(character => {
        tableBody.appendChild(createTableRow(character));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} character
 * @returns {HTMLTableRowElement}
 */
function createTableRow(character) {
    let tr = document.createElement('tr');
    tr.appendChild(createImageCell(getBackendUrl() + '/api/users/' + getParameterByName('user') + '/characters/'
        + character.id + '/portrait'));
    tr.appendChild(createTextCell(character.name));
    tr.appendChild(createLinkCell('edit', '../character_edit/character_edit.html?user='
        + getParameterByName('user') + '&character=' + character.id));
    tr.appendChild(createButtonCell('delete', () => deleteCharacter(character.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} character to be deleted
 */
function deleteCharacter(character) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCharacters();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/users/' + getParameterByName('user')
        + '/characters/' + character, true);
    xhttp.send();
}


/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayCourse() {
    const xhttp = new XMLHttpRequest();
    let course = null;
    console.log("start")
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            course = JSON.parse(this.responseText);
            displayUser(JSON.parse(this.responseText))
        }
    };
    console.log(getBackendUrl() + '/api/courses/' + getParameterByName('course'));
    xhttp.open("GET", getBackendUrl() + '/api/courses/' + getParameterByName('course'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display user.
 *
 * @param {{login: string, name: string, surname:string}} user
 */
function displayUser(user) {
    setTextNode('username', user.login);
    setTextNode('name', user.name);
    setTextNode('surname', user.surname);
}
