package lv.rvt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Piezime {
    private String virsraksts;
    private String saturs;

    public Piezime (String virsraksts, String saturs) {
        this.virsraksts = virsraksts;
        this.saturs = saturs;
    }

    public String gatVirsraksts() {
        return virsraksts;
    }

    public String geSaturs() {
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
        return "Virsraksts: " + virsraksts + "\nSaturs: " + saturs + "\nIzveidots: " + getLaiks();
    }
}
