<%@page import="model.Narudzbenica"%>
<%@page import="model.NarudzbenicaDetalji"%>
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
                
        

        
   
          
        
    <%        Korisnik kor = (Korisnik) session.getAttribute("korisnik");
            
              List<Dobavljac> dob = (List<Dobavljac>) request.getAttribute("dobavljac");
              NarudzbenicaDetalji kat = (NarudzbenicaDetalji) request.getAttribute("katalog");
              Narudzbenica narudzbenica =(Narudzbenica) request.getSession().getAttribute("narudzbenica");
                if(narudzbenica != null) {  %>
                    <input type="hidden" name="id_katalog" value="<%=narudzbenica.getSifraNarudzbenice()%>">
          
       
           <%  }  %>
                   
                <div class="container">
               <div class="my_user_form login">    
                   <h2>Unesite Novi Datum za Narudzbenicu</h2>
                   
                   <form role="form" method="POST" action="CommandServlet?cmd=uredinarudzbenicu" id="signup">
                   
                       <div class="form-group">
                        <label for="uname">KATALOG:</label>
                        <label for="uname"><%=kat != null ? kat.getNazivKataloga(): ""%></label>
                    </div>
                    
                       <div class="form-group">
                        <label for="uname">DATUM:</label>
                        <input type="text" id="datepicker" name = "datum" class="form-group" value="<%=narudzbenica != null ? narudzbenica.getDatum(): ""%>"/>
                    </div>
                    
                        <div class="form-group">
                     <select class="form-control" name="dobavljac_ime">
                    
                           <%
                             for(Dobavljac lis: dob){
                             String Field = lis.getNazivDobavljaca();
                             String Dob =String.valueOf(lis.getSifraDobavljaca());
                                     %>
                                     
                    <option value="<%=Field+Dob %>" style="text-align: center"><%=Field %></option>
                    
                       <%}%>
                            
                      </select>
                      </div>
                       
                       
                       
                       
                   
                    
                    <div class="form-group" style="padding-left: 36%">
                    <button type="submit" class="btn btn-default" >Submit</button>
                    </div>
                          </form>
               </div>
                   
        
               </div>
         
       
   </body>
</html>
