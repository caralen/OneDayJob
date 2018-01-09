package hr.fer.opp.onedayjob.Models;

import hr.fer.opp.onedayjob.R;

/**
 * Created by Toshiba on 09-Jan-18.
 */

public enum Kategorija {
    INSTRUKCIJE("Instrukcije", R.drawable.instrukcije),
    FIZICKI_POSAO("Fizički posao", R.drawable.fizicki_rad),
    CUVANJE_ZIVOTINJE("Čuvanje životinja", R.drawable.cuvanje_zivotinja),
    CUVANJE_DJETETA("Čuvanje djece", R.drawable.cuvanje_djece),
    CISCENJE("Čišćenje", R.drawable.ciscenje),
    VOZNJA("Vožnja", R.drawable.voznja);

    private String ime;
    private int slikaID;


    Kategorija(String ime, int slikaID) {
        this.ime = ime;
        this.slikaID = slikaID;
    }

    public String getIme() {
        return ime;
    }
    public int getSlikaID(){return slikaID;}
}
