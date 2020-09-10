<?php

    include('security.php');



    if(isset($_POST['reg_btn'])){

        $admin_name = $_POST['admin_name'];
        $admin_email = $_POST['admin_email'];
        $admin_password = $_POST['admin_password'];
        $admin_conf_password = $_POST['admin_conf_password'];


        if($admin_password === $admin_conf_password){

            $query = "INSERT INTO admin (name, email_id, password)
                         VALUES ('$admin_name', '$admin_email', OLD_PASSWORD('$admin_password'))";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Admin Profile Added";
                    header('Location: adminreg.php');
                }
                else{
                    $_SESSION["status"] = "Admin Profile NOT Added";
                    header('Location: adminreg.php');
                
                }
        }

        else{

            $_SESSION["status"] = "Password NOT Mattach";
            header('Location: adminreg.php');
        }
    }

    if(isset($_POST['edit_update_btn'])){

        $id = $_POST['id'];
        $name = $_POST['name'];
        $email = $_POST['email'];
        $admin_old_password = $_POST['admin_old_password'];
        $admin_new_password = $_POST['admin_new_password'];
        $admin_conf_new_password = $_POST['admin_conf_new_password'];

            if($admin_new_password === $admin_conf_new_password){

                
                $query = "UPDATE admin SET name = '$name', email_id = '$email', password = OLD_PASSWORD('$admin_new_password')
                            WHERE email_id = '$email' AND password = OLD_PASSWORD('$admin_old_password')";
                $query_run = mysqli_query($connection, $query);
                
                    if($query_run){

                        $_SESSION["success"] = "Admin Profile Update";
                        header('Location: adminreg.php');
                    }
                    else{
                        $_SESSION["status"] = "Wrong Old Password";
                        header('Location: adminreg.php');
                    
                    }
    
            }

            else{

                $_SESSION["status"] = "Password NOT Mattach";
                header('Location: adminreg.php');
            }

        
    }

    if(isset($_POST['edit_cancel_btn'])){
            
        header('Location: adminreg.php');
    }


    if(isset($_POST['delete_btn'])){

       
        $delete_email = $_POST['delete_email'];
        $delete_password = $_POST['delete_password'];

        $query = "DELETE FROM admin WHERE email_id = '$delete_email' AND password = OLD_PASSWORD('$delete_password')";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Admin Account Deleted";
                header('Location: adminreg.php');
            }
            else{
                $_SESSION['status'] =  'Email iD & Password is Invalid Account not Deleted';
                header('Location: adminreg.php');
            }
    }

?>