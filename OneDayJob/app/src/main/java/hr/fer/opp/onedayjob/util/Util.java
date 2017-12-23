package hr.fer.opp.onedayjob.util;

/**
 * Created by Toshiba on 21-Dec-17.
 */

public class Util {

    /**
     * Računa hash od maila kao četvorznamenkasti kraj od hashCode metode
     */
    public static int calculateVerificationHash(String email){
        return Math.abs(email.hashCode()%10000);
    }
    public static String calculatePasswordHash(String password){
        return password;
    };
}
