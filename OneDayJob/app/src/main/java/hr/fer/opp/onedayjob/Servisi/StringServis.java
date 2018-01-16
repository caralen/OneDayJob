package hr.fer.opp.onedayjob.Servisi;

import hr.fer.opp.onedayjob.Models.Korisnik;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Toshiba on 10-Jan-18.
 */

public interface StringServis {
    /**
     * Gets the user.
     *
     * @param path relative path to which the user is retreived
     * @return the user
     */
    @GET("/{path}")
    Call<String> getString(@Path("path") String path);
}
