package Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Objects.Actor;
import Objects.Building;
import Objects.Enemy;
import Objects.Player;

public class PossessionCanvas extends PossessionDriver {

	//Constants
	public static final int SPLASH_STATE = 0;
	public static final int MAIN_STATE = 1;
	public static final int END_STATE = 2;
	public static final int PAUSE_STATE = 3; 

	private static ArrayList<Building> arena;
	private static ArrayList<Enemy> enemies;
	private static Player player;
	
	private PlayerManager playerManager;
	private EnemyManager enemyManager;

	Rectangle BG;
//	private static boolean reloadingOverride;
	private int gameState,pauseGame,frames, spawnBuffer;//Button Held deals with spawn
	private int bulletCount, bulletTimer;

	private long t0,t1;
	
	private BufferedImage backgroundImage;

	public PossessionCanvas(){
		BG = new Rectangle(0,0,PossessionDriver.SCREEN_WIDTH, PossessionDriver.SCREEN_HEIGHT);
		spawnBuffer = 0;
		//reloadingOverride = false;
		pauseGame = 0;
		gameState = MAIN_STATE;
		t0 = t1 = 0;
		frames = 0;
		
		enemies = new ArrayList<Enemy>();
		player = new Player(this, enemies);
				
		createArena();
		
		playerManager = new PlayerManager(player);
		enemyManager = new EnemyManager(player, arena);
	}

	public void draw(Graphics2D win) {
		drawBackGround(win);
		getKeyInput();
		if(gameState == SPLASH_STATE)
			splashPage(win);
		if(gameState == MAIN_STATE)
			mainPage(win);
		if(gameState == END_STATE)
			endPage(win);
		if(gameState == PAUSE_STATE)
			pausePage(win);
	}

	public void splashPage(Graphics2D win){
		win.setColor(Color.BLACK);
		win.drawString("Press Enter to Start",600,400);
		win.drawString("WASD to move, space auto-aim, mouse shoot, p pause",500,450);
		if(keys[20]){//ENTER
			gameState = MAIN_STATE;
		}
	}

	public void mainPage(Graphics2D win){
		enemyManager.act();
		for(int i = 0; i < arena.size(); i++)		//Buildings
			{arena.get(i).act(win);}
		for(int i = 0; i < enemies.size(); i++)		//Enemies
			{enemies.get(i).act(win);}
		player.act(win);							//Player
	}

	public void endPage(Graphics2D win){
	/*
	 	win.setColor(Color.BLACK);
		win.drawString("YOU SUCK!",600,400);
		win.drawString("High Score: " + score.getHighScore(), 590, 500);
		win.drawString("Your Score: " + score.getTotalScore(),590,550);
		win.drawString("R to restart", 600,600);
	*/
	}

	public void pausePage(Graphics2D win){
		win.setColor(Color.BLACK);
		win.drawString("Paused", 600,375);
		win.drawString("Press P to continue   |   Press R to restart",600,400);
	}

	public void ifGameOver(){
		if(player.getPlayerHealth() <= 0){
			gameState = 2;
			resetGame();
		}
	}


	//If bullets collide with building, zombie, or go offscreen
	public void checkBullets(){
		
	}
	//If zombies are dead remove
	public void checkEnemies(){

	}
	 
	public void drawUserInterface(Graphics2D win){

	}

	//Draws the background, changes based on gameState
	public void drawBackGround(Graphics2D win){
		if(gameState == SPLASH_STATE){
			win.setColor(Color.GREEN);
			win.fill(BG);
		}
		else if(gameState == MAIN_STATE){
			win.setColor(Color.GREEN);
			win.fill(BG);
			//win.drawImage(backgroundImage, 0, 0, null);
		}
		else if(gameState == END_STATE){
			win.setColor(Color.RED);
			win.fill(BG);
		}
		else if(gameState == PAUSE_STATE){
			win.setColor(Color.CYAN);
			win.fill(BG);
		}
	}

	private void createArena(){
		arena = new ArrayList<Building>();
		//West

		//South 

		//East

		//North

		//
		arena.add(new Building(26,26,150,150));
		arena.add(new Building(500,100,150,150));
		arena.add(new Building(100,300,150,150));
		arena.add(new Building(500,300,150,150));


	}

	//Pauses the Game
	public void pauseTheGame(){
		//4 Steps to pausing
		if( keys[8] && pauseGame == 0 && gameState == MAIN_STATE){	//Pause button pressed, PAUSE
			pauseGame = 1;
			gameState = PAUSE_STATE;
		}
		if(!keys[8] && pauseGame == 1 && gameState == PAUSE_STATE){	//Pause button released, PAUSE
			pauseGame = 2;
		}
		if( keys[8] && pauseGame == 2 && gameState == PAUSE_STATE){	//Pause button pressed, PAUSE
			pauseGame = 3;
			gameState = PAUSE_STATE;
		}
		if(!keys[8] && pauseGame == 3 && gameState == PAUSE_STATE){	//Pause button released, not paused
			pauseGame = 0;
			gameState = MAIN_STATE;
		}
	}

	/*
	 *	Spawns an enemy at a random valid point on the arena
	 *	Is valid if within arena and not colliding with building piece
	*/
	private void spawnRandomEnemy(){
			boolean willSpawn = true;
			int spawnX, spawnY;
			do{
				willSpawn = true;
				//Random location
				spawnX = (int)(Math.random()*MAP_WIDTH);
				spawnY = (int)(Math.random()*MAP_HEIGHT);
				//Make the location on even coordinates
				if(spawnX % 2 == 1)
					spawnX++;
				if(spawnY % 2 == 1)
					spawnY++;
				//Test to see if the spawn point is within a building
				Rectangle test = new Rectangle(spawnX,spawnY,26,26);
				for(int i = 0; i < arena.size(); i++){
					if( test.intersects(arena.get(i).getHitBox()) )
						willSpawn = false;
				}
			}while(!willSpawn);
			//Spawn the enemy if a valid location is chosen
			Enemy e = new Enemy(spawnX, spawnY, player);
			enemies.add(e);
			enemyManager.addEnemy(e.hashCode(), e);
	}

	/*
	 *	Spawns an enemy at a random spawn point in the arena
	 *	The spawn points are pre-determined
	*/
	private void spawnRandomSpawnPointEnemy(){

	}

	//Resets the Game when paused and reset key
	public void resetGame(){
		if((gameState == PAUSE_STATE || gameState == END_STATE) && keys[8]){
			pauseGame = 0;
			gameState = 0;
			player.resetCharacter();
		}
	}
 	
	public static int getPlayerXCenter(){
		return player.getXCenter();
	}
	public static int getPlayerYCenter(){
		return player.getYCenter();
	}
	public static ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public static Enemy getEnemy(int index){
		return enemies.get(index);
	}
	public static void reloadingOn(){
		//reloadingOverride = true;
	}
	public static void reloadingOff(){
		//reloadingOverride = false;
	}

	public static boolean intersects(Actor one, Actor two){
		if(one.getHitBox().intersects(two.getHitBox()))
			return true;
		else
			return false;
	}
	public static Building getBuilding(int index){
		return arena.get(index);
	}
	
	//Additional Key Input method
	public void getKeyInput(){
		pauseTheGame();					// P
		resetGame();					// R
		
		playerManager.handleMovement(keys[0],  keys[1],  keys[2],  keys[3], arena);
	
		if(keys[4]){					// SPACEBAR
			// Go into possess mode
			player.possessModeOn();
		}
		else{
			player.possessModeOff();
		}

		if(keys[5]){					// Q
		
		}

		if(keys[6]){					// E
		
		}
			
		if(keys[7] && gameState == MAIN_STATE){	// R
			//gun.manualReload();
		}

		if(keys[8]){					// P

		}
		
		if(keys[9]){					// F

		}	
		if(keys[10]){					// G

		}
		
		if(keys[11]){					// 1
			player.setSpeed(1);
		}
		if(keys[12]){					// 2
			player.setSpeed(2);
		}
		if(keys[13]){					// 3
			player.setSpeed(3);
		}
		if(keys[14]){					// 4
			player.setSpeed(4);
		}
		if(keys[15]){					// 5
			player.setSpeed(5);
		}
		
		if(keys[16]){					// Z

		}	
		if(keys[17]){					// X

		}
		
		if(keys[18]){					// C
			if(spawnBuffer == 0) {
				spawnRandomEnemy();
				spawnBuffer = 1;
			}
		}
		else {
			spawnBuffer = 0;
		}
		
		if(keys[19]){					// SHIFT
			Logger.setLogDebug();
		}
		if(keys[20]){					// ENTER

		}		
		if(keys[21]){					// ESCAPE
	
		}
		if(keys[22]){					// CONTROL
			Logger.setLogFinest();
		}		
		

		if(mousePressed){			// Mouse Pressed
			//gun.shoot(player.getXCenter(),player.getYCenter(),(int) super.getMousePosition().getX(), (int) super.getMousePosition().getY());
			if(player.isPossessed()){
				int enemyIndex = player.canPossessEnemy();
				if(enemyIndex != 0){
					Logger.logDebug("Possess Enemy: " + enemyIndex);
					player.possessEnemy(enemyIndex);
					player.possessModeOff();
				}
			}
			
		}
		else{
			//gun.triggerReleased();
		}
	}
	
}