window.onload = function () {
    set_examtypes();
}


function addExercise(that) {

    let form = $('#add_task_form')[0];
    let data = new FormData(form);
    let task = document.getElementById("task").value;
    let points = document.getElementById("points").value;
    let solution = document.getElementById("solution").value;

    //data.append("file", file_upload.files[0]);
    $.each($("input[type=file]"), function (i, obj) {
        $.each(obj.files, function (j, file) {
            data.append('file', file);
        })
    });

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:8080/tasks/add?" + $.param({"task": task, "points": points, "solution": solution}),
        data: data,

        processData: false, //prevent  from automatically transforming the data into a query string
        contentType: false,
        cache: false,

    })
}

function add_aid() {

    let form = $('#add_task_form')[0];
    let data = new FormData(form);

    $.each($("input[type=file]"), function (i, obj) {
        $.each(obj.files, function (j, file) {
            data.append('file', file);
        })
    });

    $.ajax({
        type: "POST", enctype: 'multipart/form-data', url: "http://localhost:8080/aid/add", data: data,

        processData: false, //prevent  from automatically transforming the data into a query string
        contentType: false, cache: false,

    })

}

function set_examtypes() {


    $.ajax({
        type: "GET",
        enctype: 'multipart/form-data',
        url: "http://localhost:8080/examtype/all",
        success: function (data) {
            let container = document.getElementById("exam-type")

            for (let i in data) {
                let input = document.createElement('input');
                let label = document.createElement('label');

                input.type = "radio";
                input.value = data[i].id;
                input.id = "examtype" + i;
                input.name = "examtype";
                label.appendChild(document.createTextNode(data[i].type));
                container.appendChild(input);
                container.appendChild(label);
            }
        }

    })
}

function add_exam() {
    let exam_type;
    let date = document.getElementById("exam-date").value;
    let exam_type_list = document.getElementsByName("examtype");

    for (let i = 0; i < exam_type_list.length; i++) {
        if (exam_type_list[i].checked) {
            exam_type = exam_type_list[i].value;
        }
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/exam/add",

        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify({
            "date": date.toString(),
            "examTyp": exam_type.toString(),
        }),
        success: function (data) {
            alert("hallo");
        }

    })

}

