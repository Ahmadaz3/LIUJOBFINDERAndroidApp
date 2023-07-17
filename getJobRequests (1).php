<?php
// Establish a connection to the database
$conn = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Build the SQL query
$sql = "SELECT * FROM Job WHERE status = 'Pending'";

// Execute the SQL query and retrieve the results
$result = mysqli_query($conn, $sql);

if (!$result) {
    die("Query failed: " . mysqli_error($conn));
}

// Create an array to store the job data
$jobs = array();

// Loop through the result set and add each job to the array
while ($row = mysqli_fetch_assoc($result)) {
    $jobs[] = $row;
}

// Convert the array to JSON format and print it
header('Content-Type: application/json');
echo json_encode($jobs);

// Close the database connection
mysqli_free_result($result);
mysqli_close($conn);
?>