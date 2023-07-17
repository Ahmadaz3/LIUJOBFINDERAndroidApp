<?php
$job_name = addslashes(strip_tags($_POST['job_name']));
$job_location = addslashes(strip_tags($_POST['job_location']));
$job_type = addslashes(strip_tags($_POST['job_type']));
$workplace_type = addslashes(strip_tags($_POST['workplace_type']));
$job_requirements = addslashes(strip_tags($_POST['job_requirements']));
$job_description= addslashes(strip_tags($_POST['job_description']));
$status = 'Pending'; // Default status value
$buss_id = addslashes(strip_tags($_POST['buss_id']));
$Domain= addslashes(strip_tags($_POST['Domain']));

$con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: ". mysqli_connect_error();
}

$sql1 = "INSERT INTO Job(job_name, job_location, job_type, workplace_type, job_requirements, job_description, status, buss_id, Domain) 
         VALUES ('$job_name', '$job_location', '$job_type', '$workplace_type', '$job_requirements', '$job_description', '$status', '$buss_id', '$Domain')";

mysqli_query($con, $sql1) or die("Can't add record to the Job table: ".mysqli_error($con));

echo "Record Added Successfully";
mysqli_close($con);
?>