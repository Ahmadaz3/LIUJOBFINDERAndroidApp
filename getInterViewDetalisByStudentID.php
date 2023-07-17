<?php
// Establish a connection to the database
$conn = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Retrieve the student_id parameter from the HTTP request
$student_id = $_GET['student_id'];

// Build the SQL query to retrieve interview details, job name, and business name based on the student_id
$sql = "SELECT ia.*, j.job_name, b.business_name 
        FROM Interview_Appointment ia 
        INNER JOIN Job j ON ia.job_id = j.job_id 
        INNER JOIN Business b ON ia.bus_id = b.business_id 
        WHERE ia.std_id = '$student_id'";

// Execute the SQL query and retrieve the results
$result = mysqli_query($conn, $sql);

if (!$result) {
    die("Query failed: " . mysqli_error($conn));
}

// Create an array to store the interview details
$interviews = array();

// Loop through the result set and add each interview detail to the array
while ($row = mysqli_fetch_assoc($result)) {
    $interviews[] = $row;
}

// Convert the array to JSON format and print it
header('Content-Type: application/json');
echo json_encode($interviews);

// Close the database connection
mysqli_free_result($result);
mysqli_close($conn);
?>

