import java.util.Scanner;

public class Main {

    static boolean login = true;
    static boolean isLoginValid = false;

    public static void main(String[] args) {
        AdministrerBarn administrerBarn = new AdministrerBarn();
        AdministrerStue administrerStue = new AdministrerStue();
        AdministrerOpbevaringsrum administrerOpbevaringsrum = new AdministrerOpbevaringsrum();
        AdministrerPersonale administrerPersonale = new AdministrerPersonale();
        Scanner console = new Scanner(System.in);
        int option;

        while (login) { //brugernavn og kodeord er begge Admin
            isLoginValid = false;
            System.out.println("+---------------------+");
            System.out.println("|    Velkommen til    |");
            System.out.println("|    Roskilde frie    |");
            System.out.println("|      boernehave     |");
            System.out.println("+---------------------+");
            System.out.println("\nIndtast brugernavn");
            String username = console.next();
            System.out.println("Indtast adgangskode");
            String password = console.next();
            if (!(username.equals("Admin") && password.equals("Admin"))) {
                System.out.println("Forkert brugernavn og adgangskode, prøv igen");
            } else {
                System.out.println("Du er nu logget ind");
                isLoginValid = true;
            }

            if (isLoginValid == true) {
                do {
                    System.out.println("+-----------------+");
                    System.out.println("|    Hovedmenu    |");
                    System.out.println("+-----------------+");
                    System.out.println("\n1. Administrer boern");
                    System.out.println("2. Administrer Personale");
                    System.out.println("3. Administrer stuer");
                    System.out.println("4. Administrer Opbevaringsrum");
                    System.out.println("5. Administrer vagtplan");
                    System.out.println("6. Log af");

                    option = console.nextInt();
                    switch (option) {
                        case 1:
                            administrerBarn.administrerMenu(administrerStue.stueKartotek);
                            break;
                        case 2:
                            administrerPersonale.administrerMenu();
                            break;
                        case 3:
                            administrerStue.administrerStueMenu(administrerBarn);
                            break;
                        case 4:
                            administrerOpbevaringsrum.administrerOpbevaringsMenu();
                            break;
                        case 5:
                            System.out.println("Vagtplan er ikke implementeret endnu");
                            break;
                        case 6:
                            System.out.println("Du har valgt at logge af");
                            break;
                    }
                }
                while (option != 6);
            }
        }
    }
}