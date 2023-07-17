<?php
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");

$conn = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");

// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// Retrieve the std_major parameter from the HTTP request
$std_major = $_GET['std_major'];
$std_id = $_GET['std_id'];

// Select all rows from the Job table where status is Accepted, Domain is equal to $std_major,
// and the job has not been applied for by the student
$sql = "SELECT *
        FROM Job
        WHERE status = 'Accepted'
          AND Domain = '$std_major'
          AND job_id NOT IN (
            SELECT job_id FROM Applied_job WHERE std_id = '$std_id'
          )";
$result = mysqli_query($conn, $sql);

// Create an array to store the rows
$rows = array();

// Loop through the result set and store the rows in the array
while($row = mysqli_fetch_assoc($result)) {
    $rows[] = $row;
}

echo(json_encode($rows));

// Free result set
mysqli_free_result($result);
mysqli_close($conn);
?>
