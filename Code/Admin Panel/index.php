<?php 
include('security.php');
include('includes/header.php');
include('includes/navbar.php');
include('includes/toolbar.php');
?>
   
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          </div>

          <!-- Content Row -->
          <!-- Row 1-->
          <div class="row">

            <!-- TOTAL Registered Admins -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">TOTAL Registered Admins</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                      
                        <?php
                           
                            $query = "SELECT sr_no FROM admin ORDER BY sr_no";
                            $query_run = mysqli_query($connection, $query);    
                            
                            $rows = mysqli_num_rows($query_run);

                            echo '<h1>' .$rows. '</h1>';
                        ?> 
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- TOTAL Registered Organizations -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">TOTAL Registered Organizations</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                        <?php
                            
                            $query = "SELECT sr_no FROM organizations ORDER BY sr_no";
                            $query_run = mysqli_query($connection, $query);    
                            
                            $rows = mysqli_num_rows($query_run);

                            echo '<h1>' .$rows. '</h1>';
                        ?> 
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- TOTAL Registered Users -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">TOTAL Registered Users</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                              <?php
                            
                            $query = "SELECT sr_no FROM users ORDER BY sr_no";
                            $query_run = mysqli_query($connection, $query);    
                            
                            $rows = mysqli_num_rows($query_run);

                            echo '<h1>' .$rows. '</h1>';
                            ?> 
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- TOTAL Registered Users Group -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">TOTAL Registered Users Group</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">                 
                        <?php
                              
                              $query = "SELECT sr_no FROM user_groups ORDER BY sr_no";
                              $query_run = mysqli_query($connection, $query);    
                              
                              $rows = mysqli_num_rows($query_run);

                              echo '<h1>' .$rows. '</h1>';
                              ?> 
                      </div>
                    </div>                    
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Row 2-->
          <div class="row">

            <!-- TOTAL Registered Drivers -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">TOTAL Registered Drivers</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">                 
                        <?php
                              
                              $query = "SELECT sr_no FROM drivers ORDER BY sr_no";
                              $query_run = mysqli_query($connection, $query);    
                              
                              $rows = mysqli_num_rows($query_run);

                              echo '<h1>' .$rows. '</h1>';
                              ?> 
                      </div>
                    </div>                    
                  </div>
                </div>
              </div>
            </div>

             <!-- TOTAL Registered Locations -->
             <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">TOTAL Registered Locations</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                              <?php
                            
                            $query = "SELECT sr_no FROM locations ORDER BY sr_no";
                            $query_run = mysqli_query($connection, $query);    
                            
                            $rows = mysqli_num_rows($query_run);

                            echo '<h1>' .$rows. '</h1>';
                            ?> 
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- TOTAL Registered Vehical Details -->
            <div class="col-xl-3 col-md-6 mb-4">
                          <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                              <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                  <div class="text-xs font-weight-bold text-success text-uppercase mb-1">TOTAL Registered Vehical Details</div>
                                  <div class="h5 mb-0 font-weight-bold text-gray-800">
                                    <?php
                                        
                                        $query = "SELECT sr_no FROM vehicle_details ORDER BY sr_no";
                                        $query_run = mysqli_query($connection, $query);    
                                        
                                        $rows = mysqli_num_rows($query_run);

                                        echo '<h1>' .$rows. '</h1>';
                                    ?> 
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>

            <!-- TOTAL Registered Rides-->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">TOTAL Registered Rides</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                      
                        <?php
                           
                            $query = "SELECT sr_no FROM rides ORDER BY sr_no";
                            $query_run = mysqli_query($connection, $query);    
                            
                            $rows = mysqli_num_rows($query_run);

                            echo '<h1>' .$rows. '</h1>';
                        ?> 
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>

          <!-- Row 3-->
          <div class="row">
            <!-- TOTAL Registered Rides-->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">TOTAL Registered Inter City Rides</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">
                      
                        <?php
                           
                            $query = "SELECT sr_no FROM inter_city_rides ORDER BY sr_no";
                            $query_run = mysqli_query($connection, $query);    
                            
                            $rows = mysqli_num_rows($query_run);

                            echo '<h1>' .$rows. '</h1>';
                        ?> 
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- TOTAL Registered Drivers -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">TOTAL Registered Transaction History </div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">                 
                        <?php
                              
                              $query = "SELECT sr_no FROM transaction_history ORDER BY sr_no";
                              $query_run = mysqli_query($connection, $query);    
                              
                              $rows = mysqli_num_rows($query_run);

                              echo '<h1>' .$rows. '</h1>';
                              ?> 
                      </div>
                    </div>                  
                  </div>
                </div>
              </div>
            </div>

             <!-- TOTAL Registered Locations -->
             <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">TOTAL Transaction</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                              <?php
                            
                            $query = "SELECT SUM(amount) AS value_sum FROM transaction_history";
                            $query_run = mysqli_query($connection, $query);  
                            $row = mysqli_fetch_assoc($query_run); 
                            $sum = $row['value_sum'];

                            echo '<h1>' .'Rs '.$sum. '</h1>';
                            ?> 
                          </div>
                        </div>
                      </div>
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
include('includes/footer.php');
?>
  
