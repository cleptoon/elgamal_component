/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_component;

import elgamal_component.ElgamalController;
import component_tool.CustomClassLoader;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.util.Random;

//      System.out.println("class loader for Integer: "
//            + Integer.class.getClassLoader());
//        System.out.println("class loader for BlowfishCipher: "
//            + com.sun.crypto.provider
//            .BlowfishCipher.class.getClassLoader());
//        System.out.println("class loader for this class: "
//            + TestComponent.class.getClassLoader());
/**
 *
 * @author wbejarano
 */
public class IntegerPrinterTest {

    public static void main(String[] args) throws Exception {
        ElgamalController a = new ElgamalController("102878210466625684448564152718275770164400015795599081323142484261174204395771542931556573005457548994300390542836390656590342719190546003475313704107511340660051255130886898195350488634781241593291629806029940967099127171111741121800016354471000128327690358673195988965015732141003244933469931582512668226603");
        byte[] encryptkey = a.signKey("hola mund000o");
        a.signVerify(encryptkey,"hola mundo" );
    }
}
