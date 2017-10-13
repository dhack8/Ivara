package util;

import maths.Vector;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LevelGenerator {

    private static final String ROOT = "./levelLoader"; // Root folder for level images
    private static final int MAX_SIZE = 100; // Max level grid size

    // GameEntity colours
    private static final Color PLAYER = new Color(255,255,255);
    private static final Color PLATFORM = new Color(0,0,0);
    private static final Color FAKEPLATFORM = new Color(57,57,57);
    private static final Color MOVINGPLATFORM = new Color(255,0,0);
    private static final Color FLAG = new Color(0,255,249);
    private static final Color COIN = new Color(21,0,255);
    private static final Color GHOST = new Color(177,177,177);
    private static final Color BEE = new Color(246,255,0);
    private static final Color BARNACLE = new Color(223,0,255);
    private static final Color SNAKE = new Color(32,108,0);
    private static final Color SLIME = new Color(77,255,0);


    public static String imgToLevel(String filename) throws IOException{
        // Read image
        BufferedImage img = readImage(filename);

        // Convert to 2D Color array
        Color[][] rgbTable = convertToArray(img);

        // Convert to level class string
        StringBuilder level = new StringBuilder();
        level.append(levelHeader(filename) + "\n");
        level.append(levelEntities(rgbTable) + "\n");
        level.append(levelDefaults(img.getHeight()) + "\n");
        level.append(levelFooter());


        return level.toString();
    }

    private static String levelEntities(Color[][] grid) {
        boolean[][] checked = new boolean[grid.length][grid[0].length];
        StringBuilder sb = new StringBuilder(codeLine("//ENTITIES---"));
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (checked[y][x])
                    continue;

                Color tile = grid[y][x];
                if (tile == null)
                    continue;

                checked[y][x] = true;

                if (tile.equals(PLATFORM)) {
                    sb.append(platform(x, y, platformFill(x, y, grid, checked, PLATFORM), false));
                } else if (tile.equals(MOVINGPLATFORM)) {
                    sb.append(platform(x, y, platformFill(x, y, grid, checked, MOVINGPLATFORM), true));
                } else if (tile.equals(FAKEPLATFORM))
                    sb.append(fakePlatform(x,y));
                else if (tile.equals(COIN))
                    sb.append(coin(x,y));
                else if (tile.equals(PLAYER))
                    sb.append(player(x,y));
                else if (tile.equals(FLAG))
                    sb.append(flag(x,y));
                else if (tile.equals(GHOST))
                    sb.append(ghost(x,y));
                else if (tile.equals(BEE))
                    sb.append(bee(x,y));
                else if (tile.equals(BARNACLE))
                    sb.append(barnacle(x,y, grid));
                else if (tile.equals(SNAKE))
                    sb.append(snake(x,y));
                else if (tile.equals(SLIME))
                    sb.append(slime(x,y));
                else
                    System.err.println("Unknown tile colour: " + tile.toString());

            }
        }
        return sb.toString();
    }

    private static String slime(int x, int y) {
        return codeLine("addEntity(new SlimeEntity(new Vector("+x+","+y+"));");
    }

    private static String snake(int x, int y) {
        return codeLine("addEntity(new SnakeEntity(new Vector("+x+","+((float)y-0.5f)+"f));");
    }

    private static String barnacle(int x, int y, Color[][] grid) {
        List<Vector> adjacentPlatforms = new ArrayList<>();
        int width = grid[0].length;
        int height = grid.length;
        if (y+1 < height && grid[y+1][x].equals(PLATFORM))
            adjacentPlatforms.add(new Vector(x,y+1));
        if (y-1 >= 0 && grid[y-1][x].equals(PLATFORM))
            adjacentPlatforms.add(new Vector(x,y-1));
        if (x+1 < width && grid[y][x+1].equals(PLATFORM))
            adjacentPlatforms.add(new Vector(x+1,y));
        if (x-1 >= 0 && grid[y][x-1].equals(PLATFORM))
            adjacentPlatforms.add(new Vector(x-1,y));

        if (adjacentPlatforms.isEmpty())
            return codeLine("addEntity(new BarnacleEntity(new Vector("+x+", "+y+"), true));");
        else {
            Vector platform = adjacentPlatforms.get(0);
            String dir = "NORTH";
            if (y < platform.y)
                dir = "NORTH";
            else if (y > platform.y)
                dir = "SOUTH";
            else if (x < platform.x)
                dir = "EAST";
            else if (x > platform.x)
                dir = "WEST";
            return codeLine("addEntity(new BarnacleEntity(new Vector("+x+","+y+"), BarnacleEntity.Direction."+dir+", true));");
        }
    }

    private static String bee(int x, int y) {
        return codeLine("addEntity(new BeeEntity(new Vector("+x+","+y+"), player, null)); // TODO: Fill in deviance");
    }

    private static String ghost(int x, int y) {
        return codeLine("addEntity(new GhostEntity(new Vector("+x+","+y+"), player));");
    }

    private static String flag(int x, int y) {
        return codeLine("addEntity(new LevelEndEntity("+x+", "+y+"));");
    }

    private static String player(int x, int y) {
        return codeLine("PlayerEntity player = new PlayerEntity("+x+","+y+");")
                + codeLine("addEntity(player);");
    }

    private static String coin(int x, int y) {
        return codeLine("addEntity(new CoinEntity(player, new Vector("+x+", "+y+"), true));");
    }

    private static String fakePlatform(int x, int y) {
        return codeLine("addEntity(new FakeBlockEntity(new Vector("+x+", "+y+")));");
    }

    private static String platform(int x, int y, Vector end, boolean moving) {
        String res = "addEntity(new PlatformEntity(new Vector("+x+","+y+")";

        if (end.x != x) { // horizontal
            int n = (int)end.x - x;
            res += ","+n+",false";
        } else if (end.y != y){ // vertical
            int n = (int)end.y - y;
            res += ","+n+",true";
        }

        if (moving)
            res += ",null,0f)); // TODO: Fill in end position and duration";
        else
            res += "));";

        return codeLine(res);
    }
    private static Vector platformFill(int x, int y, Color[][] grid, boolean[][] checked, Color platformType) {
        int height = grid.length;
        int width = grid[y].length;

        if (x+1 < width && grid[y][x+1] != null && grid[y][x+1].equals(platformType)) {// horizontal multi
            while (x < width && grid[y][x].equals(platformType)) {
                checked[y][x] = true;
                x++;
            }
        } else if (y+1 < height && grid[y+1][x] != null && grid[y+1][x].equals(platformType)) {// vertical multi
            while (y < height && grid[y][x].equals(platformType)) {
                checked[y][x] = true;
                y++;
            }
        }

        return new Vector(x,y);
    }

    private static String levelFooter() {
        return "\t}\n}\n";
    }

    private static String levelDefaults(int levelHeight) {
        StringBuilder sb = new StringBuilder();
        sb.append(codeLine("//DEFAULT---"));
        sb.append(codeLine("addEntity(new BackgroundEntity(new ResourceID(\"background\")));"));
        int deathHeight = levelHeight + 10;
        sb.append(codeLine("addEntity(new DeathLineEntity("+deathHeight+"));"));
        sb.append(codeLine("setCamera(new Camera());"));
        sb.append(codeLine("super.startScene(player);"));
        return sb.toString();
    }

    private static String codeLine(String s) {
        return "\t\t" + s + "\n";
    }

    private static BufferedImage readImage(String filename) throws IOException{
        BufferedImage img = ImageIO.read(new File(ROOT + "/tmp2.png")); //TODO file selection
        if (img.getType() != 6) //png type
            throw new IllegalArgumentException("Image not a png");
        if (img.getWidth() > MAX_SIZE || img.getHeight() > MAX_SIZE)
            throw new IllegalArgumentException("Image too large (" + MAX_SIZE + "x" + MAX_SIZE + ")");
        return img;
    }

    private static String levelHeader(String levelName) {
        return "package ivara.scenes;\n\n\n"
                + "import core.struct.Camera;\n"
                + "import core.struct.ResourceID;\n"
                + "import core.struct.Text;\n"
                + "import ivara.entities.PlatformEntity;\n"
                + "import ivara.entities.PlayerEntity;\n"
                + "import ivara.entities.*;\n"
                + "import ivara.entities.enemies.*;\n"
                + "import maths.Vector;\n\n"
                + "/**\n"
                + " * Auto-generated by LevelGenerator - Will Pearson.\n"
                + " */\n"
                + "public class " + levelName + " extends DefaultScene {\n"
                + "\tpublic void startScene(){\n";
    }

    private static Color[][] convertToArray(BufferedImage img) {
        Color[][] arr = new Color[img.getWidth()][img.getHeight()];
        for (int y = 0; y < arr.length; y++)
            for (int x = 0; x < arr[0].length; x++) {
                Color col = new Color(img.getRGB(x, y), true);
                arr[y][x] = col.getAlpha() == 0 ? null : col; // transparent pixels treated as invisible
            }
        return arr;
    }

    public static void main(String[] args) {
        try {
            String level = imgToLevel("tmp2.png");
            System.out.println(level);
        } catch (IOException e) {e.printStackTrace();}

    }
}
