package Configuration;
/**
 * classe contenant les valeurs constantes
 */
public class Configuration {
		
        public static final int WINDOW_WIDTH = 700;
        public static final int WINDOW_HEIGHT = 700;
        public static final int BLOCK_SIZE = WINDOW_WIDTH/25;
        public static final int PLATEAU_WIDTH = WINDOW_WIDTH*7/8;
        public static final int PLATEAU_HEIGHT = WINDOW_HEIGHT*7/8;
        public static final int LINE_COUNT = (WINDOW_HEIGHT / BLOCK_SIZE);
        public static final int COLUMN_COUNT = (WINDOW_WIDTH / BLOCK_SIZE);
        public static final int GAME_SPEED = 1000;
        
}

