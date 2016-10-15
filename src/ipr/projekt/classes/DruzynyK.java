/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ipr.projekt.classes;

import ipr.projekt.program.MySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michal
 */
public class DruzynyK {
    
    MySql mysql = MySql.getInstance();
    private static DruzynyK instane = new DruzynyK();
    
    public String[] getDruzyny(){
        ArrayList<String> druzyny = new ArrayList<String>();
        
        String sb = "SELECT nazwa FROM druzyny ORDER BY nazwa";
        
        try {
            PreparedStatement ps = mysql.getConnection().prepareStatement(sb);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                druzyny.add(rs.getString("nazwa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeczK.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int ile = druzyny.size();
        
        String[] drArray = new String[ile];
        for(int i = 0; i < ile; ++i){
            drArray[i] = druzyny.get(i);
        }
        
        return drArray;
    }
    
    public static DruzynyK getInstance() {
        return instane;
    }
}
