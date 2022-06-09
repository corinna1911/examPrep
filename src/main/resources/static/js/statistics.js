const MINUTES_PER_DAY = 1440;
const MINUTES_PER_HOUR = 60;

window.addEventListener("click", function (e) {
    if (!e.target.matches('#dropdown-filter')) {
        document.getElementById("category-list").style.display = "none";
    }
});

function toggle_dropdown(dropdown) {
    let list = document.getElementById(dropdown);

    if (list.style.display === "none") {
        list.style.display = "block";
    } else {
        list.style.display = "none";
    }
}

window.onload = function () {
    num_solved_questions();
    get_avg_score();
    get_study_duration();
    get_categories("show_statistics_by_category");
}

function show_statistics_by_category(category_id) {
    get_avg_score_by_category(category_id);
    num_solved_questions_by_category(category_id);
}


function get_categories(onclick_function) {
    $.ajax(
        {
            type: "GET",
            url: "/categories/all",
            dataType: 'json',
            success: function (data) {
                create_category_list(data, onclick_function);
            }
        })
}

function create_category_list(categories, onclick_function) {

    for (let i in categories) {
        let list = document.getElementById("category-list");
        let element = document.createElement('li');
        let button = document.createElement('button');
        button.setAttribute("class", "list-button");

        button.setAttribute("onclick", onclick_function + "(" + categories[i].id + ")");
        button.appendChild(document.createTextNode(categories[i].category));
        element.appendChild(button);
        list.appendChild(element);
    }

}


function num_solved_questions() {
    $.ajax(
        {
            type: "GET",
            url: "/statistics/solved",
            dataType: 'json',
            success: function (data) {
                let progress_bar = document.getElementById("num-solved");
                progress_bar.innerText = Math.round(data * 10) / 10 + "%";
                progress_bar.style.width = data + "%";
            }
        })
}

function num_solved_questions_by_category(category_id) {
    $.ajax(
        {
            type: "GET",
            url: "/statistics/solved/byCategory",
            dataType: 'json',
            data: {categoryId: category_id},
            success: function (data) {
                set_progressbar("num-solved", data);
            }
        })
}

function get_avg_score() {

    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/statistics/score",
            dataType: 'json',
            success: function (data) {
                set_progressbar("avg-score", data);
            }
        })

}

function get_avg_score_by_category(category_id) {

    $.ajax(
        {
            type: "GET",
            url: "/statistics/score/byCategory",
            dataType: 'json',
            data: {categoryId: category_id},
            success: function (data) {
                set_progressbar("avg-score", data);
            }
        })
}

function set_progressbar(progressbar, data) {
    let progress_bar = document.getElementById(progressbar);
    progress_bar.innerText = Math.round(data * 10) / 10 + "%";
    progress_bar.style.width = data + "%";
}

function get_study_duration() {

    $.ajax(
        {
            type: "GET",
            url: "/statistics/duration",
            dataType: 'json',
            success: function (seconds) {
                let days = Math.floor(seconds / MINUTES_PER_DAY);
                let hours = Math.floor((seconds - days * MINUTES_PER_DAY) / MINUTES_PER_HOUR);
                let minutes = seconds - hours * MINUTES_PER_HOUR;
                document.getElementById("study-duration").innerText = days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) ";
            }
        })

}
