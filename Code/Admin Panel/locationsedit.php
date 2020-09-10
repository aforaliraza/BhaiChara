<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">EDIT Location</h5>
         </div>

    <div class="card-body">

    <form action="locationscode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

            $edit_id = $_POST['edit_id'];
            $connection = mysqli_connect("localhost","root","","ride_share");

            $query = "SELECT Address, Title, City, Category, Longitude, Latitude FROM locations Where sr_no = '$edit_id' ";
            $query_run = mysqli_query($connection, $query);

              foreach($query_run as $row){
        ?>

        <div class="form-group">
          <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="Id" required>
          <label> Address </label>
            <input type="text" name="address" value="<?php echo $row['Address'];?>" class="form-control" placeholder="Address" required>
          <label> Title </label>
            <input type="text" name="title" value="<?php echo $row['Title'];?>" class="form-control" placeholder="Title" required>
          <label> City </label>
            <input type="text" name="city" value="<?php echo $row['City'];?>" class="form-control" placeholder="City" required>
          <label> Category </label>
            <input type="text" name="category" value="<?php echo $row['Category'];?>" class="form-control" placeholder="Category" required>
          <label> Longitude </label>
            <input type="text" name="longitude" value="<?php echo $row['Longitude'];?>" class="form-control" placeholder="Longitude" required>
          <label> Latitude </label>
            <input type="text" name="latitude" value="<?php echo $row['Latitude'];?>" class="form-control" placeholder="latitude" required>
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