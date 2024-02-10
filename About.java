package NotePad;
import java.awt.Font;

import javax.swing.*;
public class About extends JFrame{

	About(){
		setBounds(200,200,800,600);
		setTitle("About Notepad application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		ImageIcon icon=new ImageIcon(getClass().getResource("notpad.jpg"));
	    setIconImage(icon.getImage());	

	    JLabel iconlabel=new JLabel(new ImageIcon(getClass().getResource("notepad-icon-17525.png")));
	    
	    iconlabel.setBounds(100, 100, 250, 250);
	    add(iconlabel);
	    
	    JLabel textlabel=new JLabel("<html> Welcome to Notepad <br> Notepad is a simple text editor for Microsoft Windows and a basic text-editing program which enables computer users to create documents <br> All rights reserved@2022");
	    textlabel.setBounds(100,250,400,300);
	    textlabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
	    add(textlabel);
	}
	
	public static void main(String[] args) {
		new About().setVisible(true);
	}
	
}
