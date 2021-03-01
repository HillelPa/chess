public class Echiquier {
    Case [][] echiquier;
    
    public Echiquier(){
		echiquier = new Case[8][8];
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(i % 2 == 1 ^ j % 2 == 1){
				echiquier[i][j] = new Case(false);
				}else{
				echiquier[i][j] = new Case(true);
				}
			}
		}
		
		echiquier[0][0].piece = new Tour(false);
		echiquier[0][1].piece = new Cavalier(false);
		echiquier[0][2].piece = new Fou(false);
		echiquier[0][3].piece = new Roi(false);
		echiquier[0][4].piece = new Reine(false);
		echiquier[0][5].piece = new Fou(false);
		echiquier[0][6].piece = new Cavalier(false);
		echiquier[0][7].piece = new Tour(false);
		
		for(int j = 0; j<8; j++){
			echiquier[1][j].piece = new Pion(false);
		}
		
		echiquier[7][0].piece = new Tour(true);
		echiquier[7][1].piece = new Cavalier(true);
		echiquier[7][2].piece = new Fou(true);
		echiquier[7][3].piece = new Roi(true);
		echiquier[7][4].piece = new Reine(true);
		echiquier[7][5].piece = new Fou(true);
		echiquier[7][6].piece = new Cavalier(true);
		echiquier[7][7].piece = new Tour(true);
		
		for(int j = 0; j<8; j++){
			echiquier[6][j].piece = new Pion(true);
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

