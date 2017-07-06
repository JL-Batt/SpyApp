<?php
$name = $_POST["name"];//'larry';
$OldPassword = $_POST["OldPassword"];//'pass';
$NewPassword = $_POST["NewPassword"];//'pass';


$conn = mysqli_connect("localhost", "root", "root", "spyappusers");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql1 = "SELECT * FROM users WHERE name = '$name'";

$result = $conn->query($sql1);
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
$OldPasswordDB = $response[password];


$hashAndSaltOld = crypt($OldPassword, $OldPasswordDB); 



if($hashAndSaltOld == $OldPasswordDB){
    $hashAndSaltNew = crypt($NewPassword, $OldPasswordDB); 

    $sql2 = "UPDATE users SET `password`='$hashAndSaltNew' WHERE `name`= '$name'";
    if ($conn->query($sql2) === TRUE) {
        echo "password was updated.";
    } 
    else {
        echo "Error: " . $conn->error;
    }
}
else{
  echo "Sorry your old password didn't match";
}
 

$conn->close();
?>


