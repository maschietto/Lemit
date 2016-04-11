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
               List<ArtikalDetalji> listaArtikala = (List<ArtikalDetalji>) request.getAttribute("listaartikala");
                
               %> 
           
      
           
        <main class="main_container">
            <div class="container">
                <div class="row boxer">
                    <div class="col-sm-4">
                        <h1>Lista Artikala</h1>
                        
                        <div class="form-group">
                          <a href="CommandServlet?cmd=korisnici">
                            <input type="button" class="btn btn-group" value="Vrati Se"/></a>
                        </div>
                        
                      <form method="POST" action="CommandServlet?cmd=pronadjiartikal">
                            <div class="form-group">
                                <input type="text" name="demo_ime" placeholder="Type here" class="form-control">
                            </div>
                          
                          <button type="submit" class="btn btn-default">Search</button>
                      </form>
                    </div>
                </div>
                <hr>
                <h2>Artikli</h2>  
                <table class="table table-bordered">
                    
                 
            <%
            if (listaArtikala != null && !listaArtikala.isEmpty()) { %>
                    <thead>
                        <tr>
                            
                            <th>Sifra Artikla</th>
                            <th>Naziv Artikla</th>
                            <th>Aktuelna Cena</th>
                            <th>Jedinica Mere</th>
                            <th>Ime Objekta</th>
                            <th>Oznaka</th>
                            <th>izrazeno u kg</th>
                            <th>vrednost u kg</th>
                            <th>Uredi</th>
                            <th>Obrisi</th>
                            
                            
                        </tr>
                           <% for (ArtikalDetalji lis : listaArtikala) { %>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 
                        <a href="CommandServlet?cmd=pogledajartikla&id=<%=lis.getArtikalId()%>">
                            <input type="button" class="btn btn-group" value="<%=listaArtikala.indexOf(lis) + 1%>" /></a>
                        </td>
                            <td><%=lis.getNazivArtikla()%></td>
                  
                            <td> <%=lis.getAktuelnaCena()%></td>
                            <td><%=lis.getNazivJediniceMere()%></td>
                            <td><%=lis.getImeArtikla()%></td>
                            <td><%=lis.getOznakaArtikla()%></td>
                            <td><%=lis.getIzrazenoUKilogramima()%></td>
                            <td><%=lis.getuKilogramima()%></td>
                            
                           
                            <td><a href="CommandServlet?cmd=urediartikal&id=<%=lis.getArtikalId()%>">
                    <input type="button" class="btn btn-group" value="uredi"/>
                                </a></td>
                            <td><a href="CommandServlet?cmd=obrisiartikal&id=<%=lis.getArtikalId()%>">
                    <input type="button" class="btn btn-group" value="obrisi"/>
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
