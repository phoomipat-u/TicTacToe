import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class Application {

	private JFrame frame;
	private boolean computerTurn = true;
	JButton[][] buttonList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 479, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
		
		
		
		this.buttonList = new JButton[3][3];
		for(int i = 0 ; i < buttonList.length; i++) {
			for(int j = 0 ; j < buttonList[i].length; j++) {
				buttonList[i][j] = new JButton("");
				buttonList[i][j].setFont(new Font("Tahoma", Font.PLAIN, 76));
				buttonList[i][j].addActionListener(new GridClicked(i,j));
				frame.getContentPane().add(buttonList[i][j]);
			}
		}
		
		buttonList[1][1].doClick();
		
//		JButton btnNewButton
//		frame.getContentPane().add(btnNewButton_2);
//		
//		JButton btnNewButton_1 = new JButton("");
//		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 76));
//		frame.getContentPane().add(btnNewButton_1);
//		
//		JButton btnNewButton = new JButton("");
//		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 76));
//		frame.getContentPane().add(btnNewButton);
//		
//		JButton btnNewButton_4 = new JButton("");
//		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 76));
//		frame.getContentPane().add(btnNewButton_4);
//		
//		JButton btnNewButton_5 = new JButton("");
//		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 76));
//
//		frame.getContentPane().add(btnNewButton_5);
//		
//		JButton btnNewButton_6 = new JButton("");
//		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 76));
//
//		frame.getContentPane().add(btnNewButton_6);
//		
//		JButton btnNewButton_7 = new JButton("");
//		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 76));
//
//		frame.getContentPane().add(btnNewButton_7);
//		
//		JButton btnNewButton_8 = new JButton("");
//		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 76));
//		frame.getContentPane().add(btnNewButton_8);
//		
//		JButton btnNewButton_9 = new JButton("");
//		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 76));
//		frame.getContentPane().add(btnNewButton_9);
	}
	
	public void checkWin() {
		Boolean playerWins = false;
		Boolean computerWins = false;
		
		for(int i = 0; i < buttonList.length ; i++) {
			Boolean playerWinsTemp = true;
			Boolean computerWinsTemp = true;
			for(int j = 0 ; j < buttonList[i].length; j++) {
				playerWinsTemp = playerWinsTemp && (buttonList[i][j].getText().equals("O"));
				computerWinsTemp = computerWinsTemp && (buttonList[i][j].getText().equals("X"));
			}
			if(playerWinsTemp) {
				playerWins = true;
			}
			if(computerWinsTemp) {
				computerWins = true;
			}
		}
		
		for(int i = 0; i < buttonList.length ; i++) {
			Boolean playerWinsTemp = true;
			Boolean computerWinsTemp = true;
			for(int j = 0 ; j < buttonList[i].length; j++) {
				playerWinsTemp = playerWinsTemp && (buttonList[j][i].getText().equals("O"));
				computerWinsTemp = computerWinsTemp && (buttonList[j][i].getText().equals("X"));
			}
			if(playerWinsTemp) {
				playerWins = true;
			}
			if(computerWinsTemp) {
				computerWins = true;
			}
		}
		
		if(buttonList[0][0].getText().equals(buttonList[1][1].getText()) && buttonList[0][0].getText().equals(buttonList[2][2].getText())) {
			if(buttonList[1][1].getText().equals("X")) {
				computerWins = true;
			}
			if(buttonList[1][1].getText().equals("O")) {
				playerWins = true;
			}
		}
		if(buttonList[0][2].getText().equals(buttonList[1][1].getText()) && buttonList[0][2].getText().equals(buttonList[2][0].getText())) {
			if(buttonList[1][1].getText().equals("X")) {
				computerWins = true;
			}
			if(buttonList[1][1].getText().equals("O")) {
				playerWins = true;
			}
		}
		
		Boolean draw = true;
		for(int i = 0 ; i<buttonList.length; i++) {
			for(int j = 0 ; j<buttonList.length; j++) {
				draw = draw && !buttonList[i][j].getText().equals("");
			}
		}
		
		if(playerWins) {
			JOptionPane.showMessageDialog(frame, "You win!");
		}
		else if(computerWins) {
			JOptionPane.showMessageDialog(frame, "You lost!");
		}
		else if(draw) {
			JOptionPane.showMessageDialog(frame, "It's a draw!");
		}
		
		
		
	}
	
	public void computerPlay(Integer x, Integer y) {
		Integer leftX = x-1;
		Integer rightX = x+1;
		Integer downY = y+1;
		Integer upY = y-1;
		
		if(leftX>=0 && buttonList[leftX][y].getText().equals("")) {
			buttonList[leftX][y].doClick();
		}
		else if(rightX<=2 && buttonList[rightX][y].getText().equals("")) {
			buttonList[rightX][y].doClick();
		}
		else if(downY<=2 && buttonList[x][downY].getText().equals("")) {
			buttonList[x][downY].doClick();
		}
		else if(upY>=0 && buttonList[x][upY].getText().equals("")) {
			buttonList[x][upY].doClick();
		}
	}
	
	class GridClicked implements ActionListener {

		private Integer x;
		private Integer y;
		
		public GridClicked(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(computerTurn) {
				buttonList[x][y].setText("X");
				computerTurn = !computerTurn;
			}
			else{
				buttonList[x][y].setText("O");
				computerTurn = !computerTurn;
			}
			checkWin();
			if(computerTurn) {
				computerPlay(x,y);
				checkWin();
			}
		}
		
	}
	
	
	
}
