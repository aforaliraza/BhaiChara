<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>


<!-- Modal -->
<div class="modal fade" id="addusergroup" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add User Group</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="usergroupscode.php" method="POST">
      <div class="modal-body">

      <div class="form-group">
          <label> Mobile No </label>
            <input type="text" name="mobile_no" class="form-control" placeholder="Mobile No" required>
          <label> Group Code </label>
            <input type="text" name="group_code" class="form-control" placeholder="Group Code" required>
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
                $query = "SELECT sr_no, mobile_no, group_code 
                FROM user_groups 
                WHERE  mobile_no LIKE '$search%' OR
                     group_code LIKE '$search'";
                
                $query_run = mysqli_query($connection, $query);
            }
            else{

                $searchif = false;

                $query = "SELECT sr_no, mobile_no, group_code FROM user_groups";

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
                     $sql = " SELECT sr_no, mobile_no, group_code 
                     FROM user_groups 
                     WHERE  mobile_no LIKE '$search%' OR group_code LIKE '$search'";
                }
                else{
                  $searchif = false;
                      $sql = " SELECT sr_no, mobile_no, group_code 
                      FROM user_groups LIMIT " . $this_page_first_result . ',' .  $results_per_page;                        
                }
            $result = mysqli_query($connection, $sql);      
           ?>
<!-- Button trigger modal -->
    <div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel"> <a href="usergroupsreg.php "> User Group </a>
             <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addusergroup">
                 Add User Group 
            </button>
            <form action="usergroupsreg.php" method="GET" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small"  name = "search"placeholder="Mobile No - Gorup Code" aria-label="Search" aria-describedby="basic-addon2">
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
                    echo  ' <a href="usergroupsreg.php?page=' . $page . '" class="btn btn-success">' . $page . '</a>';         
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
                        <th>Mobile No</th> 
                        <th>Group Code </th> 
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
                        <td><?php echo $row['mobile_no']; ?></td>
                        <td><?php echo $row['group_code']; ?></td>   
                        <td>
                            <form action="usergroupedit.php" method="POST">
                                <input type="hidden" name="edit_id" value="<?php echo $row['sr_no']; ?>">
                                <button  type="submit" name="edit_btn" class="btn btn-success"> EDIT </button>
                             </form>
                        </td> 
                        <td>
                            <form action="usergroupscode.php" method="POST">
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
  