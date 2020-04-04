import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BarnDAO {


    //Metode der bliver kaldt fra main. Den loader data fra fil og fylder vores array med de i forvejen eksisterende data
    public static ArrayList<BarnData> getBarnData() {
        ArrayList<BarnData> fillArray = new ArrayList<>();

        try {
            File fileIn = new File("Boerndata.txt");

            Scanner in = new Scanner(fileIn);

            while (in.hasNext()) {
                BarnData barnData = new BarnData();
                barnData.Id = in.next();
                barnData.CPR = in.next();
                barnData.navn = in.next();
                barnData.stue = in.next();
                barnData.navnMor = in.next();
                barnData.morTlf = in.next();
                barnData.navnFar = in.next();
                barnData.farTlf = in.next();

                fillArray.add(barnData);
            }
            in.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
        } catch (IOException i) {
            i.printStackTrace();
            System.exit(0);
        }
        return fillArray;
    }






    // Metode der kaldes fra main. Denne tager vores arrayliste og gemmer den til fil igen når man afslutter programmet
    public static void setBarnData(ArrayList<BarnData> boerneKartotek) {

        try {
            File fileOut = new File("Boerndata.txt");
            PrintStream output = new PrintStream(fileOut);
            for (BarnData barnInfo : boerneKartotek) {
                output.println(barnInfo.Id);
                output.println(barnInfo.CPR);
                output.println(barnInfo.navn);
                output.println(barnInfo.stue);
                output.println(barnInfo.navnMor);
                output.println(barnInfo.morTlf);
                output.println(barnInfo.navnFar);
                output.println(barnInfo.farTlf);

            }
            output.close(); //WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?
        }
        catch(FileNotFoundException e){
                e.printStackTrace();
                System.exit(0);
            }
    }
}
