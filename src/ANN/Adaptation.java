package ANN;
import CNN.*;

public class Adaptation extends ComplexNN {
	//store a perceptron inside and gives the shape of a ComplexNN (see CNN)
	// -> accepting and returning 3d vectors but with a 1d tretment inside
	
	private Perceptron P;
	
	
	// CONSTRUCTORS
	
	public Adaptation(Perceptron p) {
		P = p;
	}
	
	
	public Adaptation(int NE,int[] Size) {
		P = new Reseau(NE,Size);
	}
	
	
	
	
	
	
	// FONCTIONS

	@Override
	public double[][][] eval(double[][][] X) {
		return tableizeS(P.eval(vectorize(X)));
	}

	

	@Override
	public double[][][] train(double[][][] X, double[][][] D, double alpha) {
		int E1=X.length,E2=X[0].length,E3=X[0][0].length;
		return tableizeC(P.train(vectorize(X),vectorize(D),alpha),E1,E2,E3);
	}

	@Override
	public void absorb() {
		P.absorb();
	}
	
	public static double[] vectorize(double[][][] E){
		int E1=E.length,E2=E[0].length;
		int E3=E[0][0].length;
		double[] out=new double[E1*E2*E3];
		for(int kt=0;kt<E3;kt++){for(int jt=0;jt<E2;jt++){for(int it=0;it<E1;it++){
			out[it+E1*(jt+E2*kt)]=E[it][jt][kt];
			
		}}}
		return out;
	}
	
	public static double[][][] tableizeS(double[] E){//simple tableize
		double[][][] out=new double[E.length][1][1];
		for(int i=0;i<E.length;i++){
			out[i][0][0]=E[i];
		}
		return out;
		
	}
	
	public static double[][][] tableizeC(double[] E, int E1,int E2,int E3){//Complexe tableize
		double[][][] out=new double[E1][E2][E3];
		for(int kt=0;kt<E3;kt++){for(int jt=0;jt<E2;jt++){for(int it=0;it<E1;it++){
			out[it][jt][kt]=E[it+E1*(jt+E2*kt)];
			
		}}}
		return out;
		
	}


	@Override
	public int[] size(int[] E) {
		int[] out=new int[3];
		out[0]=P.NbSortie;
		out[1]=1;
		out[2]=1;
		return out;
	}

}
