<?php
$student_id = addslashes(strip_tags($_POST['student_id']));
$job_id = addslashes(strip_tags($_POST['job_id']));
$bus_id = addslashes(strip_tags($_POST['business_id']));
$Interview_location = addslashes(strip_tags($_POST['Interview_location']));
$interview_DateAndTime = addslashes(strip_tags($_POST['interview_DateAndTime']));
//$interview_status = addslashes(strip_tags($_POST['interview_status']));
$status = 'Pending'; // Default status value

$con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$sql1 = "INSERT INTO Interview_Appointment(std_id, job_id, bus_id,Interview_location,interview_DateAndTime,interview_status)
         VALUES ('$student_id', '$job_id', '$bus_id', '$Interview_location', '$interview_DateAndTime', '$status')";

mysqli_query($con, $sql1) or die("Can't add record to the Interview_Appointment table: " . mysqli_error($con));

echo "Record Added Successfully";
mysqli_close($con);
?>
