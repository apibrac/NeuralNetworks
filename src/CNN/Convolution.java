package CNN;

import ANN.*;

public class Convolution extends ComplexNN {
	// basic convolutional neural layer: apply the same perceptron on all part of a 3d matrix giving back a matrix that gather all values obtained
	// can be fill aith a simple layer of Neurones (Couche), but can also accept any kind of perceptron (multi layers or even more complicated)
	
	public int DE;//3rd dimension of entrance
	public int DS;//3rd dimension of out
	public int W1;
	public int W2;//dimensions of scanning window
	
	private Perceptron P;
	
	//CONSTRUCTORS
	
	
	public Convolution(int dE, int dS, int w1, int w2) {
		DE = dE;
		DS = dS;
		W1 = w1;
		W2 = w2;
		P=new Couche(dE*w1*w2,dS);
	}
	
	public Convolution(int dE, int w1, int w2, Perceptron p) {//p.NbEntree = dE*w1*w2
		DE = dE;
		DS = p.NbSortie;
		W1 = w1;
		W2 = w2;
		P=p;
	}
	
	
	
	
	
	// FONCTIONS

	@Override
	public double[][][] eval(double[][][] X) {
		int S1=1+X.length-W1;
		int S2=1+X[0].length-W2;
		double[][][] out=new double[S1][S2][DS];
		
		double[] temp=new double[W1*W2*DE];

		for(int i=0;i<S1;i++){
			for(int j=0;j<S2;j++){
				
				for(int kt=0;kt<DE;kt++){for(int jt=0;jt<W2;jt++){for(int it=0;it<W1;it++){
					temp[it+W1*(jt+W2*kt)]=X[it+i][jt+j][kt];
				}}}
					
				out[i][j]=P.eval(temp);
			}
		}
		return out;
	}
	
	



	@Override
	public double[][][] train(double[][][] X, double[][][] D, double alpha) {
		int E1=X.length;
		int E2=X[0].length;
		int S1=1+E1-W1;
		int S2=1+E2-W2;
		double[][][] out=new double[E1][E2][DE];
		
		double[] temp=new double[W1*W2*DE];
		double[] tempD;

		for(int i=0;i<S1;i++){
			for(int j=0;j<S2;j++){
				
				for(int kt=0;kt<DE;kt++){for(int jt=0;jt<W2;jt++){for(int it=0;it<W1;it++){
					temp[it+W1*(jt+W2*kt)]=X[it+i][jt+j][kt];
				}}}
				tempD=D[i][j];
					
				temp=P.train(temp,tempD,alpha);
				
				for(int kt=0;kt<DE;kt++){for(int jt=0;jt<W2;jt++){for(int it=0;it<W1;it++){
					out[it+i][jt+j][kt]=out[it+i][jt+j][kt]+temp[it+W1*(jt+W2*kt)];
				}}}
			}
		}
		return out;
	}

	@Override
	public void absorb() {
		P.absorb();
	}
	
	
	///  LECTURE





	@Override
	public int[] size(int[] E) {
		int[] out=new int[3];
		out[0]=1+E[0]-W1;
		out[1]=1+E[1]-W2;
		out[2]=DS;
		return out;
	}





	
}
