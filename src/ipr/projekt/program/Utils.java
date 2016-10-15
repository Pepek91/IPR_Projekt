/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.program;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michal
 */
public class Utils {

    /**
     *
     * @param toConv
     * @return
     */
    static public String getMD5Sting(String toConv) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] passBytes = toConv.getBytes();
        byte[] passmd5 = md5.digest(passBytes);
        BigInteger bigInt = new BigInteger(1, passmd5);
        return bigInt.toString(16);
    }
}
