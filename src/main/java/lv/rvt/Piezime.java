package lv.rvt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Piezime {
    private String virsraksts;
    private String saturs;
    private String laiks;

    public Piezime() {}

    public Piezime (String virsraksts, String saturs) {
        this.virsraksts = virsraksts;
        this.saturs = saturs;
        this.laiks = getLaiks();
    }

    public String getVirsraksts() {
        return virsraksts;
    }

    public String getSaturs() {
        return saturs;
    }

    public String getLaiks() {
        DateTimeFormatter formatetajs = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime tgd = LocalDateTime.now();
        String laiks = tgd.format(formatetajs);
        return laiks;
    }

    @Override
    public String toString() {
        return "Virsraksts: " + virsraksts + "\nSaturs: " + saturs + "\nIzveidots: " + laiks;
    }
}
