package hr.fer.opp.onedayjob.Servisi;

import java.util.List;

import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Poruka;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Toshiba on 10-Jan-18.
 */

public interface PorukaServis {
    @GET("/poruke")
    Call<List<Poruka>> getPoruke(@Query("korisnikID1") Korisnik korisnik1, @Query("korisnikID2") Korisnik korisnik2);

    @POST("/posaljiPoruku")
    Call<Poruka> posaljiPoruku(@Body Poruka poruka);
}
