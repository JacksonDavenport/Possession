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
	
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 800;
	
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
		keys = new boolean[90];
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
			case KeyEvent.VK_W : keys[KeyEvent.VK_W]=true; break;
			case KeyEvent.VK_A : keys[KeyEvent.VK_A]=true; break;
			case KeyEvent.VK_S : keys[KeyEvent.VK_S]=true; break;
			case KeyEvent.VK_D : keys[KeyEvent.VK_D]=true; break;

			case KeyEvent.VK_Q : keys[KeyEvent.VK_Q]=true; break;
			case KeyEvent.VK_E : keys[KeyEvent.VK_E]=true; break;
			case KeyEvent.VK_R : keys[KeyEvent.VK_R]=true; break;
			case KeyEvent.VK_P : keys[KeyEvent.VK_P]=true; break;
			case KeyEvent.VK_F : keys[KeyEvent.VK_F]=true; break;
			case KeyEvent.VK_G : keys[KeyEvent.VK_G]=true;break;

			case KeyEvent.VK_0 : keys[KeyEvent.VK_0]=true;	break;
			case KeyEvent.VK_1 : keys[KeyEvent.VK_1]=true;	break;
			case KeyEvent.VK_2 : keys[KeyEvent.VK_2]=true;	break;
			case KeyEvent.VK_3 : keys[KeyEvent.VK_3]=true;	break;
			case KeyEvent.VK_4 : keys[KeyEvent.VK_4]=true;	break;
			case KeyEvent.VK_5 : keys[KeyEvent.VK_5]=true;	break;
			case KeyEvent.VK_6 : keys[KeyEvent.VK_6]=true;	break;
			case KeyEvent.VK_7 : keys[KeyEvent.VK_7]=true;	break;
			case KeyEvent.VK_8 : keys[KeyEvent.VK_8]=true;	break;
			case KeyEvent.VK_9 : keys[KeyEvent.VK_9]=true;	break;			
			
			case KeyEvent.VK_Z : keys[KeyEvent.VK_Z]=true; break;
			case KeyEvent.VK_X : keys[KeyEvent.VK_X]=true; break;
			case KeyEvent.VK_C : keys[KeyEvent.VK_C]=true; break;
			
			case KeyEvent.VK_SPACE : keys[KeyEvent.VK_SPACE]=true; break;
			case KeyEvent.VK_CONTROL : keys[KeyEvent.VK_CONTROL]=true; break;
			case KeyEvent.VK_SHIFT : keys[KeyEvent.VK_SHIFT]=true; break;
			case KeyEvent.VK_CAPS_LOCK : keys[KeyEvent.VK_CAPS_LOCK]=true; break;
			case KeyEvent.VK_ENTER : keys[KeyEvent.VK_ENTER]=true; break;
			case KeyEvent.VK_ESCAPE : keys[KeyEvent.VK_ESCAPE]=true; break;
		}

	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W : keys[KeyEvent.VK_W]=false; break;
			case KeyEvent.VK_A : keys[KeyEvent.VK_A]=false; break;
			case KeyEvent.VK_S : keys[KeyEvent.VK_S]=false; break;
			case KeyEvent.VK_D : keys[KeyEvent.VK_D]=false; break;
	
			case KeyEvent.VK_Q : keys[KeyEvent.VK_Q]=false; break;
			case KeyEvent.VK_E : keys[KeyEvent.VK_E]=false; break;
			case KeyEvent.VK_R : keys[KeyEvent.VK_R]=false; break;
			case KeyEvent.VK_P : keys[KeyEvent.VK_P]=false; break;
			case KeyEvent.VK_F : keys[KeyEvent.VK_F]=false; break;
			case KeyEvent.VK_G : keys[KeyEvent.VK_G]=false;break;
	
			case KeyEvent.VK_0 : keys[KeyEvent.VK_0]=false;	break;
			case KeyEvent.VK_1 : keys[KeyEvent.VK_1]=false;	break;
			case KeyEvent.VK_2 : keys[KeyEvent.VK_2]=false;	break;
			case KeyEvent.VK_3 : keys[KeyEvent.VK_3]=false;	break;
			case KeyEvent.VK_4 : keys[KeyEvent.VK_4]=false;	break;
			case KeyEvent.VK_5 : keys[KeyEvent.VK_5]=false;	break;
			case KeyEvent.VK_6 : keys[KeyEvent.VK_6]=false;	break;
			case KeyEvent.VK_7 : keys[KeyEvent.VK_7]=false;	break;
			case KeyEvent.VK_8 : keys[KeyEvent.VK_8]=false;	break;
			case KeyEvent.VK_9 : keys[KeyEvent.VK_9]=false;	break;			
			
			case KeyEvent.VK_Z : keys[KeyEvent.VK_Z]=false; break;
			case KeyEvent.VK_X : keys[KeyEvent.VK_X]=false; break;
			case KeyEvent.VK_C : keys[KeyEvent.VK_C]=false; break;
			
			case KeyEvent.VK_SPACE : keys[KeyEvent.VK_SPACE]=false; break;
			case KeyEvent.VK_CONTROL : keys[KeyEvent.VK_CONTROL]=false; break;
			case KeyEvent.VK_SHIFT : keys[KeyEvent.VK_SHIFT]=false; break;
			case KeyEvent.VK_CAPS_LOCK : keys[KeyEvent.VK_CAPS_LOCK]=false; break;
			case KeyEvent.VK_ENTER : keys[KeyEvent.VK_ENTER]=false; break;
			case KeyEvent.VK_ESCAPE : keys[KeyEvent.VK_ESCAPE]=false; break;
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