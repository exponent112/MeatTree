import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gui extends JFrame {
	private Container c;
	MapSize size = new MapSize();
	
	public Gui(String title) {
		c = getContentPane();
		setBounds(200,20,600,700);
		c.setLayout(null);
		Map map = new Map(size);
		map.setMap(9, 9);
		map.changeCheck();
		DrawBoard d = new DrawBoard(size,map);
		d.setBounds(0, 0, 500, 500);
		setContentPane(d);
		addMouseListener(new MouseEventHandler(map,size,d,this));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void showPopUp(String message) {
		JOptionPane.showMessageDialog(this, message,"",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}
