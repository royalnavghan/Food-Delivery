<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Register</title>
<%@ include file="./components/common_cs_js.jsp"%>
</head>
<body>
<%@ include file="./components/navbar.jsp"%>
<div class="container-fluid">
  <div class="row mt-2">
       <div class="col-md-4 offset-md-4 admin" >
            <div class="card">
                <%@ include file="./components/message.jsp"%>
                <div class="card-body px-5">
                    <img src="resources/images/registerphoto.jpeg" class="rounded mx-auto d-block" alt="img" height="90px" width="90px">
                    <h3 class="text-center my-3">Register User</h3>
            <form action="userregister" method="post">
           
           <div class="form-group">
                     <label for="name">First Name</label>
                     <input type="text" class="form-control" id="firstname" aria-describedby="emailHelp" name="firstname" placeholder="Enter first name.." required>
                 </div>
                 
                  <div class="form-group">
                     <label for="name">Last Name</label>
                     <input type="text" class="form-control" id="lastname" aria-describedby="emailHelp" name="lastname" placeholder="Enter last name.." required>
                 </div>
             
                 <!--<div class="form-group">
                     <label for="email">Email</label>
                     <input type="email" class="form-control" id="emailid" aria-describedby="emailHelp" name="emailid" placeholder="Enter email id.." required>
                 </div>-->
                 <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="emailid" aria-describedby="emailHelp" name="emailid" placeholder="Enter email id.." pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" required>
                    <small id="emailHelp" class="form-text text-muted">Please enter a valid email address.</small>
                </div>
           
          
                  <!--<div class="form-group">
                     <label for="email">Mobile</label>
                     <input type="number" class="form-control" id="mobileno" aria-describedby="emailHelp" name="mobileno" placeholder="Enter mobile no.." pattern="\d{10}" required>
                 </div>-->
                 <div class="form-group">
                    <label for="email">Mobile</label>
                    <input type="tel" class="form-control" id="mobileno" aria-describedby="emailHelp" name="mobileno" placeholder="Enter mobile no.." pattern="[0-9]{10}" required>
                    <small id="emailHelp" class="form-text text-muted">Please enter a 10-digit phone number.</small>
                </div>
                 
                  <div class="form-group">
                     <label for="name">Street</label>
                     <input type="text" class="form-control" id="street" aria-describedby="emailHelp" name="street" placeholder="Enter street name.." required>
                 </div>
                 
             
                 <div class="form-group">
                     <label for="name">City</label>
                     <input type="text" class="form-control" id="city" aria-describedby="emailHelp" name="city" placeholder="Enter city.." required>
                 </div>
                 
                 <div class="form-group">
                     <label for="email">Pin code</label>
                     <input type="number" class="form-control" id="pincode" aria-describedby="emailHelp" name="pincode" placeholder="Enter pincode.." required>
                 </div>
              
               
                 <!--<div class="form-group">
                     <label for="password">Password</label>
                     <input type="password" class="form-control" id="password" aria-describedby="emailHelp" name="password" placeholder="Enter password.." required>
                     <small id="passwordHelpInline" class="text-muted">
                        Must be 8-20 characters long.
                     </small>
                 </div>-->
                <!---- <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" aria-describedby="passwordHelpInline" name="password" placeholder="Enter password.." pattern="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=_!])([a-zA-Z0-9@#$%^&+=_!]{8,20})$" required>
                    <small id="passwordHelpInline" class="form-text text-muted">
                        Must be 8-20 characters long and contain at least one letter, one number, and one special character.
                    </small>
                </div>-->
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" aria-describedby="passwordHelpInline" name="password" placeholder="Enter password.." pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!])([a-zA-Z0-9@#$%^&+=_!]{8,20})$" required>
                    <small id="passwordHelpInline" class="form-text text-muted">
                        Must be 8-20 characters long and contain at least one lowercase letter, one uppercase letter, one number, and one special character.
                    </small>
                </div>
             
                <div class="container text-center">
                      <button class="btn custom-bg text-color"><b>Register</b></button>
                 </div>
                 
            </form>
                </div>
            </div>
           
            
       </div>
  </div>
</div>
</body>
</html>