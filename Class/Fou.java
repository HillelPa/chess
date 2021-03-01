public class Fou extends Piece {

    public Fou(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Fou",4,aCouleur);
    }

    public String toString(){
        if(couleur){
            return "fW";
        }else{
            return "fB";
        }
    }
}
