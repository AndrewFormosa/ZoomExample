class cube extends thing{
	

	public cube(double px, double py, double pz,double s){
		
		super(16);
		double[] xchange = {0,0,s,s,0,0,0,s,s,0,s,s,s,s,0,0};
		double[] ychange = {0,s,s,0,0,0,s,s,0,0,0,0,s,s,s,s};    
		double[] zchange = {0,0,0,0,0,s,s,s,s,s,s,0,0,s,s,0};     
		for (int i=0;i<16;i++){		
			x[i]=px+xchange[i];		
			y[i]=py+ychange[i];		
			z[i]=pz+zchange[i];		
		}
		
	}	
	
	
}