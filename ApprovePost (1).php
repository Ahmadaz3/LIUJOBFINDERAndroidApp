<?php
$job_id = addslashes(strip_tags($_POST['job_id']));
$con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$sql = "UPDATE Job SET status='', post_time='$post_time', job_location='$job_location', job_type='$job_type', workplace_type='$workplace_type', job_requirements='$job_requirements', job_description='$job_description' WHERE job_id=$job_id";

if (mysqli_query($con, $sql)) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . mysqli_error($con);
}

mysqli_close($con);
?>