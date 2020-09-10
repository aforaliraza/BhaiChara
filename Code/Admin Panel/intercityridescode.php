<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile_no = $_POST["mobile_no"];
            $source = $_POST["source"];
            $destination = $_POST["destination"];
            $car_type = $_POST["car_type"];
            $car_number = $_POST["car_number"];
            $seats_capacity = $_POST["seats_capacity"];
            $roles = $_POST["roles"];
            $date = $_POST["date"];
            $time = $_POST["time"];
            $status = $_POST["status"];
            

            $query = "INSERT INTO inter_city_rides (mobile_no, source, destination, car_type, car_number, seats_capacity, roles, date, time, status) 
                        VALUES ('$mobile_no','$source','$destination','$car_type','$car_number','$seats_capacity','$roles','$date','$time','$status')";

            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Inter City Ride Added";
                    header('Location: intercityridesreg.php');
                }
                else{

                    $_SESSION["status"] = "Inter City Ride Not Added";
                    header('Location: intercityridesreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile_no = $_POST["mobile_no"];
            $source = $_POST["source"];
            $destination = $_POST["destination"];
            $car_type = $_POST["car_type"];
            $car_number = $_POST["car_number"];
            $seats_capacity = $_POST["seats_capacity"];
            $roles = $_POST["roles"];
            $date = $_POST["date"];
            $time = $_POST["time"];
            $status = $_POST["status"];


        $query = "UPDATE inter_city_rides SET mobile_no = '$mobile_no', source = '$source', destination = '$destination', 
                    car_type = '$car_type', car_number = '$car_number', seats_capacity = '$seats_capacity', roles = '$roles', 
                    date = '$date', time = '$time', status = '$status' 
                    WHERE sr_no = '$id'";
                    
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Inter City Ride Update";
                header('Location: intercityridesreg.php');
            }
            else{
                $_SESSION["status"] = "Inter City Ride Not Update";
                header('Location: intercityridesreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: intercityridesreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM inter_city_rides WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Ride Deleted";
                header('Location: intercityridesreg.php');
            }
            else{
                $_SESSION['status'] =  'Inter City Ride Deleted';
                header('Location: intercityridesreg.php');
            }
    }


?>