package hr.fer.opp.onedayjob.Servisi;

import java.util.List;

import hr.fer.opp.onedayjob.Models.Korisnik;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Toshiba on 10-Jan-18.
 */

public interface KorisnikServis {
    /**
     * Gets the user.
     *
     * @param path relative path to which the user is retreived
     * @return the user
     */
    @GET("/{path}")
    Call<Korisnik> getKorisnik(@Path("path") String path);

    @GET("/{path}")
    Call<List<Korisnik>> getKorisnici(@Path("path") String path);

    @GET("/{path}")
    Call<List<Long>> getRazgovoriId(@Path("path") String path);

    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("/{path}")
    Call<Korisnik> registerKorisnik(@Path("path") String path, @Body Korisnik korisnik);

}
