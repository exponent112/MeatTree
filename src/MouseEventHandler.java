import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class MouseEventHandler extends MouseAdapter {
	private Map map;
	private MapSize ms;
	private DrawBoard d;
	private Gui main;
	
	public MouseEventHandler(Map m, MapSize ms, DrawBoard d,Gui main) {
		map = m;
		this.ms = ms;
		this.d = d;
		this.main = main;
	}
	
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		int x = (int)Math.round(arg0.getX()/(double)ms.getCell())-1;
		int y = (int)Math.round(arg0.getY()/(double)ms.getCell())-2;
		
		if(x<0 || x>19 || y<0 || y>19) {
			return;
		}
		if(map.getXY(y, x)==map.getBlack()||
				map.getXY(y, x) == map.getWhite()) {
			 JOptionPane.showMessageDialog(null, "이미 놓아진 자리입니다.");
			return;
		}
		map.setMap(y, x);

		if(  MeatTreeGame.turn == 2) {
			 map.changeCheck();
			 MeatTreeGame.turn = 0;
		}
		
		d.repaint();
		if(map.winCheck(x, y)) {
			if((map.getCheck() == false && MeatTreeGame.turn !=0) ||
					( map.getCheck() == true && MeatTreeGame.turn ==0) ) {
				main.showPopUp("White Win");
			} //false == white
			else {
				main.showPopUp("Black Win");
			}
		}
		MeatTreeGame.turn++;
		
	}
}
