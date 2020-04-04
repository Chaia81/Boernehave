import java.util.ArrayList;

public class Opbevaringsrum extends Rum{
    String inventar;
    String type;

    public void printDataOmRum(){
        System.out.println("Info: Id: " + idRum + ", Navn: " + navn + ", Etage: " + etage + ", Type: " + type + ", Inventar: " + inventar);
    }
}