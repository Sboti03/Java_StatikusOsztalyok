package hu.petrik.statikusosztalyok;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Veletlen {
    private Veletlen() {
    }

    private static final Random rnd = new Random();
    private static final List<String> vezNevek = feltolt("files/veznev.txt");
    private static final List<String> ferfiKerNevek = feltolt("files/ferfikernev.txt");
    private static final List<String> noiKerNevek = feltolt("files/noikernev.txt");
    private static final List<String> sportagak = feltolt("files/sportag.txt");
    private static final List<String> egyesuletek = feltolt("files/egyesulet.txt");

    private static List<String> feltolt(String fajlnev) {
        List<String> lista = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File(fajlnev));
            while (file.hasNext()) {
                String sor = file.nextLine();
                lista.add(sor);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static int velEgesz(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    public static char velKarakter(char min, char max) {
        return (char) velEgesz(min, max);
    }

    public static String velVezetekNev() {
        return vezNevek.get(rnd.nextInt(vezNevek.size()));
    }

    /**
     * Véletlen magyar keresztnév generálása
     * @param nem A generált keresztnév neme. Férfi esetén true, Nő esetén false.
     * @return A generált keresztnév
     */
    public static String velKeresztNev(boolean nem) {
        String keresztNev;
        if (nem) {
            keresztNev = velFerfiKeresztNev();
        } else {
            keresztNev = velNoiKeresztNev();
        }
        return keresztNev;
    }

    private static String velFerfiKeresztNev() {
        return ferfiKerNevek.get(rnd.nextInt(ferfiKerNevek.size()));
    }

    private static String velNoiKeresztNev() {
        return noiKerNevek.get(rnd.nextInt(noiKerNevek.size()));
    }

    /**
     * Véletlen magyar név generálása
     * @param nem A generált név neme. Férfi esetén true, Nő esetén false.
     * @return A generált név
     */
    public static String velTeljesNev(boolean nem) {
        return velVezetekNev() + " " + velKeresztNev(nem);
    }

    public static String velDatum(int ev1, int ev2) {
        String datum = "";
        boolean isLetezo = false;
        while (!isLetezo) {
            int ev = velEgesz(ev1, ev2);
            int honap = velEgesz(1, 12);
            int nap = velEgesz(1, 31);
            try {
                LocalDate ld = LocalDate.of(ev, honap, nap);
                datum = String.format("%s-%s-%s", ev, honap, nap);
                isLetezo = true;
            } catch (Exception ignored) {

            }
        }
        return datum;
    }

    public static String velEmail(String nev) {
        return nev +velEgesz(1, 100) + "@gmail.com";
    }

    public static String velMobil() {
        int szolgaltato = velEgesz(1, 4);
        switch (szolgaltato) {
            case 1:
                szolgaltato = 20;
                break;
            case 2:
                szolgaltato = 30;
                break;
            case 3:
                szolgaltato = 50;
                break;
            case 4:
                szolgaltato = 70;
                break;
        }
        return String.format("+36 (%d) %d-%d-%d", szolgaltato, velEgesz(100, 999), velEgesz(10,99), velEgesz(10,99));
    }


    public static String velSportag() {
        return sportagak.get(rnd.nextInt(sportagak.size()));
    }
    public static String velSportegyesulet() {
        return egyesuletek.get(rnd.nextInt(egyesuletek.size()));
    }
}
