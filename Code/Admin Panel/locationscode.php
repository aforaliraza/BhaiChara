<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            
            $address = $_POST["address"];
            $title = $_POST["title"];
            $city = $_POST["city"];
            $category = $_POST["category"];
            $longitude = $_POST["longitude"];
            $latitude = $_POST["latitude"];

            $query = "INSERT INTO locations (Address, Title, City, Category, Longitude, Latitude ) VALUES ('$address','$title','$city','$category','$longitude','$latitude')";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Loctaion Profile Added";
                    header('Location: locationsreg.php');
                }
                else{

                    $_SESSION["status"] = "Loctaion Profile Not Added";
                    header('Location: locationsreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $address = $_POST["address"];
            $title = $_POST["title"];
            $city = $_POST["city"];
            $category = $_POST["category"];
            $longitude = $_POST["longitude"];
            $latitude = $_POST["latitude"];

        $query = "UPDATE locations SET Address = '$address', Title = '$title', City = '$city', Category = '$category', Longitude = '$longitude', Latitude = '$latitude' WHERE sr_no = '$id' ";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Loctaion Profile Update";
                header('Location: locationsreg.php');
            }
            else{
                $_SESSION["status"] = "Loctaion Profile Not Update";
                header('Location: locationsreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: locationsreg.php');
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM locations WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Loctaion Account Deleted";
                header('Location: locationsreg.php');
            }
            else{
                $_SESSION['status'] =  'Loctaion Account not Deleted';
                header('Location: locationsreg.php');
            }
    }


?>