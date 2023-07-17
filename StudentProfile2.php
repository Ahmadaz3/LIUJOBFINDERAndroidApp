<?php
$con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
$personal_Email = $_POST['perEmail'];
$Education = $_POST['Edu'];
$Loc = $_POST['Loc'];
$Experience = $_POST['Exp'];
$std_id = $_POST['std'];
$Image = $_POST['imageUpload'];

$targetdir = "stdimage/";
$imagestore = rand() . "_" . time() . ".jpeg";
$targetdir = $targetdir . "/" . $imagestore;
file_put_contents($targetdir, base64_decode($Image));

// Check if the profile already exists
$query = "SELECT * FROM Student_Profile WHERE Std_id = '$std_id'";
$result = mysqli_query($con, $query);

if (mysqli_num_rows($result) > 0) {
  // Profile already exists, perform update
  $updateQuery = "UPDATE Student_Profile SET Personal_Email = '$personal_Email', Education = '$Education', Location = '$Loc', Experience = '$Experience', Image = '$imagestore' WHERE Std_id = '$std_id'";
  $updateResult = mysqli_query($con, $updateQuery);

  if ($updateResult) {
    echo 'Profile Updated';
  } else {
    echo 'Update Failed';
  }
} else {
  // Profile doesn't exist, perform insert
  $insertQuery = "INSERT INTO Student_Profile (Personal_Email, Education, Location, Experience, Std_id, Image)
                  VALUES ('$personal_Email', '$Education', '$Loc', '$Experience', '$std_id', '$imagestore')";
  $insertResult = mysqli_query($con, $insertQuery);

  if ($insertResult) {
    echo 'Profile Created';
  } else {
    echo 'Insert Failed';
  }
}

mysqli_close($con);
?>