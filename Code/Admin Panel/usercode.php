<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile_no = $_POST["mobile_no"];
            $first_name = $_POST["first_name"];
            $last_name = $_POST["last_name"];
            $user_email = $_POST["user_email"];
            $group_code = $_POST["group_code"];
            $roles = $_POST["roles"];

            $query = "INSERT INTO users (mobile_no, first_name, last_name, email_id, group_code, roles) VALUES ('$mobile_no','$first_name','$last_name','$user_email','$group_code','$roles')";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "User Profile Added";
                    header('Location: userreg.php');
                }
                else{

                    $_SESSION["status"] = "User Profile Not Added";
                    header('Location: userreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile_no = $_POST["mobile_no"];
            $first_name = $_POST["first_name"];
            $last_name = $_POST["last_name"];
            $user_email = $_POST["user_email"];
            $group_code = $_POST["group_code"];
            $roles = $_POST["roles"];

        $query = "UPDATE users SET mobile_no = '$mobile_no', first_name = '$first_name', last_name = '$last_name', email_id = '$user_email', group_code = '$group_code', roles = '$roles' WHERE sr_no = '$id' ";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "User Profile Update";
                header('Location: userreg.php');
            }
            else{
                $_SESSION["status"] = "User Profile Not Update";
                header('Location: userreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: userreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM users WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "User Account Deleted";
                header('Location: userreg.php');
            }
            else{
                $_SESSION['status'] =  'User Account not Deleted';
                header('Location: userreg.php');
            }
    }


?>