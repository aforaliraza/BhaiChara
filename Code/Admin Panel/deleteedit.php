<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">Transaction History</h5>
         </div>

    <div class="card-body">

    <form action="transactionhistorycode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

            $edit_id = $_POST['edit_id'];
            $connection = mysqli_connect("localhost","root","","ride_share");

            $query = "SELECT sr_no, mobile_number, amount
                        FROM transaction_history  
                        Where sr_no = '$edit_id'";

            $query_run = mysqli_query($connection, $query);

              foreach($query_run as $row){
        ?>

          <div class="form-group">
                        <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="Id" required>
                    <label>Mobile No </label>
                        <input type="tel" name="mobile_no" value="<?php echo $row['mobile_number'];?>" class="form-control" placeholder="Mobile No" required>
                    <label>Amount</label>
                        <input type="text" name="amount" value="<?php echo $row['amount'];?>" class="form-control" placeholder="Source" required>
                    </div>
            <div class="modal-footer">
            <button type="submit" name="edit_cancel_btn" class="btn btn-secondary">CANCEL</button>
            <button type="submit" name="edit_update_btn" class="btn btn-success">UPDATE</button>
            </div>
        </form>
        </div>

        
   
   <?php
            }
        }
    ?>

      </div> 
   
   

<?php 
include('includes/script.php');
?>