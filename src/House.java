import java.awt.*;

/**
 * Klasse zur Darstellung der Häuser.
 * @author Marcus Vaitschulis, Ann-Kathrin Barth
 * @version 1.3
 */
public class House {
    private Color wallColor; // Farbe der Hauswände
    private int x; // x-Koordinate des Hauses
    private int y; // y-Koordinate des Hauses
    private int width; // Breite des Hauses
    private int height; // Höhe des Hauses
    private boolean lightOn = false; // Status des Lichtes
    private int windowAmount; // Anzahl der Fenster

    /**
     * Switched das Licht des Hauses auf den gegenteiligen boolean Wert.
     */
    public void switchLight() {
        this.lightOn = !this.lightOn;
    }

    /**
     * Zeichnet das Haus basierend auf den Attributen des Hauses.
     * @param g Graphics Objekt
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(wallColor);
        g2d.fillRect(x, y, width, height);

        // Zeichne Dach
        offsetColor(g2d);
        g2d.fillRect(x, y - height / 8, width, height / 8);

        //Fenster Setzen
        setWindows(g2d);
    }

    /**
     * Korrigiert die Y Position des Hauses, sodass die Häuser auf der Straße stehen.
     * @param y Die y-Koordinate des Hauses
     * @param house Das Haus
     */
    public void fixY(int y, House house) {
        house.y = y - house.height;
    }

    /**
     * Setzt die Fenster des Hauses basierend auf die Anzahl der Fenster.
     * @param g2d Das Graphics2D Objekt
     */
    private void setWindows(Graphics2D g2d) {
        Color windowColor = new Color(255, 255, 255);
        if (lightOn) {
            windowColor = new Color(255, 242, 0, 255);
        }
        int hGap = 5; // Abstand zwischen den Fenstern
        int vGap = 5; // Abstand zwischen den Fenstern
        int windowWidth = (width - (windowAmount + 1) * hGap) / windowAmount;
        int windowHeight = (height - 2 * vGap) / 2;
        int windowX = x + hGap;
        int windowY = y + vGap;
        int SHIMMER_AMOUNT = 180;
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < windowAmount; i++) {
            g2d.setColor(windowColor);
            g2d.fillRoundRect(windowX, windowY, windowWidth, windowHeight, 10, 10);
            offsetColor(g2d);
            g2d.drawRoundRect(windowX, windowY, windowWidth, windowHeight - 2, 10, 10);
            windowX += windowWidth + hGap;
            // Glass Schimmer
            if (lightOn) {
                g2d.setPaint(new GradientPaint(windowX, windowY, new Color(255, 255, 255, SHIMMER_AMOUNT), windowX + windowWidth, windowY + windowHeight, new Color(255, 255, 255, 0)));
                g2d.fillRoundRect(windowX - windowWidth + 5, windowY + 5, windowWidth - 10, windowHeight - 10, 10, 10);
            }
        }
        windowX = x + hGap;
        windowY += windowHeight + vGap;
        for (int i = 0; i < windowAmount; i++) {
            g2d.setColor(windowColor);
            g2d.fillRoundRect(windowX, windowY, windowWidth, windowHeight - 2, 10, 10);
            offsetColor(g2d);
            g2d.drawRoundRect(windowX, windowY, windowWidth, windowHeight - 2, 10, 10);
            windowX += windowWidth + hGap;
            // Glass Schimmer
            if (lightOn) {
                g2d.setPaint(new GradientPaint(windowX, windowY, new Color(255, 255, 255, SHIMMER_AMOUNT), windowX + windowWidth, windowY + windowHeight, new Color(255, 255, 255, 0)));
                g2d.fillRoundRect(windowX - windowWidth + 5, windowY + 5, windowWidth - 10, windowHeight - 10, 10, 10);
            }
        }

    }

    /**
     * Setzt die einzusetzende Farbe basierend auf die Wand-Farbe. Die Farbe wird um 50 abgedunkelt.
     * @param g2d Graphics2D Objekt
     * @throws IllegalArgumentException Farbbereich fällt unter 0
     */
    private void offsetColor(Graphics2D g2d) {
        g2d.setColor(new Color(wallColor.getRed() - 50, wallColor.getGreen() - 50, wallColor.getBlue() - 50));
    }

    /**
     * Gibt die Höhe des Hauses zurück.
     * @return Höhe des Hauses
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gibt die Breite des Hauses zurück.
     * @return Breite des Hauses
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt die X-Position des Hauses zurück.
     * @return X-Position des Hauses
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die Y-Position des Hauses zurück.
     * @return Y-Position des Hauses
     */
    public int getY() {
        return y;
    }

    /**
     * Setzt die Höhe des Hauses.
     * @param height Die Höhe des Hauses
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Setzt die Breite des Hauses.
     * @param width Die Breite des Hauses
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setzt die X-Position des Hauses.
     * @param x X-Position des Hauses
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setzt die Y-Position des Hauses.
     * @param y Y-Position des Hauses
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setzt die Anzahl der Fenster.
     * @param amount Anzahl der Fenster
     */
    public void setWindowAmount(int amount) {
        this.windowAmount = amount;
    }

    /**
     * Prüft, ob der Klick innerhalb des Hauses war.
     * @param x X-Position des Mauszeigers
     * @param y Y-Position des Mauszeigers
     * @return true wenn der Mauszeiger innerhalb des Hauses ist
     */
    public boolean isClicked(int x, int y) {
        // also add roof height
        return x >= this.x && x <= this.x + this.width && y >= this.y - this.height / 8 && y <= this.y + this.height;
    }

    /**
     * Setzt die Farbe der Hauswände.
     * @param color Die Farbe der Hauswände
     */
    public void setWallColor(Color color) {
        this.wallColor = color;
    }
}
