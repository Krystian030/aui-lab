import {getBackendUrl} from '../js/configuration.js';
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const studentForm = document.getElementById('studentEditForm');

    studentForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayCourse();
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplay() {
    console.log("fetch and display");
}
function fetchAndDisplayCourse() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    console.log(getParameterByName('student'));
    console.log(getBackendUrl() + '/api/students/' + getParameterByName('student'));
    xhttp.open("GET", getBackendUrl() + '/api/students/' + getParameterByName('student'), true);
    xhttp.send();
}


/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplay();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/students/' +  getParameterByName('student'), true);

    const name = document.getElementById('name').value;
    const surname = document.getElementById('surname').value;
    console.log(name == null || surname == null)
    if (surname == null || surname.length === 0 || name == null || name.length === 0) {
        console.error("Unexpected error occurs");
        return;
    }

    const request = {
        'name': name,
        'surname': surname
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    console.log("send request " + JSON.stringify(request))
    xhttp.send(JSON.stringify(request));
    fetchAndDisplay();
}

