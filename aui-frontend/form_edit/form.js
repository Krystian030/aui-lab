import {getBackendUrl} from '../js/configuration.js';
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const coursesForm = document.getElementById('coursesForm');

    coursesForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayCourse();
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplay() {
    window.location.href = '../courses_list/courses_list.html';
}
function fetchAndDisplayCourse() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log("test")
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
    xhttp.open("GET", getBackendUrl() + '/api/courses/' + getParameterByName('course'), true);
    xhttp.send();
}


/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();
    console.log("start update")
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplay();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/courses/' +  getParameterByName('course'), true);

    const rating = parseFloat(document.getElementById('rating').value);
    const language = document.getElementById('language').value;
    console.log(rating == null || language == null || isNaN(rating))
    if (rating == null || language.length === 0 || isNaN(rating)) {
        console.error("Unexpected error occurs");
        return;
    }

    const request = {
        'rating': rating,
        'language': language
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    console.log("send request " + JSON.stringify(request))
    xhttp.send(JSON.stringify(request));
    fetchAndDisplay();
}

