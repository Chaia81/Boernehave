public class Rum implements RumInterface {
    String navn; //klassevariabler skrives med stort forbogstav
    String idRum;
    String etage;


    public void printDataOmRum(){
        System.out.println("Info: Id: " + idRum+ ", Navn: " + navn + ", Etage:" + etage);
    }
}