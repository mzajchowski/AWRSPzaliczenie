import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private GameUI gameUI;
    private GameBoard gameBoard;
    private ArrayList<MineTile> mineList;
    private Random random;
    private boolean gameOver;
    private int mineCount;
    private int tilesClicked;

    public GameController(GameUI ui, int rows, int cols, int mines) {
        this.gameUI = ui;
        this.gameBoard = new GameBoard(rows, cols);
        this.mineCount = mines;
        this.random = new Random();
        this.gameOver = false;
        this.mineList = new ArrayList<>();
        this.tilesClicked = 0;
    }

    public void startGame() {
        gameBoard.initializeBoard(this);
        setMines();
        gameUI.updateBoardPanel(gameBoard);
    }

    public void tileClicked(MineTile tile, MouseEvent e) {
        if (gameOver) {
            return;
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            tile.toggleFlag();
        } else if (!tile.isFlagged() && SwingUtilities.isLeftMouseButton(e)) {
            if (mineList.contains(tile)) {
                revealMines();
                gameOver = true;
                gameUI.updateStatusLabel("Game Over!");
            } else {
                checkMine(tile.getRow(), tile.getColumn());
                if (tilesClicked == gameBoard.getNumberOfRows() * gameBoard.getNumberOfColumns() - mineCount) {
                    gameOver = true;
                    gameUI.updateStatusLabel("You Win!");
                }
            }
        }
    }

    private void setMines() {
        while (mineList.size() < mineCount) {
            int r = random.nextInt(gameBoard.getNumberOfRows());
            int c = random.nextInt(gameBoard.getNumberOfColumns());
            MineTile tile = gameBoard.getTile(r, c);

            if (!mineList.contains(tile)) {
                mineList.add(tile);
            }
        }
    }

    private void revealMines() {
        for (MineTile tile : mineList) {
            tile.setText("💣");
        }
    }

    private void checkMine(int r, int c) {
        MineTile tile = gameBoard.getTile(r, c);
        if (tile == null || !tile.isEnabled()) {
            return;
        }

        tile.setEnabled(false);
        tilesClicked++;

        int minesFound = countSurroundingMines(r, c);
        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        } else {
            tile.setText("");
            revealAdjacentTiles(r, c);
        }
    }
    public void resetGame() {
        gameOver = false;
        tilesClicked = 0;
        mineList.clear();
        gameBoard = new GameBoard(gameBoard.getNumberOfRows(), gameBoard.getNumberOfColumns());
        gameBoard.initializeBoard(this);
        setMines();
        gameUI.updateBoardPanel(gameBoard);
        gameUI.updateStatusLabel("Minesweeper");
    }

    private void revealAdjacentTiles(int r, int c) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    checkMine(r + i, c + j);
                }
            }
        }
    }

    private int countSurroundingMines(int r, int c) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isMine(r + i, c + j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isMine(int r, int c) {
        MineTile tile = gameBoard.getTile(r, c);
        return tile != null && mineList.contains(tile);
    }
}
