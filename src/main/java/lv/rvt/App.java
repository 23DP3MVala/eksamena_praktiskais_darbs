package lv.rvt;
import java.util.*;

public class App {
    private List<Piezime> piezimes;
    private Scanner input;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_COLOR = "\u001B[92m";

    public App() {
        this.piezimes = FileHandler.Ieladet();
        this.input = new Scanner(System.in);
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public void pievienotPiezimi() {
        clearScreen();
        System.out.println("Ievadi piezīmes virsrakstu: ");
        input.nextLine();
        String virsraksts = input.nextLine();
        System.out.println("Ievadi piezīmes saturu: ");
        String saturs = input.nextLine();

        Piezime piezime = new Piezime(virsraksts, saturs);
        piezimes.add(piezime);
        FileHandler.Saglabat(piezimes);
        System.out.println("Piezīme saglabāta!");
    }

    public void izvaditPiezimes() {
        if (piezimes.isEmpty()) {
            System.out.println("Netika atrasta neviena piezīme");
            return;
        }
        for (int i = 0; i < piezimes.size(); i++) {
            System.out.println((i+1) + ". " + piezimes.get(i).getVirsraksts());
        }
    }

    public void paraditPiezimi() {
        clearScreen();
        izvaditPiezimes();
        System.out.println("\nIevadi piezīmes numuru: ");
        int index = input.nextInt();
        input.nextLine();

        if (index > 0 && index <= piezimes.size()) {
            clearScreen();
            System.out.println(piezimes.get(index - 1));
            System.out.println("\n1. Atgriezties");
            String atgriezties = input.nextLine();
            if (atgriezties.equals("1")) {
                return;
            }
            else {
                System.out.println("Nederīga izvēle.");
            }
        }
        else {
            System.out.println("Nederīga izvēle.");
        }
    }

    public void dzestPiezimi() {
        clearScreen();
        izvaditPiezimes();
        System.out.println("Ievadi piezīmes numuru lai izdzēstu: ");
        int i = input.nextInt();
        input.nextLine();
        if (i > 0 && i <= piezimes.size()) {
            piezimes.remove(i-1);
            FileHandler.Saglabat(piezimes);
            System.out.println("Piezīme izdzēsta");
            System.out.println("\n1. Atgriezties");
            String atgriezties = input.nextLine();
            if (atgriezties.equals("1")) {
                return;
            }
            else {
                System.out.println("Nederīga izvēle.");
            }

        }
        else {
            System.out.println("Nederīga izvēle.");
        }
    }

    public void run() {
        while (true) {
            clearScreen();
            System.out.println(ANSI_COLOR + """ 
            ███    ██  ██████  ████████ ███████ ███████ ██       ██████  ██     ██ 
            ████   ██ ██    ██    ██    ██      ██      ██      ██    ██ ██     ██ 
            ██ ██  ██ ██    ██    ██    █████   █████   ██      ██    ██ ██  █  ██ 
            ██  ██ ██ ██    ██    ██    ██      ██      ██      ██    ██ ██ ███ ██ 
            ██   ████  ██████     ██    ███████ ██      ███████  ██████   ███ ███  
            """ + ANSI_RESET);
            System.out.println("\n1. Pievienot piezīmi 2. Apskatīt piezīmes 3. Dzēst piezīmi 4. Iziet");
            System.out.print("Izvēlies opciju: ");
            int izvele = input.nextInt();
            if (izvele == 1) {
                pievienotPiezimi();
            }
            else if (izvele == 2) {
                paraditPiezimi();
            }
            else if (izvele == 3) {
                dzestPiezimi();
            }
            else if (izvele == 4) {
                System.out.println("Atā!");
                return;
            }
            else {
                System.out.println("Nederīga izvēle.");
            }
        }
    }
}
