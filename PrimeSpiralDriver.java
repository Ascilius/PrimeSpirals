import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class PrimeSpiralDriver {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Prime Spiral");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		PrimeSpiralPanel panel = new PrimeSpiralPanel(screenSize.getWidth(), screenSize.getHeight());
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
}
