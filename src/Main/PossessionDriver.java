package Main;
/*
 Class the implements mouse, key, graphics, sound, and starter thingys
*/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class PossessionDriver extends Canvas implements KeyListener, MouseMotionListener, MouseListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static final int MAP_WIDTH_OFFSET = 16;
	public static final int MAP_HEIGHT_OFFSET = 39;

	public static final int MAP_WIDTH = SCREEN_WIDTH - MAP_WIDTH_OFFSET;
	public static final int MAP_HEIGHT = SCREEN_HEIGHT - MAP_HEIGHT_OFFSET;
	
	protected boolean[] keys;
	protected boolean mousePressed;
	protected Point mousePoint;
	protected BufferedImage back;
	protected int timer = 16;

	public PossessionDriver() {
		//	Set up all variables related to the game
		// 	Number of key possibilities
		keys = new boolean[23];
		mousePoint = new Point();
		mousePressed = false;

        setBackground(Color.WHITE);
		setVisible(true);

		new Thread(this).start();
		addKeyListener(this);		//starts the key thread to log key strokes
		setFocusable(true);

		addMouseListener(this);
		addMouseMotionListener(this);
               
	}
   
   public void update(Graphics window){
	   paint(window);
   }

   public void setTimer(int value) {
   		timer = value;
   }
   public void paint(Graphics window) {
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics2D graphToBack = (Graphics2D) back.createGraphics();

		draw(graphToBack);

		Graphics2D win2D = (Graphics2D) window;
		win2D.drawImage(back, null, 0, 0);

	}

	public abstract void draw(Graphics2D win);

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W : keys[0]=true; break;
			case KeyEvent.VK_A : keys[1]=true; break;
			case KeyEvent.VK_S : keys[2]=true; break;
			case KeyEvent.VK_D : keys[3]=true; break;
			case KeyEvent.VK_SPACE : keys[4]=true; break;
			case KeyEvent.VK_Q : keys[5]=true; break;
			case KeyEvent.VK_E : keys[6]=true; break;
			case KeyEvent.VK_R : keys[7]=true; break;
			case KeyEvent.VK_P : keys[8]=true; break;
			case KeyEvent.VK_F : keys[9]=true; break;
			case KeyEvent.VK_G : keys[10]=true;break;

			case KeyEvent.VK_1 : keys[11]=true;	break;
			case KeyEvent.VK_2 : keys[12]=true;	break;
			case KeyEvent.VK_3 : keys[13]=true;	break;
			case KeyEvent.VK_4 : keys[14]=true;	break;
			case KeyEvent.VK_5 : keys[15]=true;	break;
			case KeyEvent.VK_Z : keys[16]=true; break;
			case KeyEvent.VK_X : keys[17]=true; break;
			case KeyEvent.VK_C : keys[18]=true; break;
			case KeyEvent.VK_SHIFT : keys[19]=true; break;
			case KeyEvent.VK_ENTER : keys[20]=true; break;
			case KeyEvent.VK_ESCAPE : keys[21]=true; break;
			case KeyEvent.VK_CONTROL : keys[22]=true; break;
		}

	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W : keys[0]=false; 	break;
			case KeyEvent.VK_A : keys[1]=false; 	break;
			case KeyEvent.VK_S : keys[2]=false; 	break;
			case KeyEvent.VK_D : keys[3]=false; 	break;
			case KeyEvent.VK_SPACE : keys[4]=false; break;
			case KeyEvent.VK_Q : keys[5]=false; 	break;
			case KeyEvent.VK_E : keys[6]=false; 	break;
			case KeyEvent.VK_R : keys[7]=false; 	break;
			case KeyEvent.VK_P : keys[8]=false; 	break;
			case KeyEvent.VK_F : keys[9]=false; 	break;
			case KeyEvent.VK_G : keys[10]=false;	break;

			case KeyEvent.VK_1 : keys[11]=false;	break;
			case KeyEvent.VK_2 : keys[12]=false;	break;
			case KeyEvent.VK_3 : keys[13]=false;	break;
			case KeyEvent.VK_4 : keys[14]=false;	break;
			case KeyEvent.VK_5 : keys[15]=false;	break;
			case KeyEvent.VK_Z : keys[16]=false; 	break;
			case KeyEvent.VK_X : keys[17]=false; 	break;
			case KeyEvent.VK_C : keys[18]=false; 	break;
			case KeyEvent.VK_SHIFT : keys[19]=false; break;
			case KeyEvent.VK_ENTER : keys[20]=false; break;
			case KeyEvent.VK_ESCAPE : keys[21]=false; break;
			case KeyEvent.VK_CONTROL : keys[22]=false; break;
		}
	}

	public void keyTyped(KeyEvent e){}

  	public void run(){
  		try{
  			while(true){
  				Thread.currentThread();
				Thread.sleep(timer);
				repaint();
  			}
  		}catch(Exception e){
  			Logger.logError("Exception: " + e.toString());
  		}
	}

  	public BufferedImage addImage(String name) {

  		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(name));
		} 
		catch (IOException e) {
			System.out.println(e +" - "+name);
		}
		
  		return img;
   	}

	//Mouse Listener Interface
  	public void mousePressed(MouseEvent e){
  		mousePressed = true;
  	}

  	public void mouseReleased(MouseEvent e){
  		mousePressed = false;
  	}

  	public void mouseClicked(MouseEvent e){}
  	public void mouseEntered(MouseEvent e){}
  	public void mouseExited(MouseEvent e){}

  	//Mouse Motion Interface
  	public void mouseDragged(MouseEvent e){
  		mousePoint.setLocation(e.getX(),e.getY());
  	}
  	public void mouseMoved(MouseEvent e){
  		mousePoint.setLocation(e.getX(),e.getY());
  	}

  	public Point getMousePosition(){
  		return mousePoint;
  	}
  	
  	public int getMouseX() {
  		return (int) mousePoint.getX();
  	}
  	
  	public int getMouseY() {
  		return (int) mousePoint.getY();
  	}
}