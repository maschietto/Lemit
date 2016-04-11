package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.DobavljacDetalji;
import model.Katalog;
import model.KatalogDetalji;
import model.Korisnik;
import model.Narudzbenica;
import model.NarudzbenicaDetalji;
import model.Profaktura;
import model.ProfakturaDetalji;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;

public class CreateProfakturaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {

        String id = request.getParameter("id");

        List<ProfakturaDetalji> listaStavkiProfakture = new ArrayList<ProfakturaDetalji>();
        List<NarudzbenicaDetalji> listaStavkiNarudzbenice = new ArrayList<NarudzbenicaDetalji>();
        listaStavkiNarudzbenice = NarudzbenicaDetalji.findByParamsStavke("S.SIFRA_NARUDZBENICE", Integer.valueOf(id));
        NarudzbenicaDetalji nar = NarudzbenicaDetalji.findByPrimaryKey(Integer.valueOf(id));

        Katalog k = Katalog.findByPrimaryKey(nar.getRedniBrojKataloga());
        String katalogIMe = k.getNazivKataloga();

        List<ArtikalDetalji> list = ArtikalDetalji.findAll();

        Integer aktuelnaCena = 0;
        String jedMer = "";

        for (NarudzbenicaDetalji nd : listaStavkiNarudzbenice) {

            for (ArtikalDetalji li : list) {

                Integer broj1 = nd.getArtikalId();
                Integer broj2 = li.getArtikalId();

                if (broj1.equals(broj2)) {
                   
                    aktuelnaCena = li.getAktuelnaCena();
                    jedMer = li.getNazivJediniceMere();
                }
            }

            ProfakturaDetalji pd = new ProfakturaDetalji();

            pd.setNazivArtikla(nd.getNazivArtikla());
            pd.setCena(aktuelnaCena);
            pd.setNazivKataloga(katalogIMe);
            pd.setKolicina(nd.getKolicina());
            pd.setCenaStavke(aktuelnaCena * nd.getKolicina());
            pd.setNazivJm(jedMer);

            listaStavkiProfakture.add(pd);
        }

        Integer ukupno = 0;

        for (ProfakturaDetalji lis : listaStavkiProfakture) {

            ukupno += lis.getCenaStavke();

        }

        request.setAttribute("ukupno_novaca", ukupno);
        request.setAttribute("listastavkiprofakture", listaStavkiProfakture);
        request.setAttribute("narudzbenica_new", nar);

        Integer brojac = 0;

        request.getSession().setAttribute("brojac", brojac);

        return "KreiranjeProfakture";

    }

}
