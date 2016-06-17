package ANN;


import java.util.Random;




public class Neurone extends Perceptron {
	// atomic structure of all perceptron: simple neurone with one vector of parameters (size of the input vector) and outputs of size 1
	// used to be put on parallel thanks to Couche or in series with Reseau
	
	private double[] w;//size NbEntree + 1 (for a bias)
	private double[] dw;//stored value before absorption
	private int nd;//number of iteration since last absorption
	
	
	
	////  CONSTRUCTOR
	
	public Neurone(int Entree){
		NbEntree=Entree;
		NbSortie=1;
		w=new double[NbEntree+1];
		Random X = new Random();
		for(int i=0;i<NbEntree+1;i++){
			w[i]=X.nextDouble()*2-0.5;
		}
		dw=new double[NbEntree+1];
		nd=0;
	}


	
	//// PROCEDURES
	
	private static double LG(double z){
		return 1/(1+Math.exp(-z));
	}
	
	private double inside(double[] X){
		double temp=w[NbEntree];
		for(int i=0;i<NbEntree;i++){
			temp=temp+X[i]*w[i];
		}
		return temp;
	}
	
	
	public double[] eval(double[] X){
		double In=inside(X);
		double[] out=new double[1];
		out[0]= LG(In);
		return out;
	}
	
	private double delta(double a,double D){
		return LG(a)*(1-LG(a))*D;
	}
	
	private void calib(double alpha,double[] X,double Delta){
		for(int i=0;i<NbEntree;i++){
			dw[i]=dw[i]+alpha*X[i]*Delta;//store parameters instead of calibrate right now
		}
		dw[NbEntree]=dw[NbEntree]+alpha*Delta;
		nd++;
	}
	
	private double[] dprec(double Delta){
		double[] out=new double[NbEntree];
		for(int i=0;i<NbEntree;i++){
			out[i]=w[i]*Delta;
		}
		return out;
	}
	
	
	public double[] train(double[] X, double[] D,double alpha){
		double In=inside(X);
		double Delta=delta(In,D[0]);
		calib(alpha,X,Delta);
		return dprec(Delta);

	}
	
	public void absorb() {
		for(int i=0;i<NbEntree+1;i++){
			w[i]=w[i]+dw[i]/nd;
			dw[i]=0;
		}
		nd=0;
	}

	////   LECTURE
	
	public void presentation() {
		System.out.print("Neurone de taille ");
		System.out.println(NbEntree);
	}


	public void detail() {
		System.out.print("Coefficients du Neurone :");
		for(int i=0;i<NbEntree+1;i++){
			System.out.print(" "+w[i]);
		}
		System.out.println();
	}



	
	
}
