package ivara.entities;

import core.entity.GameEntity;
import maths.Vector;
import java.util.*;

/**
 * Static class used to create a collection of platforms
 * to form a point to point line of platforms in any
 * direction from point 0 to point 1.
 * @author Will Pearson
 */
public final class LinePlatformFactory {

    private static boolean vege = true;

    /**
     * Constructs a collection of game entities that creates a line from point a to b.
     * Vegetation is on by default with this constructor.
     * @param x0 Starting x position.
     * @param y0 Starting y position.
     * @param x1 Ending x position.
     * @param y1 Ending y position.
     * @return A collection of GameEntities
     */
    public static Collection<GameEntity> line(int x0, int y0, int x1, int y1) {
        return plotLine(x0,y0,x1,y1);
    }

    /**
     * Constructs a collection of game entities that creates a line from point a to b.
     * Vegetation can be turned off with this constructor.
     * @param x0 Starting x position.
     * @param y0 Starting y position.
     * @param x1 Ending x position.
     * @param y1 Ending y position.
     * @param veg Whether there is vegetation on the platform
     * @return A collection of GameEntities
     */
    public static Collection<GameEntity> line(int x0, int y0, int x1, int y1, boolean veg) {
        vege = veg;
        return plotLine(x0,y0,x1,y1);
    }

    /**
     * Plots the line given by the constructors and delegates further work to sub methods.
     * @param x0 Starting x position.
     * @param y0 Starting y position.
     * @param x1 Ending x position.
     * @param y1 Ending y position.
     * @return A collection of GameEntities
     */
    private static Collection<GameEntity> plotLine(int x0, int y0, int x1, int y1){
        int dx = x1 - x0;
        int dy = y1 - y0;
        if (dx >= 0) {
            if (dy >= 0) {
                if (dx >= dy) {
                    return plot2(x0,y0,x1,y1);
                } else {
                    return plot1(x0,y0,x1,y1);
                }
            } else {
                if (dx >= -dy) {
                    return plot3(x0,y0,x1,y1);
                } else {
                    return plot4(x1,y1,x0,y0);
                }
            }
        } else {
            if (dy >= 0) {
                if (-dx >= dy) {
                    return plot3(x1,y1,x0,y0);
                } else {
                    return plot4(x0,y0,x1,y1);
                }
            } else {
                if (-dx >= -dy) {
                    return plot2(x1,y1,x0,y0);
                } else {
                    return plot1(x1,y1,x0,y0);
                }
            }
        }
    }

    /**
     * Constructs the necessary platform dependant on the input.
     * @param x The x position of the first block.
     * @param y The y position of the first block.
     * @param n The number of blocks.
     * @param isVertical Whether the block is vertical.
     * @return A Platform with these constraints taken into consideration.
     */
    private static GameEntity platformType(int x, int y, int n, boolean isVertical) {
        assert(n > 0);
        if (n == 1)
            return new PlatformEntity(new Vector(x, y), vege);
        else if (isVertical)
            return new PlatformEntity(new Vector(x,y), n, true, vege);
        else
            return new PlatformEntity(new Vector(x,y), n, false, vege);
    }

    // Delegation of work into sub sections of the platform

    private static Collection<GameEntity> plot1(int x0, int y0, int x1, int y1) {
        Collection<GameEntity> platforms = new HashSet<>();
        int dy = y1 - y0, d2y = dy+dy;
        int d2x = 2*(x1 - x0);
        int x = x0;
        int xf = 0;
        int y = y0;
        int originY = y;
        int n = 0;
        while (y <= y1) {
            y++;
            xf+=d2x;
            n++;
            if (xf > dy) {
                platforms.add(platformType(x, originY, n, true));
                x++;
                xf-=d2y;
                n = 0;
                originY = y;
            }
        }
            if (n != 0) {
                platforms.add(platformType(x, originY, n, false));
            }

        return platforms;
    }

    private static Collection<GameEntity> plot2(int x0, int y0, int x1, int y1) {
        Collection<GameEntity> platforms = new HashSet<>();
        int dx = x1 - x0, d2x = dx+dx;
        int d2y = 2*(y1 - y0);
        int x = x0;
        int yf = 0;
        int y = y0;
        int originX = x;
        int n = 0;
        while (x <= x1) {
            x++;
            yf+=d2y;
            n++;
            if (yf > dx) {
                platforms.add(platformType(originX, y, n, false));
                y++;
                yf-=d2x;
                n = 0;
                originX = x;
            }
        }
        if (n != 0) {
            platforms.add(platformType(originX, y, n, false));
        }
        return platforms;
    }

    private static Collection<GameEntity> plot3(int x0, int y0, int x1, int y1) {
        Collection<GameEntity> platforms = new HashSet<>();
        int dx = x1 - x0, d2x = dx+dx;
        int d2y = 2*(y1 - y0);
        int x = x0;
        int yf = 0;
        int y = y0;
        int originX = x;
        int n = 0;
        while (x <= x1) {
            x++;
            yf-=d2y;
            n++;
            if (yf >= dx) {
                platforms.add(platformType(originX, y, n, false));
                y--;
                yf-=d2x;
                n = 0;
                originX = x;
            }
        }
        if (n != 0) {
            platforms.add(platformType(originX, y, n, false));
        }
        return platforms;
    }

    private static Collection<GameEntity> plot4(int x0, int y0, int x1, int y1) {
        Collection<GameEntity> platforms = new HashSet<>();
        int dy = y1 - y0, d2y = dy+dy;
        int d2x = 2*(x1 - x0);
        int x = x0;
        int xf = 0;
        int y = y0;
        int originY = y;
        int n = 0;
        while (y <= y1) {
            y++;
            xf-=d2x;
            n++;
            if (xf > dy) {
                platforms.add(platformType(x, originY, n, true));
                x--;
                xf-=d2y;
                n = 0;
                originY = y;
            }
        }
        if (n != 0)
            platforms.add(platformType(x, originY, n, true));
        return platforms;
    }
}
