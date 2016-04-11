<%@page import="model.Katalog"%>
<%@page import="model.DobavljacDetalji"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>DOBAVLJAC GLAVNA</title>
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
         
           List<Katalog> listaKataloga = (List<Katalog>) request.getAttribute("listakataloga");
           DobavljacDetalji dobavljac = (DobavljacDetalji) session.getAttribute("dobavljacShow");
               %> 
      
        <main class="main_container">
          <div class="container">
                <div class="row boxer">
                    <div class="col-sm-5">
                       
                       <h2 style="text-align: center">PODACI O DOBAVLJACU:</h2>
                        <hr>
                       
                        <h2 style="text-align: center"> <%=dobavljac.getDobavljacNaziv()%></h2>
                         
                            
                        
                        <form role="form" method="POST" action="CommandServlet?cmd=dobavljacupdate" id="signup">
                            
                            <input type="hidden" name="id" value="<%=dobavljac.getDobavljacId()%>">
                            
                            <div class="form-group">
                                <label for="naziv">Naziv Dobavljaca:</label>
                                <input type="text" name="naziv_dobavljaca" class="form-control" placeholder="" required value="<%=dobavljac.getDobavljacNaziv()%>">
                            </div>
                            <div class="form-group">
                                <label for="naziv_mesta">Naziv Mesta:</label>
                                <input type="text" name="naziv_mesta" class="form-control" placeholder="" required value="<%=dobavljac.getNazivMesta()%>">
                            </div>
                            <div class="form-group">
                                <label for="uname">Naziv Ulice:</label>
                                <input type="text" name="naziv_ulice" class="form-control" placeholder="" required value="<%=dobavljac.getUlicaNova()%>">
                            </div>
                            <div class="form-group">
                                <label for="email">Broj:</label>
                                <input type="text" name="broj" class="form-control" id="email" placeholder="" required value="<%=dobavljac.getBrojZgrade()%>">
                            </div>
                            
                            <div class="form-group">
                                <label for="comment">OPIS:</label>
                                
                                <textarea class="form-control" style="text-align: center" rows="5" name="dobavljac_opis" ><%=dobavljac.getDobavljacOpis()%></textarea>
                            </div>
                            
                          
                            
                          
                 
                     <div class="form-group">
                    <button type="submit" class="btn btn-default" name="update">Preuredi</button>
                    </div>  
                 
                                     

                            </form>
                            
                      
                        <form role="form" method="POST" action="CommandServlet?cmd=dobavljacdelete" id="signup">
                           
                            <input type="hidden" name="id_delete" value="<%=dobavljac.getDobavljacId()%>">
                            
                            
                        <div class="form-group">         
                       <button type="submit" class="btn btn-default" name="obrisi">Obrisi</button>
                       </div>   
                              </form>
                    </div>
                          
                      
                    <div class="col-sm-7 rightside" style="text-align: center">
                        
                 
                        <h2 style="text-align: center">KATALOZI DOBAVLJACA:</h2>
                            <hr>
                        
             
                            
                            <div>              
                            
                    <table class="table table-bordered">
                         <%
            if (listaKataloga != null && !listaKataloga.isEmpty()) { %>
                    <thead>
                        <tr>
                           
                            <th>Naziv kataloga</th>
                           
                            
                        </tr>
         
                  <% for (Katalog lis : listaKataloga) { %>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 
                        <a href="CommandServlet?cmd=katalogmain&id=<%=lis.getSifraKataloga()%>">
                            <%=lis.getNazivKataloga()%> </a>
                        </td>
                    
                        </tr>
                        
                             <% } %>
              <% } else {%>
              <h4>Nema podataka za prikaz</h4>
            <% } %>
                    </tbody>
                </table>
                        
                        
                        
                        
                    <div class="form-group">
                               
                        <a href="CommandServlet?cmd=dodajkatalogdobavljaca&dobavljac_id=<%=dobavljac.getDobavljacId()%>">
                            <input type="button" class="btn btn-group" value="Dodaj katalog"/></a>
                           
                         </div>
                            </div>
                 
                     
                    </div>     
                </div>
            </div>
              
        </main>
       
    </body>
</html>
