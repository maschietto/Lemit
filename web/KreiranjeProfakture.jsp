
<%@page import="model.Profaktura"%>
<%@page import="model.ProfakturaDetalji"%>
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
        <title>Profaktura</title>
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



        <% //Korisnik logged = (Korisnik) session.getAttribute("korisnik");

            List<ProfakturaDetalji> listaStavki = (List<ProfakturaDetalji>) request.getAttribute("listastavkiprofakture");

            NarudzbenicaDetalji kat = (NarudzbenicaDetalji) request.getAttribute("narudzbenica_new");
            Integer ukupno =(Integer) request.getAttribute("ukupno_novaca");
            
            Integer brojac = (Integer) request.getSession().getAttribute("brojac");

            Integer prviUnos = (Integer) request.getAttribute("prviunos");
            if (prviUnos == null) {
                prviUnos = 0;
            }

            Integer povecavanje = (Integer) request.getAttribute("povecavanje");
            if (povecavanje == null) {
                povecavanje = 5;
            }
        %> 



    <main class="main_container">
        <%@include file="jspf/poruka.jspf"%>
        <div class="container">


            <div class="row boxer">
                <div class="center-block" style="text-align: center">
                    <h1>Profakturica</h1>



                    <hr>
                    <h3 style="text-align: center"><%=kat.getNazivDobavljaca()%> <strong></strong></h3>

                </div>
            </div>
            <form role="form" method="POST" action="CommandServlet?cmd=sacuvajprofakturu" id="signup">

                
         
                <div class="form-group">
                    <label for="dat">DAtum:</label>
                    <input type="text" name="datum" id="datepicker" class="form-control" placeholder="" required>
                </div>

                <input type = "hidden" name = "sifradobavljaca" value = "<%=kat.getSifraDobavljaca()%>"/>
                <input type = "hidden" name = "sifranarudzbenice" value = "<%=kat.getRedniBrojNarudzbenice()%>"/>


                <div class="center-block" style="text-align: center">
                    <h2>Stavke Profakture</h2>  
                </div>
                <table class="table table-bordered">


                    <%
                        if (listaStavki != null && !listaStavki.isEmpty()) { %>
                    <thead>
                        <tr>


                            <th>Naziv Proizvoda</th>
                             <th>Naziv Kataloga</th>
                             <th>jedinica mere</th>
                            <th>Cena Artikla</th>
                            <th>kolicina</th>
                            <th>cena Stavke</th>

                        </tr>
                        <% for (ProfakturaDetalji prof : listaStavki) {
                        
                           
                        brojac = listaStavki.size();
                        request.getSession().setAttribute("brojac", brojac);
                        %>
                     
                        
                        
                    </thead>
                    <tbody>

                        <tr>


                           <td><input type = "hidden" name = "nazivartikla<%=listaStavki.indexOf(prof)%>" value = "<%=prof.getNazivArtikla()%>"/><%=prof.getNazivArtikla()%></td>
                           <td><input type = "hidden" name = "nazivkataloga<%=listaStavki.indexOf(prof)%>" value = "<%=prof.getNazivKataloga()%>"/><%=prof.getNazivKataloga()%></td>
                           <td><input type = "hidden" name = "nazivjm<%=listaStavki.indexOf(prof)%>" value = "<%=prof.getNazivJm()%>"/><%=prof.getNazivJm()%></td>
                           <td><input type = "hidden" name = "cenaartikla<%=listaStavki.indexOf(prof)%>" value = "<%=prof.getCena()%>"/><%=prof.getCena()%></td>
                           <td><input type = "hidden" name = "kolicina<%=listaStavki.indexOf(prof)%>" value = "<%=prof.getKolicina()%>"/><%=prof.getKolicina()%></td>
                           <td><input type = "hidden" name = "cenastavke<%=listaStavki.indexOf(prof)%>" value = "<%=prof.getCenaStavke()%>"/><%=prof.getCenaStavke()%></td>




                        </tr>
                        <% } %>
                        <% } else {%>
                    <h4>Nema podataka za prikaz</h4>
                    <% }%>

                    </tbody>
                </table>

                           <div class="form-group levicosak"><label for="dat">Ukupan Iznos: <%=ukupno%> RSD</label></div>
                    
                <%request.setAttribute("lista_stavki_profakture", listaStavki);%>

                <div class="form-group alexa">
                    <input type="submit" name="page" value="Sacuvaj Profakturu">
                </div>
            </form>   


        </div>

    </main>
    <%@include file="jspf/footer.jspf"%>
</body>
</html>
