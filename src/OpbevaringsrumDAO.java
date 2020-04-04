import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OpbevaringsrumDAO {

        //Metode der bliver kaldt fra main. Den loader data fra fil og fylder vores array med de i forvejen eksisterende data
        public static ArrayList<Opbevaringsrum> getOpbevaringrumData() {
            ArrayList<Opbevaringsrum> fillArray = new ArrayList<>();

            try {
                File fileIn = new File("Opbevaringsrumdata.txt");
                Scanner in = new Scanner(fileIn);

                while (in.hasNext()) {
                    Opbevaringsrum opbevaringsData = new Opbevaringsrum();
                    opbevaringsData.idRum = in.next();
                    opbevaringsData.navn = in.next();
                    opbevaringsData.etage = in.next();
                    opbevaringsData.type = in.next();
                    opbevaringsData.inventar = in.next();
                    fillArray.add(opbevaringsData);
                }
                in.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
            } catch (IOException i) {
                i.printStackTrace();
                System.exit(0);
            }
            return fillArray;
        }


        // Metode der kaldes fra main. Denne tager vores arrayliste og gemmer den til fil igen når man afslutter programmet
        public static void setOpbevaringsrumData(ArrayList<Opbevaringsrum> opbevaringsKartotek) {
            try {
                File fileOut = new File("Opbevaringsrumdata.txt");
                PrintStream output = new PrintStream(fileOut);
                for (Opbevaringsrum opbevaringsData : opbevaringsKartotek) {
                    output.println(opbevaringsData.idRum);
                    output.println(opbevaringsData.navn);
                    output.println(opbevaringsData.etage);
                    output.println(opbevaringsData.type);
                    output.println(opbevaringsData.inventar);
                }
                output.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
}