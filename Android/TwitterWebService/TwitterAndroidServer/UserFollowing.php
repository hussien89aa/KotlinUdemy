
<?php

require("DBInfo.inc");

//Call service to new register
//http://localhost/~hussienalrubaye/TwitterAndroidServer/UserFollowing.php?op=1&user_id=1&following_user_id=2
// op=1 for follow , op=2 for unsubsrible

if ($_GET['op']==1) {
$query ="insert into following (user_id,following_user_id) values (".$_GET['user_id'] .",".$_GET['following_user_id'] .")" ;
}else if ($_GET['op']==2) {
$query ="delete from following where user_id = ".$_GET['user_id'] ." and following_user_id=".$_GET['following_user_id'] ;
}

$result= mysqli_query($connect,$query);

if (!$result){
	$output ="{ 'msg':'fail'}" ;
}else{
	$output ="{ 'msg':'follwoing is updated'}" ;
}

print($output);

mysqli_close($connect);
?>