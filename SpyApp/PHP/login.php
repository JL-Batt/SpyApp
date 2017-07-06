<?php
error_reporting(0);

$name = $_POST["name"];
$inputPassword = $_POST["password"];

$conn = mysqli_connect("localhost", "root", "root", "spyappusers");

if ($conn->connect_error) {
    echo "Connection failed". $conn->connect_error;
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM users WHERE name = '$name'";

$result = $conn->query($sql);
$response = array();

while($row = mysqli_fetch_array($result)){
    $response = array(
        "id"=>$row[0],
      "name"=>$row[1],
      "password"=>$row[2],
      "email"=>$row[3],
      );

}

//Hash the password entered on login
$dbPassword = $response[password];
//echo $dbPassword,"<br/>";
$rehashedPass =  crypt($inputPassword, $dbPassword); 
//echo $rehashedPass;

if ($rehashedPass == $dbPassword){
  echo "Details Correct";
}
else{
  echo "Data is incorrect.";
  }

?>
