public class thing{
	
	int NoPoints;
	double[] x;
	double[] y;
	double[] z;
	double MovX;
	double MovY;
	double MovZ;
	double SpinT;
	double SpinA;
	double SpinG;
	double SpinX;
	double SpinY;
	double SpinZ;
	int Life;
	int Colour;


	
	public thing(int Points){
		
		NoPoints=Points;
		x=new double[NoPoints];
		y=new double[NoPoints];
		z=new double[NoPoints];
	
	}

	
		
	public double[] GetXPoints(){return(x);}
	public double[] GetYPoints(){return(y);}
	public double[] GetZPoints(){return(z);}
	public int GetNoPoints(){return(NoPoints);}
	
	public void SetXPoints(double[]X){x=X;}
	public void SetYPoints(double[]Y){y=Y;}
	public void SetZPoints(double[]Z){z=Z;}
	
	public void MovePoints(){
			for (int j=0;j<NoPoints;j++){
				x[j]=x[j]+MovX;
				z[j]=z[j]+MovZ;
				y[j]=y[j]+MovY;
			}
		
		}
		
		
		}