public class Roi extends Piece {

    public Roi(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Roi",6,aCouleur);
    }

    public String toString(){
        if(couleur){
            return "kW";
        }else{
            return "kB";
        }
    }
}
