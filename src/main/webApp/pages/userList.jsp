    <%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<!doctype html>
<html lang="en"> 
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Booking</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/libs/css/style.css">
    <link rel="stylesheet" href="assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="assets/vendor/datepicker/tempusdominus-bootstrap-4.css" /> 
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     
<script type="text/javascript">

 function jsFunction(value)
 {
	
     console.log("js function");
     console.log("Check if..."+value=="English");
     if(value=="English")
         
         {
         window.location.href="userList";
        
         }else
             
             {
        	 window.location.href="languageList";
             }
         
 }

 </script>
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

<style>
.goog-te-gadget-simple {
border:0px;
}
</style>
 
</head>

<body>


  <div class="dashboard-main-wrapper">
  
  
  
  <div class="dashboard-header">
            <nav class="navbar navbar-expand-lg bg-white fixed-top">
            <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">
                <a class="navbar-brand" href="#">${lang.booking}</a></c:forEach></c:when>
                <c:otherwise>
                <a class="navbar-brand" href="#">Booking</a>
                </c:otherwise>
                </c:choose>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto navbar-right-top">
                        <li class="nav-item">
                             <div id="custom-search" class="top-search-bar">
                                <!-- <input class="form-control" type="text" placeholder="Search.."> -->
                              <form action="multiSearch" method="post">
                             <div class="form-inline">
                              <input class="form-control" type="text" placeholder="Search.." name="filter">
                              <button style="display:none;"><i class="fa fa-search"></i></button>
                              </div>

                             </form>
                            </div>
                        </li>
                         
                        <li>
                          <div id="custom-search" class="top-search-bar">
                                <!-- <input class="form-control" type="text" placeholder="Search.."> -->
                              <form>
                             <div class="form-inline form-group">
                               <select name="lang" class="form-control" onmousedown="this.value='';"  onchange="jsFunction(this.value);">
      <c:choose><c:when test="${not empty sessionScope.lang}">
      <option value="Bengali">Bengali</option><option value="English">English</option>
   </c:when>
   <c:otherwise><option value="English">English</option><option value="Bengali">Bengali</option></c:otherwise></c:choose>
   </select>
 
 
                              </div>

                             </form>
                            </div>
                        </li>
                        
                        <li class="nav-item dropdown nav-user">
                            <a class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="assets/images/avatar-1.jpg" alt="" class="user-avatar-md rounded-circle"></a>
                            <div class="dropdown-menu dropdown-menu-right nav-user-dropdown" aria-labelledby="navbarDropdownMenuLink2">
                                <div class="nav-user-info">
                                    <h5 class="mb-0 text-white nav-user-name">${sessionScope.aemail}</h5>
                                    <h5 class="mb-0 text-white nav-user-name">${sessionScope.email}</h5>
                                    <span class="status"></span><span class="ml-2">Available</span>
                                     
                                </div>
                                <c:choose>
                             <c:when test="${not empty sessionScope.email}">
                                <a class="dropdown-item" href="<c:url value='/createPdf'/>"><i class="fa fa-file-pdf-o mr-2" style="color:red;"></i>PDF</a>
                                 <a class="dropdown-item" href="<c:url value='/createCsv'/>"><i class="fa fa-file-excel-o mr-2" style="color:blue;"></i>EXCEL</a>
                                 <a class="dropdown-item" href='<c:url value="/logoutForSuperAdmin"></c:url>'><i class="fas fa-power-off mr-2"></i>Logout</a></c:when>
                                 <c:otherwise>
                                  <a class="dropdown-item" href='<c:url value="/logoutForAdmin"></c:url>'><i class="fas fa-power-off mr-2"></i>Logout</a>
                                 </c:otherwise>
                                 </c:choose>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
     <div class="nav-left-sidebar sidebar-dark">
            <div class="menu-list">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="d-xl-none d-lg-none" href="#">Dashboard</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav flex-column">
                            <li class="nav-divider">
                                Menu
                            </li>
                           <li class="nav-item ">
                                <a class="nav-link" href="<c:url value='/userList'/>" ><i class="fa fa-fw fa-user-circle"></i>
                                <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">${lang.dashboard}</c:forEach></c:when><c:otherwise>Dashboard</c:otherwise></c:choose>
                                
                                </a>
                                
                            </li>
                        
                            
                            <li class="nav-item ">
                                <a class="nav-link "  href="<c:url value='/adminPage'/>"><i class="fab fa-fw fa-wpforms"></i>
                                
                                 <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">${lang.createAdmin}</c:forEach></c:when><c:otherwise>Create Admin</c:otherwise></c:choose>
                                </a>
                              
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="<c:url value='/adminList'/>"><i class="fas fa-fw fa-table"></i>
                                 
                                 <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">${lang.admin_List}</c:forEach></c:when><c:otherwise>Admin List</c:otherwise></c:choose>
                                </a>
                             
                            </li>
                          
                           
                           
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
  
  <div class="dashboard-wrapper">
            <div class="container-fluid  dashboard-content">
                <!-- ============================================================== -->
                <!-- pageheader -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title"> <c:forEach items="${sessionScope.lang}" var="lang">
                <a class="navbar-brand" href="#">${lang.booking}</a></c:forEach> </h2>
                          
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">
                                        
                                         <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">${lang.dashboard}</c:forEach></c:when><c:otherwise>Dashboard</c:otherwise></c:choose>
                                        
                                        </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">
                                        
                                         <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">${lang.booking}</c:forEach></c:when><c:otherwise>Roles</c:otherwise></c:choose>
                                        </a></li>
                                        
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
             
             
             <div>
             
                
                         
           
             
        <div class="row">
      
                    <!-- ============================================================== -->
                    <!-- basic table  -->
                    <!-- ============================================================== -->
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="card">
                            <h5 class="card-header">
                             <c:choose>
<c:when test="${not empty sessionScope.lang}">
            <c:forEach items="${sessionScope.lang}" var="lang">${lang.admin_List}</c:forEach></c:when><c:otherwise>User Details</c:otherwise></c:choose>
                            </h5>
                            <div class="card-body">
                                <div class="table-responsive">
                                
                                    <table class="table table-striped table-bordered first">
                                     

<c:choose>
<c:when test="${not empty sessionScope.lang}">
<c:forEach items="${sessionScope.lang}" var="lang">
<tr>
<th>${lang.mr_mS}</th>
<th>${lang.surname}</th>
<th>${lang.email}</th>
<th>${lang.password}</th>
<th>${lang.datecreated}</th>
<th>${lang.dateUpdated}</th>
<th>${lang.gender}</th>
<th>${lang.address}</th>
<th>${lang.phone}</th>
<th colspan='3'>${lang.settings}</th>
</tr>
<c:remove var="lang" scope="session" />
</c:forEach>

</c:when>
<c:otherwise>
<th>MR/MRS</th>
<th>Surname</th>
<th>Email</th>
<th>Gender</th>
<th>Password</th>
<th>DateCreated</th>
<th>DateUpdated</th>
<th>Address</th>
<th>Phone</th>
<th colspan='3'>Settings</th>
</c:otherwise>
</c:choose>
  

<c:forEach items="${user}" var="user">
<tr>
<td>${user.fname}</td>
<td>${user.lname}</td>
<td>${user.email}</td>
<td>${user.gender}</td>
<td>${user.password}</td>
<td>${user.dateCreated}</td>
<td>${user.dateUpdated}</td>
<td>${user.phone}</td>
<td>${user.address}</td>
<td><a href="<c:url value='/editUser/${user.userId}'/>" style="color:green;">Edit</a></td>
<td><a href="<c:url value='deleteUser/${user.userId}'/>" style="color:red;">Delete</a></td>

</tr>
</c:forEach>
<tr>
</table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end basic table  -->
                    <!-- ============================================================== -->
                </div>
             
             
             
             </div>
             
             
                    
  </div>
   <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="assets/vendor/slimscroll/jquery.slimscroll.js"></script>
    <script src="assets/vendor/parsley/parsley.js"></script>
     <script src="assets/vendor/datepicker/moment.js"></script>
    <script src="assets/vendor/datepicker/tempusdominus-bootstrap-4.js"></script>
    <script src="assets/vendor/datepicker/datepicker.js"></script>
    <script src="assets/libs/js/main-js.js"></script>
    <script>
    $('#form').parsley();
    </script>
    
    
    <script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
    </script>
</body>
 
</html>
