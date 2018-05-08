// The main playing GameState.
// Contains everything you need for gameplay:
// Player, TileMap, Pokeballs, etc.
// Updates and draws all game objects.
package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Entity.Pokeball;
import Entity.Item;
import Entity.Player;
import Entity.Sparkle;
import HUD.Hud;
import Main.GamePanel;
import Manager.Data;
import Manager.GameStateManager;
import Manager.JukeBox;
import Manager.Keys;
import TileMap.TileMap;
import java.util.Random;

public class PlayState extends GameState {

    // player
    private Player player;

    // tilemap
    private TileMap tileMap;

    // pokeballs
    private ArrayList<Pokeball> pokeballs;

    // items
    private ArrayList<Item> items;

    // sparkles
    private ArrayList<Sparkle> sparkles;

    // camera position
    private int xsector;
    private int ysector;
    private int sectorSize;

    // hud
    private Hud hud;

    // events
    private boolean blockInput;
    private boolean eventStart;
    private boolean eventWin;
    private boolean eventLose;
    private int eventTick;

    // transition box
    private ArrayList<Rectangle> boxes;

    // random path
    private int randomPath;
    private Random rand;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        // Random path
        rand = new Random();
        randomPath = rand.nextInt(3) + 1;

        // create lists
        pokeballs = new ArrayList<Pokeball>();
        sparkles = new ArrayList<Sparkle>();
        items = new ArrayList<Item>();

        // load map
        tileMap = new TileMap(16);
        //tileMap.loadTiles("/Tilesets/testtileset.gif");
        tileMap.loadTiles("/Tilesets/tileset.gif");
        if (MenuState.SIZE_TYPE == 1) {
            tileMap.loadMap("/Maps/map.csv");
        } else {
            tileMap.loadMap("/Maps/map2.csv");
        }

        // create player
        player = new Player(tileMap);

        // fill lists
        populatePokeballs();
        populateItems();

        // initialize player
        if (MenuState.SIZE_TYPE == 1) {
            player.setTilePosition(17, 17);
        } else {
            player.setTilePosition(3, 3);
        }
        player.setTotalPokeballs(pokeballs.size());

        // set up camera position
        sectorSize = GamePanel.WIDTH;
        xsector = player.getx() / sectorSize;
        ysector = player.gety() / sectorSize;
        tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);

        // load hud
        hud = new Hud(player, pokeballs);

        // load music
        JukeBox.load("/Music/background.wav", "background");
        JukeBox.loop("background", 1000, 1000, JukeBox.getFrames("background") - 1000);
        JukeBox.load("/Music/victory.wav", "finish");
        JukeBox.setVolume("finish", -10);
        JukeBox.load("/Music/dead.wav", "dead");
        JukeBox.setVolume("dead", 3);

        // load sfx
        JukeBox.load("/SFX/treasure.wav", "treasure");
        JukeBox.load("/SFX/pause.wav", "pause");
        JukeBox.load("/SFX/hitwall.wav", "hitwall");
        JukeBox.load("/SFX/cutdown.wav", "cutdown");
        JukeBox.load("/SFX/collect.wav", "collect");
        JukeBox.setVolume("collect", -5);
        JukeBox.load("/SFX/mapmove.wav", "mapmove");
        JukeBox.setVolume("mapmove", -5);
        JukeBox.load("/SFX/tilechange.wav", "tilechange");
        JukeBox.setVolume("tilechange", -5);
        JukeBox.load("/SFX/splash.wav", "splash");

        // start event
        boxes = new ArrayList<Rectangle>();
        eventStart = true;
        eventStart();

    }
    
    private void populatePokeballs() {

        Pokeball d;

        if (MenuState.SIZE_TYPE == 1) {
            switch (randomPath) {
                case 1:
                    System.out.println("Case 1");
                    d = new Pokeball(tileMap);
                    d.setTilePosition(20, 20);
                    d.addChange(new int[]{23, 19, 1});
                    d.addChange(new int[]{23, 20, 1});
                    d.addChange(new int[]{24, 11, 21});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(12, 36);
                    d.addChange(new int[]{31, 17, 1});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(28, 4);
                    d.addChange(new int[]{27, 7, 1});
                    d.addChange(new int[]{28, 7, 1});
                    d.addChange(new int[]{27, 8, 1});
                    d.addChange(new int[]{28, 8, 1});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(4, 34);
                    d.addChange(new int[]{31, 21, 1});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(35, 26);
                    for (int i = 17; i < 23; i++) {
                        for (int j = 17; j < 23; j++) {
                            d.addChange(new int[]{i, j, 4});
                        }
                    }
                    pokeballs.add(d);

                    d = new Pokeball(tileMap);
                    d.setTilePosition(28, 19);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(38, 36);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(27, 28);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(20, 30);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(14, 25);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(4, 21);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(9, 14);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(4, 3);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(18, 2);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(13, 20);
                    pokeballs.add(d);
                    break;
                case 2:

                case 3:
                    System.out.println("Case 3");
                    d = new Pokeball(tileMap);
                    d.setTilePosition(20, 20);
                    d.addChange(new int[]{23, 19, 1});
                    d.addChange(new int[]{23, 20, 1});
                    d.addChange(new int[]{23, 11, 21});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(12, 36);
                    d.addChange(new int[]{15, 19, 21});
                    d.addChange(new int[]{16, 19, 21});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(4, 3);
                    d.addChange(new int[]{27, 7, 1});
                    d.addChange(new int[]{28, 7, 1});
                    d.addChange(new int[]{27, 8, 1});
                    d.addChange(new int[]{28, 8, 1});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(4, 34);
                    d.addChange(new int[]{31, 21, 1});
                    d.addChange(new int[]{31, 17, 2});
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(29, 14);
                    d.addChange(new int[]{29, 15, 21});
                    d.addChange(new int[]{28, 14, 40}); // Trap
                    d.addChange(new int[]{29, 13, 40}); // Trap
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(32, 17);
                    d.addChange(new int[]{36, 11, 41}); // Trap
                    pokeballs.add(d);

                    d = new Pokeball(tileMap);
                    d.setTilePosition(28, 19);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(35, 26);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(38, 36);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(27, 28);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(20, 30);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(14, 25);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(4, 21);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(28, 4);
                    pokeballs.add(d);
                    d = new Pokeball(tileMap);
                    d.setTilePosition(13, 20);
                    pokeballs.add(d);
            }

        } else {
            d = new Pokeball(tileMap);
            d.setTilePosition(5, 5);
            pokeballs.add(d);
            d = new Pokeball(tileMap);
            d.setTilePosition(4, 5);
            pokeballs.add(d);
            d = new Pokeball(tileMap);
            d.setTilePosition(3, 5);
            pokeballs.add(d);
            d = new Pokeball(tileMap);
            d.setTilePosition(4, 4);
            pokeballs.add(d);
            d = new Pokeball(tileMap);
            d.setTilePosition(3, 4);
            pokeballs.add(d);
        }

    }

    // Variable itemPositionRecorder is used to store: boat row, boat column, axe row, axe column.
    private void populateItems() {

        Item item;

        if (MenuState.SIZE_TYPE == 1) {
            item = new Item(tileMap);
            item.setType(Item.AXE);
            item.setTilePosition(26, 37);
            items.add(item);

            item = new Item(tileMap);
            item.setType(Item.BOAT);
            item.setTilePosition(12, 4);
            items.add(item);

            item = new Item(tileMap);
            item.setType(Item.KEY);
            item.setTilePosition(21, 21);
            items.add(item);
        } else {
        }

    }

    public void update() {

        // check keys
        handleInput();

        // check events
        if (eventStart) {
            eventStart();
        }
        if (eventWin) {
            eventWin();
        }
        if (eventLose) {
            eventLose();
        }

        if (player.numPokeballs() == player.getTotalPokeballs()) {
            eventWin = blockInput = true;
        }

        if (player.isDead()) {
            eventLose = blockInput = true;
        }

        if (player.getTicks() == 0) {
            eventLose = blockInput = true;
        }

        // update camera
        int oldxs = xsector;
        int oldys = ysector;
        xsector = player.getx() / sectorSize;
        ysector = player.gety() / sectorSize;
        tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
        tileMap.update();

        if (oldxs != xsector || oldys != ysector) {
            JukeBox.play("mapmove");
        }

        if (tileMap.isMoving()) {
            return;
        }

        // update player
        player.update();

        // update pokeballs
        for (int i = 0; i < pokeballs.size(); i++) {

            Pokeball d = pokeballs.get(i);
            d.update();

            // player collects pokeball
            if (player.intersects(d)) {

                // remove from list
                pokeballs.remove(i);
                i--;

                // increment amount of collected pokeballs
                player.collectedPokeball();

                // play collect sound
                JukeBox.play("collect");

                // add new sparkle
                Sparkle s = new Sparkle(tileMap);
                s.setPosition(d.getx(), d.gety());
                sparkles.add(s);

                // make any changes to tile map
                ArrayList<int[]> ali = d.getChanges();
                for (int[] j : ali) {
                    tileMap.setTile(j[0], j[1], j[2]);
                }
                if (!ali.isEmpty()) {
                    JukeBox.play("tilechange");
                }

            }
        }

        // update sparkles
        for (int i = 0; i < sparkles.size(); i++) {
            Sparkle s = sparkles.get(i);
            s.update();
            if (s.shouldRemove()) {
                sparkles.remove(i);
                i--;
            }
        }

        // update items
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (player.intersects(item)) {
                items.remove(i);
                i--;
                item.collected(player);
                JukeBox.play("collect");
                Sparkle s = new Sparkle(tileMap);
                s.setPosition(item.getx(), item.gety());
                sparkles.add(s);
            }
        }

    }

    public void draw(Graphics2D g) {

        // draw tilemap
        tileMap.draw(g);

        // draw player
        player.draw(g);

        // draw pokeballs
        for (Pokeball d : pokeballs) {
            d.draw(g);
        }

        // draw sparkles
        for (Sparkle s : sparkles) {
            s.draw(g);
        }

        // draw items
        for (Item i : items) {
            i.draw(g);
        }

        // draw hud
        hud.draw(g);

        // draw transition boxes
        g.setColor(java.awt.Color.BLACK);
        for (int i = 0; i < boxes.size(); i++) {
            g.fill(boxes.get(i));
        }

    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ESCAPE)) {
            JukeBox.stop("background");
            JukeBox.play("pause");
            gsm.setPaused(true);
        }
        if (blockInput) {
            return;
        }
        if (Keys.isDown(Keys.LEFT)) {
            player.setLeft();
        }
        if (Keys.isDown(Keys.RIGHT)) {
            player.setRight();
        }
        if (Keys.isDown(Keys.UP)) {
            player.setUp();
        }
        if (Keys.isDown(Keys.DOWN)) {
            player.setDown();
        }
        if (Keys.isPressed(Keys.SPACE)) {
            player.setAction();
        }
    }

    //===============================================
    private void eventStart() {
        eventTick++;
        if (eventTick == 1) {
            boxes.clear();
            for (int i = 0; i < 9; i++) {
                boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
            }
        }
        if (eventTick > 1 && eventTick < 32) {
            for (int i = 0; i < boxes.size(); i++) {
                Rectangle r = boxes.get(i);
                if (i % 2 == 0) {
                    r.x -= 4;
                } else {
                    r.x += 4;
                }
            }
        }
        if (eventTick == 33) {
            boxes.clear();
            eventStart = false;
            eventTick = 0;
        }
    }

    private void eventWin() {
        eventTick++;
        if (eventTick == 1) {
            boxes.clear();
            for (int i = 0; i < 9; i++) {
                if (i % 2 == 0) {
                    boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
                } else {
                    boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
                }
            }
            JukeBox.stop("background");
            JukeBox.play("finish");
        }
        if (eventTick > 1) {
            for (int i = 0; i < boxes.size(); i++) {
                Rectangle r = boxes.get(i);
                if (i % 2 == 0) {
                    if (r.x < 0) {
                        r.x += 4;
                    }
                } else {
                    if (r.x > 0) {
                        r.x -= 4;
                    }
                }
            }
        }
        if (eventTick > 33) {
            Data.setType(0);
            Data.setTime(player.getTicks());
            Data.setPoint(player.numPokeballs());
            gsm.setState(GameStateManager.GAMEOVER);
        }
    }

    private void eventLose() {
        eventTick++;
        if (eventTick == 1) {
            boxes.clear();
            boxes.add(new Rectangle(-128, 0, GamePanel.WIDTH, GamePanel.HEIGHT2));
            JukeBox.stop("background");
            JukeBox.play("dead");
        }
        if (eventTick > 1 && eventTick < 33) {
            for (int i = 0; i < boxes.size(); i++) {
                Rectangle r = boxes.get(i);
                if (i % 2 == 0) {
                    if (r.x < 0) {
                        r.x += 4;
                    }
                } else {
                    if (r.x > 0) {
                        r.x -= 4;
                    }
                }
            }
        }
        if (eventTick > 33) {
            if (!JukeBox.isPlaying("finish")) {
                if (player.getTicks() == 0) {
                    Data.setType(1);
                } else {
                    Data.setType(2);
                }
                Data.setPoint(player.numPokeballs());
                gsm.setState(GameStateManager.GAMEOVER);
            }
        }
    }

}
