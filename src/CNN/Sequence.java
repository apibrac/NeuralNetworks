package CNN;


public class Sequence extends ComplexNN {
	// same construction than reseau but for ComplexNN layers
	
	private ComplexNN[] P;
	
	
	
	
	// CONSTRUCTORS
	
	
	public Sequence(ComplexNN[] Pi){
		this.P=Pi;
	}
	
	
	
	
	
	/// FONCTIONS

	@Override
	public double[][][] eval(double[][][] X) {
		double[][][] temp;
		temp=X;
		for(int p=0;p<this.P.length;p++){
			temp=P[p].eval(temp);
		}
		return temp;
	}

	@Override
	public double[][][] train(double[][][] X, double[][][] D, double alpha) {
		double[][][][] ent=new double[P.length+1][][][];//contient les entrées pour chaque couche : premier parcourt vers la droite
		ent[0]=X;
		for(int p=0;p<this.P.length;p++){
			ent[p+1]=P[p].eval(ent[p]);
		}
		
		double[][][][] Di=new double[P.length+1][][][];//contient toutes les couches de D : deuxième parcourt vers la gauche
		Di[P.length]=D;
		for(int p=P.length-1;p>=0;p--){
			Di[p]=P[p].train(ent[p], Di[p+1], alpha);
		}
		return Di[0];
	}

	@Override
	public void absorb() {
		for(int k=0;k<P.length;k++){
			P[k].absorb();
		}
	}





	@Override
	public int[] size(int[] E) {
		for(int i=0;i<P.length;i++){
			E=P[i].size(E);
		}
		return E;
	}






}
