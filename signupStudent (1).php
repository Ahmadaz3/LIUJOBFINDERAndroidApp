<?php
$full_name = addslashes(strip_tags($_POST['full_name']));
$email = addslashes(strip_tags($_POST['email']));
$password = addslashes(strip_tags($_POST['password']));
$university_name = addslashes(strip_tags($_POST['university_name']));

$con = mysqli_connect("localhost","id20478454_abcdee","Ahmadazimeh372#","id20478454_abcd");

// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: ". mysqli_connect_error();
}

// Insert data into the Student table
$sql1 = "INSERT INTO Student(full_name, email, password, university_name)
         VALUES ('$full_name', '$email', '$password', '$university_name')";
mysqli_query($con, $sql1) or die("Can't add record to the Student table");

// Insert data into the user table
$sql2 = "INSERT INTO user(id, email, password)
         VALUES (LAST_INSERT_ID(), '$email', '$password')";
mysqli_query($con, $sql2) or die("Can't add record to the user table");

echo "Record added successfully!";

mysqli_close($con);
?>