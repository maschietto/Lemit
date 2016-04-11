<%@page import="model.DobavljacDetalji"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Dobavljaci Lista</title>
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
               List<DobavljacDetalji> listaDobavljaca = (List<DobavljacDetalji>) request.getAttribute("listaDobavljaca");
                
               %> 
        
    <main class="main_container">
            <div class="container">
                <div class="row boxer">
                    <div class="col-sm-4">
                        <h1>Lista Dobavljaca</h1>
     
                        
                      <form method="POST" action="CommandServlet?cmd=pronadjidobavljaca">
                          
                         
                            <div class="form-group">
                                <input type="text" name="demo_ime" placeholder="Type here" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                      </form>
                    </div>
                </div>
                <hr>
                <h2>Dobavljaci</h2>  
                <table class="table table-bordered">
                    
                 
            <%
            if (listaDobavljaca != null && !listaDobavljaca.isEmpty()) { %>
                    <thead>
                        <tr>
                            <th style="text-align: center">*</th>
                            <th>Naziv Dobavljaca</th>
                            <th>Naziv Mesta</th>
                            <th>Naziv Ulice</th>
                            <th>Broj</th>
                            
                        </tr>
                           <% for (DobavljacDetalji lis : listaDobavljaca) { %>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 
                        <a href="CommandServlet?cmd=dobavljacmain&id=<%=lis.getDobavljacId()%>">
                            <input type="button" class="btn btn-group" value="*" /></a>
                        </td>
                            <td><%=lis.getDobavljacNaziv()%></td>
                            <td><%=lis.getNazivMesta()%></td>
                            <td><%=lis.getUlicaNova()%></td>
                            <td><%=lis.getBrojZgrade()%></td>
                            
                    
                        </tr>
                             <% } %>
              <% } else {%>
              <h4>Nema podataka za prikaz</h4>
            <% } %>
                    </tbody>
                </table>
     
                        
            </div>
        </main>
    
     
   
    </body>
</html>
