import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard {
    private MineTile[][] board;
    private int numberOfRows;
    private int numberOfColumns;

    public GameBoard(int rows, int cols) {
        this.numberOfRows = rows;
        this.numberOfColumns = cols;
        this.board = new MineTile[rows][cols];
    }

    public void initializeBoard(GameController gameController) {
        for (int r = 0; r < numberOfRows; r++) {
            for (int c = 0; c < numberOfColumns; c++) {
                MineTile tile = new MineTile(r, c);
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        gameController.tileClicked(tile, e);
                    }
                });
                board[r][c] = tile; // Przypisz tile do tablicy po dodaniu słuchacza
            }
        }
    }

    public MineTile getTile(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfColumns) {
            return null;
        }
        return board[row][col];
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
