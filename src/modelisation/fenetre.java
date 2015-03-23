package modelisation;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class fenetre extends JFrame {

	Image i;

	JPanel pano;

	public fenetre() {

		super("Affichage image");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setPreferredSize(new Dimension(333, 333));
		pano = new JPanel();
		this.add(pano);
		// t = new Thread(mod);
		try {

			File f = new File("");
			String aze = f.getAbsolutePath();
			System.out.println(aze);

			System.out.println("pouik");
			Image i = ImageIO.read(new File("bidule.pgm"));
			
			
			ImageIcon ic = new ImageIcon("C:\\Users\\Dark_Valou\\Google Drive\\java\\compilation\\bidule.pgm");
			
			System.out.println(ic.getIconWidth());
			JLabel img = new JLabel(ic);
			
			pano.add(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// this.setLayout(new GridLayout(5,5));
		// this.add(zs, 0);
		// add(vm,BorderLayout.NORTH);
		// add(vg.va, BorderLayout.EAST);
		// add(vv, BorderLayout.SOUTH);
		// add(vgn,BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
}
