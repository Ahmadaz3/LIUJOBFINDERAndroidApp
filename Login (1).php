<?php
$con = mysqli_connect("localhost","id20478454_liujobfinder1","rpy^[bYvHGb68]on","id20478454_liujobfinder");
$email = $_POST['email'];
$password = $_POST['password'];
$query = "SELECT * FROM users WHERE email='$email' AND password='$password'";
$result = mysqli_query($con, $query);
if (mysqli_num_rows($result) == 1) {
    $user = mysqli_fetch_assoc($result);
    $response = array(
        'error' => false,
        'user' => $user
    );
} else {
    $response = array(
        'error' => true,
        'message' => 'Invalid username or password'
    );
}
echo json_encode($response);
?>