package Objects;
/*
 Controllable player
*/
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Main.Logger;
import Main.PossessionCanvas;

import java.awt.image.BufferedImage;

public class Player extends Actor{

	private static final int INIT_SPEED = 4;
	
	private static int playerHealth, startingHealth, bodyArmor; 
	private int direction, killPoints;
	private int debugCount;
	private boolean possessMode;
	private boolean[] playerKeys;  // W,A,S,D,SPACEBAR = 0,1,2,3,4 | MOVEMENT AND ATTACK
	
	private BufferedImage imageToDraw,imageRight,imageLeft;
	
	private PossessionCanvas canvas;
	private ArrayList<Enemy> enemies;
	
	public Player(PossessionCanvas gameCanvas, ArrayList<Enemy> enemyList){
		super(0, 0, 26, 26, Color.PINK);
		canvas = gameCanvas;
		enemies = enemyList;
		
		playerHealth = startingHealth = 15;
		bodyArmor = 0;
		
		super.setSpeed(INIT_SPEED);
		direction = EAST;				//0 = right, 90 = north, 180 = east, 270 = south
		killPoints = 0;
		
		possessMode = false;
		playerKeys = new boolean[5];
		
		debugCount = 0;
	}

	public void act(Graphics2D win){
		if(isPossessed()){
			possessMove();
		}
		else{
			//standardMove();
		}

		drawActor(win);
	}
			
	private void possessMove(){
		Point mousePoint = canvas.getMousePosition();
		int xPos = (int) mousePoint.getX();
		int yPos = (int) mousePoint.getY();
		if(xPos % 2 != 0)
			xPos++;
		if(yPos %2 != 0)
			yPos++;
		setLocation(xPos, yPos);
	}

	public void hit(int damage){
		if(bodyArmor != 0){
			bodyArmor -= damage;
		}
		else{
			playerHealth -= damage;
		}
	}

	public void setKeysOn(int index){
		playerKeys[index] = true;
	}
	public void setKeysOff(int index){
		playerKeys[index] = false;
	}

	public void drawActor(Graphics2D win){
		super.drawActor(win);
		/*
		if(direction == EAST)
			imageToDraw = imageRight;
		if(direction == WEST)
			imageToDraw = imageLeft;
		win.drawImage(imageToDraw, getXPos(), getYPos(), null);
		*/
	}
	
	public static void heal(){
		playerHealth = startingHealth;
	}
	public static void increaseHealth(){
		startingHealth += 5;
	}
	public static void setHealth(int value){
		startingHealth = value;
	}

	public static void bodyArmor(){
		bodyArmor = 10;
	}
	
	public void resetCharacter(){
		startingHealth = 15;
		playerHealth = startingHealth;
		bodyArmor = 0;
		killPoints = 0;

		super.setLocation(0,0);
	}

	public void enemyKilled(){
		
	}
	
	public void possessModeOn(){
		possessMode = true;
	}	
	public void possessModeOff(){
		possessMode = false;
	}
	
	public boolean isPossessed(){
		return possessMode;
	}
	
	public int canPossessEnemy(){
		for(int i = 0; i < enemies.size(); i++){
			Logger.logFinest("Enemy: " + enemies.get(i).getHitBox().toString() + " Mouse At: " + canvas.getMousePosition().toString());
			if(enemies.get(i).getHitBox().contains(canvas.getMousePosition())){
				return i;
			}
		}
		return 0;
	}
	public void possessEnemy(int e){
		int xPos = enemies.get(e).getXPos();
		int yPos = enemies.get(e).getYPos();
		setLocation(xPos, yPos);
		enemies.get(e).die();
	}
	
	
	
	public int getDirection(){
		return direction;
	}

	public int getPlayerHealth(){
		return playerHealth;
	}
	public int getPlayerStartingHealth(){
		return startingHealth;
	}
	public int getPlayerArmor(){
		return bodyArmor;
	}

	
}
