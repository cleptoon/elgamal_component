/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_component;

    /*  
     * MD5 Hashing Class and Method  
     *  
     * @author Ge ZHANG (2937207)  
     * @login name: gz847  
     * @version 1.00 07/08/11*/   
       
    import java.util.*;   
    import java.io.*;   
    import java.security.*;   
       
    public class MD5{   
       
     public final static String md5(String s) {   
      char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
        'a', 'b', 'c', 'd', 'e', 'f' };   
      try {   
           byte[] strTemp = s.getBytes();   
           MessageDigest mdTemp = MessageDigest.getInstance("MD5");   
           mdTemp.update(strTemp);   
           byte[] md = mdTemp.digest();   
           int j = md.length;   
           char str[] = new char[j * 2];   
           int k = 0;   
           for (int i = 0; i < j; i++) {   
           byte byte0 = md[i];   
           str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
           str[k++] = hexDigits[byte0 & 0xf];   
       }   
       return new String(str);   
      }catch (Exception e){   
       return null;   
      }   
     }   
    }  