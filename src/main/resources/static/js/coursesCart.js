window.addEventListener('DOMContentLoaded', getCourses);


let courseApi = 'http://localhost:8080/api/courses';


async function getCourses() {
    const res = await fetch(courseApi + "/all");
    const courses = await res.json();
    courses.forEach(course => coursesToHTML(course));
}

function coursesToHTML({id, name, description}) {
    const personInfo = document.getElementById('courses');
    personInfo.insertAdjacentHTML('beforeend', `
        <div class="card" id="id" style="width: 18rem;">
          <div class="card-body">
            <h5 class="card-title">${name}</h5>
            <p class="card-text">${description}</p>
            <a href="#" class="btn btn-primary">Открыть</a>
          </div>
        </div>
    `)
}