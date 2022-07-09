class zline extends thing{
	

	public zline(double px, double py, double pz,double s){
		
		super(2);
		double[] xchange = {0,0};
		double[] ychange = {0,0};    
		double[] zchange = {0,s};     
		for (int i=0;i<2;i++){		
			x[i]=px+xchange[i];		
			y[i]=py+ychange[i];		
			z[i]=pz+zchange[i];		
		}
				
	}
		
		
	
}