
<?php

$host="127.0.0.1";
$user ="root";
$password="12345";
$database ="twitter";

$connect = mysqli_connect($host, $user,$password,$database);

if(mysqli_connect_errno()){
	
	die(" cannot connect to database ". mysqli_connect_error());
}


?>