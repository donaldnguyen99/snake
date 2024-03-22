import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameOver extends JPanel {

    private static final String TITLE = "Game Over";
    private static final String TITLE_FONT = "Arial";
    private static final int TITLE_FONT_STYLE = java.awt.Font.BOLD;
    private static final int TITLE_FONT_SIZE = 40;

    private static final String SUBTITLE = "Press Enter to Restart";
    private static final String SUBTITLE_FONT = "Arial";
    private static final int SUBTITLE_FONT_STYLE = java.awt.Font.BOLD;
    private static final int SUBTITLE_FONT_SIZE = 20;


    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;

    GameOver(Graphics g, GamePanel gamePanel) {
        SCREEN_WIDTH = gamePanel.SCREEN_WIDTH;
        SCREEN_HEIGHT = gamePanel.SCREEN_HEIGHT;

        drawGameOver(g);
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.red);

        g.setFont(new Font(TITLE_FONT, TITLE_FONT_STYLE, TITLE_FONT_SIZE));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(TITLE, (SCREEN_WIDTH - metrics.stringWidth(TITLE)) / 2, SCREEN_HEIGHT / 2);

        g.setFont(new Font(SUBTITLE_FONT, SUBTITLE_FONT_STYLE, SUBTITLE_FONT_SIZE));
        metrics = getFontMetrics(g.getFont());
        g.drawString(SUBTITLE, (SCREEN_WIDTH - metrics.stringWidth(SUBTITLE)) / 2, SCREEN_HEIGHT / 2 + 50);
    }
    
}
