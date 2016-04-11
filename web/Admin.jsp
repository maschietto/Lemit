<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>ADMIN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/main.js"></script>
    </head>
    <body>
        <%@include file="jspf/header_admin.jspf"%>
        <%@include file="jspf/poruka.jspf"%>
        <%@include file="jspf/footer.jspf"%>
        
           <% Korisnik logged = (Korisnik) session.getAttribute("korisnik");
               List<Korisnik> korisnici = (List<Korisnik>) request.getAttribute("lista");
               if(logged.getIsAdmin().equals("TRUE")) { 
               %> 
    
        <main class="main_container">
            <div class="container">
                <div class="row boxer">
                    <div class="col-sm-4">
                        <h1>ADMINISTRATORSKA STRANA</h1>
                     
                      <form method="POST" action="CommandServlet?cmd=pronadjikorisnika">
                            <div class="form-group">
                                <input type="text" name="demo_ime" placeholder="Type here" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                      </form>
                    </div>
                </div>
                <hr>
                <h2>KORISNICI</h2>  
                <table class="table table-bordered">
                    
                     <% } 
            
            if (korisnici != null && !korisnici.isEmpty()) { %>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Naziv</th>
                            <th>Jmbg</th>
                            <th>brojTelefona</th>
                            <th>Aktivan</th>
                        </tr>
                           <% for (Korisnik kor : korisnici) { %>
                    </thead>
                    <tbody>
                        <tr>
                            <td>  <% if(logged.getIsAdmin().equals("TRUE")) { %>
                        <a href="CommandServlet?cmd=editkorisnik&id=<%=kor.getId_korisnika()%>">
                        <input type="button" class="btn btn-group" value="<%=korisnici.indexOf(kor) + 1 %>"/>
                        <% } %></td>
                            <td><%=kor.getNazivKorisnika()%></td>
                            <td><%=kor.getJmbg()%></td>
                            <td><%=kor.getKontaktTelefon()%></td>
                            <td><% if(logged.getIsAdmin().equals("TRUE")) { 
                        if(!kor.isAktivan().equals("TRUE")) { %>
                    <a href="CommandServlet?cmd=aktivirajkorisnika&id=<%=kor.getId_korisnika()%>" style="text-decoration:none">
                    <input type="button" class="btn btn-group" value="aktiviraj"/>
                    </a>
                        <% } else { %>
                    <a href="CommandServlet?cmd=deaktivirajkorisnika&id=<%=kor.getId_korisnika()%>" style="text-decoration:none">
                       <input type="button" class="btn btn-group" value="obriši"/>
                    </a>
                        <% } 
                    }%></td>
                        </tr>
                             <% } %>
              <% } else {%>
              <h4>Nema podataka za prikaz</h4>
            <% } %>
                    </tbody>
                </table>
            <%--   <ul class="pagination">
                    <li><a href="#">Previous</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">Next</a></li>
            </ul>--%>
            </div>
        </main>
        <footer class="footer navbar-fixed-bottom">
            <div class="container">
                <div>Copyright &copy 2015</div>
                <div>Ninoslav Bozilovic</div>
            </div>
        </footer>
    </body>
</html>
