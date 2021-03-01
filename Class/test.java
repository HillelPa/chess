
public class test {
    public static void main (String [] args){
        Echiquier ech = new Echiquier();
        //ech.afficher();
        System.out.println(ech.echiquier[0][0]);
        EchiquierG te = new EchiquierG(ech);
        System.out.println(te.getHeight());
    }
}
