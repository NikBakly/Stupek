window.addEventListener('DOMContentLoaded', getPersonByLogin);
document.getElementById('updatePerson').addEventListener('click', updatePersonById);
document.getElementById('updatePerson').addEventListener('click', updatePersonById);

// document.getElementById('banned_button').addEventListener('click', bannedPersonById);
// document.getElementById('unbanned_button').addEventListener('click', updatePersonById);
// document.getElementById('remove_button').addEventListener('click', updatePersonById);


let personApi = 'http://localhost:8080/api/persons';
let courseApi = 'http://localhost:8080/api/courses';
let personId;


async function getPersonByLogin() {
    const res = await fetch(personApi);
    const person = await res.json();
    personInfoToHTML(person)
    if (document.getElementById('edit_course_and_persons') !== null) {
        await getPersons();
        await getCoursesForAdmin()
    }
    if (document.getElementById('edit_courses') !== null) {
        await getDeveloperCourses();
    }
}

async function getDeveloperCourses() {
    const res = await fetch(courseApi);
    const developerCourses = await res.json();
    developerCourses.forEach(course => coursesInfoToHTMLForDev(course));
}

async function getCoursesForAdmin() {
    const res = await fetch(courseApi + "/admin");
    const courses = await res.json();
    courses.forEach(course => coursesInfoToHTMLForAdmin(course));
}

async function updatePersonById() {
    const email = document.getElementById('email').value;
    let role;
    if (document.getElementById('role_developer').checked) {
        role = document.querySelector('#role_developer').value
    }
    console.log(role)
    const res = await fetch(personApi + `/${personId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email, personRole: role})
    });

    const updatedPerson = await res.json();
    window.location.reload();
}

async function getPersons() {

    const res = await fetch(personApi + '/all');
    const persons = await res.json();
    persons.forEach(person => personInfoToHTMLForAdmin(person))
}

async function bannedPersonById() {
    const personId = document.getElementById('banned_button').parentElement.id

    console.log(personId)
    // const res = await fetch(personApi + `/${personId}`, {
    //     method: 'PATCH',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({email, personRole: role})
    // });
    //
    // const updatedPerson = await res.json();
    // window.location.reload();
}

async function unbannedPersonById() {
    const personId = document.getElementById('unbanned_button').parentElement.id

    console.log(personId)
    // const res = await fetch(personApi + `/${personId}`, {
    //     method: 'PATCH',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({email, personRole: role})
    // });
    //
    // const updatedPerson = await res.json();
    // window.location.reload();
}

async function removePersonById() {
    const personId = document.getElementById('remove_button').parentElement.id

    console.log(personId)
    // const res = await fetch(personApi + `/${personId}`, {
    //     method: 'PATCH',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify({email, personRole: role})
    // });
    //
    // const updatedPerson = await res.json();
    // window.location.reload();
}

function personInfoToHTML({id, login, email, balance, personRole}) {
    personId = id
    if (document.getElementById(`person${personId}`)) {
        document.getElementById(`person${personId}`).remove();
    }
    const personInfo = document.getElementById('person_info')
    personInfo.insertAdjacentHTML('afterbegin', `
        <div id="person${personId}">
            <label>Login: ${login} </label> <br>
            <label>Email: ${email} </label> <br>
            <label>Balance: ${balance} </label> <br>
            <label>Your role: ${personRole} </label>
        </div>
    `);
}

function personInfoToHTMLForAdmin({id, login, email, personRole, personStatus, lastUpdate}) {
    const personInfo = document.getElementById('persons_for_admin');
    let myButton;
    if (personStatus === 'ACTIVE') {
        myButton = `<td><button type="button" id="banned_button" class="btn btn-warning">Забанить</button> <button type="button" class="btn btn-danger" id="remove_button">Удалить</button></a></td>`
    } else {
        myButton = `<td><button type="button" id="unbanned_button" class="btn btn-success">Разбанить</button> <button type="button" class="btn btn-danger" id="remove_button">Удалить</button></a></td>`
    }
    personInfo.insertAdjacentHTML('beforeend', `
        <tr id="${id}">
            <th scope="row">${login}</th>
            <td>${email}</td>
            <td>${personRole}</td>
            <td>${personStatus}</td>
            <td>${lastUpdate}</td>
            ${myButton}
        </tr>
    `);
}

function coursesInfoToHTMLForAdmin({id, name, description, isOpen}) {
    const personInfo = document.getElementById('courses_for_admin');
    let endValue;
    if (isOpen) {
        endValue = `<td>Открытый</td>
                    <td><button type="button" class="btn btn-secondary">Закрыть доступ</button> <button type="button" class="btn btn-danger" id="remove_button">Удалить</button></a></td>`
    } else {
        endValue = `<td>Закрытый</td>
                    <td><button type="button" class="btn btn-success">Открыть доступ</button> <button type="button" class="btn btn-danger" id="remove_button">Удалить</button></a></td>`
    }
    personInfo.insertAdjacentHTML('beforeend', `
        <tr id="${id}">
            <th scope="row">${name}</th>
            <td>${description}</td>
            ${endValue}
        </tr>
    `);
}

function coursesInfoToHTMLForDev({id, name, description, isOpen}) {
    const personInfo = document.getElementById('developer_courses');
    let endValue;
    if (isOpen) {
        endValue = `<td>Открытый</td>
                    <td><button type="button" class="btn btn-secondary">Закрыть доступ</button> <button type="button" class="btn btn-danger" id="remove_button">Удалить</button></a></td>`
    } else {
        endValue = `<td>Закрытый</td>
                    <td><button type="button" class="btn btn-success">Открыть доступ</button> <button type="button" class="btn btn-danger" id="remove_button">Удалить</button></a></td>`
    }
    personInfo.insertAdjacentHTML('beforeend', `
        <tr id="${id}">
            <th scope="row">${name}</th>
            <td>${description}</td>
            ${endValue}
        </tr>
    `);
}
