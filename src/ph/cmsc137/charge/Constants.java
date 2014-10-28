package ph.cmsc137.charge;
import java.awt.Color;


public interface Constants {
	
	/**
	 * Game states.
	 */
	public static final int GAME_START=0;
	public static final int IN_PROGRESS=1;
	public final int GAME_END=2;
	public final int WAITING_FOR_PLAYERS=3;
	public final int WAITING_FOR_READY=4;
	/**
	 * Game port
	 */
	public static final int PORT=4447;
	
	public static final int UNITCOST = 100;
	public static final int UPGRADECOST = 1000;
	public static final int REPAIRCOST = 500;
	
	public static final String [][] laneMatch = {
		{"","RIGHT","MIDDLE","LEFT"},
		{"LEFT","","RIGHT","MIDDLE"},
		{"MIDDLE","LEFT","","RIGHT"},
		{"RIGHT","MIDDLE","LEFT",""},
	};
	
	public static final Color teamColors[] = {
		Color.BLUE,Color.GREEN,Color.RED,Color.PINK
	};
}
