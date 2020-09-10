<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile_no = $_POST["mobile_no"];
            $start_lat = $_POST["start_lat"];
            $start_long = $_POST["start_long"];
            $end_lat = $_POST["end_lat"];
            $end_long = $_POST["end_long"];
            $ride_type = $_POST["ride_type"];
            $start_date = $_POST["start_date"];
            $start_time = $_POST["start_time"];
            $end_date = $_POST["end_date"];
            $end_time = $_POST["end_time"];
            $status = $_POST["status"];
            $comment = $_POST["comment"];

            $query = "INSERT INTO rides (driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment) 
                        VALUES ('$mobile_no','$start_lat','$start_long','$end_lat','$end_long','$ride_type','$start_date','$start_time','$end_date','$end_time','$status','$comment')";

            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Ride Added";
                    header('Location: ridesreg.php');
                }
                else{

                    $_SESSION["status"] = "Ride Not Added";
                    header('Location: ridesreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile_no = $_POST["mobile_no"];
            $start_lat = $_POST["start_lat"];
            $start_long = $_POST["start_long"];
            $end_lat = $_POST["end_lat"];
            $end_long = $_POST["end_long"];
            $ride_type = $_POST["ride_type"];
            $start_date = $_POST["start_date"];
            $start_time = $_POST["start_time"];
            $end_date = $_POST["end_date"];
            $end_time = $_POST["end_time"];
            $status = $_POST["status"];
            $comment = $_POST["comment"];


        $query = "UPDATE rides SET driver_mobile_no = '$mobile_no', ride_start_lat = '$start_lat', ride_start_long = '$start_long', 
                    ride_end_lat = '$end_lat', ride_end_long = '$end_long', ride_type = '$ride_type', start_date = '$start_date', 
                    start_time = '$start_time', end_date = '$end_date', end_time = '$end_time', ride_status = '$status', 
                    comment = '$comment' WHERE sr_no = '$id' ";
                    
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Ride Update";
                header('Location: ridesreg.php');
            }
            else{
                $_SESSION["status"] = "Ride Not Update";
                header('Location: ridesreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: ridesreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM rides WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Ride Deleted";
                header('Location: ridesreg.php');
            }
            else{
                $_SESSION['status'] =  'Ride not Deleted';
                header('Location: ridesreg.php');
            }
    }


?>