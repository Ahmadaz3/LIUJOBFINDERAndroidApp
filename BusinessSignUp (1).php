<?php
$Business_name = addslashes(strip_tags($_POST['Business_name']));
$Business_Email = addslashes(strip_tags($_POST['Business_Email']));
$password = addslashes(strip_tags($_POST['password']));
$Business_Size = addslashes(strip_tags($_POST['Business_Size']));
$Business_Type = addslashes(strip_tags($_POST['Business_Type']));
$Business_Location = addslashes(strip_tags($_POST['Business_Location']));
$Business_website = addslashes(strip_tags($_POST['Business_website']));
$con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: ". mysqli_connect_error();
}

// check information 
$query_1 = "SELECT * FROM Business WHERE Business_Email ='$Business_Email'";
$result = mysqli_query($con,$query_1);

if (mysqli_num_rows($result)<1) {
      $sql1 = "INSERT INTO Business (Business_name,Business_Email,password,Business_Size,Business_Type,Business_Location,Business_website)
         VALUES ('$Business_name','$Business_Email','$password','$Business_Size','$Business_Type','$Business_Location','$Business_website')";
    mysqli_query($con, $sql1) or die("Can't add record to the Business table");
    
    // Insert data into the user table
    $sql2 = "INSERT INTO users(id,email, password)
         VALUES (LAST_INSERT_ID(),'$Business_Email','$password')";
    mysqli_query($con, $sql2) or die("Can't add record to the user table");

    echo "Record added successfully!";
} else {
  echo "Record failed to add";
}
mysqli_close($con);
?>