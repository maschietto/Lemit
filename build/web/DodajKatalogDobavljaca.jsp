<%@page import="model.StavkaKataloga"%>
<%@page import="model.ArtikalDetalji"%>
<%@page import="model.DobavljacDetalji"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Dobavljac dodavanje Kataloga</title>
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
                
        
         <% 
            List<ArtikalDetalji>  listaArtikala = (List<ArtikalDetalji>) request.getAttribute("listaartikala");
            DobavljacDetalji dobavljac = (DobavljacDetalji) session.getAttribute("dobavljacShow");
            List<StavkaKataloga>  listaStavki = (List<StavkaKataloga>) session.getAttribute("listastavki");
            
            
            
               %> 
    
         
        <main class="main_container">
          <div class="container">
                <div class="row boxer">
                    <h1 style="text-align: center">KATALOG</h1>
                    
                        <hr>
                       
                    <div class="col-sm-5">
                        
                        <h2 style="text-align: center">Stavke Kataloga</h2>
                   
                        <table class="table table-bordered">
                    
                 
            <%
            if (listaStavki != null && !listaArtikala.isEmpty()) { %>
                    <thead>
                        <tr>
                            
                        
                          
                            <th>naziv proizvoda</th>
                            <th>ukloni stavku</th>
                   
                            
                            
                        </tr>
                           <% for (StavkaKataloga lis : listaStavki) { %>
                    </thead>
                    <tbody>
                        <tr> <td><%=lis.getNazivProizvoda()%></td>
                             <td><a href="CommandServlet?cmd=oduzmistavkukataloga&id=<%=lis.getSifraArtikla()%>">
                            <input type="button" class="btn btn-group" value="ukloni"/>
                             </a></td>
                        </tr>
                             <% } %>
              <% } else {%>
              <h4>Nema podataka za prikaz</h4>
            <% } %>
                    </tbody>
                </table>
                       
                  
             
                       <form role="form" method="POST" action="CommandServlet?cmd=unesikatalog" id="signup">
                        <input type="hidden" name="dobavljac_id" value="<%=dobavljac.getDobavljacId()%>">
                        
                               <div class="form-group">
                                <label for="naziv">Ime Dobavljaca:</label>
                                <input type="text" name="dobavljac_naziv" class="form-control" placeholder="" required value="<%=dobavljac.getDobavljacNaziv()%>">
                        </div>
                 
                        <div class="form-group">
                                <label for="naziv">Naziv Kataloga:</label>
                                <input type="text" name="naziv_kataloga" class="form-control" placeholder="" required>
                        </div>
                 
                        
                    
                     <div class="form-group" style="text-align: center">
                    <button type="submit" class="btn btn-default">Unesi Katalog</button>
                    </div>
                    
                       </form>
                    
                    </div>
                          
                      
                    <div class="col-sm-7 rightside" style="text-align: center">
                        
                        <br>
                        <br>
                        <br>
                      
            <table class="table table-bordered">
                    
                 
            <%
            if (listaArtikala != null && !listaArtikala.isEmpty()) { %>
                    <thead>
                        <tr>
                            
                            <th>Sifra Artikla</th>
                            <th>Naziv Artikla</th>
                            <th>Jedinica Mere</th>
                            <th>ADD</th>
                   
                        </tr>
                           <% for (ArtikalDetalji lis : listaArtikala) { %>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 
                                <%=lis.getArtikalId()%>
                            </td>
                            <td><%=lis.getNazivArtikla()%></td>
                  
                          <td><%=lis.getNazivJediniceMere()%></td>
                             
                            <td><a href="CommandServlet?cmd=dodajstavkukataloga&id=<%=lis.getArtikalId()%>">
                            <input type="button" class="btn btn-group" value="dodaj"/>
                             </a></td>
                 
                    
                        </tr>
                             <% } %>
              <% } else {%>
              <h4>Nema podataka za prikaz</h4>
            <% } %>
                    </tbody>
                </table>
                    </div>     
                </div>
            </div>
              
        </main>
      
    </body>
</html>
