// The main menu GameState.

package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.GameStateManager;
import Manager.JukeBox;
import Manager.Keys;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage pokeball;
        
        // Time elapse
        public static int TIME;

        // Map size
        public static int SIZE_TYPE;
	
	private int currentOption = 0;
	private String[] options = {
                "BIG",
                "SMALL",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		pokeball = Content.POKEBALL[0][0];
                JukeBox.load("/Music/menu.wav", "menu");
                JukeBox.setVolume("menu", -10);
                JukeBox.loop("menu");
		JukeBox.load("/SFX/menuoption.wav", "select");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 50, 90);
		Content.drawString(g, options[1], 44, 100);
                Content.drawString(g, options[2], 48, 110);
		
		if(currentOption == 0) g.drawImage(pokeball, 25, 86, null);
		else if(currentOption == 1) g.drawImage(pokeball, 25, 96, null);
                else if(currentOption == 2) g.drawImage(pokeball, 25, 106, null);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			JukeBox.play("select");
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			JukeBox.play("select");
			currentOption--;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("select");
			selectOption();
		}
	}
	
	private void selectOption() {
		if(currentOption == 0) {
                        JukeBox.stop("menu");
                        SIZE_TYPE = 1;
                        TIME = 9000;
			gsm.setState(GameStateManager.PLAY);
		}
		if(currentOption == 1) {
			TIME = 5000;
			SIZE_TYPE = 2;
                        JukeBox.stop("menu");
                        gsm.setState(GameStateManager.PLAY);
		}
                if(currentOption == 2) {
                        System.exit(0);
		}
	}
	
}
