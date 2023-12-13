package utils;

public class Constants {
    public static class Directions {
        public static final int UP = 1;
        public static final int DOWN = 3;
        public static final int LEFT = 0;
        public static final int RIGHT = 2;
    }
    public static class PlayerConstants {
        public static final int SPAWN = 0;
        public static final int IDLE = 1;
        public static final int RUN_RIGHT = 2;
        public static final int JUMP = 3;
        public static final int FALL = 4;
        public static final int WALK_UP = 5;
        public static final int DEATH = 6;
        public static final int ATTACK_1 = 7;
        public static final int ATTACK_2 = 8;
        public static final int RUN_LEFT = 9;
        public static int GetSpriteAmount(int player_action) {
            return switch (player_action) {
                case SPAWN, IDLE -> 23;
                case RUN_RIGHT, RUN_LEFT, JUMP, FALL, WALK_UP -> 2;
                case DEATH -> 26;
                case ATTACK_1 -> 3;
                case ATTACK_2 -> 5;
                default -> 1;
            };
        }

    }
}
