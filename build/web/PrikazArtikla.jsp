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
                   <h2>Prikaz Artikla</h2>

           <form method="POST" action="CommandServlet?cmd=zabranaartikal">               
                                                <%  
             //  Korisnik kor = (Korisnik) session.getAttribute("korisnik");
               ArtikalDetalji artikal=(ArtikalDetalji) request.getAttribute("art");
                if(artikal != null) {  %>
                    <input type="hidden" name="id_artikla" value="<%=artikal.getArtikalId()%>"/>
          
       
           <%  }  %>
            
          
               <table class="table table-bordered">
           
                       <tr>
                          
                          <td>Naziv Artikla:</td>
                          <td><%=artikal.getNazivArtikla()%></td>
                          
                   </tr>
                   
                         <tr>
                          
                          <td>Jedinica Mere:</td>
                          <td><%=artikal.getNazivJediniceMere()%></td>
                          
                   </tr>
                         
                   <tr>
                          
                          <td>Tip:</td>
                          <td><%=artikal.getOznakaArtikla()%></td>
                          
                   </tr>
                         
                   
                   <tr>
                          
                          <td>Cena Artikla:</td>
                          <td><input  name="aktuelnaCena" class="form-control" type="number" value="<%=artikal.getAktuelnaCena()%>" /></td>
                          
                   </tr>
                   
                   
           </table>
                          <input type="submit" class="btn btn-default" value="SACUVAJ">
           </form>
                      
           </div>
               </div>
   </body>
   
</html>
