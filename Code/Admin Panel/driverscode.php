<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile = $_POST["mobile"];
            $licence_no = $_POST["licence_no"];
            $longitude = $_POST["longitude"];
            $latitude = $_POST["latitude"];
            $status = $_POST["status"];

            $query = "INSERT INTO drivers (mobile_no, licence_no, longitude, latitude, status) VALUES ('$mobile','$licence_no','$longitude','$latitude','$status')";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Driver Profile Added";
                    header('Location: driversreg.php');
                }
                else{

                    $_SESSION["status"] = "Driver Profile Not Added";
                    header('Location: driversreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile = $_POST["mobile"];
            $licence_no = $_POST["licence_no"];
            $longitude = $_POST["longitude"];
            $latitude = $_POST["latitude"];
            $status = $_POST["status"];

        $query = "UPDATE drivers SET mobile_no = '$mobile', licence_no = '$licence_no', longitude = '$longitude', latitude = '$latitude', status = '$status' WHERE sr_no = '$id' ";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Driver Profile Update";
                header('Location: driversreg.php');
            }
            else{
                $_SESSION["status"] = "Driver Profile Not Update";
                header('Location: driversreg.php');       
            }
        }


    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: driversreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM drivers WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Driver Account Deleted";
                header('Location: driversreg.php');
            }
            else{
                $_SESSION['status'] =  'Driver Account not Deleted';
                header('Location: driversreg.php');
            }
    }


?>