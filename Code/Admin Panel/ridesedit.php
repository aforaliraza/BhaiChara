<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">EDIT Ride</h5>
         </div>

    <div class="card-body">

    <form action="ridescode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

            $edit_id = $_POST['edit_id'];
            $connection = mysqli_connect("localhost","root","","ride_share");

            $query = "SELECT driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment FROM rides Where sr_no = '$edit_id' ";
            $query_run = mysqli_query($connection, $query);

              foreach($query_run as $row){
        ?>

          <div class="form-group">
                        <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="Id" required>
                    <label>Driver Mobile No </label>
                        <input type="text" name="mobile_no" value="<?php echo $row['driver_mobile_no'];?>" class="form-control" placeholder="Mobile No" required>
                    <label>Start Lat </label>
                        <input type="text" name="start_lat" value="<?php echo $row['ride_start_lat'];?>" class="form-control" placeholder="Start Lat" required>
                    <label>Start Long</label>
                        <input type="text" name="start_long" value="<?php echo $row['ride_start_long'];?>" class="form-control" placeholder="Start Long" required>
                    <label>End Lat</label>
                        <input type="text" name="end_lat" value="<?php echo $row['ride_end_lat'];?>" class="form-control" placeholder="End Lat" required>
                    <label>End Long</label>
                        <input type="text" name="end_long" value="<?php echo $row['ride_end_long'];?>" class="form-control" placeholder="End Long" required>
                    <label>Ride Type</label>
                        <input type="text" name="ride_type" value="<?php echo $row['ride_type'];?>" class="form-control" placeholder="Ride Type" required>
                    <label>Start Date </label>
                        <input type="date" name="start_date" value="<?php echo $row['start_date'];?>" class="form-control" placeholder="Start Date" required>
                    <label>Start Time </label>
                        <input type="time" name="start_time" value="<?php echo $row['start_time'];?>" class="form-control" placeholder="Start Time" required>
                    <label>End Date </label>
                        <input type="date" name="end_date" value="<?php echo $row['end_date'];?>" class="form-control" placeholder="End Date" required>
                    <label>End Time </label>
                        <input type="time" name="end_time" value="<?php echo $row['end_time'];?>" class="form-control" placeholder="End Time" required>
                    <label>Status</label>
                        <input type="text" name="status" value="<?php echo $row['ride_status'];?>" class="form-control" placeholder="Status" required>
                    <label>Comment</label>
                        <input type="text" name="comment" value="<?php echo $row['comment'];?>" class="form-control" placeholder="Comment"></div>
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