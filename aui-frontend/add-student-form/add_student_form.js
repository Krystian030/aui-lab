import {getBackendUrl} from '../js/configuration.js';
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const coursesForm = document.getElementById('addStudentForm');

    coursesForm.addEventListener('submit', event => updateInfoAction(event));
});


/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplay() {
    console.log("fetch and display");
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
    const course = getParameterByName("course")
    // http://localhost:8082/api/courses/AAA/students/2/add
    xhttp.open("POST", getBackendUrl() + `/api/courses/${course}/students/`, true);

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

