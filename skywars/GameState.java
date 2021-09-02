package fr.kenda.skywars;

public enum GameState {

    WAITING, STARTING, CAGE, GAME, FINISH;

    private static GameState current;

    public static void setState(GameState state){
        current = state;
    }

    public static boolean isState(GameState state){
        return current == state;
    }
}
