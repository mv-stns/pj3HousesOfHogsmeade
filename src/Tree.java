import java.awt.*;

public class Tree {
    private int x;
    private int y;
    private int width;
    private int height;

    public Tree(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //draw stem (stem should go to the middle of the circle to the street level (450)
        g2d.setColor(new Color(139, 69, 19));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x + width/2, y + height, x + width/2, 448);
        g2d.setColor(new Color(164, 212, 91));
        g2d.fillOval(x,y,width,height);


    }

}
