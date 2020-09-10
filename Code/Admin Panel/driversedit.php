<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">EDIT Driver Profile </h5>
         </div>

    <div class="card-body">

    <form action="driverscode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

            $edit_id = $_POST['edit_id'];
            $connection = mysqli_connect("localhost","root","","ride_share");

            $query = "SELECT mobile_no, licence_no, longitude, latitude, status FROM drivers Where sr_no = '$edit_id' ";
            $query_run = mysqli_query($connection, $query);

              foreach($query_run as $row){
        ?>

            <div class="form-group">
                    <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="Id" required>
                <label> Mobile No </label>
                    <input type="text" name="mobile" value="<?php echo $row['mobile_no'];?>" class="form-control" placeholder="Mobile No" required>
                <label> Licence No </label>
                    <input type="text" name="licence_no" value="<?php echo $row['licence_no'];?>" class="form-control" placeholder="Licence No" required>
                <label> Longitude </label>
                    <input type="text" name="longitude" value="<?php echo $row['longitude'];?>" class="form-control" placeholder="Longitude" required>
                <label> Latitude </label>
                    <input type="text" name="latitude" value="<?php echo $row['latitude'];?>" class="form-control" placeholder="Latitude" required>
                <label> Status </label>
                    <input type="text" name="status" value="<?php echo $row['status'];?>" class="form-control" placeholder="Status" required>
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