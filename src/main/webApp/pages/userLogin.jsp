
<!-- Registration page for user -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- Required meta tags -->
   
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Concept - Bootstrap 4 Admin Dashboard Template</title>
    <!-- Bootstrap CSS -->
    
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
       <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular.min.js"></script>
    <link rel="stylesheet" href="assets/libs/css/style.css">
    <link rel="stylesheet" href="assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
  
    
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
    <form:form action="userLogin" method="post" modelAttribute="user" name="registration" class="splash-container">
       <div class="card">
            <div class="card-header">
                <h3 class="mb-1">Registrations Form</h3>
                <p>Please enter your user information.</p>
            </div>
            <div class="card-body">
               
                <div class="form-group">
                    <form:input type="text" path="email" name="email" class="form-control form-control-lg" placeholder="Enter email"/>
               <span id="nameemail" class="validate"></span>
                </div>
                <div class="form-group">
               <form:input type="password" name="password" path="password" class="form-control form-control-lg" placeholder="Enter Password"/>
                 <span id="namepassword" class="validate"></span>
                </div>
                <div class="form-group pt-2">
                    <button class="btn btn-block btn-primary" type="submit">Sign in</button>
                </div>
               
            <div class="card-footer bg-white">
                <p>Already member? <a href="register" class="text-secondary">Register Here.</a></p>
            </div>
       
       </div>
       </div>

    </form:form>
</body>

 
</html>