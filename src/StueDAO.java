import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StueDAO {

        //Metode der bliver kaldt fra main. Den loader data fra fil og fylder vores array med de i forvejen eksisterende data
        public static ArrayList<Stue> getStueData() {
            ArrayList<Stue> fillArray = new ArrayList<>();

            try {
                File fileIn = new File("Stuedata.txt");

                Scanner in = new Scanner(fileIn);

                while (in.hasNext()) {
                    Stue stueData = new Stue();
                    stueData.idRum = in.next();
                    stueData.navn = in.next();
                    stueData.motto = in.next();
                    stueData.etage = in.next();
                    fillArray.add(stueData);
                }
                in.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
            } catch (IOException i) {
                i.printStackTrace();
                System.exit(0);
            }
            return fillArray;
        }


        // Metode der kaldes fra main. Denne tager vores arrayliste og gemmer den til fil igen når man afslutter programmet
        public static void setStueData(ArrayList<Stue> stueKartotek) {

            try {
                File fileOut = new File("Stuedata.txt");
                PrintStream output = new PrintStream(fileOut);
                for (Stue stueData : stueKartotek) {
                    output.println(stueData.idRum);
                    output.println(stueData.navn);
                    output.println(stueData.motto);
                    output.println(stueData.etage);

                }
                output.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
}
