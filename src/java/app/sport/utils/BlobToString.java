/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.sport.utils;

/**
 *
 * @author muanz
 */
public class BlobToString {
    public String generate(byte[] blob) {
        String str = "";
        //read bytes from ByteArrayInputStream using read method
        if (blob != null && blob.length > 0) {
            for (byte b : blob) {
                str = str + (char) b;
            }
        }
        return str;
    }
}
