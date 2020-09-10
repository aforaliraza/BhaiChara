<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile_no = $_POST["mobile_no"];
            $group_code = $_POST["group_code"];

            $query = "INSERT INTO user_groups (mobile_no, group_code) VALUES ('$mobile_no','$group_code')";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "User Group  Added";
                    header('Location: usergroupsreg.php');
                }
                else{

                    $_SESSION["status"] = "User Group Not Added";
                    header('Location: usergroupsreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile_no = $_POST["mobile_no"];
            $group_code = $_POST["group_code"];

        $query = "UPDATE user_groups SET mobile_no = '$mobile_no', group_code = '$group_code' WHERE sr_no = '$id' ";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "User Group Added";
                header('Location: usergroupsreg.php');
            }
            else{
                $_SESSION["status"] = "User Group Not Added";
                header('Location: usergroupsreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: usergroupsreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM user_groups WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "User Group Deleted";
                header('Location: usergroupsreg.php');
            }
            else{
                $_SESSION['status'] =  'User Group Not Deleted';
                header('Location: usergroupsreg.php');
            }
    }


?>