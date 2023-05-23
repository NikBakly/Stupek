let nameHost = "http://localhost:8080/";
let apiPerson = "api/persons"
let apiCourse = "api/courses"

function registration_person() {
    // с помощью jQuery обращаемся к элементам на странице по их именам
    let login = document.querySelector('#login');
    let password = document.querySelector('#password');
    let email = document.querySelector('#email');
    let role;
    if (document.getElementById("role_developer").checked) {
        role = document.querySelector('#role_developer')
    } else {
        role = document.querySelector('#role_user')
    }
    // а вот сюда мы поместим ответ от сервера
    let result = document.querySelector('.result');
    // создаём новый экземпляр запроса XHR
    let xhr = new XMLHttpRequest();
    // адрес, куда мы отправим нашу JSON-строку
    let url = nameHost + apiPerson
    // преобразуем наши данные JSON в строку
    const data = JSON.stringify({
        "login": login.value,
        "password": password.value,
        "email": email.value,
        "personRole": role.value
    });
    send_json(xhr, url, result, data)
}

function create_course() {
    let courseName = document.querySelector('#course_name');
    let courseDescription = document.querySelector('#course_description');
    let courseMaterial = document.querySelector('#course_material');
    let courseStatus = !!document.getElementById("open_course").checked
    let result = document.querySelector('.result');
    let xhr = new XMLHttpRequest();
    let url = nameHost + apiCourse;
    const data = JSON.stringify({
        "name": courseName.value,
        "description": courseDescription.value,
        "material": courseMaterial.value,
        "isOpen": courseStatus
    });
    send_json(xhr, url, result, data)
}

function send_json(xhr, url, result, data) {
    // открываем соединение
    xhr.open("POST", url, true);
    // устанавливаем заголовок — выбираем тип контента, который отправится на сервер, в нашем случае мы явно пишем, что это JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
    xhr.onreadystatechange = function () {
        // если запрос принят и сервер ответил, что всё в порядке
        if (xhr.readyState === 4 && xhr.status === 200) {
            // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
            result.innerHTML = this.responseText;
        }
    };
    // когда всё готово, отправляем JSON на сервер
    xhr.send(data);
}
