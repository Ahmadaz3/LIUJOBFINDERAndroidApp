<?php
// Include your database connection file

$connection = mysqli_connect("localhost", "id20478454_liujobfinder1", "rpy^[bYvHGb68]on", "id20478454_liujobfinder");
// Get the business ID from the Android application
$businessId = $_GET['business_id'];

// Prepare the SQL query to fetch the students who applied for jobs for the specific business
$query = "SELECT s.Student_id, s.First_name, s.Last_name, s.Student_Email, s.Major, s.Campus,
          j.job_id, j.job_name, j.job_location, j.job_type, j.workplace_type, j.job_requirements, j.job_description, j.status
          FROM Student s
          INNER JOIN Applied_job a ON s.Student_id = a.std_id
          INNER JOIN Job j ON a.job_id = j.job_id
          WHERE j.buss_id = $businessId";

// Execute the query
$result = mysqli_query($connection, $query);

// Check if there are any results
if (mysqli_num_rows($result) > 0) {
    $students = array();

    // Loop through the result set and fetch student and job data
    while ($row = mysqli_fetch_assoc($result)) {
        $student = array(
            'Student_id' => $row['Student_id'],
            'First_name' => $row['First_name'],
            'Last_name' => $row['Last_name'],
            'Student_Email' => $row['Student_Email'],
            'Major' => $row['Major'],
            'Campus' => $row['Campus'],
            'Job_id' => $row['job_id'],
            'Job_name' => $row['job_name']
        );

        // Add the student to the students array
        $students[] = $student;
    }

    // Convert the students array to JSON format
    $response = json_encode($students);

    // Send the JSON response back to the Android application
    echo $response;
} else {
    // If no students found, send an empty response
    echo "[]";
}

// Close the database connection
mysqli_close($connection);
?>
