<!-- login page for user  -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
 
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    
  <meta name="google-signin-client_id" content="742243318517-6trvbgpn8ao7db4qitt17ff4nfvqkkr1.apps.googleusercontent.com">
  <!--line 13 script is for gmailLogin  -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/libs/css/style.css">
    <link rel="stylesheet" href="assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
  <script>
  
  $(function () {
	     
	    if (localStorage.chkbox && localStorage.chkbox != '') {
	        $('#rememberChkBox').attr('checked', 'checked');
	        $('#signinId').val(localStorage.username);
	        $('#signinPwd').val(localStorage.pass);
	    } else {
	        $('#rememberChkBox').removeAttr('checked');
	        $('#signinId').val('');
	        $('#signinPwd').val('');
	    }
	    
	    $('#rememberChkBox').click(function () {

	        if ($('#rememberChkBox').is(':checked')) {
	            // save username and password
	           localStorage.username = $('#signinId').val();
	            localStorage.pass = $('#signinPwd').val();
	            localStorage.chkbox = $('#rememberChkBox').val();
	        } else {
	            localStorage.usrname = '';
	            localStorage.pass = '';
	            localStorage.chkbox = '';
	        }
	    });
	});

  </script>
    <style>
    html,
    body {
        height: 100%;
    }

    body {
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align: center;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
    }
    .validate{
    color:red;
    }
    </style>
    
</head>

<body>
    <!-- ============================================================== -->
    <!-- login page  -->
    <!-- ============================================================== -->
    <div class="splash-container">
        <div class="card ">
            <div class="card-header text-center"><a href="#"><img class="logo-img" src="assets/images/logo.png" alt="logo"></a><span class="splash-description">Please enter your user information.</span></div>
            <div class="col-md-12 text-center">  
                                         
                         </div>                  
            <div class="card-body">
              
                 <form:form action="superAdminloginProcess" method="post" modelAttribute="super">
                    <div class="form-group">
                    <form:input type="text" class="form-control form-control-lg" id="signinId" placeholder="Username" name="email" path="name"/>
                    <span id="nameemail" class="validate"></span>
                    </div>
                    <div class="form-group">
                         <form:input type="password" class="form-control form-control-lg" id="signinPwd" placeholder="Password" name="password" path="password"/>
                     <span id="namepassword" class="validate"></span>
                   
                    </div>
                    <div class="form-group">
                        <label class="custom-control custom-checkbox">
                            <input class="custom-control-input" type="checkbox" id="rememberChkBox"><span class="custom-control-label">Remember Me</span>
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>       
<div id="status">
</div>
                </form:form>
            </div>
            <div class="card-footer bg-white p-0  ">
                <div class="card-footer-item card-footer-item-bordered">
                    <a href="register" class="footer-link">Create An Account</a></div>
                    
            </div>
        </div>
    </div>
    <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    
    
    
    
    
 
    
     
</body>
 
</html>