/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_component;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.SignatureException;

/**
 *
 * @author wbejarano
 *
 */
public class ElgamalController {

    // Decalaraci{on de variables globales
    static ElGamalSignature esign;
    static ElGamalEncryption encrypt;
    static ElGamalKeyPairGenerator ekpg;
    static KeyPair epair;
    static SecureRandom s;
    static BigInteger seed;
    private ElGamalPrivateKey eprik;
    private ElGamalPublicKey epubk;


    public void init(String random_number) {
        
        seed = new BigInteger(random_number);

        ekpg = new ElGamalKeyPairGenerator();
        ekpg.initialize(16, new SecureRandom());


        if (seed.compareTo(new BigInteger("0")) == 0) {
            epair = ekpg.generateKeyPair();
        } else {
            epair = ekpg.generateKeyPair(seed);
        }
        eprik = (ElGamalPrivateKey) epair.getPrivate();
        epubk = (ElGamalPublicKey) epair.getPublic();

//        System.out.println("Private Key: k = " + eprik.getK() + ", g = " + eprik.getG() + ", p = " + eprik.getP());
//        System.out.println("Public Key: y = " + epubk.getY() + ", g = " + epubk.getG() + ", p = " + epubk.getP());
    }

    public BigInteger[] encryptKey(String key) throws InvalidKeyException {
        encrypt = new ElGamalEncryption();
        encrypt.engineInitEncrypt(epubk);
        BigInteger msg_num = new BigInteger(key);
        BigInteger[] encryptedKey = encrypt.engineEncrypt(msg_num);
        System.out.println("Encrpyted Message key: " + encryptedKey[0] + "," + encryptedKey[1]);
        return encryptedKey;
    }

    public String encryptText(String message) throws InvalidKeyException {
        encrypt = new ElGamalEncryption();
        encrypt.engineInitEncrypt(epubk);
        String encryptedText = encrypt.engineTextEncrypt(message);
        //System.out.println("Encrpyted Message Text: " + encryptedText);
        return encryptedText;
    }

    public BigInteger decryptKey(BigInteger[] encryptedmsg) throws InvalidKeyException {
        BigInteger C;
        encrypt.engineInitDecrypt(eprik);
        C = encrypt.engineDecrypt(encryptedmsg);
        System.out.println("Decrypted Message: " + C);
        return C;
    }

    public String decryptText(String encryptedmsg) throws InvalidKeyException {
        String C;
        encrypt.engineInitDecrypt(eprik);
        C = encrypt.engineTextDecrypt(encryptedmsg);
        //System.out.println("Decrypted Text Message: " + C);
        return C;
    }

    public byte[] signKey(String str) throws InvalidKeyException, SignatureException {
        esign = new ElGamalSignature();
        esign.engineInitSign(eprik);

        String hash_str = MD5.md5(str);
        System.out.println("Message : " + str);
        esign.engineUpdate(hash_str.getBytes(), 0, hash_str.length());
        byte[] signedb = esign.engineSign();
        //System.out.println("Signed Message : " + new BigInteger(signedb));
        return signedb;
    }

    public boolean signVerify(byte[] signedb, String mensaje) throws InvalidKeyException, SignatureException {
        String hash_decrypt_str = MD5.md5(mensaje);
        System.out.println("Hashed decrypted message: " + hash_decrypt_str);
        //Verificat mensaje
        esign.engineInitVerify(epubk);
        esign.engineUpdate(hash_decrypt_str.getBytes(), 0, hash_decrypt_str.length());
        boolean veri = esign.engineVerify(signedb);
        if (veri) {
            //System.out.println("Verification Succeeded!");
            return true;
        } else {
            //System.out.println("Verification Failed!");
            return false;
        }
    }
}
