<?php
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");

$conn = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");

// Check connection
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// Retrieve the std_major parameter from the HTTP request
$std_major = $_GET['std_major'];
$std_id = $_GET['std_id'];

// Select job_id, job_name, job_location, job_type, workplace_type, job_requirements, job_description,
// status, Business_id, Domain, and Business_name from Job and Business tables
$sql = "SELECT J.job_id, J.job_name, J.job_location, J.job_type, J.workplace_type, J.job_requirements,
               J.job_description, J.status, J.buss_id, J.Domain, B.Business_name
        FROM Job J
        INNER JOIN Business B ON J.buss_id = B.Business_id
        WHERE J.status = 'Accepted'
          AND J.Domain = '$std_major'
          AND J.job_id NOT IN (
            SELECT job_id FROM Applied_job WHERE std_id = '$std_id'
          )";
$result = mysqli_query($conn, $sql);

// Create an array to store the rows
$rows = array();

// Loop through the result set and store the rows in the array
while ($row = mysqli_fetch_assoc($result)) {
    $rows[] = $row;
}

echo (json_encode($rows));

// Free result set
mysqli_free_result($result);
mysqli_close($conn);
?>