
<%@page import="model.ProfakturaDetalji"%>
<%@page import="model.Profaktura"%>
<%@page import="model.NarudzbenicaDetalji"%>
<%@page import="model.StavkaNarudzbenice"%>
<%@page import="model.Narudzbenica"%>
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
          <link rel="stylesheet" href="assets/css/cupertino/datapicker.css">
     <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/jquery-1.10.2.js"></script>
        <script src="assets/js/jquery-ui.js"></script>
        <script src="assets/js/jquery.ui.datepicker-sr-SR.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/main.js"></script>



    </head>

    <body>

        <%@include file="jspf/header_admin.jspf"%>
       


        <% //Korisnik logged = (Korisnik) session.getAttribute("korisnik");

           // List<NarudzbenicaDetalji> listaStavki = (List<NarudzbenicaDetalji>) request.getAttribute("stavke_narudzbenice");
            
            List<ProfakturaDetalji> listaStavkiProfakture = (List<ProfakturaDetalji>) request.getAttribute("lista_stavki_profakture");
            
            Profaktura prof  = (Profaktura) request.getSession().getAttribute("profaktura_new");
            
            
//            String nazivKataloga = (String)request.getSession().getAttribute("nazivKatalogaZaMain");
//            Narudzbenica kat = (Narudzbenica) session.getAttribute("narudzbenica");
//
//            Integer prviUnos = (Integer) request.getAttribute("prviunos");
//            if (prviUnos == null) {
//                prviUnos = 0;
//            }
//
//            Integer povecavanje = (Integer) request.getAttribute("povecavanje");
//            if (povecavanje == null) {
//                povecavanje = 5;
//            }
        %> 



    <main class="main_container">
        
       <%@include file="jspf/poruka.jspf"%> 
        <div class="container">


            <div class="row boxer">
                <div class="center-block" style="text-align: center">
                    
                    <h1>Profaktura</h1>



                    <hr>
                    <h3 style="text-align: center">PROFAKTURA <strong></strong></h3>

                </div>
            </div>
                    <form method="post" action ="CommandServlet?cmd=uredinarudzbenicu">
            <ul class="alexa" >
                <label>Cena Profakture:</label>
                <li><input type="text" class="form-control" name="ukupnaSuma" value="<%=prof.getUkupno()%>" readonly="true" ></li>
              
                <label>Datum:</label>
                <li><input type="text" class="form-control" name="datumZaNarudzbenicu" id="datepicker" value="<%=prof.getDatum()%>"></li>
                <br>
                <li >

          
                        <input type="submit" name="" class="btn btn-group" value="Uredi narudzbenicu"/></a>
 
                </li>
                
                   </form>

                <li >

                    <a href="CommandServlet?cmd=obrisinarudzbenicusastranice&id=<%=prof.getSifraProfakture()%>">
                        <input type="button" name="di2" class="btn btn-group" value="Obrisi Narudzbenicu"/></a>

                </li>
            </ul>



            <div class="center-block" style="text-align: center">
                <h2>Stavke Narudzbenice</h2>  
            </div>
            <table class="table table-bordered">


                <%
                if (listaStavkiProfakture != null && !listaStavkiProfakture.isEmpty()) { %>
                <thead>
                    <tr>


                        <th>Naziv Dobavljaca</th>
                        <th>Kolicina</th>
                        <th>Naziv Artikla</th>
                        <th>Naziv Jedinice Mere</th>
                        <th>Cena Stavke</th>
                     

                    </tr>
                    <% for (ProfakturaDetalji lis : listaStavkiProfakture) {%>

                </thead>
                <tbody>
                    <tr>

                        <td><%=lis.getNazivDobavljaca()%></td>
                        <td><%=lis.getKolicina()%></td>
                        <td><%=lis.getNazivArtikla()%></td>
                        <td><%=lis.getNazivJm()%></td>
                        <td><%=lis.getCenaStavke()%></td>
                       

                    </tr>
                    <% } %>
                    <% } else {%>
                <h4>Nema podataka za prikaz</h4>
                <% }%>

                </tbody>
            </table>
    



        </div>

    </main>
 <%@include file="jspf/footer.jspf"%>
</body>
</html>
