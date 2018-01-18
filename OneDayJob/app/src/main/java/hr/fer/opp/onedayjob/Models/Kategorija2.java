package hr.fer.opp.onedayjob.Models;

import hr.fer.opp.onedayjob.R;

/**
 * Created by Toshiba on 09-Jan-18.
 */

public enum Kategorija2 {
    INSTRUKCIJE(1, "Instrukcije", R.drawable.instrukcije),
    FIZICKI_POSAO(2, "Fizički posao", R.drawable.fizicki_rad),
    CUVANJE_ZIVOTINJE(3, "Čuvanje životinja", R.drawable.cuvanje_zivotinja),
    CUVANJE_DJETETA(4, "Čuvanje djece", R.drawable.cuvanje_djece),
    CISCENJE(5, "Čišćenje", R.drawable.ciscenje),
    VOZNJA(6, "Vožnja", R.drawable.voznja);

    private String ime;
    private int slikaID;


    private long id;
    public long getId() {
        return id;
    }


    Kategorija2(long id, String ime, int slikaID) {
        this.ime = ime;
        this.slikaID = slikaID;
        this.id=id;
    }

    public String getIme() {
        return ime;
    }
    public int getSlikaID(){return slikaID;}

    public static Kategorija2 dajKategoriju(long id){
        if(id==1){
            return INSTRUKCIJE;
        } else if(id==2){
            return FIZICKI_POSAO;
        } else if(id==3){
            return CUVANJE_ZIVOTINJE;
        } else if(id==4){
            return CUVANJE_DJETETA;
        } else if(id==5){
            return CISCENJE;
        } else if(id==6){
            return VOZNJA;
        }
        return null;
    }


    public static long KategorijaStringToID(String kategorija){
        switch (kategorija) {
            case "Kategorija":
                return 0;

            case "Instrukcije":
                return 1;

            case "Fizički posao":
                return 2;

            case "Čuvanje životinja":
                return 3;

            case "Čuvanje djece":
                return 4;

            case "Čišćenje":
                return 5;

            case "Vožnja":
                return 6;

            default:
                return 404;

        }

    }
}
