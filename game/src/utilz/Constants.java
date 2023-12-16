package utilz;

/**
 * definiert Konstanten fuer verschiedene Klassen, worueber die Animationen geladen werden koennen.
 * 
 * @author leonie & hannah
 *
 */
public class Constants {
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		
		public static final int STEHEN = 0;
		public static final int STEHEN2 = 1;
		public static final int GEHEN = 2;
		public static final int LAUFEN = 3;
		public static final int DUCKEN = 4;
		public static final int SPRINGEN = 5;
		public static final int AUFLOESEN = 6;
		public static final int STERBEN = 7;
		public static final int ATTACKE = 8;
		public static final int FLIEGEN = 5;

		/**
		 * gibt an, wie viele Animations-Bilder zu der Player-Aktion zur Verfuegung stehen.
		 * @param player_action
		 * @return
		 */
		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
	
			case STEHEN:
			case STEHEN2:
				return 2;
			case GEHEN:
				return 4;
			case AUFLOESEN:
				return 3;
			case DUCKEN:
				return 6;
			case LAUFEN:
			case SPRINGEN:
			case STERBEN:
			case ATTACKE:
				return 8;
				
			default:
				return 1;
			}
		}
	}
	
public static class FlagConstants {
		
		public static final int STEHEN = 0;

		/**
		 * gibt an, wie viele Animations-Bilder zu der Player-Aktion zur Verfuegung stehen.
		 * @param player_action
		 * @return
		 */
		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case STEHEN:
				return 5;
				
			default:
				return 1;	
			}
		}
	}

}
