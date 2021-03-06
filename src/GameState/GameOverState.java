// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.
// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot
package GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Manager.Content;
import Manager.Data;
import Manager.GameStateManager;
import Manager.JukeBox;
import Manager.Keys;

public class GameOverState extends GameState {

    private Color color;

    private int rank;
    private int points;
    private long ticks;
    private int type;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        color = new Color(164, 198, 222);
        points = Data.getPoint() * 2;
        type = Data.getType();
        if (type == 0) {
            ticks = Data.getTime();
            points += ticks;
        }
        if (points >= 5400) {
            rank = 1;
        } else if (points >= 3600 && points < 5400) {
            rank = 2;
        } else if (points >= 1800 && points < 3600) {
            rank = 3;
        } else {
            rank = 4;
        }
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {

        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);

        if (type == 0) {
                Content.drawString(g, "time remaining", 8, 36);
                int minutes = (int) (ticks / 1800);
                int seconds = (int) ((ticks / 30) % 60);
                if (minutes < 10) {
                    if (seconds < 10) {
                        Content.drawString(g, "0" + minutes + ":0" + seconds, 44, 48);
                    } else {
                        Content.drawString(g, "0" + minutes + ":" + seconds, 44, 48);
                    }
                } else {
                    if (seconds < 10) {
                        Content.drawString(g, minutes + ":0" + seconds, 44, 48);
                    } else {
                        Content.drawString(g, minutes + ":" + seconds, 44, 48);
                    }
                }
        } else { Content.drawString(g, "you lose", 33, 36); }

        Content.drawString(g, "rank", 48, 66);
        if (rank == 1) {
            Content.drawString(g, "speed demon", 20, 78);
        } else if (rank == 2) {
            Content.drawString(g, "adventurer", 24, 78);
        } else if (rank == 3) {
            Content.drawString(g, "beginner", 32, 78);
        } else if (rank == 4) {
            Content.drawString(g, "bumbling idiot", 8, 78);
        }

        Content.drawString(g, "press Enter to", 8, 110);
        Content.drawString(g, "go to menu", 24, 120);

    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            gsm.setState(GameStateManager.MENU);
            JukeBox.play("select");
        }
    }

}
