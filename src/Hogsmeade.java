import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

import javax.swing.JPanel;

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

	private void initSun(int SCREEN_WIDTH) {
		sun = new Sun(SCREEN_WIDTH - 150, 50, 50);
	}

	private void initHouses() {
		houses = new House[5];
		houses[0] = new House(10, 450, 100, 150, new Color(165, 231, 165));
		houses[0].setY(houses[0].getY() - houses[0].getHeight()); // Häuser sollen auf der Straße stehen, position wird umgeschrieben
		houses[1] = new House(houses[0].getWidth() + houses[0].getX(), 450, 200, 200, new Color(210, 210, 185));
		houses[1].setY(houses[1].getY() - houses[1].getHeight());
		houses[2] = new House(houses[1].getWidth() + houses[1].getX(), 450, 150, 160, new Color(252, 181, 50));
		houses[2].setY(houses[2].getY() - houses[2].getHeight());
		houses[3] = new House(houses[2].getWidth() + houses[2].getX(), 450, 50, 90, new Color(231, 165, 165));
		houses[3].setY(houses[3].getY() - houses[3].getHeight());
		houses[4] = new House(houses[3].getWidth() + houses[3].getX(), 450, 250, 60, new Color(165, 165, 231));
		houses[4].setY(houses[4].getY() - houses[4].getHeight());
	}

	private void initTrees() {
		trees = new Tree[3];
		trees[0] = new Tree(100, 350, 50, 50);
		trees[1] = new Tree(300, 350, 75, 75);
		trees[2] = new Tree(500, 350, 60, 60);
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
		if (sun.isDayTime()) {
		g.setColor(new Color(136, 171, 253));
		} else {
			g.setColor(new Color(0, 0, 0));
		}
		g.fillRect(0, 0, getWidth(), getHeight());

		// Straße Zeichen
		g.setColor(new Color(129, 122, 113));
		g.fillRect(0, 450, getWidth(), 50);

		//hier wird alles gezeichnet ...
		sun.draw(g);
		for (House house : houses) {
			house.draw(g);
		}
		for (Tree tree : trees) {
			tree.draw(g);
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
		// if clicked on Sun -> run sun.switchTime();
		if (sun.isClicked(x, y)) {
			sun.switchTime();
		}
		// if clicked on House -> run house.switchLight();
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
