package lv.rvt;

import java.time.format.DateTimeFormatter;

public class Piezime {
    private String virsraksts;
    private String saturs;
    private String laiks;

    public Piezime() {}

    public Piezime (String virsraksts, String saturs) {
        this.virsraksts = virsraksts;
        this.saturs = saturs;
        this.laiks = generateLaiku();
    }

    public String getVirsraksts() {
        return virsraksts;
    }

    public String getSaturs() {
        return saturs;
    }

    public String getLaiks() {
        return laiks;
    }

    public String generateLaiku() {
        DateTimeFormatter formatetajs = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        java.time.ZonedDateTime tgd = java.time.ZonedDateTime.now(java.time.ZoneId.of("Europe/Riga"));
        String laiks = tgd.format(formatetajs);
        return laiks;
    }

    @Override
    public String toString() {
        return "Virsraksts: " + virsraksts + "\nSaturs: " + saturs + "\nIzveidots: " + laiks;
    }
}
