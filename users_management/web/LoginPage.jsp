<%--
  Created by IntelliJ IDEA.
  User: Shoham and Sveta
  Date: 2/24/2020
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1255">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>

    <body>
        <div id="wrapper">
            <div class="title">
                <h1>מסגור נשים בעיתונות</h1>
                <h2>הרשמה</h2>
            </div>

            <div class="inputCards">
                <div class="login">
                    <div class="card bg-light mb-3" style="max-width: 18rem;">
                        <div class="card-header">התחברות</div>
                        <div class="card-body">
                            <form action="LoginServlet" method="post">

                                <label>:שם משתמש
                                    <input type="text" name="username"/>
                                </label><br>

                                <label>סיסמה:
                                    <input autocomplete="false" type="password" name="password"/>
                                </label><br>

                                <input type="submit" value="התחברות">

                            </form>
                        </div>
                    </div>
                </div>

                <img src='https://miro.medium.com/max/1200/1*iwfaO6Z-zKUp1GkCfwlWKA.jpeg' alt="Logo"  class="imgLogin"/>

                <div class="registration">
                    <div class="card bg-light mb-3 " style="max-width: 18rem;">
                        <div class="card-header">רישום</div>
                        <div class="card-body">
                            <form action="RegisterServlet" method="post">
                                <label>:שם משתמש
                                    <input type="text" name="username"/>
                                </label><br>

                                <label>:סיסמה
                                    <input autocomplete="false" type="password" name="password"/>
                                </label><br>

                                <label>:שם פרטי
                                    <input type="text" name="FirstName"/>
                                </label><br>

                                <label>:שם משפחה
                                    <input type="text" name="LastName"/>
                                </label><br>

                                <input type="submit" value="הרשמה">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>