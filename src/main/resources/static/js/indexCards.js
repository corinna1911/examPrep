let task_id;
let category_id;
let tasks;
let current_task = 0;
let load_dynamically = true;

window.addEventListener("click", function (e) {
    if (!e.target.matches('#dropdown-filter')) {
        document.getElementById("dropdown-list").style.display = "none";
    }
});

window.onload = function () {

    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/categories/all",
            dataType: 'json',
            success: function (data) {
                for (let i in data) {
                    let list = document.getElementById("category-list");
                    let element = document.createElement('li');
                    let button = document.createElement('button');
                    button.setAttribute("class", "list-button");

                    button.setAttribute("onclick", "show_tasks_by_category(" + data[i].id + ");");
                    button.appendChild(document.createTextNode(data[i].category));
                    element.appendChild(button);
                    list.appendChild(element);
                }
            }
        })

    get_difficulties();
}

function get_difficulties() {

    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/difficulty/all",
            dataType: 'json',
            success: function (data) {
                let radios = document.createElement('span');

                radios.id = 'difficulty';
                for (let i in data) {
                    appendInput('difficulty', 'radio', data[i].level, 'difficulty', data[i].level);

                    let list = document.getElementById("difficulty-list");
                    let element = document.createElement('li');
                    let button = document.createElement('button');


                    button.setAttribute("onclick", "get_by_difficulty(" + data[i].id + ");");
                    button.setAttribute("class", "list-button");
                    button.appendChild(document.createTextNode(data[i].level));
                    element.setAttribute("class", "list-group-item w-3");
                    element.appendChild(button);
                    list.appendChild(element);

                }
            }
        })
}


function show_tasks_by_category(category) {
    category_id = category;

    next_exercise(category_id);
    load_dynamically = true;
}

function appendInput(container, type, id, name, value) {
    let radiobox = document.createElement('input');
    document.getElementById("difficulty").appendChild(radiobox);
    radiobox.type = type;
    radiobox.id = id;
    radiobox.name = name;

    let label = document.createElement('label')
    label.htmlFor = id;

    let description = document.createTextNode(value);
    label.appendChild(description);

    let newline = document.createElement('br');

    let parent = document.getElementById(container);
    parent.appendChild(radiobox);
    parent.appendChild(label);
    parent.appendChild(newline);
}

function setInvisible(element) {
    element.style.display = "none";
}

function setVisible(element) {
    element.style.display = "block";
}

function next_exercise() {
    if (load_dynamically) {

        $.ajax(
            {
                type: "GET",
                url: "http://localhost:8080/tasks/next",
                dataType: 'json',
                data: {category: category_id},
                success: function (data) {
                    set_task(data);
                    show_task();

                }
            }
        )
    } else {
        if (current_task < tasks.length - 1) {
            ++current_task;
            set_task(tasks[current_task].task);
            show_task();
        }
    }
}

function createAbsolvedQuestion(difficulty) {

    $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/solved/add",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "task": task_id.toString(),
                "difficulty": difficulty.toString(),
            }),
        })
}

function setDifficulty() {
    let radios = document.querySelectorAll('#difficulty input');
    for (let key in radios) {
        if (radios[key].checked) {
            createAbsolvedQuestion(radios[key].id);
            radios[key].checked = false;
        }
    }
}


function show_solution() {
    setDifficulty();
    document.getElementById("heading").innerText = "Lösung";
    setVisible(document.getElementById("next-exercise"));
    setInvisible(document.getElementById("show-solution"));
    setInvisible(document.getElementById("task"));
    setInvisible(document.getElementById("exercise-graphic"));
    setVisible(document.getElementById("solution"));
    setVisible(document.getElementById("solution-graphic"));
}

function show_task() {
    document.getElementById("index-card").style.display = "block";
    document.getElementById("heading").innerText = "Aufgabe";
    setInvisible(document.getElementById("next-exercise"));
    setVisible(document.getElementById("show-solution"));
    setInvisible(document.getElementById("solution"));
    setInvisible(document.getElementById("solution-graphic"));
    setVisible(document.getElementById("task"));
    setVisible(document.getElementById("exercise-graphic"));
}

function set_task(data) {
    let task = document.getElementById("task");
    let exerciseGraphic = document.getElementById("exercise-graphic");
    let solutionGraphic = document.getElementById("solution-graphic");
    let solution = document.getElementById("solution");

    task_id = data.id;
    task.innerText = data.task;
    solution.innerText = data.solution.solution;
    exerciseGraphic.setAttribute("src", "data:image/png;base64," + data.file.data);
    solutionGraphic.setAttribute("src", "data:image/png;base64," + data.solution.graphic.data);
}

function set_task_by_difficulty() {

    if (tasks.length > 0) {
        set_task(tasks[current_task].task);
        show_task();
        load_dynamically = false;
    } else {

    }

}

function get_by_difficulty(difficulty) {

    $.ajax(
        {
            type: "get",
            url: "/solved/difficulty",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: {
                difficulty: difficulty,
            },
            success: function (data) {
                tasks = data;
                current_task = 0;
                set_task_by_difficulty();
                load_dynamically = false;
            }
        })
}

function toggle_dropdown(dropdown) {
    let list = document.getElementById(dropdown);

    if (list.style.display === "none") {
        list.style.display = "block";
    } else {
        list.style.display = "none";
    }
}




