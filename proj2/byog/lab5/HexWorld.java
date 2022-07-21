package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 50;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s){
            effectiveI = 2 * s -1 - i;
        }
        return -effectiveI;
    }

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - i;
        }
        return s + 2*effectiveI;
    }
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int i = 0; i < width; i++) {
            int xCoord = p.x + i;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2){
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        //yi is the row of the hexagon
        for (int yi = 0; yi < 2*s; yi++) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
        
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0 : return Tileset.FLOWER;
            case 1 : return Tileset.GRASS;
            case 2 : return Tileset.MOUNTAIN;
            case 3 : return Tileset.SAND;
            default: return Tileset.TREE;
        }
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int N) {
        Position nextP = new Position(p.x,p.y);
        for (int i = 0;i < N; i++) {
            addHexagon(world,nextP,s,randomTile());
            nextP.y += 2 * s;
        }
    }
    public static int hexNum(int i) {
        if (i <= 2){
            return i + 3;
        } else {
            return 7 - i;
        }
    }
    public static int yOffset(int s, int i) {
        int yOff = s;
        if (i < 2){
            return -yOff;
        }
        return yOff;
    }
    public static void drawTesselationofHexagons(TETile[][] world, Position p, int s) {
        Position nextp = new Position(p.x, p.y);
        int xOffset = 2*s - 1;
        for (int i = 0; i < 5; i ++) {
            drawRandomVerticalHexes(world, nextp, s, hexNum(i));
            nextp.x += xOffset;
            nextp.y += yOffset(s, i);

        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }


       Position startP = new Position(3,10);
       // addHexagon(world, startP, 3,Tileset.FLOWER);
        //drawRandomVerticalHexes(world,startP,3,4);
        drawTesselationofHexagons(world,startP,4);
        // draws the world to the screen
        ter.renderFrame(world);
    }

}
