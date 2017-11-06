package Main;

import java.util.ArrayList;

import Objects.Building;
import Objects.Player;

public class PlayerManager extends ObjectManager{
	private Player player;
	
	public PlayerManager(Player p) {
		player = p;
	}
	
	public void handleMovement(boolean W, boolean A, boolean S, boolean D, ArrayList<Building> arena) {
		int dx = 0;
		int dy = 0;
		
		if(A)
			dx -= super.distanceRemainingLeftSide(player,  arena,  player.getXVel(), player.getYVel());
		if(D)
			dx += super.distanceRemainingRightSide(player,  arena,  player.getXVel(), player.getYVel());
		if(W)
			dy -= super.distanceRemainingTopSide(player, arena, player.getXVel(), player.getYVel());
		if(S)
			dy += super.distanceRemainingBottomSide(player, arena, player.getXVel(), player.getYVel());
		
		player.passMovement(dx, dy);
		
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
