<?php
$con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
    $personal_Email =$_POST['perEmail'];
    $Education =$_POST['Edu'];
    $Loc =$_POST['Loc'];
    $Experience = $_POST['Exp'];
    $std_id = $_POST['std'];
	$Image = $_POST['imageUpload'];
	
	 $targetdir = "stdimage/";

	$imagestore = rand()."_".time().".jpeg";
	$targetdir = $targetdir."/".$imagestore;

 	file_put_contents($targetdir,base64_decode($Image));

  $query = "INSERT INTO Student_Profile(Personal_Email,Education,Location,Experience,Std_id,Image)
         VALUES ('$personal_Email','$Education','$Loc','$Experience','$std_id','$imagestore')";
	$result=mysqli_query($con,$query);

	if($result){
		echo 'Profile Updated';
	}else{
		echo 'failed';
	}

	mysqli_close($con);
?>