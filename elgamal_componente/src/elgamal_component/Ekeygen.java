/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_component;

/*  
 * ElGamal Signature Sample  
 * login name: gz847  
 * @author Ge ZHANG (2937207)  
 * @version 1.00 07/08/11*/
import java.math.BigInteger;
import java.security.*;

public class Ekeygen {

    static ElGamalSignature esign;
    static ElGamalEncryption encrypt;
    static ElGamalKeyPairGenerator ekpg;
    static KeyPair epair;
        
    public static void main(String[] args) {
        ElGamalPrivateKey eprik;
        ElGamalPublicKey epubk;
        ekpg = new ElGamalKeyPairGenerator();
        ekpg.initialize(64, new SecureRandom());
        epair = ekpg.generateKeyPair();

        eprik = (ElGamalPrivateKey) epair.getPrivate();
        epubk = (ElGamalPublicKey) epair.getPublic();


        System.out.println("Private Key: k = " + eprik.getK() + ", g = " + eprik.getG() + ", p = " + eprik.getP());
        System.out.println("Public Key: y = " + epubk.getY() + ", g = " + epubk.getG() + ", p = " + epubk.getP());

        try {
            
            String str = "12345";
            
            //Firma mensaje
            esign = new ElGamalSignature();
            esign.engineInitSign(eprik);
            
            String hash_str = MD5.md5("12345");
            System.out.println("Message : " + str);
            esign.engineUpdate(hash_str.getBytes(), 0, hash_str.length());
            byte[] signedb = esign.engineSign();
            System.out.println("Signed Message : " + new BigInteger(signedb));

            //Encripta mensaje
            encrypt = new ElGamalEncryption();
            encrypt.engineInitEncrypt(epubk);
            BigInteger msg_num = new BigInteger(str);

            BigInteger[] encryptedmsg = encrypt.engineEncrypt(msg_num);

            System.out.println("Encrpyted Message: " + encryptedmsg[0] + "," + encryptedmsg[1]);

            //Desencriptar mensaje
            BigInteger C;
            encrypt.engineInitDecrypt(eprik);
            C = encrypt.engineDecrypt(encryptedmsg);
            System.out.println("Decrypted Message: " + C);

            //Hash del mensaje
            String hash_decrypt_str = MD5.md5(C.toString());
            System.out.println("Hashed decrypted message: " + hash_decrypt_str);

            //Verificat mensaje
            esign.engineInitVerify(epubk);
            esign.engineUpdate(hash_decrypt_str.getBytes(), 0, hash_decrypt_str.length());
            boolean veri = esign.engineVerify(signedb);
            if (veri) {
                System.out.println("Verification Succeeded!");
            } else {
                System.out.println("Verification Failed!");
            }
            
            
        } catch (InvalidKeyException ike) {
            System.out.println("Invalid Key!");
        } catch (SignatureException s) {
            System.out.println("Signature Error Occured!");
        }
    }
}
