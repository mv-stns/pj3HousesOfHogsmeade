import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

/**
 * Basis-Panel stellt Grundfunktionen fuer den Aufbau interaktiver Anwendungen zur
 * Verfuegung.
 * Alle Mausereignisse koennen in einzelnen Methoden verarbeitet werden. 
 *
 * @author Joerg Berdux
 * @version 1.0
 */
public class Hogsmeade extends JPanel implements MouseListener {

	@Serial
	private static final long serialVersionUID = 1L;
	Sun sun;
	House[] houses;
	Tree[] trees;
	final int STREET_LEVEL = 450;
	/**
	 * Initialisierung des Panels und setzen des MouseListerns
	 * fuer die Verwendung von Maus-Ereignissen
	 */
	public Hogsmeade(int frameWidth, int frameHeight) {


		/* registriert Panel als MouseListener, so dass die jeweilige spezialisierte
		 * Methode aufgerufen wird, wenn ein Mausereignis innerhalb des Panels ausgeloest
		 * wird
		 */
		this.addMouseListener(this);

		// Initialisiere Haeuser, Baeume, Sonne ...
		initTrees();
		initHouses();
		initSun(frameWidth);

	}

	/**
	 * Methode zum initialisieren der Sonne
	 * @param SCREEN_WIDTH Breite des JFrames. Wird benoetigt, um die Sonne an der richtigen Stelle zu platzieren
	 */
	private void initSun(int SCREEN_WIDTH) {
		sun = new Sun();
		sun.setX(SCREEN_WIDTH - 150);
		sun.setY(50);
		sun.setRadius(50);
	}

	/**
	 * Methode zum initialisieren der Häuser
	 */
	private void initHouses() {
		houses = new House[5]; // Initialisiere Array mit 5 Haeusern
		for (int i = 0; i < houses.length; i++) {
			houses[i] = new House();
		}
		houses[0].setX(10);
		houses[0].setY(STREET_LEVEL);
		houses[0].setWidth(100);
		houses[0].setHeight(150);
		houses[0].setWallColor(new Color(255, 50, 50));
		houses[0].setWindowAmount(3);
		houses[1].setX(houses[0].getWidth() + houses[0].getX());
		houses[1].setY(STREET_LEVEL);
		houses[1].setWidth(200);
		houses[1].setHeight(200);
		houses[1].setWallColor(new Color(210, 210, 185));
		houses[1].setWindowAmount(5);
		houses[2].setX(houses[1].getWidth() + houses[1].getX());
		houses[2].setY(STREET_LEVEL);
		houses[2].setWidth(150);
		houses[2].setHeight(160);
		houses[2].setWallColor(new Color(252, 181, 50));
		houses[2].setWindowAmount(4);
		houses[3].setX(houses[2].getWidth() + houses[2].getX());
		houses[3].setY(STREET_LEVEL);
		houses[3].setWidth(50);
		houses[3].setHeight(90);
		houses[3].setWallColor(new Color(231, 165, 165));
		houses[3].setWindowAmount(2);
		houses[4].setX(houses[3].getWidth() + houses[3].getX());
		houses[4].setY(STREET_LEVEL);
		houses[4].setWidth(250);
		houses[4].setHeight(120);
		houses[4].setWallColor(new Color(165, 165, 231));
		houses[4].setWindowAmount(6);
		for (House house : houses) {
			house.fixY(STREET_LEVEL, house);
		}
	}

	/**
	 * Methode zum initialisieren der Baeume
	 */
	private void initTrees() {
		trees = new Tree[3];
		trees[0] = new Tree();
		trees[0].setX(100);
		trees[0].setY(350);
		trees[0].setWidth(50);
		trees[0].setHeight(50);

		trees[1] = new Tree();
		trees[1].setX(300);
		trees[1].setY(350);
		trees[1].setWidth(75);
		trees[1].setHeight(75);

		trees[2] = new Tree();
		trees[2].setX(500);
		trees[2].setY(350);
		trees[2].setWidth(60);
		trees[2].setHeight(60);
	}

	/**
	 * Zeichnen der Strasse.
	 * Umsetzung der Methode
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 *
	 * @param g Graphik-Kontext, auf dem die Landschaft gezeichnet wird
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Himmel Zeichnen
		GradientPaint gradientSky;
		if (sun.isDayTime()) {
			gradientSky = new GradientPaint(0, 0, new Color(0, 128, 255), 0, getHeight(), new Color(205, 171, 231));
		} else {
			gradientSky = new GradientPaint(0, 0, new Color(0, 0, 0), 0, getHeight(), new Color(47, 10, 72));
		}
		g2d.setPaint(gradientSky);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		if (!sun.isDayTime()) {
			for (int i = 0; i < 100; i++) {
				g2d.setColor(new Color(255, 255, 255, (int) (Math.random() * 255)));
				int x = (int) (Math.random() * getWidth());
				int y = (int) (Math.random() * getHeight());
				int width = (int) (Math.random() * 5);
				int height = (int) (Math.random() * 5);
				g2d.fillArc(x, y, width, height, 0, 360);
			}
		}
		// Straße Zeichen
		Color STREET_COLOR = new Color(129, 122, 113);
		g2d.setColor(STREET_COLOR);
		g2d.fillRect(0, STREET_LEVEL, getWidth(), 50);
		g2d.setColor(Color.WHITE);
		for (int i = 0; i < getWidth(); i += 50) {
			g2d.fillRect(i, STREET_LEVEL+8, 15, 4);
		}
		//hier wird alles gezeichnet ...
		sun.draw(g2d);
		for (House house : houses) {
			house.draw(g2d);
		}
		for (Tree tree : trees) {
			tree.draw(g2d);
		}
	}

	/**
	 * Aufloesung der x, y-Position, an der Mausbutton betaetigt wurde.
	 * Umsetzung der Methode
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 *
	 * @param e Maus-Ereignis, das ausgeloest wurde 
	 */
	public void mouseClicked(MouseEvent e){
		int x, y;

		x = e.getX(); // x-Koordinate, an der Mausereignis stattgefunden hat
		y = e.getY(); // y-Koordinate, an der Mausereignis stattgefunden hat

		// hier sollte dann der Maus-Event entsprechend verarbeitet werden

		// Wenn Maus auf Sonne geklickt -> Sonne wechselt Zeit
		if (sun.isClicked(x, y)) {
			sun.switchTime();
		}
		// Wenn Maus auf Haus geklickt -> Haus wechselt Licht
		for (House house : houses) {
			if (house.isClicked(x, y)) {
				house.switchLight();
			}
		}
		// nach jeder Veraenderung soll der Graphik-Kontext neu gezeichnet werden
		repaint();
	}

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e){
		// diese Methode bleibt einfach leer
	}

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e){
		// diese Methode bleibt einfach leer
	}

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e){
		// diese Methode bleibt einfach leer
	}

	/** Faengt Mouse-Event ab, ohne ihn weiter zu verarbeiten
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e){
		// diese Methode bleibt einfach leer
	}
}
