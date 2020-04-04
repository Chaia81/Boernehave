import java.util.*;

public class AdministrerBarn {
    BarnDAO barnDao;
    ArrayList<BarnData> boerneKartotek;
    Scanner console;
    int subOption;

    public AdministrerBarn(){  // initialisere variabler i constructor????????????????
        barnDao = new BarnDAO();
        boerneKartotek = barnDao.getBarnData();
        console = new Scanner(System.in);
    }

    public void administrerMenu(ArrayList<Stue> stueKartotek) { //har her kun deffineret parameteren og sagt jeg vil have en arraylist stue
        do {
            System.out.println("\n+------------------+");
            System.out.println("| Administrer barn |");
            System.out.println("+------------------+");
            System.out.println("\n1. Registrer barn");
            System.out.println("2. Opdater barn");
            System.out.println("3. Slet barn");
            System.out.println("4. Se liste over børn");
            System.out.println("5. Telefonliste - boern");
            System.out.println("6. Tilbage");

            int input = console.nextInt();
            switch (input) {
                case 1:
                    registrerBarn(stueKartotek);
                    break;
                case 2:
                    opdaterBarn(stueKartotek);
                    break;
                case 3:
                    sletBarn();
                    break;
                case 4:
                    printListeOverBoern(stueKartotek);
                    break;
                case 5:
                    printTelefonlisteForBoernsForaeldre();
                    break;
                case 6:
                    System.out.println("Du har valgt at gå tilbage\n");
                    return;
            }
        }
        while (subOption != 6);
    }


    public void registrerBarn(ArrayList<Stue> stueKartotek){
        BarnData barninfo = new BarnData();
        System.out.println("+------------------------------------+");
        System.out.println("| Du har valgt at registrere et barn |");
        System.out.println("+------------------------------------+");
        System.out.println("\nIndtast Id:");
        barninfo.Id = console.next();
        System.out.println("Indtast CPR:");
        barninfo.CPR = console.next();
        System.out.println("Indtast Navn:");
        barninfo.navn = console.next();
        System.out.println("Vaelg stue:");
        for (int i = 0; i < stueKartotek.size(); i++) {
            System.out.println(i + "." + stueKartotek.get(i).navn);
        }
        int stueNr = console.nextInt();
        Stue stue = stueKartotek.get(stueNr); //stuevariablen henviser til et object i arraylisten på plads stuenr
        barninfo.stue = stue.idRum;
        System.out.println("Indtast navn på mor:");
        barninfo.navnMor = console.next();
        System.out.println("Indtast mors telefonnummer");
        barninfo.morTlf = console.next();
        System.out.println("Indtast navn på far:");
        barninfo.navnFar = console.next();
        System.out.println("Indtast fars telefonnummer");
        barninfo.farTlf = console.next();
        System.out.println("Bekræft regestrering af barn: 1. Ja, 2. Nej");
        int input = console.nextInt();
        if(input == 1){
            boerneKartotek.add(barninfo);
            barnDao.setBarnData(boerneKartotek);
        }
        else if (input == 2){
            System.out.println("Registreringen er annulleret");
        }
    }



    public void sletBarn() {
        System.out.println("+--------------------------------+");
        System.out.println("| Du har valgt at slette et barn |");
        System.out.println("+--------------------------------+");
        System.out.println("\nIndtast CPR for at slette:");
        String input = console.next();
        for (int i = 0; i < boerneKartotek.size(); i++) {
            if (boerneKartotek.get(i).CPR.equals(input)) {
                System.out.println("Bekræft sletning af barn: 1. Ja, 2. Nej");
                int inputint = console.nextInt();
                if (inputint == 1) {
                    boerneKartotek.remove(i);
                    System.out.println("Barn med CPR " + input + " er nu slettet");
                    barnDao.setBarnData(boerneKartotek);
                } else if (inputint == 2) {
                    System.out.println("Registreringen er annulleret");
                }
                return; //gør at den hopper ud af metoden hvis CPR matcher
            }
        }
        System.out.println("Barn eksistere ikke i kartotek");
    }



    public void opdaterBarn(ArrayList<Stue> stueKartotek){
        System.out.println("+--------------------------------------+");
        System.out.println("| Du har valgt at opdatere information |");
        System.out.println("+--------------------------------------+");
        System.out.println("\nIndtast CPR for at finde barn");
        String input = console.next();
        for (int i = 0; i < boerneKartotek.size(); i++){
            if (boerneKartotek.get(i).CPR.equals(input)) {
                System.out.println("Du har valgt: CPR: " + boerneKartotek.get(i).CPR + ", Navn: " + boerneKartotek.get(i).navn);
                System.out.println("Vælg hvad du vil opdatere:");
                System.out.println("1. Id: " + boerneKartotek.get(i).Id + "\n2. CPR: " + boerneKartotek.get(i).CPR + "\n3. Navn: " + boerneKartotek.get(i).navn + "\n4. Stue: " +
                        boerneKartotek.get(i).stue + "\n5. Mor: " + boerneKartotek.get(i).navnMor + "\n6. Nor Tlf:: " + boerneKartotek.get(i).morTlf + "\n7. Far: " + boerneKartotek.get(i).navnFar +
                        "\n8. Far Tlf:: " + boerneKartotek.get(i).farTlf);
                int input2 = console.nextInt();
                if(input2 == 1){
                    System.out.println("Indtast nyt Id:");
                    boerneKartotek.get(i).Id = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 2){
                    System.out.println("Indtast nyt CPR:");
                    boerneKartotek.get(i).CPR = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 3){
                    System.out.println("Indtast nyt navn:");
                    boerneKartotek.get(i).navn = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 4){
                    System.out.println("Vaelg ny stue:");
                    for (int j = 0; j < stueKartotek.size(); j++) {
                        System.out.println(j + "." + stueKartotek.get(j).navn);
                    }
                    int stueNr = console.nextInt();
                    Stue stue = stueKartotek.get(stueNr); //stuevariablen henviser til et object i arraylisten på plads stuenr
                    boerneKartotek.get(i).stue = stue.idRum;
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 5){
                    System.out.println("Indtast nyt navn på mor:");
                    boerneKartotek.get(i).navnMor = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 6){
                    System.out.println("Indtast nyt telefonnummer på mor:");
                    boerneKartotek.get(i).morTlf = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 7){
                    System.out.println("Indtast nyt navn på far");
                    boerneKartotek.get(i).navnFar = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
                if(input2 == 8){
                    System.out.println("Indtast nyt telefonnummer på far:");
                    boerneKartotek.get(i).farTlf = console.next();
                    barnDao.setBarnData(boerneKartotek);
                }
            }
        }
    }



    public void printListeOverBoern(ArrayList<Stue> stueKartotek){
        System.out.println("+------------------------+");
        System.out.println("| Liste over alle boern: |");
        System.out.println("+------------------------+");

        for (BarnData barninfo : boerneKartotek){
            Stue stue = null; //skal man bruge variablen efter scopet/loopet skal den sættes før loopet

            for (Stue stueData : stueKartotek) {
                if (stueData.idRum.equals(barninfo.stue)){
                    stue = stueData;
                }
            }
            if (stue == null){
                System.out.println("Fejl. Kunne ikke finde stuen " + barninfo.stue);
            }
            else {
                System.out.println("CPR: " + barninfo.CPR + ", Navn: " + barninfo.navn + ", Stue: " + stue.navn);
            }
        }

    }



    public void printTelefonlisteForBoernsForaeldre() { //Kaldes fra main
        System.out.println("Telefonliste for foraeldre:");
        for (int i = 0; i < boerneKartotek.size(); i++) {
            System.out.println("Barn: Id: " + boerneKartotek.get(i).Id + ", Navn " + boerneKartotek.get(i).navn + ", Mor: " + boerneKartotek.get(i).navnMor + ", Tlf: " + boerneKartotek.get(i).morTlf +
                    ", Far: " + boerneKartotek.get(i).navnFar + ", Tlf: " + boerneKartotek.get(i).farTlf);
        }
    }


}