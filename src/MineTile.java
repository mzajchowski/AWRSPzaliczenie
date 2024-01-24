import javax.swing.JButton;
import java.awt.*;

public class MineTile extends JButton {
    private int row;
    private int column;
    private boolean isFlagged;

    public MineTile(int row, int column) {
        this.row = row;
        this.column = column;
        setPreferredSize(new Dimension(50, 50));
        isFlagged = false;
    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
        setText(isFlagged ? "🚩" : "");
    }

    public boolean isFlagged() {
        return isFlagged;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
