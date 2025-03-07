package lv.rvt;

import java.time.LocalDateTime;

public class Piezime {
    private String virsraksts;
    private String saturs;
    private LocalDateTime laiks;

    public Piezime (String virsraksts, String saturs) {
        this.virsraksts = virsraksts;
        this.saturs = saturs;
        this.laiks = LocalDateTime.now();
    }

    public String gatVirsraksts() {
        return virsraksts;
    }

    public String geSaturs() {
        return saturs;
    }

    public LocalDateTime getLaiks() {
        return laiks;
    }

    @Override
    public String toString() {
        return "Virsraksts: " + virsraksts + "\nSaturs: " + saturs + "Izveidots: " + laiks;
    }
}
