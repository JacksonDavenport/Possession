package Objects;
/*
	The enemies who move at int enemySpeed speed, set to either 1 or 2 prefer 
	Enemies have health, direction, spawn at buildings, and will attempt to clear the building if player is on the other side
*/
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.Random;

import Main.Logger;
import Main.PossessionCanvas;

public class Enemy extends Actor{
	
	private int dx, dy, drawDirection, direction, 
		        health, startingHealth, 
		        enemySpeed, moveCount;
		        
	private Actor healthBar,remainingHealth;
	
	private boolean dead, clearingBuilding, halfSpeed;
	
	private BufferedImage imageToDraw,imageRight,imageLeft;
	private Random r1;
	
	private Player player;

	public Enemy(int x, int y, Player p){
		super(x,y,26,26,Color.MAGENTA);
		
		r1 = new Random();
		healthBar = new Actor(x,y-8,26,6,Color.RED);
		remainingHealth = new Actor(x,y-8,26,6,Color.GREEN);
		
		drawDirection = EAST;
		direction = 0;
		dead = false;
		enemySpeed = 2;
		super.setSpeed(enemySpeed);
		halfSpeed = false;
		moveCount = 0;
		startingHealth = health = 10;
		player = p;
		
		Logger.logDebug("Hash code for generated enemy is: " + this.hashCode());
	}

	public void act(Graphics2D win){
		/*
		if(!dead){
			

		}
		if(health <= 0)
			die();
		*/
		Logger.logFinest("Draw Actor: " + toString());
		drawActor(win);
	}

	public void passMovement(int dx, int dy) {
		getHitBox().translate(dx,dy);
		if(dx > 0)
			direction = EAST;
		else if(dx < 0)
			direction = WEST;
	}	

    /* 
     * Name: Move
     * Purpose: Direct the enemies movement, will head in the direction of the player
     *          if enemy is set to move a vertical or horizontal of 1 pixel changes it
     *          to two, if it hits a building it will ignore the direction heading into
     *          the building.  If the player is at the enemy or horizontal-ness from the 
     *          enemy it will clear the building, moving at its original direction.
     *          Will also set drawing direction and its movement direction.
     * Void
     */
    public void move(){
    	/*
    	//If the slow enemy moves on its turn then move, or if its not a slow enemy
		if( (halfSpeed && moveCount % 2 == 1) || !halfSpeed ){
			if(!clearingBuilding){
				//If the enemy is set to clear the building move appropriately
				//dx and dy are its moving translate direction
				double hypot = 	Math.sqrt(	Math.pow(Math.abs(PossessionCanvas.getPlayerXCenter()-getXCenter()),2) 
							  			  + Math.pow(Math.abs(PossessionCanvas.getPlayerYCenter()-getYCenter()),2) );
				double factor = hypot/enemySpeed;
				dx = (int) Math.round( (PossessionCanvas.getPlayerXCenter() - getXCenter())/factor );
				dy = (int) Math.round( (PossessionCanvas.getPlayerYCenter() - getYCenter())/factor );
	            
	            //If enemy is set to move to an odd pixel then make it even, so it
	            //does not go through buildings
					
				if(enemySpeed == 2){
					if(dx == -1)
						dx = -2;
					else if(dx == 1)
						dx = 2;
					if(dy == -1)
						dy = -2;
					else if(dy == 1)
						dy = 2;
				}
	
		   	    //If the enemy is touching the building on its left side, set its dx to nothing
				if( dx < 0 && PossessionCanvas.touchingLeftRight(this) == -1 ){
					dx = 0;
					//If the enemy is at the same vertical position as the player clear the building
					if( getYCenter() == PossessionCanvas.getPlayerYCenter() ){
						clearBuilding();
					}
					//If the enemy is lower than the player, move south
					else if(getYCenter() < PossessionCanvas.getPlayerYCenter()){
						dy = enemySpeed;
						direction = SOUTH;
					}
					//If the enemy is above the player, move north
					else{
						dy = -enemySpeed;
						direction = NORTH;
					}
				}
				
				//If the enemy is touching the building on its right side, set its dx to nothing
				if( dx > 0 && PossessionCanvas.touchingLeftRight(this) ==  1 ){
					dx = 0;
					//If the enemy is at the same vertical position as the player clear the building
					if( getYCenter() == PossessionCanvas.getPlayerYCenter() ){
						clearBuilding();
					}
					//If the enemy is lower than the player, move south
					else if(getYCenter() < PossessionCanvas.getPlayerYCenter()){
						dy = enemySpeed;
						direction = SOUTH;
					}
					//If the enemy is above the player, move north
					else{
						dy = -enemySpeed;
						direction = NORTH;
					}
				}
				
				//If the enemy is touching building on its top side, set its dy to nothing
				if( dy < 0 && PossessionCanvas.touchingTopBottom(this) == -1 ){
					dy = 0;
					//If the enemy is at the same vertical position as the player clear the building
					if( getXCenter() == PossessionCanvas.getPlayerXCenter() ){
						clearBuilding();
					}
					//If the enemy is to the left of the player, move east
					else if(getXCenter() < PossessionCanvas.getPlayerXCenter()){
						dx = enemySpeed;
						direction = EAST;
					}
					//If he enemy is to the right of the player, move west
					else{
						dx = -enemySpeed;
						direction = WEST;
					}
				}
				
				//If the enemy is touching building on its bottom side, set its dy to nothing
				if( dy > 0 && PossessionCanvas.touchingTopBottom(this) ==  1 ){
					dy = 0;
					//If the enemy is at the same vertical position as the player clear the building
					if( getXCenter() == PossessionCanvas.getPlayerXCenter() ){
						clearBuilding();
					}
					//If the enemy is to the left of the player, move east
					else if(getXCenter() < PossessionCanvas.getPlayerXCenter()){
						dx = enemySpeed;
						direction = EAST;
					}
					//If he enemy is to the right of the player, move west
					else{
						dx = -enemySpeed;
						direction = WEST;
					}
				}
			}
			//If set to clear the building
			else if( clearingBuilding ){
			clearBuilding();
			}
		}

	            
		//Move all elements of the enemy and change the direction its facing
		getHitBox().translate(dx,dy);
		remainingHealth.getHitBox().translate(dx,dy);
		healthBar.getHitBox().translate(dx,dy);
		if(dx > 0)
			drawDirection = EAST;
		if(dx < 0)
			drawDirection = WEST;
	
		//For the enemies moving at half speed	
		moveCount++;
		*/
	}
	
	/* 
	 * Name: clearBuilding
	 * Purpose: If the enemy is at the same x or y coordinate of the player, and it is touching 
	 *          a building it will continue in its direction to pass the building.  Gets called
	 *          in through the move method, and boolean clearingBuilding makes it so this movement
	 *          action is called.
	 * Void
	 */
    public void clearBuilding(){
    	/*
    	//If its touching a wall, uses its previous direction to guide it.
    	if(PossessionCanvas.touchingTopBottom(this) != 0 || PossessionCanvas.touchingLeftRight(this) != 0){
    		clearingBuilding = true;
    		if(direction == EAST){
    			dx = enemySpeed;
    			dy = 0;
    		}
    		else if(direction == WEST){
    			dx = -enemySpeed;
    			dy = 0;
    		}
    		else if(direction == SOUTH){
    			dy = enemySpeed;
    			dx = 0;
    		}
    		else if(direction == NORTH){
    			dy = -enemySpeed;
    			dx = 0;
    		}
    	}
    	//Once it clears the wall continue move normally
    	else{
    		clearingBuilding = false;
    	}
    	*/
    }

	/*
	 * Name: damage
	 * Purpose: Take health away and shrink the health bar of the enemy
	 * Parameters: int value - Damage
	 * Void
	*/
    public void damage(int value){
    	health -= value;
    	double value2 = (health/(double)startingHealth) * 26;
    	remainingHealth.setWidth((int) value2);
    }

	/*
	 * Name: drawActor
	 * Purpose: draws the enemy and its health bar, changes according to its 
	 *          direction, and if the enemy has taken damage or not.
	 * Parameter: Graphics2D win - Graphics obj
	 * Void
	*/
    public void drawActor(Graphics2D win){
		super.drawActor(win);
		/*
		if(drawDirection == EAST)
			imageToDraw = imageRight;
		if(drawDirection == WEST)
			imageToDraw = imageLeft;
		win.drawImage(imageToDraw, getXPos(), getYPos(), null);
		*/
		//If the enemy has not taken damage, don't draw its health bar
		//Looks cooler
		if(health != startingHealth){
			healthBar.drawActor(win);
			remainingHealth.drawActor(win);
		}
    }

    public void die(){
    	dead = true;
    }

    public int getHealth(){
    	return health;
    }

	public boolean isDead(){
    	return dead;
    }

	
}