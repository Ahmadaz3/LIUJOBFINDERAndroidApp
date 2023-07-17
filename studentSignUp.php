<?php
$First_name = addslashes(strip_tags($_POST['First_name']));
$Last_name = addslashes(strip_tags($_POST['Last_name']));
$Student_Email = addslashes(strip_tags($_POST['Student_Email']));
$Password = addslashes(strip_tags($_POST['Password']));
$Major = addslashes(strip_tags($_POST['Major']));
$Campus= addslashes(strip_tags($_POST['Campus']));
$con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: ". mysqli_connect_error();
}

// check information 
$query_1 = "SELECT * FROM Student WHERE Student_Email='$Student_Email'";
$result = mysqli_query($con,$query_1);

if (mysqli_num_rows($result)<1) {
    // Insert data into the Student table
    $sql1 = "INSERT INTO Student(First_name,Last_name,Student_Email,Password,Major,Campus)
         VALUES ('$First_name','$Last_name','$Student_Email','$Password','$Major','$Campus')";
    mysqli_query($con, $sql1) or die("Can't add record to the Student table");
    
    // Insert data into the user table
    $sql2 = "INSERT INTO users(id,email,password)
         VALUES (LAST_INSERT_ID(),'$Student_Email','$Password')";
    mysqli_query($con, $sql2) or die("Can't add record to the users table");

    echo "Record added successfully!";
} else {
  echo "Record falied to add";
}
mysqli_close($con);
?>