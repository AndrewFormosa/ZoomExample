import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class zoom extends JPanel implements KeyListener{


	
	
	int spriteNo=0;
	double mx = -1*(1250/2);
	double my = 10;
	double mz = 0;
	double theta = 0;
	double alpha = 0;
	double gamma = 0;
	double d = 1000;
	double speed = 0.00000001;
	boolean ypress=false;
	boolean upress=false;
	
	thing[] sprt = new thing[1000000];
	
	
	
	double XForward;
	double YForward;
	double ZForward;
	
	double A;
	double B;
	double C;

	public void startgame(){
		
		
		//Set up screen
		
		JFrame MyFrame = new JFrame("Zoom");
		MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyFrame.setSize(1250,950);
		MyFrame.setVisible(true);
		MyFrame.addKeyListener(this);
		MyFrame.setFocusable(true);
		MyFrame.getContentPane().add(this);
		
		
		//SET UP THE FOUR SIDES OF THE HORIZON
		for (double xp=-500000;xp<500000;xp=xp+5000){
				sprt[spriteNo] = new xline(xp,0,500000,5000);
				spriteNo++;
			}
		for (double zp=-500000;zp<500000;zp=zp+5000){			
				sprt[spriteNo] = new zline(-500000,0,zp,5000);
				spriteNo++;			
		}
		for (double xp=-500000;xp<500000;xp=xp+5000){
				sprt[spriteNo] = new xline(xp,0,-500000,5000);
				spriteNo++;
			}
		for (double zp=-500000;zp<500000;zp=zp+5000){			
				sprt[spriteNo] = new zline(500000,0,zp,5000);
				spriteNo++;			
		}
		
		//SET UP RANDOM CUBES ABOVE THE HORIZON
		for (int i=1;i<10;i++){
				sprt[spriteNo] = new cube((-5000 + (double)(Math.random()*(10000))),( (double)(Math.random()*(10000))),(-5000 + (double)(Math.random()*(10000))),(1 + (double)(Math.random()*(100))));
				spriteNo++;
		}
		//SET UP RANDOM PYRAMIDS ABOVE THE HORIZON
		for (int i=1;i<1000;i++){
				sprt[spriteNo] = new cube((-5000 + (double)(Math.random()*(10000))),0,(-5000 + (double)(Math.random()*(10000))),(1 + (double)(Math.random()*(100))));
				spriteNo++;
		}
		
		
		int loop = 1;
		while (loop>0){
			
			
			//MANANGE MY MOVEMENT WITHIN THE SPACE AS FUNCTION OF THETE, ALPHA, SPEED ETC.
			XForward = Math.sin(theta)*Math.cos(alpha)*speed;
			YForward = Math.sin(alpha)*speed;
			ZForward = Math.cos(theta)*Math.cos(alpha)*speed;			
			mx=mx+XForward;my=my+YForward;mz=mz+ZForward;
			theta = theta - (gamma/10000000);			
			if (ypress==false && upress==false){
				if(gamma<0){gamma=gamma+0.00000005;}
				if(gamma>0){gamma=gamma-0.00000005;}
			}
			
			
			//MANAGE MOVEMENT OF METERORS
			
			this.repaint();
			
		}
				
		
	}
	
	public void paintComponent(Graphics g){
		
			super.paintComponent(g);

			g.drawLine(620,475,630,475);
			g.drawLine(625,470,625,480);
			
			for (int i=0;i<spriteNo;i++){
			
				sprt[i].MovePoints();
			

				//Get the XYZ (ox,oy,oz) coordinates for the sprite	and the number of points.
				double[]ox = sprt[i].GetXPoints();
				double[]oy = sprt[i].GetYPoints();
				double[]oz = sprt[i].GetZPoints();
				int NoPoints=sprt[i].GetNoPoints();

				//Prepare the 2D Matrix for each sprite point x and y frame (x,y)
				
				double[] x = new double[NoPoints];
				double[] y = new double[NoPoints];
				double[] dist = new double[NoPoints];
		
				//Convert XYZ points to 2D Frame xy points working through each of the sprite's points at a time.
				for (int n=0;(n<NoPoints);n++){
								
					double AA = ((ox[n]-mx)*Math.cos(theta))-(((oz[n]-mz))*Math.sin(theta));
					double BB = (-1*(ox[n]-mx)*Math.sin(theta)*Math.sin(alpha)) + ((oy[n]-my)*Math.cos(alpha)) - ((oz[n]-mz)*Math.sin(alpha)*Math.cos(theta));
					A =(Math.hypot(AA,BB)*(Math.sin(Math.atan2(AA,BB)+gamma)));
					B =(Math.hypot(AA,BB)*(Math.cos(Math.atan2(AA,BB)+gamma)));
					C = ((ox[n]-mx)*Math.sin(theta)*Math.cos(alpha)) + ((oy[n]-my)*Math.sin(alpha)) + ((oz[n]-mz)*Math.cos(alpha)*Math.cos(theta));
					x[n]=((A/C)*d)+475;y[n]=(-1*((B/C)*d))+625;
					dist[n]= java.lang.Math.cbrt((A*A)+(B*B)+(C*C));
				}
		
				//Check if each point would be visable and Draw lines to make up the sprite using the x and y coordinates 
				if (C>(d/100) && x[0]>-500 && x[0]<1750 && y[0]>-500 && y[0]<1400){
				
					
				
					for (int line=0;line<NoPoints-1;line++){
					g.drawLine((int)x[line], (int)y[line], (int)x[line+1],(int)y[line+1]);					
					}
				g.drawString("XForward:" + (int)(XForward/speed) + "      Y:" + (int)(YForward/speed) + "       Speed:" + (int)(speed*1000000),25,900);
				
				}
			}
				

	
	
	}

	
	public void keyPressed(KeyEvent evt){
		if (evt.getKeyChar()=='p'){theta=theta+0.01;}
		if (evt.getKeyChar()=='o'){theta=theta-0.01;}
		if (evt.getKeyChar()=='y'){if(speed>0 && gamma<1){gamma=gamma+0.01;ypress=true;upress=false;}}
		if (evt.getKeyChar()=='u'){if(speed>0 && gamma>-1){gamma=gamma-0.01;upress=true;ypress=false;}}

		
		
		if (evt.getKeyChar()=='q'){alpha=alpha+0.01;}
		if (evt.getKeyChar()=='a'){alpha=alpha-0.01;}
		if (evt.getKeyChar()=='b' && speed<0.0006){speed=speed+0.0000001;}
		if (evt.getKeyChar()=='n'){speed=0.0000000001;}
		if (evt.getKeyChar()=='t'){
								sprt[spriteNo] = new bullet(mx,my,mz,2,(XForward*50/speed), (YForward*50/speed),(ZForward*50/speed));
								spriteNo++;
								}
								
		
		
		
	}
	
	public void keyReleased(KeyEvent evt){
		if (evt.getKeyChar()=='u'){upress=false;}
		if (evt.getKeyChar()=='y'){ypress=false;}
		}
		
	public void keyTyped(KeyEvent evt){}
	
}
