<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">EDIT Ogansiation Profile </h5>
         </div>

    <div class="card-body">

    <form action="orgcode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

            $edit_id = $_POST['edit_id'];
            $connection = mysqli_connect("localhost","root","","ride_share");

            $query = "SELECT name, address, group_code, longitude, latitude, ml FROM organizations Where sr_no = '$edit_id' ";
            $query_run = mysqli_query($connection, $query);

              foreach($query_run as $row){
        ?>

        <div class="form-group">
          <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="id" required>
          <label> Name </label>
            <input type="text" name="name" value="<?php echo $row['name'];?>" class="form-control" placeholder="Name" required>
          <label> Address </label>
            <input type="text" name="address" value="<?php echo $row['address'];?>" class="form-control" placeholder="Address" required>
          <label> Group Code </label>
            <input type="text" name="groupcode" value="<?php echo $row['group_code'];?>" class="form-control" placeholder="Group Code" required>
          <label> Longitude </label>
            <input type="text" name="longitude" value="<?php echo $row['longitude'];?>" class="form-control" placeholder="Longitude" required>
          <label> Latitude </label>
            <input type="text" name="latitude" value="<?php echo $row['latitude'];?>" class="form-control" placeholder="Latitude" required>
          <label> Ml </label>
            <input type="text" name="ml" value="<?php echo $row['ml'];?>" class="form-control" placeholder="Ml" required>
            </div>

            <div class="modal-footer">
            <button type="submit" name="org_edit_cancel_btn" class="btn btn-secondary">CANCEL</button>
            <button type="submit" name="org_edit_update_btn" class="btn btn-success">UPDATE</button>
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