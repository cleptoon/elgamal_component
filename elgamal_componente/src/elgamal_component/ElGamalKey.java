/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_component;

/*  
 * ElGamal Key Class  
 *  http://en.pudn.com/downloads85/sourcecode/crypt/detail328862_en.html#
 * @author Ge ZHANG (2937207)  
 * @login name: gz847  
 * @version 1.00 07/08/11*/
import java.math.BigInteger;
import java.security.*;

public class ElGamalKey implements Key {

    private BigInteger mP, mG;

    protected ElGamalKey(BigInteger g, BigInteger p) {
        mG = g;
        mP = p;
    }

    protected BigInteger getG() {
        return mG;
    }

    protected BigInteger getP() {
        return mP;
    }

    public String getAlgorithm() {
        return "ElGamal";
    }

    public String getFormat() {
        return "NONE";
    }

    public byte[] getEncoded() {
        return null;
    }
}
