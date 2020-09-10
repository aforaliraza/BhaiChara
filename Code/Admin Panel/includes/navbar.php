 <!-- Sidebar -->
 <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

<!-- Sidebar - Brand -->
<a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.php">
  <div class="sidebar-brand-icon rotate-n-15">
    <!--Lodgo add        <i class="fas fa-laugh-wink"></i> -->
  </div>
  <div class="sidebar-brand-text mx-3">Ride Share</div>
</a>

<!-- Divider -->
<hr class="sidebar-divider my-0">

<!-- Nav Item - Dashboard -->
<li class="nav-item active">
  <a class="nav-link" href="index.php">
    <i class="fas fa-fw fa-tachometer-alt"></i>
    <span>Dashboard</span></a>
</li>

<!-- Divider -->
<hr class="sidebar-divider">

<!-- Heading -->
<div class="sidebar-heading">
  Interface
</div>

<!-- Nav Item - Pages Collapse Menu -->
<li class="nav-item">
  <a class="nav-link collapsed" href="adminreg.php" >
    <i class="fas fa-user-secret"></i>
    <span>Admin</span>
  </a>
  <a class="nav-link collapsed" href="userreg.php" >
    <i class="fas fa-user"></i>
    <span>User</span>
  </a>
  <a class="nav-link collapsed" href="orgreg.php" >
    <i class="fas fa-fw fa-building"></i>
    <span>Organization</span>
  </a>

  <a class="nav-link collapsed" href="usergroupsreg.php" >
    <i class="fas fa-fw fa-users"></i>
    <span>User Group</span>
  </a>

  <a class="nav-link collapsed" href="driversreg.php" >
    <i class="fas fa-fw fa-taxi"></i>
    <span>Driver</span>
  </a>

  <a class="nav-link collapsed" href="locationsreg.php" >
    <i class="fas fa-fw fa-map-marker"></i>
    <span>Location</span>
  </a>

  <a class="nav-link collapsed" href="vehicledetailsreg.php" >
    <i class="fas fa-fw fa-car"></i>
    <span>Vehicle Detail</span>
  </a>

  <a class="nav-link collapsed" href="ridesreg.php" >
    <i class="fas fa-fw fa-handshake"></i>
    <span>Ride</span>
  </a>

  <a class="nav-link collapsed" href="intercityridesreg.php" >
    <i class="fas fa-fw fa-handshake"></i>
    <span>Inter City Ride</span>
  </a>

  <a class="nav-link collapsed" href="transactionhistoryreg.php" >
    <i class="fas fa-fw fa-handshake"></i>
    <span>Transaction History</span>
  </a>
</li>




<!-- Divider -->
<hr class="sidebar-divider d-none d-md-block">

<!-- Sidebar Toggler (Sidebar) -->
<div class="text-center d-none d-md-inline">
  <button class="rounded-circle border-0" id="sidebarToggle"></button>
</div>

</ul>
<!-- End of Sidebar -->

 <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>


<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          
          <form action = "logout.php" method = "POST">

          <button type="submit" name="logout_btn" class="btn btn-success" >Logout</button>

            </form>
        </div>
      </div>
    </div>
  </div>

 