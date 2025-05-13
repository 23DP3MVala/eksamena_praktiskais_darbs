package lv.rvt;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void paraditPiezimi() {
        clearScreen();
        tabula(piezimes);
        System.out.println("\nIevadi piezīmes numuru vai ievadi 0. lai filtrētu: ");
        int index = input.nextInt();
        input.nextLine();
        if (index == 0) {
            filtrs();
        }

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
        tabula(piezimes);
        System.out.println("");
        System.out.println("Ievadi piezīmes numuru lai izdzēstu: ");
        int i = input.nextInt();
        input.nextLine();
        if (i > 0 && i <= piezimes.size()) {
            piezimes.remove(i-1);
            FileHandler.Saglabat(piezimes);
            clearScreen();
            System.out.println("Piezīme veiksmīgi izdzēsta");
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

    private static String truncate(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }

    public void tabula(List<Piezime> note) {
        String leftAlignFormat = "| %-3s | %-30s | %-20s |%n";
        String separator = "+-----+--------------------------------+----------------------+";
        System.out.printf(leftAlignFormat, "#", "Virstaksts", "Izveidots");
        System.out.println(separator);
        for (int i = 0; i < note.size(); i++) {
            Piezime piezime = piezimes.get(i);
            System.out.printf(leftAlignFormat, i + 1, truncate(piezime.getVirsraksts(), 30), piezime.getLaiks());
        }
    }

    public void filtrs(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        ArrayList<Piezime> filtered = new ArrayList<Piezime>();

        clearScreen();
        System.out.print("Ievadi sākuma datumu (dd-MM-yyyy): ");
        LocalDate start = LocalDate.parse(input.nextLine(), formatter);
        System.out.print("Ievadi beigu datumu (dd-MM-yyyy): ");
        LocalDate end = LocalDate.parse(input.nextLine(), formatter);

        for (int i = 0; i < piezimes.size(); i++) {
            Piezime piezime = piezimes.get(i);
            LocalDateTime piezimeDateTime = LocalDateTime.parse(piezime.getLaiks(), outputFormatter);
            LocalDate piezimeDate = piezimeDateTime.toLocalDate();
            if ((piezimeDate.isEqual(start) || piezimeDate.isAfter(start)) &&
                (piezimeDate.isEqual(end) || piezimeDate.isBefore(end))) {
                filtered.add(piezime);
            }
        }
        tabula(filtered);
        System.out.println("\n1. Atgriezties");
        String atgriezties = input.nextLine();
        if (atgriezties.equals("1")) {
            return;
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
                clearScreen();
                return;
            }
            else {
                System.out.println("Nederīga izvēle.");
            }
        }
    }
}
