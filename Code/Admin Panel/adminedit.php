<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>

<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">EDIT Admin Profile </h5>
         </div>

    <div class="card-body">

    <form action="admincode.php" method="POST">
       <?php

          if(isset($_POST['edit_btn'])){

          $id = $_POST['edit_id'];
          $connection = mysqli_connect("localhost","root","","ride_share");

          $query = "SELECT  name, email_id FROM admin Where sr_no = '$id' ";
          $query_run = mysqli_query($connection, $query);

          foreach($query_run as $row){
        ?>

        <div class="form-group">
            <input type="hidden" name="id" value="<?php echo $edit_id;?>" class="form-control" placeholder="Id">
            <input type="hidden" name="email" value="<?php echo $row['email_id'];?>" class="form-control" placeholder="Email">
            
          <label> Email </label>
            <input type="text"  value="<?php echo $row['email_id'];?>" class="form-control" placeholder="Email" disabled>
          <label> Name </label>
            <input type="text" name="name" value="<?php echo $row['name'];?>" class="form-control" placeholder="Name" required>
          <label> New Passsword </label>
            <input type="password" name="admin_new_password" class="form-control" placeholder="NEW PASSWORD" >
          <label> Confirm NEW PASSWORD</label>
            <input type="password" name="admin_conf_new_password" class="form-control" placeholder="Confirm NEW PASSWORD" >
          <label> OLD Password </label>
            <input type="password" name="admin_old_password" class="form-control" placeholder="OLD PASSWORD" >
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