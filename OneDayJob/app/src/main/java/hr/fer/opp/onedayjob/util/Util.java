package hr.fer.opp.onedayjob.util;

import java.util.List;

import hr.fer.opp.onedayjob.Models.Kategorija;

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

    public static int slikaZa(List<Kategorija> kategorije){
        return kategorije.get(0).getSlikaID();
    }

    public static String datumIz(String vrijemeString) {
        return vrijemeString.substring(0,10).replaceAll("-", "/");
    }
}
