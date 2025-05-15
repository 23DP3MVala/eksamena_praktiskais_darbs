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

    public void atgriezties(int n) {
        System.out.println("\n" + n + ". Atgriezties");
        String atgriezties = input.nextLine();
        if (atgriezties.equals("n")) {
            return;
        }
        else {
            System.out.println("Nederīga izvēle.");
        }

    }

    public void izvaditPiezimi(int i) {
        if (i > 0 && i <= piezimes.size()) {
            clearScreen();
            System.out.println(piezimes.get(i - 1));
            atgriezties(1);
        }
        else {
            System.out.println("Nederīga izvēle.");
        }
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
        System.out.println("\nPiezīme saglabāta!");
        atgriezties(1);
    }

    public void paraditPiezimi() {
        clearScreen();
        tabula(piezimes);
        System.out.println("\n0. Filtrēt vai kārtot  #. Skatīt");
        System.out.print("Izvēlies opciju: ");
        int index = input.nextInt();
        input.nextLine();
        if (index == 0) {
            System.out.println("1. Filtrēt pēc datuma  2. Kārtot ");
            System.out.print("Izvēlies opciju: ");
            int n = input.nextInt();
            input.nextLine();
            if (n == 1) {
                filtrs();
            }
            else if (n == 2) {
                kartot();
            }
            else {
                System.out.println("Nederīga izvēle.");
            }
        }
        izvaditPiezimi(index);

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
            atgriezties(1);
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
            Piezime piezime = note.get(i);
            System.out.printf(leftAlignFormat, i + 1, truncate(piezime.getVirsraksts(), 30), piezime.getLaiks());
        }
    }

    public void filtrs(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Piezime> filtered = new ArrayList<Piezime>();

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
        System.out.println("\n0. Atgriezties  #. Skatīt");
        int index = input.nextInt();
        input.nextLine();
        if (index == 0) {
            return;
        }
        else {
        izvaditPiezimi(index);
        }
    }

    public void kartot(){
        clearScreen();
        System.out.println("Kārtot pēc:\n1. Datums (Vecākais -> Jaunākais)\n2. Datums (Jaunākais -> Vecākais)\n3. Nosaukums (A -> Z)\n4. Nosaukums (Z -> A)");
        System.out.print("Izvēlies opciju: ");
        int izvele = input.nextInt();
        input.nextLine();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Comparator<Piezime> comparator = switch (izvele) {
            case 1 -> Comparator.comparing((Piezime p) -> LocalDateTime.parse(p.getLaiks(), outputFormatter));
            case 2 -> Comparator.comparing((Piezime p) -> LocalDateTime.parse(p.getLaiks(), outputFormatter)).reversed();
            case 3 -> Comparator.comparing(Piezime::getVirsraksts, String.CASE_INSENSITIVE_ORDER);
            case 4 -> Comparator.comparing(Piezime::getVirsraksts, String.CASE_INSENSITIVE_ORDER).reversed();
            default -> {
                System.out.println("Nederīga izvēle");
                yield null;
            }
        };

        if (comparator != null) {
            List<Piezime> sakartots = new ArrayList<Piezime>(piezimes);
            sakartots.sort(comparator);
            clearScreen();
            tabula(sakartots);
            System.out.println("\n0. Atgriezties  #. Skatīt");
            int index = input.nextInt();
            input.nextLine();
            if (index == 0) {
                return;
            }
            else {
            izvaditPiezimi(index);
            }
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
            System.out.println("\n1. Pievienot piezīmi  2. Apskatīt piezīmes  3. Dzēst piezīmi  4. Iziet");
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