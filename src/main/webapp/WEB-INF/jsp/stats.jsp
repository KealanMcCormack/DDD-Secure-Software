<html>
<head>
        <!--Load the AJAX API-->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">


            // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages':['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);
            google.charts.setOnLoadCallback(drawChart2);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            function drawChart() {
                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Nationality');
                data.addColumn('number', 'Vaccinated');
                data.addRows([
                        ["Male", ${Male}],
                        ["Female", ${Female}],
                        ["Other", ${Other}]
                    ]
                );

                // Set chart options
                var options = {'title':'Number of people with at least one dose',
                    'width':400,
                    'height':300};

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }

            function drawChart2() {
                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Nationality');
                data.addColumn('number', 'Not yet vaccinated');
                data.addRows([
                        ["Male", ${MaleNoVac}],
                        ["Female", ${FemaleNoVac}],
                        ["Other", ${OtherNoVac}]
                    ]
                );

                // Set chart options
                var options = {'title':'Number of people without one dose',
                    'width':400,
                    'height':300};

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.BarChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }
        </script>

</head>

<body>
<!--Div that will hold the pie chart-->
<div style="text-align: center; left: 50%">
    <h1 style="border-bottom: 5px solid lightgreen;">Statistics for the Vaccination Programme</h1>
    <div style="text-align: center;">
        <h2>
            <%
                if(request.getSession().getAttribute("privilege") != null){
            %>
            <a href="/admin_homepage">Back</a>
            <%
            } else{
            %>
            <a href="/">Back</a>
            <%
                }
            %>

        </h2>
    </div>
    <div style="text-align: center;" id="chart_div"></div>
    <div id="piechart" style="width: 900px; height: 500px; text-align: center;"></div>
</div>

</body>
</html>