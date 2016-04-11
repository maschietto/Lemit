<%@page import="model.KatalogDetalji"%>
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
                
        

        
   
          
        
    <%  Korisnik kor = (Korisnik) session.getAttribute("korisnik");
               KatalogDetalji katalog =(KatalogDetalji) request.getSession().getAttribute("katalog");
                if(katalog != null) {  %>
                    <input type="hidden" name="id_katalog" value="<%=katalog.getArtikalId()%>">
          
       
           <%  }  %>
                   
                <div class="container">
               <div class="my_user_form login">    
                   <h2>Unesite Nove podatke za Katalog</h2>
                   
                   <form role="form" method="POST" action="CommandServlet?cmd=uredikatalog" id="signup">
                   
                       <div class="form-group">
                        <label for="uname">Naziv kataloga</label>
                        <input type="text" name="naziv_kataloga" class="form-control" value="<%=katalog != null ? katalog.getNazivKataloga(): ""%>" >
                    </div>
                   
                         <div class="form-group">
                        <label for="uname">Naziv Dobavljaca</label>
                        <input type="text" name="naziv_dobavljaca" class="form-control" value="<%=katalog != null ? katalog.getNazivDobavljaca(): ""%>" >
                    </div>
                   
                  
                  
                    <div class="form-group" style="padding-left: 36%">
                    <button type="submit" class="btn btn-default" >Submit</button>
                    </div>
                          </form>
               </div>
                   
        
               </div>
         
       
   </body>
</html>
