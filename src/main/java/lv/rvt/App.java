package lv.rvt;
import java.util.*;

public class App {
    private List<Piezime> piezimes;
    private Scanner input;

    public App() {
        this.piezimes = FileHandler.Ieladet();
        this.input = new Scanner(System.in);
    }

    public void PievienotPiezimi() {
        System.out.println("Ievadi piezīmes virsrakstu: ");
        String virsraksts = input.nextLine();
        System.out.println("Ievadi piezīmes saturu: ");
        String saturs = input.nextLine();

        Piezime piezime = new Piezime(virsraksts, saturs);
        piezimes.add(piezime);
        FileHandler.Saglabat(piezimes);
        System.out.println("Piezīme saglabāta!");
    }
}
