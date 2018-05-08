// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.

package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.JukeBox;
import TileMap.TileMap;

public class Player extends Entity {
	
	// sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	private BufferedImage[] downBoatSprites;
	private BufferedImage[] leftBoatSprites;
	private BufferedImage[] rightBoatSprites;
	private BufferedImage[] upBoatSprites;
	
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;
	
	// gameplay
	private int numPokeballs;
	private int totalPokeballs;
	private boolean hasBoat;
	private boolean hasAxe;
	private boolean hasKey;
	private boolean onWater;
	private long ticks = GameState.MenuState.TIME;
        private final int animationDelay = 3;
        
        public static boolean isDead;
	
	public Player(TileMap tm) {
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		moveSpeed = 2;
		
		numPokeballs = 0;
		
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(animationDelay);
		
        isDead = false;
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public void collectedPokeball() { numPokeballs++; }
	public int numPokeballs() { return numPokeballs; }
	public int getTotalPokeballs() { return totalPokeballs; }
        public void setPokeballs(int i) { numPokeballs = i; }
	public void setTotalPokeballs(int i) { totalPokeballs = i; }
	
	public void gotBoat() { hasBoat = true; tileMap.replace(22, 4); }
	public void gotAxe() { hasAxe = true; }
	public void gotKey() { hasKey = true; }
	public boolean hasBoat() { return hasBoat; }
	public boolean hasAxe() { return hasAxe; }
	public boolean hasKey() { return hasKey; }
        public boolean isDead() { return isDead; }
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}
	
	// Keyboard input.
	// If Player has axe, dead trees in front
	// of the Player will be chopped down.
	public void setAction() {
		if(hasAxe) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
				JukeBox.play("cutdown");
			}
			if(currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
				JukeBox.play("cutdown");
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
				JukeBox.play("cutdown");
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
				JukeBox.play("cutdown");
			}
		}
		if (hasKey) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 23) {
				tileMap.setTile(rowTile - 1, colTile, 24);
				JukeBox.play("treasure");
			}
			if(currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 23) {
				tileMap.setTile(rowTile + 1, colTile, 24);
				JukeBox.play("treasure");
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 23) {
				tileMap.setTile(rowTile, colTile - 1, 24);
				JukeBox.play("treasure");
			}
			if(currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 23) {
				tileMap.setTile(rowTile, colTile + 1, 24);
				JukeBox.play("treasure");
			}
		}
	}
	
	public void update() {
		
		ticks--;
		
		// check if on water
		boolean current = onWater;
		if(tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			onWater = true;
		}
		else {
			onWater = false;
		}
		// if going from land to water
		if(!current && onWater) {
			JukeBox.play("splash");
		}
		
		// set animation
		if(down) {
			if(onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, animationDelay);
			}
			else if(!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, animationDelay);
			}
		}
		if(left) {
			if(onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, animationDelay);
			}
			else if(!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, animationDelay);
			}
		}
		if(right) {
			if(onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, animationDelay);
			}
			else if(!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, animationDelay);
			}
		}
		if(up) {
			if(onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, animationDelay);
			}
			else if(!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, animationDelay);
			}
		}
		
		// update position
		super.update();
		
	}
	
	// Draw Player.
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}