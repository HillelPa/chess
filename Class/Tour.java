public class Tour extends Piece {

    public Tour(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Tour",3,aCouleur);
    }

    public String toString(){
        if(couleur){
            return "tW";
        }else{
            return "tB";
        }
    }
}
