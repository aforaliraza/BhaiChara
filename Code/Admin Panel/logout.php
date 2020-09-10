<?php

include("security.php");



header('Location: login.php');
if(isset($_POST['logout_btn'])){

    session_destroy();
    unset($_SESSION['username']);
    
}

?>