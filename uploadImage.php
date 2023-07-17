<?php
$con = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
	$Image = $_POST['imageUpload'];
	 $targetdir = "stdimage/";

	$imagestore = rand()."_".time().".jpeg";
	$targetdir = $targetdir."/".$imagestore;

 	file_put_contents($targetdir,base64_decode($Image));

	$query="INSERT INTO `images` (`Image`) VALUES ('$imagestore')";
	$result=mysqli_query($con,$query);

	if($result){
		echo 'image added';
	}else{
		echo 'failed';
	}

	mysqli_close($con);
?>