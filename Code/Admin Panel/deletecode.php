<?php
include("security.php");



    if(isset($_POST["reg_btn"])){

            $mobile_no = $_POST["mobile_no"];
            $amount = $_POST["amount"];

            $query = "INSERT INTO transaction_history (mobile_number, amount) 
                        VALUES ('$mobile_no','$amount')";

            $query_run = mysqli_query($connection, $query);

                if($query_run){

                    $_SESSION["success"] = "Transaction Added";
                    header('Location: transactionhistoryreg.php');
                }
                else{

                    $_SESSION["status"] = "Transaction Not Added";
                    header('Location: transactionhistoryreg.php');
                }
    }

    elseif(isset($_POST["edit_update_btn"])){
        

            $id = $_POST["id"];
            $mobile_no = $_POST["mobile_no"];
            $amount = $_POST["amount"];


        $query = "UPDATE transaction_history 
                    SET mobile_number = '$mobile_no', amount = '$amount'
                    WHERE sr_no = '$id'";
                    
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){

                $_SESSION["success"] = "Transaction Update";
                header('Location: transactionhistoryreg.php');
            }
            else{
                $_SESSION["status"] = "Transaction Not Update";
                header('Location: transactionhistoryreg.php');
            
            }
   
        
        }



    elseif(isset($_POST["edit_cancel_btn"])){ 
        
        header('Location: transactionhistoryreg.php');
        
    }


    elseif(isset($_POST['delete_btn'])){

        $delete_id = $_POST['delete_id'];

        $query = "DELETE FROM transaction_history WHERE sr_no = '$delete_id'";
        $query_run = mysqli_query($connection, $query);
        
            if($query_run){
                $_SESSION["success"] = "Transaction Deleted";
                header('Location: transactionhistoryreg.php');
            }
            else{
                $_SESSION['status'] =  'Transaction Deleted';
                header('Location: transactionhistoryreg.php');
            }
    }


?>