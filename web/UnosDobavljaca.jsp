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
        <title>Korisnik</title>
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
               Korisnik kor = (Korisnik) session.getAttribute("korisnik");
             %>
             <%--  
             boolean registration = (kor == null && dobDet == null );
                    if (!registration) {%>
                    <input type="hidden" name="id" value="<%=kor.getId_korisnika()%>">
                     
                    <% }%>
           
             %>
             --%>
                 <form role="form" method="POST" action="CommandServlet?cmd=unesidobavljaca" id="signup">
          

                   
                <div class="container">
               <div class="my_user_form login">    
                   <h2>Unesite Novog Dobavljaca</h2>

                   <div class="form-group">
                        <label for="uname">Naziv Dobavljaca:</label>
                        <input type="text" name="nazivDob" class="form-control"  placeholder="Unesite naziv" required>
                    </div>
                    <div class="form-group">
                        <label for="mesto">Mesto:</label>
                        <input type="text" name="naziv_mesta" class="form-control"  placeholder="Unesite Mesto" required>
                    </div>
                    <div class="form-group">
                        <label for="nazivUlice">Naziv Ulice:</label>
                        <input type="text" name="naziv_ulice" class="form-control"  placeholder="Unesite naziv ulice" required>
                    </div>
                    <div class="form-group">
                        <label for="brojZgrade">Broj Zgrade:</label>
                        <input type="text"  name="broj_zgrade" class="form-control" id="password" placeholder="Unesite broj" required>
                    </div>
                  
                  
                    <div class="form-group" style="padding-left: 36%">
                    <button type="submit" class="btn btn-default" >Submit</button>
                    </div>
               </div>
               </div>
         </form>
        
      
   </body>
</html>
