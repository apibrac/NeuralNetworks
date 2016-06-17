package CNN;

public abstract class ComplexNN {
	//abstract class that defines the basic shape of a CNN: same kind of definition than perceptron but accepting inputs and outputs in 3 dimensions (to keep the link between to next positions)
	//cf ANN.perceptron
	
	public abstract double[][][] eval(double[][][] X);
	public abstract double[][][] train(double[][][] X, double[][][] D,double alpha);
	public abstract void absorb();
	
	public double[][][] trainG(double[][][] X, double[][][] Y,double alpha) {
		double[][][] s=eval(X);
		double[][][] temp=new double[s.length][s[0].length][s[0][0].length];
		for(int i=0;i<temp.length;i++){
			for(int j=0;j<temp[0].length;j++){
				for(int k=0;k<temp[0][0].length;k++){
			temp[i][j][k]=Y[i][j][k]-s[i][j][k];
		}}}
		this.train(X, temp, alpha);
		return s;
	}
	
	
	
	public abstract int[] size(int[] E);
	
	
}
