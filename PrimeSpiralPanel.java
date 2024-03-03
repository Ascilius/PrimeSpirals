import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PrimeSpiralPanel extends JPanel {
	private InputHandler inputHandler;

	int screenWidth, screenHeight;
	double scale = 1.0;

	ArrayList<BetterPoint> primes = new ArrayList<BetterPoint>();

	public PrimeSpiralPanel(double screenWidth, double screenHeight) {
		this.screenWidth = (int) screenWidth;
		this.screenHeight = (int) screenHeight;
		this.inputHandler = new InputHandler();
		addKeyListener(this.inputHandler);
		setFocusable(true);

		int i = 2;
		while (primes.size() < 100) {
			boolean prime = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}
			if (prime == true) {
				primes.add(new BetterPoint(Math.cos(i) * i, Math.sin(i) * i * -1));
			}
			i++;
		}
	}

	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenWidth, screenHeight);

		g.translate(screenWidth / 2, screenHeight / 2);

		g.setColor(Color.WHITE);
		for (int i = 0; i < primes.size(); i++) {
			g.fillOval((int) (primes.get(i).getX() * scale) - 1, (int) (primes.get(i).getY() * scale) - 1, 2, 2);
		}
	}

	public void zoomOut() {
		scale /= 2;
		repaint();
	}

	public void zoomIn() {
		scale *= 2;
		repaint();
	}

	public void more() {
		int newSize = primes.size() * 2;
		int i = 0;
		while (primes.size() < newSize) {
			boolean prime = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}
			if (prime == true) {
				primes.add(new BetterPoint(Math.cos(i) * i, Math.sin(i) * i * -1));
			}
			i++;
		}
		repaint();
	}

	public void less() {
		int newSize = primes.size() / 2;
		while (primes.size() > newSize) {
			primes.remove(primes.size() - 1);
		}
		repaint();
	}

	class InputHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				zoomIn();
			} else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
				zoomOut();
			} else if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
				more();
			} else if (e.getKeyCode() == KeyEvent.VK_COMMA) {
				less();
			}
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}

		public void keyTyped(KeyEvent e) {
		}
	}
}
