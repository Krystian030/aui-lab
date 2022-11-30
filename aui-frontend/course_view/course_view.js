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
    fetchAndDisplayStudents();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayStudents() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayStudents(JSON.parse(this.responseText))
        }
    };
    console.log(getBackendUrl() + '/api/courses/' + getParameterByName('course') + '/students/')
    xhttp.open("GET", getBackendUrl() + '/api/courses/' + getParameterByName('course') + '/students/', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display students.
 *
 * @param {{students: {id: number, name:string}[]}} students
 */
function displayStudents(students) {
    console.log(students)
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    students.students.forEach(student => {
        tableBody.appendChild(createTableRow(student));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} student
 * @returns {HTMLTableRowElement}
 */
function createTableRow(student) {
    let tr = document.createElement('tr');
    // tr.appendChild(createImageCell(getBackendUrl() + '/api/users/' + getParameterByName('course') + '/students/'
    //     + student.id + '/portrait'));
    tr.appendChild(createTextCell(student.name));
    tr.appendChild(createTextCell(student.id));
    tr.appendChild(createTextCell(student.surname));
    tr.appendChild(createLinkCell('edit', '../student_edit/student_edit.html?user='
        + getParameterByName('course') + '&student=' + student.id));
    tr.appendChild(createButtonCell('delete', () => deleteStudent(student.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} student to be deleted
 */
function deleteStudent(student) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayStudents();
        }
    };
    console.log(getBackendUrl() + '/api/courses/' + getParameterByName('course')
        + '/students/' + student)
    xhttp.open("DELETE", getBackendUrl() + '/api/courses/' + getParameterByName('course')
        + '/students/' + student, true);
    xhttp.send();
}


/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayCourse() {
    console.log("start")
    const xhttp = new XMLHttpRequest();
    let course = null;
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            course = JSON.parse(this.responseText);
            console.log(this.responseText)
            displayUser(JSON.parse(this.responseText))
        }
    };
    console.log(getBackendUrl() + '/api/courses/' + getParameterByName('course'));
    xhttp.open("GET", getBackendUrl() + '/api/courses/' + getParameterByName('course'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display course.
 *
 * @param {{login: string, name: string, surname:string}} course
 */
function displayUser(course) {
    setTextNode('title', course.title);
    setTextNode('language', course.language);
    setTextNode('rating', course.rating);
}
