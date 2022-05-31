window.onload = function () {

    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/tasks/all",
            dataType: 'json',
            success: function (data) {
                let exercise_table = document.getElementById("exercises");
                for (let i in data) {
                    let row = exercise_table.insertRow(1);
                    let cell1 = row.insertCell(0);
                    let cell2 = row.insertCell(1);
                    let cell3 = row.insertCell(2);
                    cell1.innerHTML = data[i].title;
                    cell2.innerHTML = data[i].points;
                    cell3.innerHTML = "keine Lösung vorhanden"
                }
            }
        })

}