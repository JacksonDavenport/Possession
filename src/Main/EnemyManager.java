package Main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Objects.Actor;
import Objects.Building;
import Objects.Enemy;
import Objects.Player;

public class EnemyManager extends ObjectManager{
	
	private HashMap<Integer, Enemy> enemiesMap;
	private ArrayList<Building> arena;
	private Player player;
	
	public EnemyManager(Player player, ArrayList<Building> arena) {
		super();
		this.player = player;
		this.arena = arena;
		enemiesMap = new HashMap<Integer, Enemy>();
	}
	
	public void act() {
		for(Entry<Integer, Enemy> e : enemiesMap.entrySet()) {
			Logger.logFinest("Act on object: " + (e.getValue()));
			move(e.getValue());
		}
	}
	
	public void move(Enemy e) {
		Point dest = determineDestination(e, player);
		handleMovement(e, dest);
	}
	
	public void move(int enemyKey) {
		Enemy e = enemiesMap.get(enemyKey);
		move(e);
	}
	
	public Point determineDestination(Enemy enemy, Actor a) {
		return new Point(a.getXCenter(), a.getYCenter());
	}
	
	public void handleMovement(Enemy enemy, Point p) {
		int dx = getDX(enemy, p);
		int dy = getDY(enemy, p);
		Logger.logFinest("Enemy movement prior to collision detection: (" + dx + ", " + dy + ") for enemy: " + enemy.toString() + " to destination: " + p);

		if(dx > 0) {
			dx = super.distanceRemainingRightSide(enemy, arena, dx, dy);
		}
		else if(dx < 0) {
			dx = -1 * super.distanceRemainingLeftSide(enemy,  arena,  dx,  dy);
		}
		if(dy > 0) {
			dy = super.distanceRemainingBottomSide(enemy,  arena,  dx,  dy);
		}
		else if(dy < 0 ) {
			dy = -1 * super.distanceRemainingTopSide(enemy,  arena,  dx,  dy);
		}
		Logger.logFinest("Enemy movement after collision detection: (" + dx + ", " + dy + ") for enemy: " + enemy.toString() + " to destination: " + p);
		
		if(Math.abs(dx) > enemy.getXVel() || Math.abs(dy) > enemy.getYVel()) {
			Logger.logError("Speed of: (" + dx + ", " + dy + ") too high for speed: (" + enemy.getXVel() + ", " + enemy.getYVel() + ")"); 
		}
		
		enemy.passMovement(dx, dy);
	}
	
	public int getDX(Enemy enemy, Point p) {
		int d = p.x - enemy.getXCenter();
		return d == 0 ? 0 : (d / Math.abs(d)) * enemy.getXVel();
	}
	
	public int getDY(Enemy enemy, Point p) {
		int d = p.y - enemy.getYCenter();
		return d == 0 ? 0 : (d / Math.abs(d)) * enemy.getYVel();
	}
	
	public void addEnemy(int enemyIndex, Enemy enemy) {
		enemiesMap.put(enemyIndex,  enemy);
		Logger.logDebug("Added enemy to enemy map with index: " + enemyIndex + ", " + enemy.toString() + ", " + enemiesMap.size() + " enemies in map");
	}
	
	public void removeEnemy(int enemyIndex) {
		enemiesMap.remove(enemyIndex);
		Logger.logDebug("Remove enemy to enemy map with index: " + enemyIndex + ", " + enemiesMap.size() + " enemies in map");
	}
}
