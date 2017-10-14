package util;

import maths.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.io.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Level Generator takes a pixel image and converts it in to a level file. Run the main method
 * to generate a level. Takes png files from /levelLoader directory and saves to the /scenes folder.
 * This is purely used as a developer tool at this stage as it doesn't make checks to ensure the level is
 * correct. I.e. Possible to have more than one player and end flag.
 *
 * @author Will Pearson
 */
public class LevelGenerator {

    private static final String ROOT = "./levelLoader"; // Root folder for level images
    private static final String LEVELFOLDER = "./src/main/java/ivara/scenes"; // Save .java files here
    public static final int MAX_SIZE = 100; // Max level grid size

    // GameEntity colours
    private static final Color EMPTY = new Color(0,0,1);
    private static final Color PLAYER = new Color(255,255,255);
    private static final Color PLATFORM = new Color(0,0,0);
    private static final Color FAKEPLATFORM = new Color(57,57,57);
    private static final Color MOVINGPLATFORM = new Color(255,0,0);
    private static final Color ENDFLAG = new Color(0,255,249);
    private static final Color CHECKPOINT = new Color(70,0,70);
    private static final Color COIN = new Color(21,0,255);
    private static final Color GHOST = new Color(177,177,177);
    private static final Color BEE = new Color(246,255,0);
    private static final Color BARNACLE = new Color(223,0,255);
    private static final Color SNAKE = new Color(32,108,0);
    private static final Color SLIME = new Color(77,255,0);

    /**
     * Generates the level file.
     * @return The whole string representing the level.
     * @throws IOException Occurs when the selected file isn't found or saving issues occur.
     */
    public static String generateLevel() throws IOException{
        // Select image
        String filename = selectImage();
        String levelName = fileToLevelName(filename);

        // Read image
        BufferedImage img = readImage(filename);

        // Convert to 2D Color array
        Color[][] rgbTable = convertToArray(img);

        // Convert to level string
        String level = colorToLevel(rgbTable, levelName, img.getHeight());

        // Save as .java
        saveToFile(level, levelName);

        return level;
    }

    // Utility methods -----------------------------------------------------------------------

    /**
     * Selects the png image that the user wants to generate.
     * @return The name of the file.
     */
    private static String selectImage() {
        JFileChooser fileChooser = new JFileChooser(ROOT);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Filter", "png");
        int option;
        do {
            option = fileChooser.showOpenDialog(null);
        } while (option != JFileChooser.APPROVE_OPTION);
        File file = fileChooser.getSelectedFile();
        return fileChooser.getName(file);
    }

    /**
     * Attempts to read the given filename as an image. The image's dimensions must be less than the
     * MAX_SIZE field. the image must also be a png.
     * @param filename The name of the file.
     * @return The image object.
     * @throws IOException Thrown if an error occurs reading the file.
     */
    private static BufferedImage readImage(String filename) throws IOException{
        BufferedImage img = ImageIO.read(new File(ROOT + "/" + filename)); //TODO file selection
        if (img.getType() != 6) //png type
            throw new IllegalArgumentException("Image not a png");
        if (img.getWidth() > MAX_SIZE || img.getHeight() > MAX_SIZE)
            throw new IllegalArgumentException("Image too large (" + MAX_SIZE + "x" + MAX_SIZE + ")");
        return img;
    }

    /**
     * Converts an image to a 2D-array of colours. In the png file, transparent pixels are treated as
     * empty, and so are stored as the EMPTY Color constant stored locally.
     * @param img The image to process
     * @return The 2D array of colours.
     */
    private static Color[][] convertToArray(BufferedImage img) {
        Color[][] arr = new Color[img.getHeight()][img.getWidth()];
        for (int y = 0; y < arr.length; y++)
            for (int x = 0; x < arr[0].length; x++) {
                Color col = new Color(img.getRGB(x, y), true);
                arr[y][x] = col.getAlpha() == 0 ? EMPTY : col; // transparent pixels treated as invisible
            }
        return arr;
    }

    /**
     * Turns a 2D array of Colors representing a level to it's string representation.
     * The string version is suitable for use in a java file.
     * @param rgbTable The 2D array.
     * @param levelName The level name.
     * @param height The level's maximum height.
     * @return The string representation of the level.
     */
    private static String colorToLevel(Color[][] rgbTable, String levelName, int height){
        StringBuilder level = new StringBuilder();
        level.append(levelHeader(levelName));
        level.append(levelEntities(rgbTable));
        level.append('\n');
        level.append(levelDefaults(height));
        level.append(levelFooter(levelName));

        return level.toString();
    }

    /**
     * Converts an image file name to it's level name. Assumes there is only one dot in the file name.
     * @param fileName The file name.
     * @return The level name.
     */
    private static String fileToLevelName(String fileName) {
        return Character.toUpperCase(fileName.charAt(0)) + fileName.substring(1,fileName.indexOf('.'));
    }

    /**
     * Formats a regular string to a proper line of code. Used for ease of use within this class.
     * @param s The string.
     * @return The formatted string.
     */
    private static String codeLine(String s) {
        return "\t\t" + s + "\n";
    }

    /**
     * Saves a level to a file. If the file already exists it overwrites it.
     * @param level The string representation of the level.
     * @param levelName The level name.
     * @throws IOException Thrown if an error occurs while saving.
     */
    private static void saveToFile(String level, String levelName) throws IOException {
        String fileName = LEVELFOLDER + "/" + levelName + ".java";
        FileWriter fileWriter = new FileWriter(fileName, false);
        fileWriter.write(level);
        fileWriter.close();
    }

    // Text output -------------------------------------------------------------------------

    /**
     * Returns the level file's header information.
     * @param levelName The level name.
     * @return The level file's header information.
     */
    private static String levelHeader(String levelName) {

        return "package ivara.scenes;\n\n\n"
                + "import core.scene.Scene;\n"
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

    /**
     * Generates the entity section of the level file. It iterates the color grid
     * using the colors to determine what entity it should create.
     * @param grid The color grid.
     * @return A string representing all the entities in the level.
     */
    private static String levelEntities(Color[][] grid) {
        boolean[][] checked = new boolean[grid.length][grid[0].length]; // Records if the tile has been checked
        Map<Integer, StringBuilder> entityStrings = new TreeMap<>(); // Used to group the strings by category so that all platforms are together etc.
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (checked[y][x])
                    continue;

                Color tile = grid[y][x];
                if (tile.equals(EMPTY))
                    continue;

                checked[y][x] = true;

                // Determine entity type and add to map
                if (tile.equals(PLATFORM)) {
                    addToMap(entityStrings,3, platform(x, y, platformFill(x, y, grid, checked, PLATFORM), false));
                } else if (tile.equals(MOVINGPLATFORM)) {
                    addToMap(entityStrings, 4,platform(x, y, platformFill(x, y, grid, checked, MOVINGPLATFORM), true));
                } else if (tile.equals(FAKEPLATFORM))
                    addToMap(entityStrings, 5,fakePlatform(x,y));
                else if (tile.equals(COIN))
                    addToMap(entityStrings, 6,coin(x,y));
                else if (tile.equals(PLAYER))
                    addToMap(entityStrings, 0,player(x,y));
                else if (tile.equals(ENDFLAG))
                    addToMap(entityStrings, 2,endFlag(x,y));
                else if (tile.equals(GHOST))
                    addToMap(entityStrings, 7,ghost(x,y));
                else if (tile.equals(BEE))
                    addToMap(entityStrings, 8,bee(x,y));
                else if (tile.equals(BARNACLE))
                    addToMap(entityStrings, 9,barnacle(x,y,grid));
                else if (tile.equals(SNAKE))
                    addToMap(entityStrings, 10,snake(x,y));
                else if (tile.equals(SLIME))
                    addToMap(entityStrings, 11,slime(x,y));
                else if (tile.equals(CHECKPOINT))
                    addToMap(entityStrings, 1,checkPoint(x,y));
                else
                    System.err.println("Unknown tile colour: " + tile.toString());
            }
        }

        return collect(entityStrings);
    }

    /**
     * Returns the default scripts that are added to every level.
     * @param levelHeight The max height of the level
     * @return The default scripts.
     */
    private static String levelDefaults(int levelHeight) {
        StringBuilder sb = new StringBuilder();
        sb.append(codeLine("//DEFAULT---"));
        sb.append(codeLine("addEntity(new BackgroundEntity(new ResourceID(\"background\")));"));
        int deathHeight = levelHeight + 10; // place death line slighty lower than the level's depth
        sb.append(codeLine("addEntity(new DeathLineEntity("+deathHeight+"));"));
        sb.append(codeLine("setCamera(new Camera());"));
        sb.append(codeLine("super.startScene(player);"));
        return sb.toString();
    }

    /**
     * Returns the level footer.
     * @param levelName The level name.
     * @return The level footer.
     */
    private static String levelFooter(String levelName) {
        return "\t}\n\n" +
                "\tpublic Scene hardReset() {\n" +
                "\t\treturn new "+levelName+"();\n" +
                "\t}\n}\n";
    }

    // levelEntites() helpers ---------------------------------------------------------------

    /**
     * Adds a command to the map of commands.
     * @param commands The map of commands.
     * @param category The category to add the command to.
     * @param command The command.
     */
    private static void addToMap(Map<Integer,StringBuilder> commands, int category, String command) {
        if (!commands.containsKey(category)) {
            commands.put(category, new StringBuilder(initialString(category)));
        }
        commands.get(category).append(command);
    }

    /**
     * Determines the initial string to use for the map of commands based on the category. Current
     * category codes used are 0-11 inclusive. Anything else returns an "UNKNOWN CATEGORY".
     * @param category The category code.
     * @return The initial string.
     */
    private static String initialString(int category) {
        switch (category) {
            case 0: return "\n\t\t// Player\n";
            case 1: return "\n\t\t// Checkpoints\n";
            case 2: return "\n\t\t// Flag\n";
            case 3: return "\n\t\t// Platforms\n";
            case 4: return "\n\t\t// Moving Platforms\n";
            case 5: return "\n\t\t// Fake Platforms\n";
            case 6: return "\n\t\t// Coins\n";
            case 7: return "\n\t\t// Ghosts\n";
            case 8: return "\n\t\t// Bees\n";
            case 9: return "\n\t\t// Barnacles\n";
            case 10: return "\n\t\t// Snakes\n";
            case 11: return "\n\t\t// Slimes\n";
            default: return "\n\t\t// UNKNOWN CATEGORY: "+category+"\n";
        }
    }

    /**
     * Collects every StringBuilder in the map in to a single string. Built by
     * ordering of the map.
     * @param stringMap The map to collect.
     * @return The whole string.
     */
    private static String collect(Map<Integer, StringBuilder> stringMap) {
        StringBuilder collatedString = new StringBuilder();
        for (StringBuilder sb : stringMap.values()) {
            collatedString.append(sb);
        }
        return collatedString.toString();
    }

    // Entity text --------------------------------------------------------------------------

    private static String slime(int x, int y) {
        return codeLine("addEntity(new SlimeEntity(new Vector("+x+","+y+")));");
    }

    private static String snake(int x, int y) {
        return codeLine("addEntity(new SnakeEntity(new Vector("+x+","+((float)y-0.5f)+"f)));");
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
        return codeLine("addEntity(new BeeEntity(new Vector("+x+","+y+"), player, new Vector("+x+","+y+"))); // TODO: Fill in deviance");
    }

    private static String ghost(int x, int y) {
        return codeLine("addEntity(new GhostEntity(new Vector("+x+","+y+"), player));");
    }

    private static String checkPoint(int x, int y) {
        return codeLine("addEntity(new CheckpointEntity("+x+", "+y+"));");
    }

    private static String endFlag(int x, int y) {
        return codeLine("addEntity(new LevelEndEntity("+x+", "+y+"));");
    }

    private static String player(int x, int y) {
        return codeLine("PlayerEntity player = new PlayerEntity("+x+","+((float)y-0.5f)+"f);")
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
            res += ",new Vector("+x+","+y+"),1)); // TODO: Fill in end position and duration";
        else
            res += "));";

        return codeLine(res);
    }
    private static Vector platformFill(int x, int y, Color[][] grid, boolean[][] checked, Color platformType) {
        int height = grid.length;
        int width = grid[y].length;

        if (x+1 < width && grid[y][x+1].equals(platformType)) {// horizontal multi
            while (x+1 < width && grid[y][x].equals(platformType)) {
                checked[y][x] = true;
                x++;
            }
        } else if (y+1 < height && grid[y+1][x].equals(platformType)) {// vertical multi
            while (y+1 < height && grid[y][x].equals(platformType)) {
                checked[y][x] = true;
                y++;
            }
        }

        return new Vector(x,y);
    }


    public static void main(String[] args) {
        try {
            String level = generateLevel();
            System.out.println(level);
        } catch (IOException e) {e.printStackTrace();}

    }
}
