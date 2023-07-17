<?php
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");
$conn = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  
// Retrieve the buss_id parameter from the HTTP request
$buss_id = $_GET['buss_id'];

// Select all the employees with the same JSSNF
//$sql = "SELECT job_name,job_location FROM Job WHERE buss_id = $buss_id";
//$result = mysqli_query($conn, $sql);
$sql = "SELECT * FROM Business WHERE Business_id = $buss_id";
$result = mysqli_query($conn, $sql);
// Create an array to store the names
$names = array();

// Loop through the result set and store the names in the array
while($row = mysqli_fetch_assoc($result)) {
    $names[] = $row;
}
  echo(json_encode($names));
  // Free result set
  mysqli_free_result($result);
  mysqli_close($conn);
?> 	