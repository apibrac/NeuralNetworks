package CNN;

public class Subsampling extends ComplexNN {
	// ComplexNN: average 4 by 4 (divide the size by 4)
	
	public Subsampling(){
		
	}

	@Override
	public double[][][] eval(double[][][] X) {
		int E1=X.length,E2=X[0].length,E3=X[0][0].length;
		double[][][] out=new double[E1/2][E2/2][E3];
		for(int k=0;k<E3;k++){for(int j=0;j<E2/2;j++){for(int i=0;i<E1/2;i++){
			out[i][j][k]=(X[2*i][2*j][k]+X[2*i+1][2*j][k]+X[2*i][2*j+1][k]+X[2*i+1][2*j+1][k])/4;
		}}}
		return out;
	}

	@Override
	public double[][][] train(double[][][] X, double[][][] D, double alpha) {
		int E1=D.length,E2=D[0].length,E3=D[0][0].length;
		double[][][] out=new double[E1*2][E2*2][E3];
		for(int k=0;k<E3;k++){for(int j=0;j<E2;j++){for(int i=0;i<E1;i++){
			double v=X[2*i][2*j][k]+X[2*i][2*j+1][k]+X[2*i+1][2*j][k]+X[2*i+1][2*j+1][k];
			v=D[i][j][k]+v/4;
			out[2*i][2*j][k]=-X[2*i][2*j][k]+v;
			out[2*i+1][2*j][k]=-X[2*i+1][2*j][k]+v;
			out[2*i][2*j+1][k]=-X[2*i][2*j+1][k]+v;
			out[2*i+1][2*j+1][k]=-X[2*i+1][2*j+1][k]+v;
		}}}
		return out;
	}

	@Override
	public void absorb() {
	}
	
	public int NbSortie(int NbEntree){
		return NbEntree/4;
	}

	@Override
	public int[] size(int[] E) {
		E[0]=E[0]/2;
		E[1]=E[1]/2;
		return E;
	}



}
