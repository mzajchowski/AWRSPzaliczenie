public class Minesweeper {

    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        GameController gameController = new GameController(gameUI, 8, 8, 10);
        gameUI.setGameController(gameController);
        gameController.startGame();
    }
}
