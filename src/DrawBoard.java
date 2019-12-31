
 
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawBoard extends JPanel implements ActionListener {
	JButton newGame = new JButton("새로운 게임");
	BufferedImage img  = null;
	BufferedImage image = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
	private MapSize size;
	Image changeImg = null;

	private Map map;
	private final int STONE_SIZE=28; //돌 사이즈
	public DrawBoard(MapSize m,Map map) {
		// TODO Auto-generated constructor stub
		setBackground(Color.lightGray); 
		size=m;
		setLayout(null);
		this.map=map;
		newGame.setBounds(250,580,100,50);
		add(newGame);
		newGame.addActionListener(this);
	}

	@Override

	public void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D g2 = (Graphics2D)arg0;
		try {
			if(Map.checkBNW == true)
				img = ImageIO.read(new File("./image/black.png"));
			else
				img = ImageIO.read(new File("./image/white.png"));
			changeImg = img.getScaledInstance(100,80,Image.SCALE_SMOOTH);
		}catch(IOException e1) {
			JOptionPane.showMessageDialog(null, "Fail");
		}
		if(Map.checkBNW == true)
			g2.drawImage(changeImg, 20,580, null);
		else 
			g2.drawImage(changeImg, 480,580, null);
		arg0.setColor(Color.BLACK); //그려질 색을 블랙지정
		board(arg0); //보드를 그림
		drawStone(arg0); //배열에 정보에 따라 돌을 그림
	}
	public void board(Graphics arg0) {
		for(int i=1;i<=size.getSize();i++){
			//가로 줄 그리기
			arg0.drawLine(size.getCell(), i*size.getCell(), size.getCell()*size.getSize(), i*size.getCell()); //시작점 x : 30, 시작점 y : i값 (줄번호)*30, 끝점 x : 600,끝점 y : i값 (줄번호)*30
			//세로줄 그리기
			arg0.drawLine(i*size.getCell(), size.getCell(), i*size.getCell() , size.getCell()*size.getSize()); //시작점 x : i값 (줄번호)*30, 시작점 y : 30, 끝점 x : i값 (줄번호)*30, 끝점 y : 600
		}
	}
	public void drawStone(Graphics arg0) {
		for(int y=0;y<size.getSize();y++){
			for(int x=0;x<size.getSize();x++){
				//배열의 정보가 흑일경우 흑돌, 백일경우 백돌을 그림
				if(map.getXY(y, x)==map.getBlack())
					drawBlack(arg0,x,y);
				else if(map.getXY(y, x)==map.getWhite())
					drawWhite(arg0, x, y);
			}
		}
	}
	public void drawBlack(Graphics arg0,int x,int y){
		//그려질 색을 블랙으로 바꿈
		arg0.setColor(Color.BLACK);
		arg0.fillOval((x+1)*size.getCell()-15, (y)*size.getCell()+15, STONE_SIZE, STONE_SIZE);
	}

	public void drawWhite(Graphics arg0,int x,int y){
		//그려질 색을 화이트로 바꿈
		arg0.setColor(Color.WHITE);
		arg0.fillOval(x*size.getCell()+15, y*size.getCell()+15, STONE_SIZE, STONE_SIZE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newGame) {
			for(int i=0;i<19;i++)
				for(int j=0;j<19;j++)
				Map.map[i][j]= 0;
			Map.map[9][9]= 1;
			MeatTreeGame.turn = 1;
			Map.checkBNW=false;
			repaint();
		}
	}
}
