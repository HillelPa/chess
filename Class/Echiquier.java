public class Echiquier {
    Case [][] echiquier;
    
    public Echiquier(){
		echiquier = new Case[8][8];
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(i % 2 == 1 ^ j % 2 == 1){
				echiquier[i][j] = new Case(false, new Pion(true, i));
				System.out.println("noire");
				}else{
				echiquier[i][j] = new Case(true, new Pion(true, i));
				System.out.println("blanche");
				}
			}
		}
	}
	
	public void afficher(){
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				System.out.print("[");
				if(echiquier[i][j].couleur){
					System.out.print("w");
				}else{
					System.out.print("b");
				}
				System.out.print(echiquier[i][j].piece);
				System.out.print("]");
			}
			System.out.println();
		}
	}	
}

