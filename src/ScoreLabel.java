import java.awt.Color;

import javax.swing.JLabel;

public class ScoreLabel extends JLabel {
    
    ScoreLabel() {
        this.setText("Score: 0");
        this.setBounds(0, 0, 100, 25);
        this.setForeground(new Color(255, 255, 255, 128));
    }
    
    public void incrementScore() {
        int score = Integer.parseInt(this.getText().split(" ")[1]);
        this.setText("Score: " + (score + 1));
    }

    public void resetScore() {
        this.setText("Score: 0");
    }
}
