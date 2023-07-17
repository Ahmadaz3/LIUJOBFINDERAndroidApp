<?php
$con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");

if(isset($_GET['std_id'])) {
    $std_id = $_GET['std_id'];

    $query = "SELECT Image FROM Student_Profile WHERE Std_id = '$std_id'";
    $result = mysqli_query($con, $query);

    if(mysqli_num_rows($result) > 0) {
        $row = mysqli_fetch_assoc($result);
        $image = $row['Image'];
        $imageData = base64_encode(file_get_contents('stdimage/'.$image));
        $src = 'data: '.mime_content_type('stdimage/'.$image).';base64,'.$imageData;
        echo json_encode(array('image' => $src));
    }
    else {
        echo json_encode(array('error' => 'Image not found'));
    }
}
else {
    echo json_encode(array('error' => 'Invalid request'));
}

mysqli_close($con);
?>
