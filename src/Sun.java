import java.awt.*;

public class Sun {
    private int x;
    private int y;
    private int radius;
    private boolean dayTime = true;

    public Sun(int x, int y, int radius){
            this.x= x;
            this.y= y;
            this.radius= radius;
    }

    public void draw(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
        if (dayTime) {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x,y,radius*2,radius*2);
        } else {
            g2d.setColor(new Color(103, 103, 103, 100));
            g2d.fillOval(x,y,radius*2,radius*2);
            g2d.setColor(new Color(114, 114, 114, 100));
            g2d.setStroke(new BasicStroke(4));
            g2d.drawOval(x,y,radius*2,radius*2);
        }
    }

    public void switchTime() {
        this.dayTime = !this.dayTime;
    }

    public boolean isDayTime() {
        return dayTime;
    }

    public boolean isClicked(int x, int y) {
        int centerX = this.x + this.radius;
        int centerY = this.y + this.radius;
        int distance = (int) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
        return distance < this.radius;
    }
}
