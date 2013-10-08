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
        
        
        BigInteger bi1, bi2;

        bi1 = new BigInteger("4918");

        // assign nextProbablePrime value of bi1 to bi2
	bi2 = bi1.nextProbablePrime();

        String str = "Next probable prime after " + bi1 +" is " +bi2;

	// print bi2 value
	System.out.println( str );
        //System.exit(0);
        
        //ElgamalController a = new ElgamalController("102878210466625684448564152718275770164400015795599081323142484261174204395771542931556573005457548994300390542836390656590342719190546003475313704107511340660051255130886898195350488634781241593291629806029940967099127171111741121800016354471000128327690358673195988965015732141003244933469931582512668226603");
        ElgamalController a = new ElgamalController();
        //"2147483647"
        //ElgamalController a = new ElgamalController("1028028782104602878210460287821046028782104602878210460287821046028782104678210466625645646541456135645676872844134273");
        //ElgamalController a = new ElgamalController();
        //a.decryptText(a.encryptText("hola mundo"));
        
        byte[] encryptkey = a.signKey("hola mundo");
        a.signVerify(encryptkey,"hola mundo");
    }
}
