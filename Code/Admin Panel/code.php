<?php
include('security.php');


if(isset($_POST['login_btn'])){

    $email_login = $_POST['email'];
    $password_login = $_POST['password'];

    $query = "SELECT name FROM admin WHERE email_id = '$email_login' AND password = OLD_PASSWORD('$password_login')";
    $query_run = mysqli_query($connection, $query);
    
        if(mysqli_fetch_array($query_run)){
    
            $_SESSION['username'] =  $email_login;
            header('Location: index.php');
        }
        else{
            $_SESSION['status'] =  'Email iD & Password is Invalid';
            header('Location: login.php');


        }

    }
?>