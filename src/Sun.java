import java.awt.*;

/**
 * Klasse zur Darstellung der Sonne
 * @author Marcus Vaitschulis, Ann-Kathrin Barth
 * @version 1.1
 */
public class Sun {
    private int x; // X-Koordinate der Sonne
    private int y; // Y-Koordinate der Sonne
    private int radius; // Radius der Sonne
    private boolean dayTime = true; // Status der Sonne (Tag/Nacht)

    /**
     * Zeichnet die Sonne auf dem Graphics Objekt basierend auf den Attributen der Sonne
     * @param g Graphics Objekt
     */
    public void draw(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
        if (dayTime) { // Wenn Tag, zeichne Sonne
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.fillOval(x - 10, y - 10, radius*2 + 20, radius*2 + 20);
            g2d.setColor(new Color(255, 255, 255, 50));
            g2d.fillOval(x - 20, y - 20, radius*2 + 40, radius*2 + 40);
            g2d.setColor(new Color(255, 255, 255, 25));
            g2d.fillOval(x - 30, y - 30, radius*2 + 60, radius*2 + 60);
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x,y,radius*2,radius*2);
            RadialGradientPaint p = new RadialGradientPaint(x + radius, y + radius, radius, new float[]{0.0f, 1.0f}, new Color[]{new Color(255, 255, 255, 0), new Color(255, 235, 147, 192)});
            g2d.setPaint(p);
            g2d.fillOval(x,y,radius*2,radius*2);
        } else { // Wenn Nacht, zeichne Mond
            g2d.setColor(new Color(103, 103, 103));
            g2d.fillOval(x,y,radius*2,radius*2);
            g2d.setColor(new Color(114, 114, 114));
            g2d.setStroke(new BasicStroke(4));
            g2d.drawOval(x,y,radius*2,radius*2);
            RadialGradientPaint p = new RadialGradientPaint(x + radius, y + radius, radius, new float[]{0.0f, 1.0f}, new Color[]{new Color(255, 255, 255, 0), new Color(255, 255, 255, 100)});
            g2d.setPaint(p);
            g2d.fillOval(x,y,radius*2,radius*2);
            g2d.setStroke(new BasicStroke(1));
        }

    }

    /**
     * Wechselt den Status der Sonne (Tag/Nacht) zum gegenteiligen boolean Wert
     */
    public void switchTime() {
        this.dayTime = !this.dayTime;
    }

    /**
     * Gibt den Status der Sonne (Tag/Nacht) zurück
     * @return Status der Sonne (Tag/Nacht)
     */
    public boolean isDayTime() {
        return dayTime;
    }

    /**
     * @param x X-Koordinate der Sonne
     * @param y Y-Koordinate der Sonne
     * @return true, wenn die Koordinaten innerhalb des Kreises der Sonne liegen
     */
    public boolean isClicked(int x, int y) {
        int centerX = this.x + this.radius;
        int centerY = this.y + this.radius;
        int distance = (int) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)); // Berechnet die Distanz zwischen den Koordinaten und dem Mittelpunkt des Kreises
        return distance < this.radius;
    }

    /**
     * Setzt die X-Koordinate der Sonne
     * @param x X-Koordinate der Sonne
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setzt die Y-Koordinate der Sonne
     * @param y Y-Koordinate der Sonne
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setzt den Radius der Sonne
     * @param radius Radius der Sonne
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
}
