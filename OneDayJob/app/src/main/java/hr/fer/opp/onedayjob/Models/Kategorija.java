package hr.fer.opp.onedayjob.Models;

/**
 * Created by Toshiba on 09-Jan-18.
 */

public enum Kategorija {
    INSTRUKCIJE("Instrukcije"), FIZICKI_POSAO("Fizički posao"), CUVANJE_ZIVOTINJE("Čuvanje životinja"), CUVANJE_DJETETA("Čuvanje djece"), CISCENJE("Čišćenje"), VOZNJA("Vožnja");

    private String ime;
    Kategorija(String ime) {
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }
}
