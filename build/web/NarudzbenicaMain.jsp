
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

            List<NarudzbenicaDetalji> listaStavki = (List<NarudzbenicaDetalji>) request.getAttribute("stavke_narudzbenice");
            
            List<KatalogDetalji> listaStavkiKataloga = (List<KatalogDetalji>) request.getAttribute("katalog_stavke");
            
            
            String nazivKataloga = (String)request.getSession().getAttribute("nazivKatalogaZaMain");
            Narudzbenica kat = (Narudzbenica) session.getAttribute("narudzbenica");

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
                    <h1>Narudzbenica</h1>



                    <hr>
                    <h3 style="text-align: center"><%=kat.getNazivDobavljaca()%> <strong></strong></h3>

                </div>
            </div>
                    <form method="post" action ="CommandServlet?cmd=uredinarudzbenicu">
            <ul class="alexa" >
                <li> <label>Naziv DObavljaca Da Srdja Vidi DA Trigger radi:</label>
               <input type="text" class="form-control" name="imeKataloga" value="<%=kat.getNazivDobavljaca()%>" /></li>
              
                <li> <label>Datum:</label>
               <input type="text" class="form-control" name="datumZaNarudzbenicu" id="datepicker" value="<%=kat.getDatum()%>"></li>
                
                <li>

          
                        <input type="submit" name="" class="btn btn-group" value="Uredi narudzbenicu"/>
 
                </li>
                
                   </form>

                <li >

                    <a href="CommandServlet?cmd=obrisinarudzbenicusastranice&id=<%=kat.getSifraNarudzbenice()%>">
                        <input type="button" name="di2" class="btn btn-group" value="Obrisi Narudzbenicu"/></a>

                </li>
            </ul>



            <div class="center-block" style="text-align: center">
                <h2>Stavke Narudzbenice</h2>  
            </div>
            <table class="table table-bordered">


                <%
                if (listaStavki != null && !listaStavki.isEmpty()) { %>
                <thead>
                    <tr>


                        <th>Naziv Proizvoda</th>
                        <th>Kolicina</th>
                        <th>Jedinica Mere</th>
                        <th>Uredi</th>
                        <th>Obrisi</th>

                    </tr>
                    <% for (NarudzbenicaDetalji lis : listaStavki) {%>

                </thead>
                <tbody>
                    <tr>

                        <td><%=lis.getNazivArtikla()%></td>
                        <td><%=lis.getKolicina()%></td>
                        <td><%=lis.getNazivJm()%></td>

                        <td><a href="#">
                                <input type="button" class="btn btn-group" value="uredi"/>
                            </a></td>

                        <td><a href="CommandServlet?cmd=obrisistavkunarudzbenice&idbrisi=<%=lis.getRedniBrojStavkeNarudzbenice()%>">
                                <input type="button" class="btn btn-group" value="obrisi"/>
                            </a></td>

                    </tr>
                    <% } %>
                    <% } else {%>
                <h4>Nema podataka za prikaz</h4>
                <% }%>

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

            <form action="CommandServlet?cmd=paginationnarudzbenicamain" method="post">

                <input type="hidden" value="<%=kat.getSifraDobavljaca()%>" name="sifra_dob"> 
                <input type="hidden" value="<%=kat.getSifraNarudzbenice()%>" name="id"> 

                <ul class="pagination" >  

                    <input type="hidden" name="firstrow" value="<%=prviUnos%>">
                    <input type="hidden" name="rowcount" value="<%=povecavanje%>">
                    <li><input type="submit" name="page" value="PREVIOUS"></li>
                    <li><input type="submit" name="page" value="NEXT"></li>

                </ul>

            </form>   


            <div>
                <div>
                    <h2 style="text-align: center" > dodaj stavku </h2>




                    <form role="form" method="POST" action="CommandServlet?cmd=dodajnovustavkunarudzbenice" id="signup">

                        <div class="form-group">
                            <select class="form-control" name="katalog_select">

                                <%
                                    for (KatalogDetalji lis : listaStavkiKataloga) {
                                        String Field = lis.getNazivArtikla();
                                %>
                                <option value="<%=Field%>" style="text-align: center"><%=Field%></option>

                                <%}%>

                            </select>
                        </div>
                        <div class="form-group">
                            <input type="hidden" value="<%=kat.getSifraDobavljaca()%>" name="sifra_dob"> </div>
                        <div class="form-group">             
                            <input type="hidden" value="<%=kat.getSifraNarudzbenice()%>" name="narudzbenica_id"> </div>

                        <div class="form-group" Style="text-align: center">       

                            <input type="number" name="kolicina_id"> </div>

                        <div class="form-group" Style="text-align: center">              


                            <input type="submit" class="btn btn-group" value="Dodaj stavku"/></a></div>

                    </form>  



                    <%--  
                                        <td><a href="CommandServlet?cmd=dodajstavkukataloga&id=<%%>">
                                        <input type="button" class="btn btn-group" value="dodaj"/>
                                                    </a></td> --%>



                </div>
            </div>


        </div>

    </main>
 <%@include file="jspf/footer.jspf"%>
</body>
</html>
