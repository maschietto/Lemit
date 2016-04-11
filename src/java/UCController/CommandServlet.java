package UCController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;



@WebServlet(name = "CommandServlet", urlPatterns = {"/CommandServlet"})
public class CommandServlet extends HttpServlet {

    Map<String, Command> komande = new HashMap<String, Command>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        komande.put("katalogmain", new KatalogMainCommand());
        komande.put("login", new LoginCommand());
        komande.put("logout", new LogoutCommand());
        komande.put("index", new NullCommand("index"));
        komande.put("editkorisnik", new EditUserCommand());
        komande.put("aktivirajkorisnika", new ActivateUserCommand("TRUE"));
        komande.put("deaktivirajkorisnika", new ActivateUserCommand("FALSE"));
        komande.put("pronadjikorisnika", new FindUserCommand());
        komande.put("dobavljacilista",new DobavljaciListaCommand());
        komande.put("dobavljacupdate", new UpdateDobavljacCommand());
        komande.put("prosledidobavljacaunos",new ProccedDobavljacCommand());
        komande.put("korisnik", new UserCommand());
        komande.put("korisnici", new UserListCommand());
        komande.put("prosledikorisnika",new ProccedUserCommand());
        komande.put("dobavljacmain",new DobavljacMainCommand());
        komande.put("unesiartikal",new DodajArtikalCommand());
        komande.put("artikalunos", new DodajArtikalCommand());
        komande.put("pronadjidobavljaca", new FindDobavljacCommand());
        komande.put("unesidobavljaca",new DodajDobavljacCommand());
        komande.put("dobavljacilista",new DobavljaciListaCommand());
        komande.put("dobavljacdelete", new DobavljacDeleteCommand());
        komande.put("proslediartikal", new ProccedArtikalCommand());
        komande.put("listaartikala", new ArtikalListaCommand());
        komande.put("urediartikal",new EditArtikalCommand());
        komande.put("obrisiartikal", new ArtikalDeleteCommand());
        komande.put("pronadjiartikal", new FindArtikalCommand());
        komande.put("dodajkatalogdobavljaca",new DodajKatalogDobavljacaCommand());
        komande.put("dodajstavkukataloga",new DodajStavkuKatalogaCommand());
        komande.put("dodajnovustavkukataloga",new AddNewStavkuKatalogCommand());
        komande.put("oduzmistavkukataloga",new UkloniStavkuKatalogaCommand());
        komande.put("unesikatalog",new UnesiKatalogCommand());
        komande.put("paginationkatalogmain",new KatalogPagginationCommand());
        komande.put("obrisistavkukataloga", new StavkaKatalogaDeleteCommand());
        komande.put("kataloglista",new KatalogListaCommand());
        komande.put("pronadjikatalog",new FindKatalogCommand());
        komande.put("obrisikatalog",new KatalogDeleteCommand());
        komande.put("obrisikatalogsastranice",new KatalogDeleteSaStraniceCommand());
        komande.put("uredikatalog",new KatalogUpdateCommand());
        komande.put("katalogprosledi", new ProccedKataloglCommand());
        komande.put("pronadjikatalog2", new FindKatalog2Command());
        komande.put("odaberitekatalognarudzbenica", new OdaberiKatalogNarCommand());
        komande.put("napravinarudzbenicu", new CreateNarudzbenicaCommand());
        komande.put("dodajstavkunarudzbenice", new DodajStavkuNarudzbeniceCommand());
        komande.put("unesinarudzbenicu", new UnesiNarudzbenicuCommand());
        komande.put("pronadjinarudzbenicu", new FindNarudzbenicaCommand());
        komande.put("uredinarudzbenicu",new NarudzbenicaUpdateCommand());
        komande.put("paginationnarudzbenicamain",new NarudzbenicaPagginationCommand());
        komande.put("dodajnovustavkunarudzbenice",new AddNewStavkuNarudzbenicaCommand());
        komande.put("oduzmistavkunarudzbenice",new UkloniStavkuNarudzbeniceCommand());
        komande.put("prosledinarudzbenicu", new ProccedNarudzbenicuCommand());
        komande.put("obrisinarudzbenicusastranice",new NarudzbenicaDeleteSaStraniceCommand());
        komande.put("narudzbenicalista",new NarudzbenicaListaCommand());
        komande.put("narudzbenicamain", new NarudzbenicaMainCommand());
        komande.put("napraviprofakturu", new CreateProfakturaCommand());
        komande.put("sacuvajprofakturu", new UnesiProfakturuCommand());
        komande.put("listaartikalaproizvodjaca", new ArtikiProizvodjacaCommand());
        komande.put("zabranaartikal", new ArtikalErorCommand());
        komande.put("pogledajartikla", new ArtikalViewCommand());
}

    private Command lookupCommand(String cmd, HttpServletRequest request) {
        if (komande.containsKey(cmd.toLowerCase())) {
            return komande.get(cmd);
        } else {
            request.getSession().invalidate();
            return new NullCommand("index");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String cmd = request.getParameter("cmd");
        Command komanda = lookupCommand(cmd, request);
        String next;
        try {
            next = komanda.execute(request);
            getServletContext().getRequestDispatcher("/" + next + ".jsp").forward(request, response);
        } catch (IOException ex) {
           request.setAttribute(RequestDispatcher.ERROR_EXCEPTION, ex);
           request.setAttribute(RequestDispatcher.ERROR_SERVLET_NAME, CommandServlet.class.getName());
           getServletContext().getRequestDispatcher("/ErrorHandlingServlet").forward(request, response); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CommandServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CommandServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Komandni servlet";
    }
}
