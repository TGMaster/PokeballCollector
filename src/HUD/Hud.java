// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entity.Pokeball;
import Entity.Player;
import Main.GamePanel;
import Manager.Content;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;
	private BufferedImage pokeball;
	private BufferedImage boat;
	private BufferedImage axe;
	private BufferedImage key;
	
	private Player player;
	
	private int numPokeballs;
	
	private Font font;
	private Color textColor; 
	
	public Hud(Player p, ArrayList<Pokeball> d) {
		
		player = p;
		numPokeballs = d.size();
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		pokeball = Content.POKEBALL[0][0];
		boat = Content.ITEMS[0][0];
		axe = Content.ITEMS[0][1];
		key = Content.ITEMS[0][2];
		
		font = new Font("Arial", Font.PLAIN, 10);
		textColor = new Color(255, 51, 51);
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
		
		// draw bar
		g.setColor(textColor);
		g.fillRect(4, yoffset + 6, (int)(28.0 * player.numPokeballs() / numPokeballs), 4);
		
		// draw amount
		g.setColor(textColor);
		g.setFont(font);
		String s = player.numPokeballs() + "/" + numPokeballs;
		Content.drawString(g, s, 35, yoffset + 3);
		if(player.numPokeballs() >= 10) g.drawImage(pokeball, 75, yoffset, null);
		else g.drawImage(pokeball, 67, yoffset, null);
		
		// draw items
		if(player.hasBoat()) g.drawImage(boat, 100, yoffset, null);
		if(player.hasAxe()) g.drawImage(axe, 88, yoffset, null);
		if(player.hasKey()) g.drawImage(key, 112, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}
		
		
		
	}
	
}
