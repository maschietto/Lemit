<%@page import="model.ArtikalDetalji"%>
<%@page import="model.DobavljacDetalji"%>
<%@page import="model.BrojZgrade"%>
<%@page import="model.Ulica"%>
<%@page import="model.Mesto"%>
<%@page import="model.Dobavljac"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<%@page import="model.Katalog"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Unos Artikla</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/cupertino/datapicker.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/jquery-1.10.2.js"></script>
        <script src="assets/js/jquery-ui.js"></script>
        <script src="assets/js/jquery.ui.datepicker-sr-SR.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/main.js"></script>
        <script>
        $(function() {
          $( "#datepicker" ).datepicker();
        });
        </script>
        <script>    $('.selectpicker').selectpicker({
         style: 'btn-info',
         size: 4
         });
        </script>
        
    </head>
    <body>
            
            <%@include file="jspf/header_admin.jspf"%>     
            <%@include file="jspf/poruka.jspf"%>
            <%@include file="jspf/footer.jspf"%>
                
        
    

                   
                <div class="container">
               <div class="my_user_form login">    
                   <h2>Unesite Novi Artikal</h2>

                     <form role="form" method="POST" action="CommandServlet?cmd=unesiartikal" id="signup">
                         
                                                <%  
             //  Korisnik kor = (Korisnik) session.getAttribute("korisnik");
               ArtikalDetalji artikal=(ArtikalDetalji) request.getAttribute("art");
                if(artikal != null) {  %>
                    <input type="hidden" name="id_artikla" value="<%=artikal.getArtikalId()%>">
          
       
           <%  }  %>
            
                   
                   <div class="form-group">
                        <label for="uname">Naziv artikla:</label>
                        <input type="text" name="naziv_artikla" class="form-control" value="<%=artikal != null ? artikal.getNazivArtikla(): ""%>" >
                    </div>
                    <div class="form-group">
                        <label for="ime_objekta">Naziv tipa:</label>
                        <input type="text" name="ime" class="form-control"  >
                    </div>
                       <div class="form-group">
                        <label for="ime_objekta">oznaka:</label>
                        <input type="text" name="oznaka" class="form-control"   >
                    </div>
                    <div class="form-group">
                        <label for="jedinica_mere">Jedinica Mere:</label>
                        <input type="text" name="jednica_mere" class="form-control" value="<%=artikal != null ? artikal.getNazivJediniceMere(): ""%>"  >
                    </div>
                         <div class="form-group">
                    <label for="jedinica_mere">Izrazeno u kilogramima:</label>
                    <select class="form-control" name="kilogrami">
                    
                    <option id="1" value="Jeste">jeste</option>
                    <option id="2" value="Nije">nije</option>
                 
                    </select>
                         </div>
                    
                      <div class="form-group">
                        <label for="vrednost_u_kilgoramima">izrazena vrednost:</label>
                        <input type="text" name="u_kilogramima" class="form-control" value="<%=artikal != null ? artikal.getuKilogramima(): ""%>"  >
                    </div>
                         
                    <div class="form-group">
                        <label for="cena">Cena:</label>
                        <input type="text"  name="cena" value="<%=artikal != null ? artikal.getAktuelnaCena(): ""%>" class="form-control" >
                    </div>
                      <div class="form-group">
                        <label for="datum">Datum_unosa artikla:</label>
                        <input type="text"  id="datepicker" name="datum_unosa"  class="form-control" >
                    </div>
                   
                  
                  
                    <div class="form-group" style="padding-left: 36%">
                    <button type="submit" class="btn btn-default" >Submit</button>
                    </div>
                          </form>
               </div>
                   
        
               </div>
         
       
   </body>
</html>
