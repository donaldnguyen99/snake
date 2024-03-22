import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public static final String TITLE = "Snake Game";

    GameFrame() {
        
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(new GamePanel());
        this.pack();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
}
