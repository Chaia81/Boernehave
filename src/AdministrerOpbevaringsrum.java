import java.util.ArrayList;
import java.util.Scanner;

public class AdministrerOpbevaringsrum {

    Scanner console;
    ArrayList<Opbevaringsrum> opbevaringsKartotek;
    int subOption;
    OpbevaringsrumDAO opbevaringsrumDAO;


    public AdministrerOpbevaringsrum() {
        opbevaringsrumDAO = new OpbevaringsrumDAO();
        console = new Scanner(System.in);
        opbevaringsKartotek = OpbevaringsrumDAO.getOpbevaringrumData();
    }


    public void administrerOpbevaringsMenu() {
        do {
            System.out.println("+----------------------------+");
            System.out.println("| Administrer Opbevaringsrum |");
            System.out.println("+----------------------------+");
            System.out.println("\n1. Opret opbevaringsrum");
            System.out.println("2. Slet Opbevaringsrum");
            System.out.println("3. Se liste over opbevarinsrum");
            System.out.println("4. Se inventar i bestemt opbevaringsrum");
            System.out.println("5. Tilbage");

            int input = console.nextInt();
            switch (input) {
                case 1:
                    registrerOpbevaringsrum();
                    break;
                case 2:
                    sletOpbevaringsrum();
                    break;
                case 3:
                    printListeOverOpbevaringsrum();
                    break;
                case 4:
                    printListeOverInventar();
                    break;
                case 5:
                    System.out.println("Du har valgt at gå tilbage\n");
                    return;
            }
        }
        while (subOption != 5);
    }


    public void registrerOpbevaringsrum() {
        Opbevaringsrum opbevaringsData = new Opbevaringsrum();
        System.out.println("+---------------------------------------------+");
        System.out.println("| Du har valgt at oprette en et opbevaringsrum|");
        System.out.println("+---------------------------------------------+");
        System.out.println("\nIndtast et ID paa Opbevaringsrummet:");
        opbevaringsData.idRum = console.next();
        System.out.println("Indtast navn på opbevaringsrum");
        opbevaringsData.navn = console.next();
        System.out.println("Indtast etage på opbevaringsrum");
        opbevaringsData.etage = console.next();
        System.out.println("Indtast type opbevaringsrum, fx lager eller skur:");
        opbevaringsData.type = console.next();
        System.out.println("Indtast inventar");
        opbevaringsData.inventar = console.next();
        System.out.println("Bekræft regestrering af opbvaringsrummet: 1. Ja, 2. Nej");
        int input = console.nextInt();
        if (input == 1) {
            System.out.println("Opbevaringsrummet er nu oprettet");
            opbevaringsKartotek.add(opbevaringsData);
            opbevaringsrumDAO.setOpbevaringsrumData(opbevaringsKartotek);
        } else if (input == 2) {
            System.out.println("Oprettelsen af opbevaringsrummet er annulleret");
        }
    }


    public void sletOpbevaringsrum() {
        System.out.println("+------------------------------------------+");
        System.out.println("| Du har valgt at slette et opbevaringsrum |");
        System.out.println("+------------------------------------------+");
        System.out.println("\nIndtast navn på opbevaringsrum for at slette:");

        for (int i = 0; i < opbevaringsKartotek.size(); i++) {
            System.out.println(i + ". " + opbevaringsKartotek.get(i).navn);
        }
        String input = console.next();
        for (int i = 0; i < opbevaringsKartotek.size(); i++) {
            if (opbevaringsKartotek.get(i).navn.equals(input)) {
                System.out.println("Bekræft sletning af opbevaringsrum: 1. Ja, 2. Nej");
                int inputint = console.nextInt();
                if (inputint == 1) {
                    opbevaringsKartotek.remove(i);
                    System.out.println("Opbevaringsrum: " + input + " er nu slettet");
                    opbevaringsrumDAO.setOpbevaringsrumData(opbevaringsKartotek);
                } else if (inputint == 2) {
                    System.out.println("Registreringen er annulleret");
                }
                return; //gør at den hopper ud af metoden hvis CPR matcher
            }
        }
        System.out.println("Opbevaringsrum eksistere ikke i kartotek");
    }


    public void printListeOverOpbevaringsrum() {
        System.out.println("\n+---------------------------------+");
        System.out.println("| Liste over alle Opbevaringsrum: |");
        System.out.println("+---------------------------------+\n");
        for (Opbevaringsrum opbevaringsrumData : opbevaringsKartotek) {
            opbevaringsrumData.printDataOmRum();
        }
    }


    public void printListeOverInventar() {
        System.out.println("Vaelg et opbvaringsrum ved at indtaste navnet på rummet for at se inventar:");
        for (int i = 0; i < opbevaringsKartotek.size(); i++) {
            System.out.println("Navn " + opbevaringsKartotek.get(i).navn);
        }
        String input = console.next();

        for (int i = 0; i < opbevaringsKartotek.size(); i++) {
            if (opbevaringsKartotek.get(i).navn.equals(input)) {
                System.out.println(input + " indeholder: " + opbevaringsKartotek.get(i).inventar);
            }
        }
    }
}
