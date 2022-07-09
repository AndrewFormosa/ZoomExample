class xline extends thing{
	

	public xline(double px, double py, double pz,double s){
		
		super(2);
		double[] xchange = {0,s};
		double[] ychange = {0,0};    
		double[] zchange = {0,0};     
		for (int i=0;i<2;i++){		
			x[i]=px+xchange[i];		
			y[i]=py+ychange[i];		
			z[i]=pz+zchange[i];		
		}
				
	}
		
		
	
}