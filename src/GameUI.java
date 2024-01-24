import javax.swing.*;
import java.awt.*;

public class GameUI {
    private JFrame frame;
    private JLabel textLabel;
    private JPanel boardPanel;
    private JButton resetButton;

    private GameController gameController;

    public GameUI() {
        frame = new JFrame("Minesweeper");
        textLabel = new JLabel("Minesweeper", SwingConstants.CENTER);
        boardPanel = new JPanel(new GridLayout(8, 8));
        initializeUI();
        resetButton = new JButton("Restart Game");
        resetButton.addActionListener(e -> gameController.resetGame());
        frame.add(resetButton, BorderLayout.SOUTH);
    }

    private void initializeUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        frame.add(textLabel, BorderLayout.NORTH);

        frame.add(boardPanel, BorderLayout.CENTER);
    }

    public void setGameController(GameController controller) {
        this.gameController = controller;
    }

    public void updateBoardPanel(GameBoard gameBoard) {
        boardPanel.removeAll();
        int rows = gameBoard.getNumberOfRows();
        int cols = gameBoard.getNumberOfColumns();
        boardPanel.setLayout(new GridLayout(rows, cols));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                MineTile tile = gameBoard.getTile(r, c);
                boardPanel.add(tile);
            }
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void updateStatusLabel(String text) {
        textLabel.setText(text);
    }
}
