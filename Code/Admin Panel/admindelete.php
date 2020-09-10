<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>



<!-- Edit Modal -->

<div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel">Delete Admin Profile </h5>
         </div>

    <div class="card-body">

     <?php
                if(isset($_POST['delete_btn'])){
                    $email_id = $_POST['email_id'];

            ?>  

     <form action = "admincode.php" method = "POST">
       
        <div
         class="form-group">               
         <input type="hidden" name="delete_email" value="<?php echo $email_id;?>" class="form-control"  placeholder="email_id" >
            <input type="text" value="<?php echo $email_id;?>" class="form-control"  placeholder="email_id" disabled>
            <label> </label>
            <input type="password" name="delete_password" class="form-control" placeholder="PASSWORD">
        </div>
               
          <button type="submit" name="edit_cancel_btn" class="btn btn-secondary">CANCEL</button>
          <button type="submit" name="delete_btn" class="btn btn-success" >Confirm</button>
        

        </form>

        <?php
                }

            ?>
        </div>
      </div> 

<?php 
    
include('includes/script.php');
?>