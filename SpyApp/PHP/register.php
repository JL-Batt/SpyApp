<?php
// $servername = "localhost";
// $username = "root";
// $DBpassword = "root";
// $dbname = "spyappusers";


$Uname = $_POST["name"];
$Upassword = $_POST["password"];
$Uemail = $_POST["email"];

// Create connection
//$conn = new mysqli($servername, $username, $DBpassword, $dbname);
$conn = mysqli_connect("localhost", "root", "root", "spyappusers");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Insert $hashAndSalt into database against user

$format = "$2y$10$";
$salt = "Salt22charactesrormore";
$formatandSalt = $format . $salt;
$hashAndSalt = crypt($Upassword,$formatandSalt); 

//$sql = "INSERT INTO users (firstname, lastname, email) VALUES ('John', 'Doe', 'john@example.com')";
$sql = "INSERT INTO users (name, password, email) VALUES ('$Uname','$hashAndSalt', '$Uemail')";

 //echo crypt($Upassword,$formatandSalt); 



if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $conn->error;
}

$conn->close();
?>
