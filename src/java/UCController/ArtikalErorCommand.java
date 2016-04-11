/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCController;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import model.Artikal;

public class ArtikalErorCommand implements Command {

    public String execute(HttpServletRequest request) throws SQLException {

        String aktuelnaCena = request.getParameter("aktuelnaCena");
        String idArtikla = request.getParameter("id_artikla");

        Artikal a = Artikal.findByPrimaryKey(Integer.valueOf(idArtikla));

        a.setAktuelnaCena(Integer.valueOf(aktuelnaCena));

        String poruka = a.update();

        request.setAttribute("poruka", poruka);

        return new ArtikalListaCommand().execute(request);

    }
}
