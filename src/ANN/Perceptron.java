package ANN;


public abstract class Perceptron {
	//abstract class that defines the extarnal shape of any Neural Network
	

	int NbEntree;// only work on vector of size NbEntree
	public int NbSortie;// send back a vector of size NbSortie

	public abstract double[] eval(double[] X);// simple execution of the NN
	public abstract double[] train(double[] X, double[] D,double alpha);// execution of backpropagation on the perceptron: send back the NbEntree residuals (for the previous layer) from the NbSortie residuals of the previous layer
	public abstract void absorb();// update all coeficients by absorbing all information stored with train since last absorb (used to avoid updating parameters after each backpropagation)
	
	public double[] trainG(double[] X, double[] Y,double alpha) {// used if it is the last layer: the residual are not known but the wanted output (Y) is
		double[] temp=eval(X);
		for(int i=0;i<NbSortie;i++){
			temp[i]=Y[i]-temp[i];
		}
		return this.train(X, temp, alpha);
	}
	
	
	public abstract void presentation();
	public abstract void detail();
	
}
