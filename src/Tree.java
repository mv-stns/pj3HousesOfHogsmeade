import java.awt.*;

/**
 * Klasse zur Darstellung von Bäumen.
 * @author Marcus Vaitschulis, Ann-Kathrin Barth
 * @version 1.1
 */
public class Tree {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Methode zum Zeichnen des Baumes auf dem Graphics Objekt.
     * @param g Graphics Objekt
     */
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Baum Stamm
        g2d.setColor(new Color(139, 69, 19));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x + width/2, y + height - 2, x + width/2, 446);
        // Baum Krone
        g2d.setColor(new Color(164, 212, 91));
        g2d.fillOval(x,y,width,height);
        g2d.setStroke(new BasicStroke(1));
        // Kübel unten am Stamm
        g2d.setColor(new Color(168, 168, 168));
        g2d.fillPolygon(new int[]{x + width/2 - 10, x + width/2 + 10, x + width/2 + 20, x + width/2 - 20}, new int[]{440, 440, 450, 450}, 4);
    }

    /**
     * Setzt die x-Koordinate des Baumes.
     * @param x Die x-Koordinate des Baumes
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setzt die y-Koordinate des Baumes.
     * @param y Die y-Koordinate des Baumes
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setzt die Breite des Baumes.
     * @param width Die Breite des Baumes
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setzt die Höhe des Baumes.
     * @param height Die Höhe des Baumes
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
