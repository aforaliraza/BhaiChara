<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">EDIT Vehicle Detail </h5>
         </div>

    <div class="card-body">

    <form action="vehicledetailscode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

            $edit_id = $_POST['edit_id'];
            $connection = mysqli_connect("localhost","root","","ride_share");

            $query = "SELECT  mobile_no, car_type, seat_available, car_number, source, destination, date, time FROM vehicle_details Where sr_no = '$edit_id' ";
            $query_run = mysqli_query($connection, $query);

              foreach($query_run as $row){
        ?>

          <div class="form-group">
                        <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="Id" required>
                    <label> Mobile No </label>
                        <input type="text" name="mobile_no" value="<?php echo $row['mobile_no'];?>" class="form-control" placeholder="Mobile No" required>
                    <label> Car Type </label>
                        <input type="text" name="car_type" value="<?php echo $row['car_type'];?>" class="form-control" placeholder="Car Type" required>
                    <label>Seat Available</label>
                        <input type="text" name="seat_available" value="<?php echo $row['seat_available'];?>" class="form-control" placeholder="Seat Available" required>
                    <label> Car Number</label>
                        <input type="text" name="car_number" value="<?php echo $row['car_number'];?>" class="form-control" placeholder="Car Number" required>
                    <label> Source </label>
                        <input type="text" name="source" value="<?php echo $row['source'];?>" class="form-control" placeholder="Source" required>
                    <label>Destination </label>
                        <input type="text" name="destination" value="<?php echo $row['destination'];?>" class="form-control" placeholder="Destination" required>
                    <label> Date </label>
                        <input type="date" name="date" value="<?php echo $row['date'];?>" class="form-control" placeholder="Date" required>
                    <label> Time </label>
                        <input type="time" name="time" value="<?php echo $row['time'];?>" class="form-control" placeholder="Time" required></div>
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