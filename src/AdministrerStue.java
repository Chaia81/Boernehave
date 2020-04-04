import java.util.ArrayList;
import java.util.Scanner;

public class AdministrerStue {
    Scanner console;
    StueDAO stueDAO;
    ArrayList<Stue> stueKartotek;
    Stue rumStue;
    int subOption;

    public AdministrerStue() {
        stueDAO = new StueDAO();
        rumStue = new Stue();
        console = new Scanner(System.in);
        stueKartotek = StueDAO.getStueData();
    }


    public void administrerStueMenu(AdministrerBarn administrerBarn){
        do {
            System.out.println("+-------------------+");
            System.out.println("| Administrer Stuer |");
            System.out.println("+-------------------+");
            System.out.println("\n1. Opret stue");
            System.out.println("2. Opdater stue");
            System.out.println("3. Slet stue");
            System.out.println("4. Se liste over stuer");
            System.out.println("5. Se boern tilknyttet bestemt stue");
            System.out.println("6. Tilbage");

            int input = console.nextInt();
            switch (input) {
                case 1:
                    registrerStue();
                    break;
                case 2:
                    opdaterStue();
                    break;
                case 3:
                    sletStue(administrerBarn.boerneKartotek);
                    break;
                case 4:
                    printListeOverStuer();
                    break;
                case 5:
                    printBoernPaaBestemtStue(administrerBarn.boerneKartotek);
                    break;
                case 6:
                    System.out.println("Du har valgt at gå tilbage\n");
                    return;
            }
        }
        while (subOption != 6);
    }



    public void registrerStue() {
        Stue stueData = new Stue();
        System.out.println("+---------------------------------+");
        System.out.println("| Du har valgt at oprette en stue |");
        System.out.println("+---------------------------------+");
        System.out.println("\nIndtast et ID paa stuen");
        stueData.idRum = console.next();
        System.out.println("Indtast navn på stue");
        stueData.navn = console.next();
        System.out.println("Indtast stuens motto");
        stueData.motto = console.next();
        System.out.println("Indtast etage på stue");
        stueData.etage = console.next();
        System.out.println("Bekræft regestrering af stue: 1. Ja, 2. Nej");
        int input = console.nextInt();
        if (input == 1) {
            System.out.println("Stuen er nu oprettet");
            stueKartotek.add(stueData);
            StueDAO.setStueData(stueKartotek);
        } else if (input == 2) {
            System.out.println("Oprettelsen af stuen er annulleret");
        }
    }


    public void sletStue(ArrayList<BarnData> boerneKartotek) {
        System.out.println("+--------------------------------+");
        System.out.println("| Du har valgt at slette en stue |");
        System.out.println("+--------------------------------+");
        System.out.println("\nIndtast nummer på stue for at slette:");

        for (int i = 0; i < stueKartotek.size(); i++) {
            System.out.println(i + ". " + stueKartotek.get(i).navn);
        }

        int stueNr = console.nextInt();
        Stue stue = stueKartotek.get(stueNr); //stuevariablen henviser til et object i arraylisten på plads stuenr

        for (int i = 0; i < boerneKartotek.size(); i++)
            if (boerneKartotek.get(i).stue.equals(stue.idRum)) {
                System.out.println("Stuen kan ikke slettes da der er boern tilknyttet stuen");
                return;
            }
            else {
                System.out.println("Bekræft sletning af stue: 1. Ja, 2. Nej");
                int inputint = console.nextInt();
                if (inputint == 1) {
                    stueKartotek.remove(stueNr);
                    System.out.println("Stuen: " + stue.navn + " er nu slettet");
                    stueDAO.setStueData(stueKartotek);
                } else if (inputint == 2) {
                    System.out.println("Sletning af stue er annulleret");
                }
                return;
            }
    }



    public void opdaterStue() {
        System.out.println("+----------------------------------+");
        System.out.println("| Du har valgt at opdatere en stue |");
        System.out.println("+----------------------------------+");
        System.out.println("\nIndtast navn for at slå stuen op");
        String input = console.next();
        for (int i = 0; i < stueKartotek.size(); i++) {
            if (stueKartotek.get(i).navn.equals(input)) {
                System.out.println("Du har valgt Stue: " + stueKartotek.get(i).navn + "\nMotto: " + stueKartotek.get(i).motto);
                System.out.println("Vælg hvad du vil opdatere:");
                System.out.println("1. Id: " + stueKartotek.get(i).idRum + "\n2. navn: " + stueKartotek.get(i).navn + "\n3. Motto: " + stueKartotek.get(i).motto + "\n4. Etage: " +
                        stueKartotek.get(i).etage);
                int input2 = console.nextInt();
                if (input2 == 1) {
                    System.out.println("Indtast nyt id");
                    stueKartotek.get(i).idRum = console.next();
                    StueDAO.setStueData(stueKartotek);
                }
                if (input2 == 2) {
                    System.out.println("Indtast nyt navn:");
                    stueKartotek.get(i).navn = console.next();
                    StueDAO.setStueData(stueKartotek);
                }
                if (input2 == 3) {
                    System.out.println("Indtast nyt motto");
                    stueKartotek.get(i).motto = console.next();
                    StueDAO.setStueData(stueKartotek);
                }
                if (input2 == 4) {
                    System.out.println("Indtast ny etage");
                    stueKartotek.get(i).etage = console.next();
                    StueDAO.setStueData(stueKartotek);
                }
            }
        }
    }





    public void printListeOverStuer() {
        System.out.println("\n+-------------------+");
        System.out.println("| Liste over alle stuer: |");
        System.out.println("+------------------------+\n");
        for (Stue stueData : stueKartotek) {
            stueData.printDataOmRum();
        }
    }




    public void printBoernPaaBestemtStue(ArrayList<BarnData> boerneKartotek) {
        System.out.println("Følgende stuer har boern tilknyttet.");
        System.out.println("Vaelg den stue du vil se hvilke børn har tilknyttet");
        for (int j = 0; j < stueKartotek.size(); j++) {
            System.out.println(j + "." + stueKartotek.get(j).navn);
        }
        int stueNr = console.nextInt();
        Stue stue = stueKartotek.get(stueNr); //stuevariablen henviser til et object i arraylisten på plads stuenr

        int count = 0;

        for (BarnData barnInfo : boerneKartotek) {
            if (barnInfo.stue.equals(stue.idRum)) {
                System.out.println(barnInfo.navn);
                count++;
            }
        }
        System.out.println("På " + stue.navn + " er der tilknyttet: " + count + " boern");
    }
}