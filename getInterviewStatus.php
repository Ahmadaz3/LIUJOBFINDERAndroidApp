<?php
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");
$conn = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// Retrieve the std_id parameter from the HTTP request
$std_id = $_GET['std_id'];

// Select the interview status based on std_id
$sql = "SELECT interview_status FROM Interview_Appointment WHERE std_id = $std_id";
$result = mysqli_query($conn, $sql);

// Create an array to store the interview status
$statuses = array();

// Loop through the result set and store the interview status in the array
while($row = mysqli_fetch_assoc($result)) {
    $statuses[] = $row;
}

echo(json_encode($statuses));

// Free result set
mysqli_free_result($result);
mysqli_close($conn);

?>
