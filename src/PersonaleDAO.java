import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonaleDAO {

        //Metode der bliver kaldt fra main. Den loader data fra fil og fylder vores array med de i forvejen eksisterende data
        public static ArrayList<PersonaleData> getPersonaleData() {
            ArrayList<PersonaleData> fillArray = new ArrayList<>();

            try {
                File fileIn = new File("Personaledata.txt");

                Scanner in = new Scanner(fileIn);

                while (in.hasNext()) {
                    PersonaleData personaleInfo = new PersonaleData();
                    personaleInfo.Id = in.next();
                    personaleInfo.CPR = in.next();
                    personaleInfo.navn = in.next();
                    personaleInfo.titel= in.next();
                    personaleInfo.telefonnummer = in.next();
                    fillArray.add(personaleInfo);
                }
                in.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
            } catch (IOException i) {
                i.printStackTrace();
                System.exit(0);
            }
            return fillArray;
        }





        // Metode der kaldes fra main. Denne tager vores arrayliste og gemmer den til fil igen når man afslutter programmet
        public static void setPersonaleData(ArrayList<PersonaleData> personaleKartotek) {

            try {
                File fileOut = new File("Personaledata.txt");
                PrintStream output = new PrintStream(fileOut);
                for (PersonaleData personaleInfo : personaleKartotek) {
                    output.println(personaleInfo.Id);
                    output.println(personaleInfo.CPR);
                    output.println(personaleInfo.navn);
                    output.println(personaleInfo.titel);
                    output.println(personaleInfo.telefonnummer);

                }
                output.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
}
