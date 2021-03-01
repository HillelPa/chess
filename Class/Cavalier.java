public class Cavalier extends Piece {

    public Cavalier(boolean aCouleur){ // c true : blanc ; c false : noir

        super("Cavalier",2,aCouleur);
    }

    public String toString(){
        if(couleur){
            return "cW";
        }else{
            return "cB";
        }
    }
}
