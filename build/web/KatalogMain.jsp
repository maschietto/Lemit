
<%@page import="model.Artikal"%>
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
     
        <script type="text/javascript">
            
            $(document).ready(function(){
                $('box1').hide();
               
                
                $('a#hide').click(function(){
                    $('box1').hide();
                })
                
                $('a#show').click(function(){
                    $('box1').show();
                })
            });
        </script>
        
        
        
    </head>
   
    <body>
        
        <%@include file="jspf/header_admin.jspf"%>     
       
      
                
        
           <% //Korisnik logged = (Korisnik) session.getAttribute("korisnik");
               
               List<KatalogDetalji> listaStavki = (List<KatalogDetalji>) request.getAttribute("listastavki");
             
               
    
                KatalogDetalji kat = (KatalogDetalji) session.getAttribute("katalog");
             
                List<Artikal> listaArtikala = (List<Artikal>) request.getAttribute("listaartikalakatalog");
                Integer prviUnos = (Integer) request.getAttribute("prviunos");
                if(prviUnos == null){prviUnos = 0;}
               
               Integer povecavanje = (Integer) request.getAttribute("povecavanje");
                if(povecavanje == null){povecavanje = 5;}
                              %> 
           
          
           
        <main class="main_container">
                 <%@include file="jspf/poruka.jspf"%>
            <div class="container">
                
                        <div >
                <div class="row boxer">
                    <div class="center-block" style="text-align: center">
                        <h1>Katalog</h1>
                        
                        <h3 style="text-align: center"><%=kat.getNazivKataloga()%> <strong></strong></h3>
                  
                        <hr>
                        <h3 style="text-align: center"><%=kat.getNazivDobavljaca()%> <strong></strong></h3>
              
                    </div>
                </div>
                        
                         <div class="form-group">
                               
                        <a href="CommandServlet?cmd=katalogprosledi&id=<%=kat.getRedniBrojKataloga()%>">
                            <input type="button" name="" class="btn btn-group" value="Uredi katalog"/></a>
                           
                         </div>
                        
                        
             
                <div class="center-block" style="text-align: center">
                <h2>Stavke kataloga</h2>  
                </div>
                <table class="table table-bordered">
                    
                 
            <%
            if (listaStavki != null && !listaStavki.isEmpty()) { %>
                    <thead>
                        <tr>
                            
                            
                            <th>Naziv Proizvoda</th>
                            <th>Obrisi</th>
                            
                        </tr>
                           <% for (KatalogDetalji lis : listaStavki) { %>
                           
                    </thead>
                    <tbody>
                        <tr>
               
                            <td><%=lis.getNazivArtikla()%></td>
                          <%--  <td><a href="CommandServlet?cmd=uredistavku&id=<%=lis.getRedniBrojStavkeKataloga()%>">
                    <input type="button" class="btn btn-group" value="uredi"/>
                                </a></td> --%>
                            <td><a href="CommandServlet?cmd=obrisistavkukataloga&idbrisi=<%=lis.getRedniBrojStavkeKataloga()%>">
                    <input type="button" class="btn btn-group" value="obrisi"/>
                                </a></td>
                    
                        </tr>
                             <% } %>
              <% } else {%>
              <h4>Nema podataka za prikaz</h4>
            <% } %>
            
              </tbody>
                </table>
           <%--<form role="form" method="POST" action="CommandServlet?cmd=paginacija" id="signup">  
            <ul class="pagination">
                    <li><a href="#">Previous</a></li>
                    <li class="active" name="prvi_red" value>1</li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">Next</a></li>
                </ul> --%>
         
           <form action="CommandServlet?cmd=paginationkatalogmain" method="post">
             
               <input type="hidden" value="<%=kat.getSifraDobavljaca()%>" name="sifra_dob"> </div>
               <input type="hidden" value="<%=kat.getRedniBrojKataloga()%>" name="id"> </div>
               
           <ul class="pagination" style="padding-left: 14%">  
               
           <input type="hidden" name="firstrow" value="<%=prviUnos%>">
           <input type="hidden" name="rowcount" value="<%=povecavanje%>">
           <li><input type="submit" name="page" value="PREVIOUS"></li>
           <li><input type="submit" name="page" value="NEXT"></li>
         
           </ul>
               
                </form> 
            
            <div>
               <div  class="container">
                   <h2 style="text-align: center" > dodaj stavku </h2>
                     
               
                    
                    
                  <form role="form" method="POST" action="CommandServlet?cmd=dodajnovustavkukataloga" id="signup">
                   
                   <div class="form-group">
                     <select class="form-control" name="artikli_select">
                    
                           <%
                             for(Artikal lis:listaArtikala){
                             String Field = lis.getNazivArtikla();
                                     %>
                    <option value="<%=Field %>" style="text-align: center"><%=Field %></option>
                            
                       <%}%>
                             
                      </select>
                      </div>
                        <div class="form-group">
                            <input type="hidden" value="<%=kat.getSifraDobavljaca()%>" name="sifra_dob"> </div>
                        <div class="form-group">             
                            <input type="hidden" value="<%=kat.getRedniBrojKataloga()%>" name="katalog_id"> </div>
                     
                      <div class="form-group" Style="text-align: center">              
                        
                            <input type="submit" class="btn btn-group" value="Dodaj stavku"/></a></div>
                            
                  </form>  
                      
               
                       
<%--  
                    <td><a href="CommandServlet?cmd=dodajstavkukataloga&id=<%%>">
                    <input type="button" class="btn btn-group" value="dodaj"/>
                                </a></td> --%>
     
                 
                        <div class="form-group">
                               
                        <a href="CommandServlet?cmd=obrisikatalogsastranice&id=<%=kat.getRedniBrojKataloga()%>">
                            <input type="button" name="di2" class="btn btn-group" value="Obrisi katalog"/></a>
                           
                         </div>
                            </div>
               </div>
                            
                     
            </div>
            </div>  
                            
        </main>
      <%@include file="jspf/footer.jspf"%>
    </body>
</html>
