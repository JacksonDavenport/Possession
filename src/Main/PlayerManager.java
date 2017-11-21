package Main;

import java.util.ArrayList;

import Objects.Building;
import Objects.Player;

public class PlayerManager extends ObjectManager{
	
	private Player player;
	
	public PlayerManager(ArrayList<Building> arena, Player p) {
		super(arena, p);
		player = p;
	}
	
	public void handleMovement(boolean W, boolean A, boolean S, boolean D, ArrayList<Building> arena) {
		int dx = 0;
		int dy = 0;
		
		if(A)
			dx -= super.distanceRemainingLeftSide(player,  player.getXVel(), player.getYVel());
		if(D)
			dx += super.distanceRemainingRightSide(player,  player.getXVel(), player.getYVel());
		if(W)
			dy -= super.distanceRemainingTopSide(player, player.getXVel(), player.getYVel());
		if(S)
			dy += super.distanceRemainingBottomSide(player, player.getXVel(), player.getYVel());
		
		super.passMovement(player, dx, dy);
		logMovement(W, A, S, D);
	}
	
	private void logMovement(boolean W, boolean A, boolean S, boolean D) {
		if(Logger.isFinest() && (W || A || S || D)) {
			String log = "";
			if(W)
				log += "W ";
			if(A)
				log += "A ";
			if(S)
				log += "S ";
			if(D)
				log += "D ";
			
			Logger.logFinest(log + "(" + player.getXCenter() + ", " + player.getYCenter() + ")");
		}
	}
}
