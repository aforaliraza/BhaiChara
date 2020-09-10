<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>


<!-- Modal -->
<div class="modal fade" id="addorgprofile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Organization Profile</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="orgcode.php" method="POST">
      <div class="modal-body">

      <div class="form-group">
          <label> Name </label>
            <input type="text" name="name" class="form-control" placeholder="Name" required>
          <label> Address </label>
            <input type="text" name="address" class="form-control" placeholder="Address" required>
          <label> Group Code </label>
            <input type="text" name="groupcode" class="form-control" placeholder="Group Code" required>
          <label> Longitude </label>
            <input type="text" name="longitude" class="form-control" placeholder="Longitude" required>
          <label> Latitude </label>
            <input type="text" name="latitude" class="form-control" placeholder="Latitude" required>
          <label> Ml </label>
            <input type="text" name="ml" class="form-control" placeholder="Ml" required>
            </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" name="reg_btn" class="btn btn-success">Save changes</button>
      </div>
    </form>
    </div>
  </div>
</div>




<div class="container-fluid">
<?php
            $searchif = true;
           

           // define how many results you want per page
           $results_per_page = 10;
            
            if (isset($_GET['searchbtn'])) {

                $searchif = true;
                $search = $_GET['search'];
                $query = "SELECT sr_no, name, address, group_code, longitude, latitude, ml 
                FROM organizations 
                 WHERE  name LIKE '$search%'OR
                address LIKE '$search%' OR
                group_code LIKE '$search'";
                
                $query_run = mysqli_query($connection, $query);
            }
            else{

                $searchif = false;

                $query = "SELECT sr_no, name, address, group_code, longitude, latitude, ml 
                FROM organizations ";

                $query_run = mysqli_query($connection, $query);
                            
                $number_of_results = mysqli_num_rows($query_run ) ;

                // determine number of total pages available
                $number_of_pages = ceil($number_of_results/$results_per_page) . ' ';

                // determine which page number visitor is currently on
                if (!isset($_GET['page'])) {

                    $page = 1;
                    } else {

                    $page = $_GET['page'];
                    }

                // determine the sql LIMIT starting number for the results on the displaying page
                 $this_page_first_result = ($page-1)*$results_per_page.' ';
            } 
            
                if($searchif == true){
                    // retrieve selected results from database and display them on page
                     $sql = " SELECT sr_no, name, address, group_code, longitude, latitude, ml 
                     FROM organizations 
                     WHERE  name LIKE '$search%'OR
                     address LIKE '$search%' OR
                     group_code LIKE '$search'";
                }
                else{
                  $searchif = false;
                      $sql = "SELECT sr_no, name, address, group_code, longitude, latitude, ml 
                      FROM organizations LIMIT " . $this_page_first_result . ',' .  $results_per_page;                        
                }
            $result = mysqli_query($connection, $sql);      
           ?>
<!-- Button trigger modal -->
    <div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel"><a href="orgreg.php "> Organization </a>
             <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addorgprofile">
                 Add Organization Profile
            </button>
            <form action="orgreg.php" method="GET" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small"  name = "search"placeholder="Name - Address - Group Code" aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" name = "searchbtn" type="submit">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>
          <?php
                  if($searchif == false){
                      for ($page = 1; $page <= $number_of_pages; $page++) {
                    echo  ' <a href="orgreg.php?page=' . $page . '" class="btn btn-success">' . $page . '</a>';         
                      } 
                  }
                  else{}
                  ?>
            </h5>
         </div>

    <div class="card-body">

        <?php
          if(isset($_SESSION['success']) &&  $_SESSION['success'] != ""){

             echo '<h3> '.$_SESSION['success'].' </h3>';
            unset($_SESSION['success']);
          }

          if(isset($_SESSION['status']) &&  $_SESSION['status'] != ""){

             echo '<h3> '.$_SESSION['status'].' </h3>';
            unset($_SESSION['status']);
           }
 
        
        ?>

        <div class="table-responsive">
        
        
            <table class="table table-borderee" id="dataTable" width="100" collspacing="0" >
                <thrad>
                    <tr>
                        <th>Id</th>
                        <th>Name</th> 
                        <th>Address</th> 
                        <th>Group Code</th> 
                        <th>Longitude</th>
                        <th>Latitude</th> 
                        <th>Ml</th>  
                        <th>Edit</th> 
                        <th>Delete</th> 
                    </tr>   
                </thrad>
                <tbody>
                <?php
                    if(mysqli_num_rows($result) > 0){

                      foreach($result as $row){

                            ?>
                       
                    <tr>
                        <td><?php echo $row['sr_no']; ?></td>
                        <td><?php echo $row['name']; ?></td> 
                        <td><?php echo $row['address']; ?></td> 
                        <td><?php echo $row['group_code']; ?></td> 
                        <td><?php echo $row['longitude']; ?></td>
                        <td><?php echo $row['latitude']; ?></td> 
                        <td><?php echo $row['ml']; ?></td>  
                        <td>
                            <form action="orgedit.php" method="POST">
                                <input type="hidden" name="edit_id" value="<?php echo $row['sr_no']; ?>">
                                <button  type="submit" name="edit_btn" class="btn btn-success"> EDIT </button>
                             </form>
                        </td> 
                        <td>
                            <form action="orgcode.php" method="POST">
                              <input type="hidden" name="delete_id" value="<?php echo $row['sr_no']; ?>">
                              <button  type="submit" name="delete_btn" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i> </button> 
                            </form>
                        </td> 
                    </tr> 
                    <?php
                        }
                     }
                     else{
                         echo "NO Record Found";
                     }
                     ?>  
                </tbody>
            </tbody>

     </div>
    </div>
</div>
</div>


</div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

<?php 
include('includes/script.php');
?>
  