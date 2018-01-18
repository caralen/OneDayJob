package hr.fer.opp.onedayjob.Servisi;

import java.util.List;

import hr.fer.opp.onedayjob.Models.Korisnik;
import hr.fer.opp.onedayjob.Models.Posao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Toshiba on 10-Jan-18.
 */

public interface PosaoServis {

    @GET("/posao/aktivni/")
    Call<List<Posao>> getAktivniPoslovi();

    @POST("/dodajPosao")
    Call<Korisnik> stvoriPosao(@Body Posao posao);
}
