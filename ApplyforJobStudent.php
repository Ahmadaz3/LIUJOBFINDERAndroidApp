<?php
$std_id = addslashes(strip_tags($_POST['stdId']));
$job_id = addslashes(strip_tags($_POST['jobId']));

$con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");

// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: ". mysqli_connect_error();
}

// Check if the user has already applied for the job
$sql_check = "SELECT * FROM Applied_job WHERE std_id = '$std_id' AND job_id = '$job_id'";
$result_check = mysqli_query($con, $sql_check);
if(mysqli_num_rows($result_check) > 0) {
    echo "true";
} else {
    $sql_insert = "INSERT INTO Applied_job (std_id, job_id) VALUES ('$std_id', '$job_id')";
    mysqli_query($con, $sql_insert) or die("Can't add record to the AppliedJob table: ".mysqli_error($con));
    echo "Applied success";
}

mysqli_close($con);
?>