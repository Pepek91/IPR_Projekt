/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.classes;

import ipr.projekt.program.MySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krzysztof
 */
public class ZawodnicyK {

    MySql mysql = MySql.getInstance();
    private static ZawodnicyK instance = new ZawodnicyK();

    public static ZawodnicyK getInstance() {
        return instance;
    }

    public Object[][] getZawodnicyMecz(int idMeczu) {
        ArrayList<String> gosp = new ArrayList<String>();
        ArrayList<String> gosc = new ArrayList<String>();

        try {
            StringBuilder sb = new StringBuilder("SELECT DISTINCT CONCAT_WS(' ', zgosp.imie, zgosp.nazwisko) AS gosp ");//, CONCAT_WS(' ', zgosc.imie, zgosc.nazwisko) AS gosc
            sb.append("FROM mecze AS m ");
            sb.append("INNER JOIN mecze_zwodnicy_udzial AS mzu ON m.id_meczu = mzu.id_meczu ");

            sb.append("INNER JOIN kontrakty AS kgosp ON m.id_gospodarzy = kgosp.id_druzyny ");
            sb.append("INNER JOIN zawodnicy AS zgosp ON zgosp.id_zawodnika = kgosp.id_zawodnika ");

            sb.append("WHERE m.id_meczu =?");
            PreparedStatement ps = mysql.getConnection().prepareStatement(sb.toString());
            ps.setInt(1, idMeczu);
            System.out.println(ps); //michal ppk
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gosp.add(rs.getString("gosp"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeczK.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            StringBuilder sb = new StringBuilder("SELECT DISTINCT CONCAT_WS(' ', zgosc.imie, zgosc.nazwisko) AS gosc ");
            sb.append("FROM mecze AS m ");
            sb.append("INNER JOIN mecze_zwodnicy_udzial AS mzu ON m.id_meczu = mzu.id_meczu ");

            sb.append("LEFT JOIN kontrakty AS kgosc ON m.id_gosci = kgosc.id_druzyny AND kgosc.id_zawodnika = mzu.id_zawodnika ");
            sb.append("INNER JOIN zawodnicy AS zgosc ON zgosc.id_zawodnika = kgosc.id_zawodnika ");

            sb.append("WHERE m.id_meczu =?");
            PreparedStatement ps = mysql.getConnection().prepareStatement(sb.toString());
            ps.setInt(1, idMeczu);
            System.out.println(ps); //michal ppk
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gosc.add(rs.getString("gosc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeczK.class.getName()).log(Level.SEVERE, null, ex);
        }

        int sizeMax = Math.max(gosp.size(), gosc.size());

        Object[][] toRet = new Object[sizeMax][2];
        for (int i = 0; i < sizeMax; ++i) {
            try {
                toRet[i][0] = gosp.get(i);
            } catch (IndexOutOfBoundsException ex) {
                toRet[i][0] = "";
            }
            try {
                toRet[i][1] = gosc.get(i);
            } catch (IndexOutOfBoundsException ex) {
                toRet[i][1] = "";
            }
        }

        return toRet;
    }
}
