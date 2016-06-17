package CNN;

import ANN.*;

public class NeuralNet {
	//classic CNN constructors

	public static ComplexNN Classic(int E,int[] Size){
		return new Adaptation(E,Size);
	}
	
	/*
	public static ComplexNN Convol1(int E1, int E2,int F1, int F2, int dim, int[] Size){
		Convolution C=new Convolution(1,dim,F1,F2);
		ComplexNN R=new Adaptation(C.NbSortie(E1,E2),Size);
		ComplexNN[] P=new ComplexNN[2];
		P[0]=C;
		P[1]=R;
		return new Sequence(P);
	}
	
	
	*/
	
	public static ComplexNN Convol1(int E1,int E2,int[] C1, int[] Size){
		int[] taille={E1,E2,1};
		Convolution NC1=new Convolution(taille[2],C1[2],C1[0],C1[1]);
		taille=NC1.size(taille);
		
		ComplexNN R=new Adaptation(taille[0]*taille[1]*taille[2],Size);
		ComplexNN[] P=new ComplexNN[2];
		P[0]=NC1;
		P[1]=R;
		return new Sequence(P);
	}
	
	
	
	public static ComplexNN ConvolSamp1(int E1,int E2,int[] C1, int[] Size){
		int[] taille={E1,E2,1};
		Convolution NC1=new Convolution(taille[2],C1[2],C1[0],C1[1]);
		taille=NC1.size(taille);
		Subsampling NS1=new Subsampling();
		taille=NS1.size(taille);
		
		ComplexNN R=new Adaptation(taille[0]*taille[1]*taille[2],Size);
		ComplexNN[] P=new ComplexNN[3];
		P[0]=NC1;
		P[1]=NS1;
		P[2]=R;
		return new Sequence(P);
	}
	
	public static ComplexNN ConvolSamp2(int E1,int E2,int[] C1, int[] C2, int[] Size){//chq int[] de conv : {int F1, int F2, int dim}
		int[] taille={E1,E2,1};
		Convolution NC1=new Convolution(taille[2],C1[2],C1[0],C1[1]);
		taille=NC1.size(taille);
		Subsampling NS1=new Subsampling();
		taille=NS1.size(taille);
		Convolution NC2=new Convolution(taille[2],C2[2],C2[0],C2[1]);
		taille=NC2.size(taille);
		Subsampling NS2=new Subsampling();
		taille=NS2.size(taille);
		
		ComplexNN R=new Adaptation(taille[0]*taille[1]*taille[2],Size);
		ComplexNN[] P=new ComplexNN[5];
		P[0]=NC1;
		P[1]=NS1;
		P[2]=NC2;
		P[3]=NS1;
		P[4]=R;
		return new Sequence(P);
	}
	
	
	
}
