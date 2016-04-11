<%@page import="model.Dobavljac"%>
<%@page import="model.KatalogDetalji"%>
<%@page import="model.ArtikalDetalji"%>
<%@page import="model.DobavljacDetalji"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Artikli Lista</title>
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
                
        
           <% //Korisnik logged = (Korisnik) session.getAttribute("korisnik");
               List<KatalogDetalji> listaKataloga = (List<KatalogDetalji>) request.getAttribute("listakataloga");
               List<Dobavljac> listaDobavljaca = (List<Dobavljac>) request.getAttribute("listadobavljaca");
               %> 
           
         
           
        <main class="main_container">
            <div class="container">
                <div class="row boxer">
                    <div class="col-sm-4">
                        <h1>Lista Kataloga</h1>
                        
                      <form method="POST" action="CommandServlet?cmd=pronadjikatalog">
                          
                            
                          
                          <div class="form-group">
                     <select class="form-control" name="demo_ime">
                    
                           <%
                             for(Dobavljac lis: listaDobavljaca){
                             String Field = lis.getNazivDobavljaca();
                                     %>
                    <option value="<%=Field %>" style="text-align: center"><%=Field %></option>
                            
                       <%}%>
                             
                      </select>
                      </div>
                          
                          <button type="submit" class="btn btn-default">Search</button>
                      </form>
                    </div>
                </div>
                <hr>
                <h2>Katalozi</h2>  
                <table class="table table-bordered">
                    
                 
            <%
            if (listaKataloga != null && !listaKataloga.isEmpty()) { %>
                    <thead>
                        <tr>
                            
                            <th>Redni Broj</th>
                            <th>Naziv Kataloga</th>
                            <th>Naziv Dobavljaca</th>
                            <th>Obrisi</th>
                            
                            
                        </tr>
                           <% for (KatalogDetalji lis : listaKataloga) { %>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 
                        <a href="CommandServlet?cmd=katalogmain&id=<%=lis.getRedniBrojKataloga()%>">
                            <input type="button" class="btn btn-group" value="<%=lis.getRedniBrojKataloga()%>"/></a>
                        </td>
                            <td><%=lis.getNazivKataloga()%></td>
                  
                            <td><%=lis.getNazivDobavljaca() %></td>
                          
            
                            <td><a href="CommandServlet?cmd=obrisikatalog&id=<%=lis.getRedniBrojKataloga()%>">
                    <input type="button" name="di1" class="btn btn-group" value="obrisi"/>
                                </a></td>
                    
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
