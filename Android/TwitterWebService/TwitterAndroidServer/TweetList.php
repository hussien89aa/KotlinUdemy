<?php

require("DBInfo.inc");

//Call service to register

 // define quesry  //StartFrom
if ( $_GET['op']==1) { // my following
	 // case 1: search op=2, user_id =? , StartFrom=0
//http://localhost/~hussienalrubaye/TwitterAndroidServer/TweetList.php?op=1&user_id=1&StartFrom=0
$query="select * from user_tweets where user_id in (select following_user_id from following where user_id=". $_GET['user_id'] . ") or user_id=" . $_GET['user_id'] . " order by tweet_date DESC". 
" LIMIT 20 OFFSET ". $_GET['StartFrom']  ;  // $usename=$_GET['username'];
}

elseif ( $_GET['op']==2) { // specific person post
 // case 2: search op=2, user_id =? , StartFrom=0
//http://localhost/~hussienalrubaye/TwitterAndroidServer/TweetList.php?op=2&user_id=1&StartFrom=0
$query="select * from user_tweets where user_id=" . $_GET['user_id'] . " order by tweet_date DESC" . 
" LIMIT 20 OFFSET ". $_GET['StartFrom'] ;  // $usename=$_GET['username'];
}
elseif ($_GET['op']==3) {
	// case 3: search op=3, query =? , StartFrom=0
//http://localhost/~hussienalrubaye/TwitterAndroidServer/TweetList.php?op=3&query=new&StartFrom=0


$query ="select * from user_tweets where tweet_text like  '%". $_GET['query'] ."%' LIMIT 20 OFFSET ". $_GET['StartFrom'] ;

}
 
 
$result= mysqli_query($connect,$query);

if (!$result){
	die(' Error cannot run query');
}

$userTweets=array();
while ($row= mysqli_fetch_assoc($result)) {
	
	$userTweets[]= $row ;
 
}

if ($userTweets) {
print("{'msg':'has tweet','info':'". json_encode($userTweets) ."'}");
}else{
print("{'msg':'no tweets' }");
}

 mysqli_free_result($result);
mysqli_close($connect);
?>