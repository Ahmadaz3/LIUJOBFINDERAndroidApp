<?php
$job_id = addslashes(strip_tags($_POST['job_id']));
$job_name = addslashes(strip_tags($_POST['job_name']));
$job_location = addslashes(strip_tags($_POST['job_location']));
$job_type = addslashes(strip_tags($_POST['job_type']));
$workplace_type = addslashes(strip_tags($_POST['workplace_type']));
$job_requirements = addslashes(strip_tags($_POST['job_requirements']));
$job_description = addslashes(strip_tags($_POST['job_description']));

$con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$sql = "UPDATE Job SET job_name='$job_name',job_location='$job_location', job_type='$job_type', workplace_type='$workplace_type', job_requirements='$job_requirements', job_description='$job_description' WHERE job_id=$job_id";

if (mysqli_query($con, $sql)) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . mysqli_error($con);
}

mysqli_close($con);
?>
