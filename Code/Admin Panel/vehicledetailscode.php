<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile_no = $_POST["mobile_no"];
            $car_type = $_POST["car_type"];
            $seat_available = $_POST["seat_available"];
            $car_number = $_POST["car_number"];
            $source = $_POST["source"];
            $destination = $_POST["destination"];
            $date = $_POST["date"];
            $time = $_POST["time"];

            $query = "INSERT INTO vehicle_details (mobile_no, car_type, seat_available, car_number, source, destination, date, time) VALUES ('$mobile_no','$car_type','$seat_available','$car_number','$source','$destination','$date','$time')";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Vehicle Detail Added";
                    header('Location: vehicledetailsreg.php');
                }
                else{

                    $_SESSION["status"] = "Vehicle Detail Not Added";
                    header('Location: vehicledetailsreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile_no = $_POST["mobile_no"];
            $car_type = $_POST["car_type"];
            $seat_available = $_POST["seat_available"];
            $car_number = $_POST["car_number"];
            $source = $_POST["source"];
            $destination = $_POST["destination"];
            $date = $_POST["date"];
            $time = $_POST["time"];


        $query = "UPDATE vehicle_details SET mobile_no = '$mobile_no', car_type = '$car_type', seat_available = '$seat_available', car_number = '$car_number', source = '$source', destination = '$destination', date = '$date', time = '$time' WHERE sr_no = '$id' ";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Vehicle Detail Update";
                header('Location: vehicledetailsreg.php');
            }
            else{
                $_SESSION["status"] = "Vehicle Detail Not Update";
                header('Location: vehicledetailsreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: vehicledetailsreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM vehicle_details WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Vehicle Detail Deleted";
                header('Location: vehicledetailsreg.php');
            }
            else{
                $_SESSION['status'] =  'Vehicle Detail not Deleted';
                header('Location: vehicledetailsreg.php');
            }
    }


?>