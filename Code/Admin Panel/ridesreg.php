<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>


<!-- Modal -->
<div class="modal fade" id="addride" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Ride</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="ridescode.php" method="POST">
      <div class="modal-body">

      <div class="form-group">
        <label>Driver Mobile No </label>
            <input type="text" name="mobile_no" class="form-control" placeholder="Mobile No" required>
        <label>Start Lat </label>
            <input type="text" name="start_lat" class="form-control" placeholder="Start Lat" required>
        <label>Start Long</label>
            <input type="text" name="start_long" class="form-control" placeholder="Start Long" required>
        <label>End Lat</label>
            <input type="text" name="end_lat" class="form-control" placeholder="End Lat" required>
        <label>End Long</label>
            <input type="text" name="end_long" class="form-control" placeholder="End Long" required>
        <label>Ride Type</label>
            <input type="text" name="ride_type" class="form-control" placeholder="Ride Type" required>
        <label>Start Date </label>
            <input type="date" name="start_date" class="form-control" placeholder="Start Date" required>
        <label>Start Time </label>
            <input type="time" name="start_time" class="form-control" placeholder="Start Time" required>
        <label>End Date </label>
            <input type="date" name="end_date" class="form-control" placeholder="End Date" required>
        <label>End Time </label>
            <input type="time" name="end_time" class="form-control" placeholder="End Time" required>
        <label>Status</label>
            <input type="text" name="status" class="form-control" placeholder="Status" required>
        <label>Comment</label>
            <input type="text" name="comment" class="form-control" placeholder="Comment" required>

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
            $query = "SELECT sr_no, driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment 
            FROM rides 
            WHERE  driver_mobile_no = '$search%'";
            
            $query_run = mysqli_query($connection, $query);
        }
        else{

            $searchif = false;

            $query = "SELECT sr_no, driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment 
            FROM rides";

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
                  $sql = "SELECT sr_no, driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment 
                  FROM rides 
                  WHERE  driver_mobile_no LIKE '$search%'";
            }
            else{
              $searchif = false;
                  $sql = "SELECT sr_no, driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment 
                  FROM rides 
                  LIMIT " . $this_page_first_result . ',' .  $results_per_page;                        
            }
        $result = mysqli_query($connection, $sql);      
        ?>


<!-- Button trigger modal -->
    <div class="card shadow mb-4">
         <div class="card-header py-3">
           <h5 class="modal-title" id="exampleModalLabel"> <a href="ridesreg.php "> Ride </a>
             <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addride">
             Add Ride
            </button>
            <form action="ridesreg.php" method="GET" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small"  name = "search"placeholder="Search BY Driver Mobile No... " aria-label="Search" aria-describedby="basic-addon2">
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
                    echo  ' <a href="ridesreg.php?page=' . $page . '" class="btn btn-success">' . $page . '</a>';         
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
           <?php

              $query = "SELECT sr_no, driver_mobile_no, ride_start_lat, ride_start_long, ride_end_lat, ride_end_long, ride_type, start_date, start_time, end_date, end_time, ride_status, comment FROM rides";
              $query_run = mysqli_query($connection, $query);      
           ?>
        
            <table class="table table-borderee" id="dataTable" width="100" collspacing="0" >
                <thrad>
                    <tr>
                        <th>Id</th>
                        <th>Driver Mobile No</th> 
                        <th>Start Lat </th> 
                        <th>Start Long </th> 
                        <th>End Lat </th> 
                        <th>End Long</th> 
                        <th>Ride Type </th>
                        <th>Start Date</th> 
                        <th>Start Time</th>
                        <th>End Date</th> 
                        <th>End Time</th>
                        <th>Status</th>
                        <th>Comment</th>
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
                        <td><?php echo $row['driver_mobile_no']; ?></td>
                        <td><?php echo $row['ride_start_lat']; ?></td> 
                        <td><?php echo $row['ride_start_long']; ?></td>
                        <td><?php echo $row['ride_end_lat']; ?></td>  
                        <td><?php echo $row['ride_end_long']; ?></td>
                        <td><?php echo $row['ride_type']; ?></td>  
                        <td><?php echo $row['start_date']; ?></td>
                        <td><?php echo $row['start_time']; ?></td>
                        <td><?php echo $row['end_date']; ?></td>
                        <td><?php echo $row['end_time']; ?></td>  
                        <td><?php echo $row['ride_status']; ?></td>
                        <td><?php echo $row['comment']; ?></td>     
                        <td>
                            <form action="ridesedit.php" method="POST">
                                <input type="hidden" name="edit_id" value="<?php echo $row['sr_no']; ?>">
                                <button  type="submit" name="edit_btn" class="btn btn-success"> EDIT </button>
                             </form>
                        </td> 
                        <td>
                            <form action="ridescode.php" method="POST">
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
  