package ANN;


public class Couche extends Perceptron {
	// layer of different perceptrons: can basically be a layer of Neurones
	//								but can be layer of any perceptron to put them in parallel
	
	private Perceptron[] P;//list of the different parallel perceptrons
	
	
	
	////CONSTRUCTORS
	
	public Couche(Perceptron[] Pi){
		this.P=Pi;
		this.NbEntree=Pi[0].NbEntree;
		this.NbSortie=0;
		for(int p=0;p<this.P.length;p++){
			this.NbSortie=this.NbSortie+P[p].NbSortie;
		}
	}
	
	public Couche(int Entree, int Sortie){// create a basic layer with neurones with inputs/ouputs of size Entree/Sortie
		Neurone[] N=new Neurone[Sortie];
		for(int i=0;i<Sortie;i++){
			N[i]=new Neurone(Entree);
		}
		this.P=N;
		this.NbEntree=N[0].NbEntree;
		this.NbSortie=0;
		for(int p=0;p<this.P.length;p++){
			this.NbSortie=this.NbSortie+P[p].NbSortie;
		}
	}
	
	
	
	//// PROCEDURES
	
	public double[] eval(double[] X) {
		double[] out=new double[NbSortie];
		int indice=0;
		for(int p=0;p<this.P.length;p++){
			double[] temp=P[p].eval(X);
			for(int i=0;i<P[p].NbSortie;i++){
				out[indice]=temp[i];
				indice++;
			}
		}
		return out;
	}


	public double[] train(double[] X, double[] D, double alpha) {
		double[] out=new double[this.NbEntree];
		int d=0;
		for(int p=0;p<this.P.length;p++){
			int n=P[p].NbSortie;
			double[] temp=new double[n];
			for(int i=0;i<n;i++){
				temp[i]=D[d];
				d++;
			}
			double[] Dtemp=P[p].train(X, temp, alpha);
			for(int i=0;i<this.NbEntree;i++){
				out[i]=Dtemp[i];
			}
		}
		return out;
	}
	
	public void absorb() {
		for(int k=0;k<P.length;k++){
			P[k].absorb();
		}
	}

	
	
	
	////  LECTURE
	
	public void presentation() {
		System.out.print("Couche de taille ");
		System.out.println(this.P.length);
	}

	
	public void detail() {
		presentation();
		for(int p=0;p<this.P.length;p++){
			P[p].detail();
		}
		System.out.println("--Fin de Couche--");
	}

}
