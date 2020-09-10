<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $name = $_POST["name"];
            $address = $_POST["address"];
            $groupcode = $_POST["groupcode"];
            $longitude = $_POST["longitude"];
            $latitude = $_POST["latitude"];
            $ml = $_POST["ml"];

            $query = "insert into organizations (name, address, group_code, longitude, latitude, ml ) VALUES ('$name','$address','$groupcode','$longitude','$latitude','$ml')";
            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Organization Profile Added";
                    header('Location: orgreg.php');
                }
                else{

                    $_SESSION["status"] = "Organization Profile Not Added";
                    header('Location: orgreg.php');
                }
    }

    elseif(isset($_POST["org_edit_update_btn"])){
        

            $id = $_POST["id"];
            $name = $_POST["name"];
            $address = $_POST["address"];
            $groupcode = $_POST["groupcode"];
            $longitude = $_POST["longitude"];
            $latitude = $_POST["latitude"];
            $ml = $_POST["ml"];

        $query = "UPDATE organizations SET name = '$name', address = '$address', group_code = '$groupcode', longitude = '$longitude', latitude = '$latitude', ml = '$ml' WHERE sr_no = '$id' ";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Organization Profile Update";
                header('Location: orgreg.php');
            }
            else{
                $_SESSION["status"] = "Organization Profile Not Update";
                header('Location: orgreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["org_edit_cancel_btn"])){ 
        
         header('Location: orgreg.php');
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM organizations WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Organization Account Deleted";
                header('Location: orgreg.php');
            }
            else{
                $_SESSION['status'] =  'Organization Account not Deleted';
                header('Location: orgreg.php');
            }
    }


?>