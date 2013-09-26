/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_component;

/*  
 * ElGamal KeypairGenerator Method  
 *  
 * @author Ge ZHANG (2937207)  
 * @login name: gz847  
 * @version 1.00 07/08/11*/

import java.math.BigInteger;
import java.security.*;
import java.util.Random;

public class ElGamalKeyPairGenerator extends KeyPairGeneratorSpi {

    private int mStrength = 0;
    private SecureRandom mSecureRandom = null;
    private BigInteger mSecureBiginteger;
    private static final BigInteger TWO = BigInteger.valueOf(2);

    @Override
    public void initialize(int strength, SecureRandom random) {
        mStrength = strength;
        mSecureRandom = random;
    }

    public void initialize(int strength) {
        mStrength = strength;

    }

    @Override
    public KeyPair generateKeyPair() {
        if (mSecureRandom == null) {
            mStrength = 1024;
            mSecureRandom = new SecureRandom();
        }
        BigInteger p = new BigInteger(mStrength, 16, mSecureRandom);
        BigInteger g = new BigInteger(mStrength - 1, mSecureRandom);
        BigInteger k = new BigInteger(mStrength - 1, mSecureRandom);
        BigInteger y = g.modPow(k, p);

        ElGamalPublicKey publicKey = new ElGamalPublicKey(y, g, p);
        ElGamalPrivateKey privateKey = new ElGamalPrivateKey(k, g, p);
        return new KeyPair(publicKey, privateKey);
    }

    public KeyPair generateKeyPair(BigInteger q) {
        mSecureBiginteger = q;

        if (mSecureRandom == null) {
            mStrength = 2048;
            mSecureRandom = new SecureRandom();
        }
        
        // p -- Es el módulo
        BigInteger p = calculateP(q);
        
        // g es el generador
        BigInteger g = calculateG(p);
        
        //k = numero aleatoreo para la clave privada
        BigInteger k = new BigInteger(mStrength - 1, mSecureRandom);
        
        //Clave pública
        BigInteger y = g.modPow(k, p);

        ElGamalPublicKey publicKey = new ElGamalPublicKey(y, g, p);
        ElGamalPrivateKey privateKey = new ElGamalPrivateKey(k, g, p);
        return new KeyPair(publicKey, privateKey);
    }

    public static BigInteger calculateP(BigInteger q) {
        
        return q.multiply(TWO).add(BigInteger.ONE);
    }
    
    
    // Se emplea el método de Euler para obtener un número primo dentro de P para
    // como generador
    private static BigInteger calculateG(BigInteger p) {
        Random random = new Random();
        BigInteger lowerBound = TWO;
        BigInteger upperBound = p.subtract(TWO);
        BigInteger result;
        do {
            result = new BigInteger(p.bitLength(), random);
        } while ((result.compareTo(lowerBound) < 0) || (result.compareTo(upperBound) > 0));
        return result.modPow(TWO, p);
    }
}