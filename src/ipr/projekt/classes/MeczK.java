/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.classes;

import ipr.projekt.model.Zaklad;
import ipr.projekt.program.MySql;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;

/**
 *
 * @author Michal
 */
public class MeczK {

    MySql mysql = MySql.getInstance();
    private static MeczK instane = new MeczK();

    public Object[][] getMecze(String druzynaA, String druzynaB, String sortStr, Date dataOd, Date dataDo, boolean sort, boolean filtr) {
        boolean wyszukajDruzyny = false;
        String where = "";
        String filtrStr = "";
        String sortString = "";
        int index = 0;
        if (druzynaA != null && !druzynaA.equals("")) {
            wyszukajDruzyny = true;
            where = "WHERE (d1.nazwa LIKE ? AND d2.nazwa LIKE ?) OR (d1.nazwa LIKE ? AND d2.nazwa LIKE ?) ";
        }
        if (sort) {
            sortString = "ORDER BY " + sortStr;
        }
        if (filtr) {
            filtrStr = "WHERE m.data >= ? AND m.data <= ?";
        }
        ArrayList<ArrayList<Object>> tabela = new ArrayList<ArrayList<Object>>();
        StringBuilder sb = new StringBuilder("SELECT m.id_meczu AS id, m.wynik, m.data, d1.nazwa AS gosc, d2.nazwa AS gosp ");
        sb.append("FROM mecze AS m ");
        sb.append("INNER JOIN druzyny AS d1 ON m.id_gosci = d1.id_druzyny ");
        sb.append("INNER JOIN druzyny AS d2 ON m.id_gospodarzy = d2.id_druzyny ");
        sb.append(where);
        sb.append(filtrStr);
        sb.append(sortString);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = mysql.getConnection().prepareStatement(sb.toString());
            if (wyszukajDruzyny) {
                ps.setString(++index, "%" + druzynaA + "%");
                ps.setString(++index, "%" + druzynaB + "%");
                ps.setString(++index, "%" + druzynaB + "%");
                ps.setString(++index, "%" + druzynaA + "%");
            }
            if (filtr) {
                ps.setDate(++index, dataOd);
                ps.setDate(++index, dataDo);
            }
            System.out.println(ps);
            rs = ps.executeQuery();
            int iter = 0;
            while (rs.next()) {
//                SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
                Date data = rs.getDate("data");
                ArrayList<Object> wierszArray = new ArrayList<Object>();
                wierszArray.add(++iter);
                wierszArray.add(rs.getString("gosp"));
                wierszArray.add(rs.getString("gosc"));
                wierszArray.add(data);
                wierszArray.add(rs.getString("wynik"));
                wierszArray.add(rs.getString("id"));
                tabela.add(wierszArray);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeczK.class.getName()).log(Level.SEVERE, null, ex);
        }
        int tableX = tabela.size();
        int tableY = 0;
        if (tableX > 0) {
            tableY = tabela.get(0).size();
            Object[][] toRet = new Object[tableX][tableY];
            for (int i = 0; i < tableX; ++i) {
                for (int j = 0; j < tableY; ++j) {
                    toRet[i][j] = tabela.get(i).get(j);
                }
            }
            return toRet;
        }
        return null;
    }

    public static MeczK getInstance() {
        return instane;
    }

    public Object[][] getMeczeZaklad(String druzynaA, String druzynaB, String sortStr, Date dataOd, Date dataDo, boolean sort, boolean filtr) {
        boolean wyszukajDruzyny = false;
        String where = "";
        String filtrStr = "";
        String sortString = "";
        int index = 0;
        if (druzynaA != null && !druzynaA.equals("")) {
            wyszukajDruzyny = true;
            where = "WHERE (d1.nazwa LIKE ? AND d2.nazwa LIKE ?) OR (d1.nazwa LIKE ? AND d2.nazwa LIKE ?) ";
        }
        if (sort) {
            sortString = "ORDER BY " + sortStr;
        }
        if (filtr) {
            filtrStr = "WHERE m.data >= ? AND m.data <= ?";
        }
        ArrayList<ArrayList<Object>> tabela = new ArrayList<ArrayList<Object>>();
        StringBuilder sb = new StringBuilder("SELECT m.id_meczu AS id, m.id_gospodarzy, m.id_gosci, m.data, d1.nazwa AS gosc, d2.nazwa AS gosp ");
        sb.append("FROM mecze AS m ");
        sb.append("INNER JOIN druzyny AS d1 ON m.id_gosci = d1.id_druzyny ");
        sb.append("INNER JOIN druzyny AS d2 ON m.id_gospodarzy = d2.id_druzyny ");
        sb.append(where);
        sb.append("WHERE m.data>DATE(NOW())");
        sb.append(filtrStr);
        sb.append(sortString);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = mysql.getConnection().prepareStatement(sb.toString());
            if (wyszukajDruzyny) {
                ps.setString(++index, "%" + druzynaA + "%");
                ps.setString(++index, "%" + druzynaB + "%");
                ps.setString(++index, "%" + druzynaB + "%");
                ps.setString(++index, "%" + druzynaA + "%");
            }
            if (filtr) {
                ps.setDate(++index, dataOd);
                ps.setDate(++index, dataDo);
            }
            System.out.println(ps);
            rs = ps.executeQuery();
            int iter = 0;
            Zaklad zaklad = Zaklad.getInstance();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date data = rs.getDate("data");
                ArrayList<Object> wierszArray = new ArrayList<Object>();
                wierszArray.add(rs.getString("gosp"));
                wierszArray.add(rs.getString("gosc"));
                wierszArray.add(data);
                wierszArray.add(0);
                if (zaklad.zakladIstniejeById(id)) {
                    wierszArray.add(Boolean.TRUE);
                } else {
                    wierszArray.add(Boolean.FALSE);
                }
                wierszArray.add(id);
                tabela.add(wierszArray);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeczK.class.getName()).log(Level.SEVERE, null, ex);
        }
        int tableX = tabela.size();
        int tableY = 0;
        if (tableX > 0) {
            tableY = tabela.get(0).size();
            Object[][] toRet = new Object[tableX][tableY];
            for (int i = 0; i < tableX; ++i) {
                for (int j = 0; j < tableY; ++j) {
                    toRet[i][j] = tabela.get(i).get(j);
                }
            }
            return toRet;
        }
        return null;
    }

    public Object[][] getMeczStat(int idMeczu) {
        ArrayList<ArrayList<Object>> tabela;
        tabela = new ArrayList<ArrayList<Object>>();

        StringBuilder sb = new StringBuilder("SELECT DISTINCT m.wynik, m.data, m.posiadanie_pilki,  "
                + "d1.nazwa AS gosc, d2.nazwa AS gosp, "
                + "COUNT(k1.id_kartki) AS kartki_z, COUNT(k2.id_kartki) AS kartki_c ");
        sb.append("FROM mecze AS m ");
        sb.append("INNER JOIN druzyny AS d1 ON m.id_gosci = d1.id_druzyny ");
        sb.append("INNER JOIN druzyny AS d2 ON m.id_gospodarzy = d2.id_druzyny ");
        sb.append("LEFT JOIN mecze_zawodnicy_kartki AS mzkz ON mzkz.id_meczu = m.id_meczu ");
        sb.append("LEFT JOIN kartki AS k1 ON mzkz.id_kartki = k1.id_kartki AND k1.rodzaj = 'żółta' ");
        sb.append("LEFT JOIN mecze_zawodnicy_kartki AS mzkc ON mzkc.id_meczu = m.id_meczu ");
        sb.append("LEFT JOIN kartki AS k2 ON mzkc.id_kartki = k2.id_kartki AND k2.rodzaj = 'czerwona' ");
        sb.append("WHERE m.id_meczu =? ");
        sb.append("GROUP BY m.id_meczu");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = mysql.getConnection().prepareStatement(sb.toString());
            ps.setInt(1, idMeczu);
            System.out.println(ps); //michal ppk
            rs = ps.executeQuery();
            while (rs.next()) {
                Date data = rs.getDate("data");
                ArrayList<Object> wierszArray = new ArrayList<Object>();
                wierszArray.add(rs.getString("gosp"));
                wierszArray.add(rs.getString("gosc"));
                wierszArray.add(data);
                wierszArray.add(1);
                wierszArray.add(rs.getString("wynik"));
                wierszArray.add(rs.getString("posiadanie_pilki"));
                wierszArray.add(2);
                wierszArray.add(rs.getInt("kartki_z"));
                wierszArray.add(rs.getInt("kartki_c"));
                tabela.add(wierszArray);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeczK.class.getName()).log(Level.SEVERE, null, ex);
        }

        int tableX = tabela.size();
        int tableY = 0;
        if (tableX > 0) {
            tableY = tabela.get(0).size();
            if (tableY > 0) {
                Object[][] toRet = new Object[tableX][tableY];
                for (int i = 0; i < tableX; ++i) {
                    for (int j = 0; j < tableY; ++j) {
                        toRet[i][j] = tabela.get(i).get(j);
                    }
                }
                return toRet;
            }
        }
        return null;
    }

    public Object[][] getMeczSklad(int idMeczu) {
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
