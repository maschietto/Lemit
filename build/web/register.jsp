<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Registracija</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/main.js"></script>
    </head>
    <body>
        <%@include file="jspf/poruka.jspf"%>
        
        <div class="container">
            <div class="my_user_form">
                <h2>Registration</h2>
                <form role="form" method="POST" action="CommandServlet?cmd=korisnik" id="signup">
                   
                    <%  Korisnik kor = (Korisnik) request.getAttribute("editable");
                    boolean registration = (kor == null);
                    if (!registration) {%>
                    <input type="hidden" name="id" value="<%=kor.getId_korisnika()%>">
                    <% }%>
                    
                    <div class="form-group">
                        <label for="uname">Ime:</label>
                        <input type="text" name="naziv" class="form-control" value="<%=kor == null ? "" : kor.getNazivKorisnika()%>" placeholder="Unesite ime" required>
                    </div>
                    <div class="form-group">
                        <label for="jmbg">Jmbg:</label>
                        <input type="text" name="jmbg" class="form-control" value="<%=kor == null ? "" : kor.getJmbg()%>" placeholder="Unesite jmbg" required>
                    </div>
                    <div class="form-group">
                        <label for="kontakt_telefon">Kontakt telefon:</label>
                        <input type="text" name="tel" class="form-control" value="<%=kor == null ? "" : kor.getKontaktTelefon()%>" placeholder="Unesite kontakt telefon" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Sifra:</label>
                        <input type="password"  name="pwd" class="form-control" id="password" placeholder="Unesite sifru" required>
                    </div>
                  
                    <div class="form-group">
                        <label for="pwd">Potvrdi Sifru:</label>
                        <input type="password" name="con_pwd" class="form-control" id="pwd" placeholder="Enter password" required>
                    </div>
                    <%--  <div class="form-group">
                        <label for="utype">User Type:</label>
                        <select class="form-control" id="sel1" required>
                            <option value="">Choose type</option>
                            <option value="admin">Administrator</option>
                            <option value="guest">Guest</option>
                        </select>
                    </div>
                    --%>
                    <table>
                        <th>  
                        <tr>
                      <div class="form-group"><input type="submit" value="Sačuvaj" class="btn btn-default"> </div>
                    </tr>
                    <tr>
                     <%if(registration) { %>
                        <a style="text-decoration: none" href="<%=request.getContextPath()%>/index.jsp">
                        <input type="button" value="Početna strana" class="btn btn-default"></a>
                        <% } %>
                    </tr>
                    </th>
                    </table>
                        
                        <%--<div class="reg_anchor">
                        <a href="login.html">Login</a>
                    </div> --%>
                    <%--<div class="checkbox rememberme">
                        <label><input type="checkbox"> Remember me</label>
                    </div>--%>
                    
                    
                    
                </form>
            </div>
        </div>
    </body>
</html>
