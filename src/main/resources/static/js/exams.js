let next_task;
let tasks;

window.onload = getAllExams();

function getAllExams() {

    $.ajax({
        type: "GET", url: "/exam/all", dataType: 'json', success: function (data) {
            for (let i in data) {
                let list = document.getElementById("exam-list");
                let element = document.createElement('li');
                let button = document.createElement('button');
                let date = new Date(data[i].date);
                let year = date.getFullYear();
                let season = date.getMonth() > 5 ? "Herbst" : "Frühling";

                button.setAttribute("onclick", "show_exam(" + data[i].id + ");");
                button.setAttribute("class", "transparent-button");
                button.appendChild(document.createTextNode(data[i].examType.type + " " + season + " " + year));
                element.setAttribute("class", "list-group-item w-3");
                element.appendChild(button);
                list.appendChild(element);
            }
            add_difficulties();

        }
    })
}

function add_difficulties() {

    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/difficulty/all",
            dataType: 'json',
            success: function (data) {
                let radios = document.getElementById("difficulty");

                for (let i in data) {
                    let list = document.createElement('li');
                    let radiobox = document.createElement('input');
                    radiobox.type = "radio";
                    radiobox.id = data[i].level;
                    radiobox.name = "difficulty";

                    let label = document.createElement('label')
                    label.htmlFor = data[i].id;

                    let description = document.createTextNode(data[i].level);
                    label.appendChild(description);
                    list.appendChild(radiobox);
                    list.appendChild(label);
                    radios.appendChild(list);
                }
            }
        })
}

function show_exam(id) {

    $.ajax({
        type: "GET", url: "/tasks/byExam", dataType: 'json', data: {id: id}, success: function (data) {
            tasks = data;
            next_task = 1;
            document.getElementById("exam-list").style.display = "none";
            show_task();
            add_pagination();
            show_exercise_button();
            document.getElementById("difficulty").style.display = "block";
        }
    })
}

function show_task() {

    document.getElementById("heading").innerText = "Aufgabe" + " " + next_task;
    document.getElementById("task-image").setAttribute("src", "data:image/png;base64," + tasks[next_task - 1].file.data);
    document.getElementById("answer-modal").style.display = "block";
    document.getElementById("task").innerText = tasks[next_task - 1].task;
    document.getElementById("index-card").style.display = "block";
    set_active("nav-task");
    setAnswer();


}

function setAnswer() {

    $.ajax({
        type: "GET",
        url: "/answers/byTaskAndUser",
        dataType: 'json',
        data: {
            task: tasks[next_task - 1].id
        },
        success: function (data) {
            if (data != null) {
                document.getElementById("answer").value = data.answer;

            } else {
                document.getElementById("answer").value = "";
            }
        }
    })

}

function next_exercise() {
    setDifficulty();

    document.getElementById("task-pagination").children[next_task - 1].classList.remove("active");

    if (next_task > tasks.length - 2) {
        document.getElementById("next-exercise").style.display = "none";
        document.getElementById("exit-exam").style.display = "block";
    }


    if (next_task < tasks.length) {
        document.getElementById("task-pagination").children[next_task].classList.add("active");
        next_task++;
        show_task();
    }


}

function add_answer() {
    let answer = document.getElementById("answer").value;
    let graphic = document.getElementById("answer-graphic");

    if (graphic.files.length == 0 && !answer) {
        let note = document.getElementById("empty-answer-note");
        note.style.display = "block";
    } else {
        let form = $('#add_answer_form')[0];
        let data = new FormData(form);

        data.append("file", $('input[type=file]')[0].files[0]);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/answers/add?" + $.param({"answer": answer, "taskId": tasks[next_task - 1].id}),
            data: data,
            processData: false,
            contentType: false, //cache: false,
            success: function () {
                alert("Antwort eingereicht");
                $("#add-answer-modal").modal("hide");
            },
        })
    }
}

//ändern
function set_active(element_id) {
    let navbar_items = document.getElementById("navbar").getElementsByTagName("li");

    for (let i = 0; i < navbar_items.length; i++) {
        navbar_items[i].classList.remove("active");
    }

    document.getElementById(element_id).classList.add("active");
}

function show_aid() {
    /**document.getElementById("answer-modal").style.display="none";
     document.getElementById("task").style.display="none";
     document.getElementById("index-card").style.display = "block";
     document.getElementById("task-image").setAttribute("src", "data:image/png;base64," + tasks[next_task - 1].aid.data);*/
    set_active("nav-aid");
}

function enlargeImg(that) {
    that.style.transform = "scale(1.5)";
    that.style.transition = "transform 0.25s ease";
}

function resetImg(image_id) {
    let image = document.getElementById(image_id);
    image.style.transform = "scale(1)";
    image.style.transition = "transform 0.25s ease";
}

function add_pagination() {
    let list = document.getElementById("task-pagination");
    list.innerText = " ";
    list.style.display = "block";

    for (let i = 0; i < tasks.length; i++) {
        let element = document.createElement('li');
        let button = document.createElement('a');
        let task = i + 1;

        button.setAttribute("href", "javascript:show_exercise(" + i + ");");
        button.setAttribute("class", "transparent-button page-link");
        button.innerText = task.toString();
        element.setAttribute("class", "page-item");
        element.appendChild(button);
        list.appendChild(element);
    }

    document.getElementById("task-pagination").children[0].classList.add("active");
}

function show_exercise_button() {
    document.getElementById("next-exercise").style.display = "block";
    document.getElementById("exit-exam").style.display = "none";
}

function show_exercise(index) {
    document.getElementById("task-pagination").children[index].classList.add("active");
    document.getElementById("task-pagination").children[next_task - 1].classList.remove("active");

    setDifficulty();

    if (index > tasks.length - 2) {
        document.getElementById("next-exercise").style.display = "none";
        document.getElementById("exit-exam").style.display = "block";
    } else {
        show_exercise_button();
    }

    next_task = index + 1;
    show_task()
}

function exit_exam() {
    document.getElementById("exam-list").style.display = "block";
    document.getElementById("index-card").style.display = "none";
    document.getElementById("task-pagination").style.display = "none";
    document.getElementById("difficulty").style.display = "none";
}

function show_solution() {
    $.ajax({
        type: "GET",
        url: "/answers/byTaskAndUser",
        dataType: 'json',
        data: {
            task: tasks[next_task - 1].id
        },
        success: function (data) {

            if (data != null) {
                let solution = document.getElementById("task");
                let heading = document.getElementById("heading");
                let answer_form = document.getElementById("answer-modal");

                heading.innerText = "Lösung";
                solution.innerText = tasks[next_task - 1].solution.solution;
                document.getElementById("task-image").setAttribute("src", "data:image/png;base64," + tasks[next_task - 1].solution.graphic.data);

                answer_form.style.display = "None";
                set_active("nav-solution");
            } else {
                $("#solution-modal").modal();
            }

        }
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

function createAbsolvedQuestion(difficulty) {

    $.ajax(
        {
            type: "POST",
            url: "http://localhost:8080/solved/add",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify({
                "task": tasks[next_task - 1].id.toString(),
                "difficulty": difficulty.toString(),
            }),
        })
}