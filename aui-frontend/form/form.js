import {getBackendUrl} from '../js/configuration.js';
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const coursesForm = document.getElementById('coursesForm');

    coursesForm.addEventListener('submit', event => updateInfoAction(event));
});


/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplay() {
    window.location.href = '../courses_list/courses_list.html';
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
    xhttp.open("POST", getBackendUrl() + '/api/courses/', true);

    const rating = parseFloat(document.getElementById('rating').value);
    const title = document.getElementById('title').value;
    const language = document.getElementById('language').value;
    console.log(rating + ' ' + title + ' ' + language)
    console.log(title == null || rating == null || language == null || isNaN(rating))
    if (title.length === 0 || rating == null || language.length === 0 || rating instanceof Float64Array) {
        console.error("Unexpected error occurs");
        return;
    }

    const request = {
        'title': title,
        'rating': rating,
        'language': language
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    console.log("send request " + JSON.stringify(request))
    xhttp.send(JSON.stringify(request));
    fetchAndDisplay();
}

