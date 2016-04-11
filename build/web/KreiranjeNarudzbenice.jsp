<%@page import="model.StavkaNarudzbenice"%>
<%@page import="model.KatalogDetalji"%>
<%@page import="UCController.KatalogDeleteSaStraniceCommand"%>
<%@page import="model.StavkaKataloga"%>
<%@page import="model.ArtikalDetalji"%>
<%@page import="model.DobavljacDetalji"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Dodavanje Narudzbenice</title>
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
    </head>
    <body>
        <%@include file="jspf/header_admin.jspf"%>     
       
       


        <%
            List<KatalogDetalji> listaStavkiKataloga = (List<KatalogDetalji>) request.getAttribute("listastavkikataloga");
            KatalogDetalji kat = (KatalogDetalji) request.getSession().getAttribute("katalog_new");

            List<StavkaNarudzbenice> listaStavki = (List<StavkaNarudzbenice>) session.getAttribute("listastavkinarudzbenice");


        %> 


    <main class="main_container">
         <%@include file="jspf/poruka.jspf"%>
        <div class="container">
            <div class="row boxer">
                <h1 style="text-align: center">NARUDZBENICA</h1>

                <hr>

                <div class="col-sm-5">

                    <h2 style="text-align: center">Stavke Narudzbenice</h2>

                    <form role="form" method="POST" action="CommandServlet?cmd=unesinarudzbenicu" id="signup">
                        <table class="table table-bordered">


                            <%                if (listaStavki != null && !listaStavki.isEmpty()) { %>
                            <thead>
                                <tr>

                                    <th>naziv proizvoda</th>
                                    <th>kolicina</th>

                                    <th>ukloni stavku</th>


                                </tr>
                                <% for (StavkaNarudzbenice lis : listaStavki) {%>

                            </thead>
                            <tbody>

                                <tr> 
                                    <td><%=lis.getNazivProizvoda()%></td>

                                    <td><input id="unos_broj" type="number" name="broj<%=listaStavki.indexOf(lis)%>" class="btn btn-group"/></td>

                                    <td><a href="CommandServlet?cmd=oduzmistavkunarudzbenice&id=<%=lis.getSifraArtikla()%>">
                                            <input type="button" class="btn btn-group" value="ukloni"/>

                                        </a></td>

                                </tr>
                                <% } %>
                                <% } else {%>
                            <h4>Nema podataka za prikaz</h4>
                            <% }%>
                            </tbody>
                        </table>


                        <input type="hidden" name="dobavljac_id" value="<%=kat.getSifraDobavljaca()%>">

                        <div class="form-group">
                            <label for="naziv">Ime Dobavljaca:</label>
                            <input type="text" name="dobavljac_naziv" class="form-control" placeholder="" required value="<%=kat.getNazivDobavljaca()%>">
                        </div>

                        <div class="form-group">
                            <label for="dat">DAtum:</label>
                            <input type="text" name="datum" id="datepicker" class="form-control" placeholder="" required>
                        </div>



                        <div class="form-group" style="text-align: center">


                            <button type="submit" class="btn btn-default">Unesi Narudzbenicu</button>
                        </div>

                    </form>

                </div>


                <div class="col-sm-7 rightside" style="text-align: center">


                    <br>
                    <br>
                    <br>




                    <table class="table table-bordered">


                        <%
                if (listaStavkiKataloga != null && !listaStavkiKataloga.isEmpty()) { %>
                        <thead>
                            <tr>


                                <th>Naziv Artikla</th>

                                <th>ADD</th>



                            </tr>
                            <% for (KatalogDetalji lis : listaStavkiKataloga) {%>
                        </thead>
                        <tbody>
                            <tr>

                                <td><%=lis.getNazivArtikla()%></td>


                                <td><a href="CommandServlet?cmd=dodajstavkunarudzbenice&id=<%=lis.getRedniBrojStavkeKataloga()%>">
                                        <input type="button" class="btn btn-group" value="dodaj"/>
                                    </a></td>


                            </tr>
                            <% } %>
                            <% } else {%>
                        <h4>Nema podataka za prikaz</h4>
                        <% }%>
                        </tbody>
                    </table>
                </div>     
            </div>
        </div>

    </main>
 <%@include file="jspf/footer.jspf"%>
</body>
</html>
