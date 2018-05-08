// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.
package Main;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game {

    protected static String TITLE = "Pokeball Collector";

    public static void main(String[] args) {

        JFrame window = new JFrame(TITLE);
        window.add(new GamePanel());

        window.setResizable(false);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        BufferedImage img = null;
        try {
            img = ImageIO.read(Game.class.getResourceAsStream("/Logo/ball.png"));
        } catch (IOException ex) {
            System.err.println("Cannot load logo!");
        }
        window.setIconImage(img);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
