// Possibly redundant subclass of Entity.
// There are two types of items: Axe and boat.
// Upon collection, informs the Player
// that the Player does indeed have the item.

package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Item extends Entity{
	
	private BufferedImage sprite;
	private int type;
	public static final int BOAT = 0;
	public static final int AXE = 1;
	public static final int KEY = 2;
	
	public Item(TileMap tm) {
		super(tm);
		type = -1;
		width = height = 16;
		cwidth = cheight = 12;
	}
	
	public void setType(int i) {
		type = i;
		if(type == BOAT) {
			sprite = Content.ITEMS[1][0];
		}
		else if(type == AXE) {
			sprite = Content.ITEMS[1][1];
		}
		else if(type == KEY) {
			sprite = Content.ITEMS[1][2];
		}
	}
	
	public void collected(Player p) {
		if(type == BOAT) {
			p.gotBoat();
		}
		if(type == AXE) {
			p.gotAxe();
		}
		if(type == KEY) {
			p.gotKey();
		}
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(sprite, x + xmap - width / 2, y + ymap - height / 2, null);
	}
	
}
