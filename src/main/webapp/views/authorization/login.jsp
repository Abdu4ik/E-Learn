<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abdullo
  Date: 2/10/23
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="/resources/css/main.css">

    <meta charset="UTF-8">
    <title> Title</title>
</head>
<c:set var="in" value="in"/>
<c:set var="up" value="up"/>
<body>
    <c:set var = "pass" scope = "session" value = "Password"/>
    <c:set var = "too_many" scope = "session" value = "Username"/>
    <c:set var = "u_pass" scope = "session" value = "Password"/>

    <c:set var="pass_err" value="${(pass_conf_err != null)? pass_conf_err : pass}"/>
    <c:set var="too_many_fields_error" value="${(too_many_fields_error != null)? too_many_fields_error : too_many}"/>
    <c:set var="username_pass_error" value="${(username_pass_error != null)? username_pass_error : u_pass}"/>
<form method="post">
    <div id="container" class="container">
        <!-- FORM SECTION -->
        <div class="row">
            <!-- SIGN UP -->
            <div class="col align-items-center flex-col sign-up">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-up">
                        <div class="input-group">
                            <i class='bx bxs-user'></i>
                            <input type="text" name="username" placeholder="Username">
                        </div>
                        <div class="input-group">
                            <i class='bx bx-mail-send'></i>
                            <input type="email" name="email" placeholder="Email">
                        </div>
                        <div class="input-group">
                            <i class='bx bxs-lock-alt'></i>
                            <input type="password" name="password" placeholder="${pass_err}">
                        </div>
                        <div class="input-group">
                            <i class='bx bxs-lock-alt'></i>
                                <input type="password" name="confirm_password" placeholder="${pass_err}">
                        </div>
                        <div>
                            <button type="submit">
                                Sign up
                            </button>
                        </div>
                        <p>
							<span>
								Already have an account?
							</span>
                            <b onclick="toggle()" class="pointer">
                                Sign in here
                            </b>
                        </p>
                    </div>
                </div>

            </div>
            <!-- END SIGN UP -->
            <!-- SIGN IN -->
            <div class="col align-items-center flex-col sign-in">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-in">

                        <div class="input-group">
                            <i class='bx bxs-user'></i>
                            <input type="text" name="in_username" placeholder="${too_many}">
                        </div>


                        <div class="input-group">
                            <i class='bx bxs-lock-alt'></i>
                            <input type="password" name="in_password" placeholder="${username_pass_error}">
                        </div>


                        <div class="switch d-flex justify-content-end">
                            <div class="primary-switch">
                                <h6 class="text-left" style="color: black">Remember me</h6>
                                <input  type="checkbox" id="primary-switch" checked name="remember">
                                <label for="primary-switch"></label>
                            </div>
                        </div>


                        <div>
                            <button type="submit">
                                Sign in
                            </button>
                        </div>
                        <p>
							<span>
								Don't have an account?
							</span>
                            <b onclick="toggle()" class="pointer">
                                Sign up here
                            </b>
                        </p>
                    </div>

                </div>
                <div class="form-wrapper">

                </div>
            </div>
            <!-- END SIGN IN -->
        </div>
        <!-- END FORM SECTION -->
        <!-- CONTENT SECTION -->
        <div class="row content-row">
            <!-- SIGN IN CONTENT -->
            <div class="col align-items-center flex-col">
                <div class="text sign-in">
                    <h2 style="color: white">
                        Welcome!
                    </h2>

                </div>
                <div class="img sign-in">

                </div>
            </div>
            <!-- END SIGN IN CONTENT -->
            <!-- SIGN UP CONTENT -->
            <div class="col align-items-center flex-col">
                <div class="img sign-up">

                </div>
                <div class="text sign-up">
                    <h2 style="color: white">
                        Join with us
                    </h2>

                </div>
            </div>
            <!-- END SIGN UP CONTENT -->
        </div>
        <!-- END CONTENT SECTION -->
    </div>
</form>


<script src="/resources/js/login.js"></script>

</body>
</html>
