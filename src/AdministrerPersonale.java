import java.util.*;

public class AdministrerPersonale {

    PersonaleDAO personaleDao;
    ArrayList<PersonaleData> personaleKartotek;
    Scanner console;
    int subOption;

    public AdministrerPersonale() {  // initialisere variabler i constructor????????????????
        personaleDao = new PersonaleDAO();
        personaleKartotek = personaleDao.getPersonaleData();
        console = new Scanner(System.in);
    }

    public void administrerMenu() { //har her kun deffineret parameteren og sagt jeg vil have en arraylist stue
        do {
            System.out.println("\n+-----------------------+");
            System.out.println("| Administrer personale |");
            System.out.println("+-----------------------+");
            System.out.println("\n1. Registrer medarbejder");
            System.out.println("2. Opdater medarbejder");
            System.out.println("3. Slet medarbejder");
            System.out.println("4. Se liste over personale");
            System.out.println("5. Telefonliste - personale");
            System.out.println("6. Tilbage");

            int input = console.nextInt();
            switch (input) {
                case 1:
                    registrerMedarbejder();
                    break;
                case 2:
                    opdaterMedarbejder();
                    break;
                case 3:
                    sletMedarbejder();
                    break;
                case 4:
                    seListeOverMedarbejdere();
                    break;
                case 5:
                    printTelefonlisteFormedarbejdere();
                    break;
                case 6:
                    System.out.println("Du har valgt at gå tilbage");
                    return;
            }
        }
        while (subOption != 6);
    }


    public void registrerMedarbejder() {
        PersonaleData personaleinfo = new PersonaleData();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("| Du har valgt at registrere en medarbejder |");
        System.out.println("+-------------------------------------------+");
        System.out.println("\nIndtast et ID:");
        personaleinfo.Id = console.next();
        System.out.println("Indtast CPR:");
        personaleinfo.CPR = console.next();
        System.out.println("Indtast Navn:");
        personaleinfo.navn = console.next();
        System.out.println("Indtast titel:");
        System.out.println("1. Leder:");
        System.out.println("2. Paedagog:");
        System.out.println("3. Paedagogmedhjaelper:");
        int inputint = console.nextInt();
        if (inputint == 1) {
            personaleinfo.titel = "Leder";
        }
        if (inputint == 2) {
            personaleinfo.titel = "Paedagog";
        } else if (inputint == 3) {
            personaleinfo.titel = "Pædagogmedhjaelper";
        }
        System.out.println("Indtast telefonnummer");
        personaleinfo.telefonnummer = console.next();
        System.out.println("Bekræft registrering af medarbejder: 1. Ja, 2. Nej");
        int input = console.nextInt();
        if (input == 1) {
            personaleKartotek.add(personaleinfo);
            personaleDao.setPersonaleData(personaleKartotek);
        } else if (input == 2) {
            System.out.println("Registreringen er annulleret");
        }
    }


    public void sletMedarbejder() {
        System.out.println("+---------------------------------------+");
        System.out.println("| Du har valgt at slette en medarbejder |");
        System.out.println("+---------------------------------------+");
        System.out.println("\nIndtast CPR for at slette:");
        String input = console.next();
        for (int i = 0; i < personaleKartotek.size(); i++) {
            if (personaleKartotek.get(i).CPR.equals(input)) {
                System.out.println("Bekræft sletning af medarbejder: 1. Ja, 2. Nej");
                int inputint = console.nextInt();
                if (inputint == 1) {
                    personaleKartotek.remove(i);
                    System.out.println("Medarbejder med CPR " + input + " er nu slettet");
                    personaleDao.setPersonaleData(personaleKartotek);
                } else if (inputint == 2) {
                    System.out.println("Registreringen er annulleret");
                }
                return; //gør at den hopper ud af metoden (hvis CPR matcher) og hopper ud så den ikke også printer sidste linje
            }
        }
        System.out.println("Medarbejder eksistere ikke i kartotek");
    }


    public void opdaterMedarbejder() {
        System.out.println("+--------------------------------------+");
        System.out.println("| Du har valgt at opdatere information |");
        System.out.println("+--------------------------------------+");
        System.out.println("\nIndtast CPR for at finde medarbejder");
        String input = console.next();
        for (int i = 0; i < personaleKartotek.size(); i++) {
            if (personaleKartotek.get(i).CPR.equals(input)) {
                System.out.println("Du har valgt: CPR: " + personaleKartotek.get(i).CPR + ", Navn: " + personaleKartotek.get(i).navn);
                System.out.println("Vælg hvad du vil opdatere:");
                System.out.println("1. Id: " + personaleKartotek.get(i).Id + "\n2. CPR: " + personaleKartotek.get(i).CPR + "\n3. Navn: " + personaleKartotek.get(i).navn + "\n4. Titel: " +
                        personaleKartotek.get(i).titel + "\n5. Telefonnummer: " + personaleKartotek.get(i).telefonnummer);
                int input2 = console.nextInt();
                if (input2 == 1) {
                    System.out.println("Indtast nyt Id:");
                    personaleKartotek.get(i).Id = console.next();
                    personaleDao.setPersonaleData(personaleKartotek);
                }
                if (input2 == 2) {
                    System.out.println("Indtast nyt CPR:");
                    personaleKartotek.get(i).CPR = console.next();
                    personaleDao.setPersonaleData(personaleKartotek);
                }
                if (input2 == 3) {
                    System.out.println("Indtast nyt navn:");
                    personaleKartotek.get(i).navn = console.next();
                    personaleDao.setPersonaleData(personaleKartotek);
                }
                if (input2 == 4) {
                    System.out.println("Vaelg ny titel:");
                    personaleKartotek.get(i).titel = console.next();
                    personaleDao.setPersonaleData(personaleKartotek);
                }
                if (input2 == 5) {
                    System.out.println("Indtast nyt telefonnummer:");
                    personaleKartotek.get(i).telefonnummer = console.next();
                    personaleDao.setPersonaleData(personaleKartotek);
                }
            }
        }
    }


    public void seListeOverMedarbejdere() {
        System.out.println("+---------------------------+");
        System.out.println("Liste over alle medarbejdere:");
        System.out.println("+---------------------------+");
        for (int i = 0; i < personaleKartotek.size(); i++) {
            System.out.println("1. Id: " + personaleKartotek.get(i).Id + ", 2. CPR: " + personaleKartotek.get(i).CPR + ", 3. Navn: " + personaleKartotek.get(i).navn + ", 4. Titel: " +
                    personaleKartotek.get(i).titel + ", 5. Telefonnummer: " + personaleKartotek.get(i).telefonnummer);
        }
    }


    public void printTelefonlisteFormedarbejdere() { //Kaldes fra main
        System.out.println("\n+---------------------------------+");
        System.out.println("| Telefonliste over medarbejdere: |");
        System.out.println("+---------------------------------+");
        for (int i = 0; i < personaleKartotek.size(); i++) {
            System.out.println("Medarbejder: Id: " + personaleKartotek.get(i).Id + ", Navn " + personaleKartotek.get(i).navn + ", Telefonnummer: " + personaleKartotek.get(i).telefonnummer);
        }
    }
}