import {clearElementChildren, createButtonCell, createLinkCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
  fetchAndDisplayUsers();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayUsers() {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      let courses = JSON.parse(this.responseText)
      if (courses.courses.length === 0) {
        console.log("empty collection")
        return;
      }
      displayCourses(JSON.parse(this.responseText))
    }
  };
  xhttp.open("GET", getBackendUrl() + '/api/courses/', true);
  xhttp.send();
}

/**
 * Updates the DOM tree in order to display users.
 *
 * @param {{users: string[]}} courses
 */
function displayCourses(courses) {
  let tableBody = document.getElementById('tableBody');
  clearElementChildren(tableBody);
  courses.courses.forEach(user => {
    tableBody.appendChild(createTableRow(user.title));
  })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} course
 * @returns {HTMLTableRowElement}
 */
function createTableRow(course) {
  console.log(course)
  let tr = document.createElement('tr');
  tr.appendChild(createTextCell(course));
  tr.appendChild(createLinkCell('view', '../course_view/course_view.html?course=' + course));
  tr.appendChild(createButtonCell('delete', () => deleteCourse(course)));
  return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } course to be deleted
 */
function deleteCourse(course) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 202) {
      fetchAndDisplayUsers();
    }
  };
  xhttp.open("DELETE", getBackendUrl() + '/api/courses/' + course, true);
  xhttp.send();
}
