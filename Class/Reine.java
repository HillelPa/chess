public class Reine extends Piece {

    public Reine(boolean aCouleur){
        super("Reine",5,aCouleur);
    }

    public String toString(){
        if(couleur){
            return "qW";
        }else{
            return "qB";
        }
    }
}
