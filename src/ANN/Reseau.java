package ANN;


public class Reseau extends Perceptron {
	// NETWORK
	//create a global perceptron by putting different perceptron in series: they can be simple layers of neurones (of different size)
	//																		but they can also be any kind of perceptron to build special architectures

	private Perceptron[] P;//lit of perceptrons
	
	
	////  CONSTRUCTEURS
	
	public Reseau(Perceptron[] Pi){
		this.P=Pi;
		this.NbEntree=Pi[0].NbEntree;
		this.NbSortie=Pi[Pi.length-1].NbSortie;
	}
	
	public Reseau(int NE,int[] Size){// create a basic network with Size.length layers of neurones of settles by Size, and with a global number of entrance NE
		Perceptron[] Pi=new Perceptron[Size.length];
		Pi[0]=new Couche(NE,Size[0]);
		for(int i=0;i<Size.length-1;i++){
			Pi[i+1]=new Couche(Size[i],Size[i+1]);
		}
		this.P=Pi;
		this.NbEntree=Pi[0].NbEntree;
		this.NbSortie=Pi[Pi.length-1].NbSortie;
	}	


	//// PROCEDURES
	
	public double[] eval(double[] X) {
		double[] temp=X;
		for(int p=0;p<this.P.length;p++){
			temp=P[p].eval(temp);
		}
		return temp;
	}


	public double[] train(double[] X, double[] D, double alpha) {
		double[][] ent=new double[P.length+1][];//store all inputs one by one: going
		ent[0]=X;
		for(int p=0;p<this.P.length;p++){
			ent[p+1]=P[p].eval(ent[p]);
		}
		
		double[][] Di=new double[P.length+1][];//store all residual: coming
		Di[P.length]=D;
		for(int p=P.length-1;p>=0;p--){
			Di[p]=P[p].train(ent[p], Di[p+1], alpha);
		}
		return Di[0];
	}
	
	public void absorb() {
		for(int k=0;k<P.length;k++){
			P[k].absorb();
		}
	}

	
	////  LECTURE
	
	public void presentation() {
		System.out.print("Reseau de taille ");
		System.out.println(this.P.length);
	}

	
	public void detail() {
		presentation();
		for(int p=0;p<this.P.length;p++){
			P[p].detail();
		}
		System.out.println("--Fin de Reseau--");
	}

	
	


}
