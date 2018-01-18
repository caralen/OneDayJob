package hr.fer.opp.onedayjob.Servisi;

import java.util.List;

import hr.fer.opp.onedayjob.Models.Korisnik;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("/korisnik/{id}/razgovori")
    Call<List<Long>> getRazgovori(@Path("id") Long id);

    @POST("/register")
    Call<Korisnik> registerKorisnik(@Body Korisnik korisnik);

    @POST("/korisnik/update")
    Call<Korisnik> updateKorisnik(@Body Korisnik korisnik);

    @GET("/verify")
    Call<String> verifyKorisnik(@Query("email") String email);
}
