/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.classes;

import ipr.projekt.model.Zaklad;
import ipr.projekt.model.ZakladSkladowy;
import ipr.projekt.program.MySql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michal
 */
public class Lista_zakladow {

    private static Lista_zakladow instance = new Lista_zakladow();

    public static Lista_zakladow getInstance() {
        return instance;
    }

    public void addZaklad(Zaklad zaklad) {
        Connection con = MySql.getInstance().getConnection();
        StringBuilder sb = new StringBuilder("INSERT INTO zaklady (id_klienta,stawka,kurs_zakladu, data) VALUES(?,?,?,DATE(NOW()))");
        int index = 0;
        int id_zakladu = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(++index, Klient.getInstance().getId());
            ps.setFloat(++index, zaklad.getStawka());
            ps.setFloat(++index, zaklad.getKursZakladu());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_zakladu = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lista_zakladow.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (id_zakladu != 0) {
            ZakladSkladowy[] zakladSkladowy = zaklad.getZakladySkladowe();
            int size = zakladSkladowy.length;
            for (int i = 0; i < size; ++i) {
                if (addMeczDoZakladu(id_zakladu, con, zakladSkladowy[i])) {
                    Zaklad.getInstance().deleteZakladSkladowy(zakladSkladowy[i]);
                }
            }
        }

    }

    public Object[][] getMeczeZaklady(String sortStr, boolean sort) {
        Zaklad zaklad = Zaklad.getInstance();
        ZakladSkladowy[] zaklady = zaklad.getZakladySkladowe();

        if(zaklady.length == 0){
            JOptionPane.showMessageDialog(null,
                    "Brak zakładów",
                    "Brak zakładów",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        StringBuilder meczeStr = new StringBuilder();

        for (int i = 0; i < zaklady.length; ++i) {
            meczeStr.append("'" + zaklady[i].getIdMeczu() + "',");
        }
        meczeStr.deleteCharAt(meczeStr.length() - 1);
        String sortString = "";

        if (sort) {
            sortString = "ORDER BY " + sortStr;
        }

        ArrayList<ArrayList<Object>> tabela = new ArrayList<ArrayList<Object>>();
        StringBuilder sb = new StringBuilder("SELECT m.id_meczu AS id, m.data, d1.nazwa AS gosc, d2.nazwa AS gosp ");
        sb.append("FROM mecze AS m ");
        sb.append("INNER JOIN druzyny AS d1 ON m.id_gosci = d1.id_druzyny ");
        sb.append("INNER JOIN druzyny AS d2 ON m.id_gospodarzy = d2.id_druzyny ");
        sb.append("WHERE m.id_meczu IN (" + meczeStr.toString() + ") ");
        sb.append(sortString);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MySql.getInstance().getConnection().prepareStatement(sb.toString());
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id_meczu = rs.getInt("id");
                Date data = rs.getDate("data");
                ArrayList<Object> wierszArray = new ArrayList<Object>();
                wierszArray.add(rs.getString("gosp"));
                wierszArray.add(rs.getString("gosc"));
                wierszArray.add(data);
                wierszArray.add(zaklad.getMeczById(id_meczu).getKurs());
                wierszArray.add(zaklad.getMeczById(id_meczu).getWynik());
                wierszArray.add(true);
                wierszArray.add(id_meczu);
                tabela.add(wierszArray);
            }
        } catch (SQLException ex) {

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

    private boolean addMeczDoZakladu(int id_zakladu, Connection con, ZakladSkladowy zs) {
        boolean status = false;
        StringBuilder sb = new StringBuilder("INSERT INTO mecze_zaklady "
                + "(id_zakladu,id_meczu,obstawiony_wynik, kurs) "
                + "VALUES(?,?,?,?)");
        int index = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sb.toString());
            ps.setInt(++index, id_zakladu);
            ps.setInt(++index, zs.getIdMeczu());
            ps.setString(++index, zs.getWynik());
            ps.setFloat(++index, zs.getKurs());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public Object[][] getZakładyHist(String druzynaA, String druzynaB, String sortStr, Date dataOd, Date dataDo, boolean sort, boolean filtr) {
       
        Connection con = MySql.getInstance().getConnection();
        
        boolean wyszukajDruzyny = false;
        String where = "";
        String filtrStr = "";
        String sortString = "";
        int index = 0;
        if (druzynaA != null && !druzynaA.equals("")) {
            wyszukajDruzyny = true;
            where = "AND (d1.nazwa LIKE ? AND d2.nazwa LIKE ?) OR (d1.nazwa LIKE ? AND d2.nazwa LIKE ?) ";
        }
        if (sort) {
            sortString = "ORDER BY " + sortStr;
        }
        if (filtr) {
            filtrStr = " AND WHERE z.data >= ? AND z.data <= ? ";
        }
        ArrayList<ArrayList<Object>> tabela = new ArrayList<ArrayList<Object>>();
        StringBuilder sb = new StringBuilder("SELECT DISTINCT z.id_zakladu AS id, z.stawka, z.kurs_zakladu AS kurs, z.data ");
        sb.append("FROM zaklady AS z ");
        sb.append("INNER JOIN mecze_zaklady AS mz ON mz.id_zakladu = z.id_zakladu ");
        sb.append("INNER JOIN mecze AS m ON mz.id_meczu = m.id_meczu ");
        sb.append("INNER JOIN druzyny AS d1 ON m.id_gospodarzy = d1.id_druzyny ");
        sb.append("INNER JOIN druzyny AS d2 ON m.id_gosci = d2.id_druzyny ");
        sb.append("WHERE id_klienta=? ");
        sb.append(where);
        sb.append(filtrStr);
        sb.append(sortString);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sb.toString());
            ps.setInt(++index, Klient.getInstance().getId());
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
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            int iter = 0;
            while (rs.next()) {
                Date data = rs.getDate("data");
                ArrayList<Object> wierszArray = new ArrayList<Object>();
                wierszArray.add(rs.getInt("id"));
                wierszArray.add(data);
                wierszArray.add(rs.getFloat("stawka"));
                wierszArray.add(rs.getFloat("kurs"));
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
}
