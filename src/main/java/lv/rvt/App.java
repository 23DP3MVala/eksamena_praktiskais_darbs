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

    public void IzvaditPiezimi() {
        if (piezimes.isEmpty()) {
            System.out.println("Netika atrasta neviena piezīme");
            return;
        }
        for (int i = 0; i < piezimes.size(); i++) {
            System.out.println((i+1) + piezimes.get(i).getVirsraksts());
        }
    }

    public void paraditPiezimi() {
        IzvaditPiezimi();
        System.out.println("Ievadi piezīmes numuru: ");
        int index = Integer.parseInt(input.nextLine());
        input.nextLine();

        if (index > 0 && index <= piezimes.size()) {
            System.out.println(piezimes.get(index - 1));
        }
        else {
            System.out.println("Nederīga izvēle.");
        }
    }
}
