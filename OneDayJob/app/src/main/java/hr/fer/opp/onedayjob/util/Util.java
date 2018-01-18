package hr.fer.opp.onedayjob.util;

import android.os.Looper;
import android.util.Log;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.onedayjob.Models.Kategorija2;
import retrofit2.http.HTTP;

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

    public static int slikaZa(List<Kategorija2> kategorije){
        return kategorije.get(0).getSlikaID();
    }

    public static String datumIz(String vrijemeString) {
        return vrijemeString.substring(0,10).replaceAll("-", "/");
    }

    public static Kategorija2 dajEnumKategorije(Long kategorijaID) {
        return Kategorija2.dajKategoriju(kategorijaID);
    }


}
