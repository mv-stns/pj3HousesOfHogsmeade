import java.awt.*;
import java.util.Random;

public class House {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color wallColor;
    private boolean lightOn = false;

    public House (int x, int y, int width, int height, Color wallColor){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wallColor = wallColor;
    }
    public void switchLight() {
        this.lightOn = !this.lightOn;
    }

    //Updated  draw method

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(wallColor);
        g2d.fillRect(x,y,width,height);

        // Zeichne Dach (rechteckig oben anliegend) | KEIN DREIECK
        //set color to a darker shade of the wall color
        g2d.setColor(new Color(wallColor.getRed() - 50, wallColor.getGreen() - 50, wallColor.getBlue() - 50));
        g2d.fillRect(x, y, width, height/5);

        //Fenster Setzen
        setWindows(g2d);

        // Tür der Häuser
        g2d.setColor(new Color(199, 199, 199));
        // door is always in the middle of the house and has a fixed width of 10
        int doorWidth = 10;
        int doorHeight = 20;
        int doorX = x + width/2 - doorWidth/2;
        int doorY = y + height - doorHeight;
        g2d.fillRect(doorX, doorY, doorWidth, doorHeight);

    }

    private void setWindows(Graphics2D g2d) {
        int leftHouseHalf = width/2;
        int rightHouseHalf = width - leftHouseHalf;
        int windowHeight;
        int windowWidth;
        Color windowColor = new Color(199, 199, 199);
        if (lightOn) {
            windowColor = new Color(255, 242, 0, 255);
        }
        if (width <= 50) {
            windowHeight = height/4;
            windowWidth = width/4;
            // center windows in each half
            int leftWindowX = x + leftHouseHalf/2 - windowWidth/2;
            int rightWindowX = x + leftHouseHalf + rightHouseHalf/2 - windowWidth/2;
            int windowY = y + height/2 - windowHeight/2 - 2;
            g2d.setColor(windowColor);
            g2d.fillRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.fillRect(rightWindowX, windowY, windowWidth, windowHeight);
            g2d.setColor(wallColor);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.drawRect(rightWindowX, windowY, windowWidth, windowHeight);
        } else if (width <= 150) {
            // set two windows above each other in each half
            windowHeight = height/4;
            windowWidth = width/4;
            int leftWindowX = x + leftHouseHalf/2 - windowWidth/2;
            int rightWindowX = x + leftHouseHalf + rightHouseHalf/2 - windowWidth/2;
            int windowY = y + height/2 - windowHeight/2 - 2;
            g2d.setColor(windowColor);
            g2d.fillRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.fillRect(rightWindowX, windowY, windowWidth, windowHeight);
            g2d.setColor(wallColor);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.drawRect(rightWindowX, windowY, windowWidth, windowHeight);
            // set two windows below each other in each half
            windowHeight = height/4;
            windowWidth = width/4;
            leftWindowX = x + leftHouseHalf/2 - windowWidth/2;
            rightWindowX = x + leftHouseHalf + rightHouseHalf/2 - windowWidth/2;
            windowY = y + height - windowHeight - 2;
            g2d.setColor(windowColor);
            g2d.fillRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.fillRect(rightWindowX, windowY, windowWidth, windowHeight);
            g2d.setColor(wallColor);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.drawRect(rightWindowX, windowY, windowWidth, windowHeight);
        } else {
            // set two windows besides each other in each half. Quater of the house width
            windowHeight = height/6;
            windowWidth = width/6;
            int leftWindowX = x + leftHouseHalf/2 - windowWidth/2;
            int rightWindowX = x + leftHouseHalf + rightHouseHalf/2 - windowWidth/2;
            int windowY = y + height/2 - windowHeight/2 - 2;
            g2d.setColor(windowColor);
            g2d.fillRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.fillRect(rightWindowX, windowY, windowWidth, windowHeight);
            g2d.setColor(wallColor);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(leftWindowX, windowY, windowWidth, windowHeight);
            g2d.drawRect(rightWindowX, windowY, windowWidth, windowHeight);




        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int setHeight(int height) {
        return this.height = height;
    }
    public int setWidth(int width) {
        return this.width = width;
    }
    public int setX(int x) {
        return this.x = x;
    }
    public int setY(int y) {
        return this.y = y;
    }

    public boolean isClicked(int x, int y) {
        if (x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
            return true;
        }
        return false;
    }

    // method to gen a number between min and max
    public int genRandom( int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public Color getWallColor() {
        return wallColor;
    }

}
