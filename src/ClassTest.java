import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

    @Test
    public void boardIsProperlyInitialized() {
        GameBoard board = new GameBoard(8, 8);
        board.initializeBoard(null);
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                assertNotNull(board.getTile(r, c), "Tile should not be null");
            }
        }
    }
    @Test
    public void tileFlaggingWorksCorrectly() {
        MineTile tile = new MineTile(0, 0);
        assertFalse(tile.isFlagged(), "Tile should not be flagged initially");
        tile.toggleFlag();
        assertTrue(tile.isFlagged(), "Tile should be flagged after toggling");
        tile.toggleFlag();
        assertFalse(tile.isFlagged(), "Tile should not be flagged after toggling again");
    }
}
