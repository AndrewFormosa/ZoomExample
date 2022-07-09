public class bullet extends thing
{
	


	public bullet(double px, double py, double pz,double s,double XM,double YM, double ZM){
		
		super(16);
		double[] xchange = {-s,-s,s,s,-s,-s,-s,s,s,-s,s,s,s,s,-s,-s};
		double[] ychange = {-s,s,s,-s,-s,-s,s,s,-s,-s,-s,-s,s,s,s,s};    
		double[] zchange = {-s,-s,-s,-s,-s,s,s,s,s,s,s,-s,-s,s,s,-s};     
		for (int i=0;i<16;i++){		
			x[i]=px+xchange[i];		
			y[i]=py+ychange[i];		
			z[i]=pz+zchange[i];		
		}
		
		MovX=XM;
		MovY=YM;
		MovZ=ZM;

		
    }
}
