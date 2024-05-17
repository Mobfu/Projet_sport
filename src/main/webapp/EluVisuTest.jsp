<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chart Example</title>
    <!-- Chart.js CDN -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- Papa Parse CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.4.1/papaparse.min.js"></script>
    <!-- Papa Parse CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.4.1/papaparse.js"></script>

    <style>
        /* Styles for the page */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        #chartContainer {
            margin-top: 20px;
            width: 600px;
            height: 400px;
        }

        #formContainer {
            margin-bottom: 20px;
        }

        #inputFields {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
    <h1>Chart Example</h1>

    <!-- Form to manually input data or upload CSV file -->
    <div id="formContainer">
        <div id="inputFields">
            <label>Labels (comma-separated):</label>
            <input type="text" id="labelsInput" placeholder="e.g., Red,Blue,Yellow,Green,Purple,Orange">
            <br>
            <label>Valeurs (comma-separated):</label>
            <input type="text" id="valuesInput" placeholder="e.g., 12,19,3,5,2,3">
        </div>
        <button id="updateButton">Update Chart</button>
        <br>
        <input type="file" id="csvFile" accept=".csv">
    </div>

    <!-- Canvas for the chart -->
    <canvas id="myChart"></canvas>

    <script>
        // Initialize the chart with default data
        const ctx = document.getElementById('myChart').getContext('2d');
        let chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: ['red', 'blue', 'yellow', 'green', 'purple', 'orange']
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        // Function to update the chart with new data
        function updateChart(labels, data) {
            chart.data.labels = labels;
            chart.data.datasets[0].data = data;
            chart.update();
        }

        // Event listener for the update button
        document.getElementById('updateButton').addEventListener('click', function () {
            const labelsInput = document.getElementById('labelsInput').value;
            const valuesInput = document.getElementById('valuesInput').value;

            // Convert the input values to arrays
            const labels = labelsInput.split(',');
            const data = valuesInput.split(',').map(Number);

            // Update the chart with new data
            updateChart(labels, data);
        });

        // Event listener for the file input
        document.getElementById('csvFile').addEventListener('change', function () {
            const file = this.files[0];

            // Use Papa Parse to parse the CSV file
            Papa.parse(file, {
                header: true,
                complete: function (results) {
                    const data = results.data;
                    // Assume the first column is labels and the second column is data
                    const labels = data.map(row => row[Object.keys(data[0])[0]]);
                    const values = data.map(row => row[Object.keys(data[0])[1]]);

                    // Update the chart with data from the CSV file
                    updateChart(labels, values);
                }
            });
        });
    </script>
</body>

</html>
