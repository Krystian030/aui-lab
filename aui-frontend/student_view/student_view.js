import {
    getParameterByName,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayStudent();
});

/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayStudent() {
    console.log("start")
    const xhttp = new XMLHttpRequest();
    let course = null;
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            course = JSON.parse(this.responseText);
            console.log(this.responseText)
            displayStudent(JSON.parse(this.responseText))
        }
    };
    console.log(getBackendUrl() + '/api/students/' + getParameterByName('student'));
    xhttp.open("GET", getBackendUrl() + '/api/students/' + getParameterByName('student'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display student.
 *
 * @param {{login: string, name: string, surname:string}} student
 */
function displayStudent(student) {
    console.log(student)
    setTextNode('id', student.id);
    setTextNode('name', student.name);
    setTextNode('surname', student.surname);
}
