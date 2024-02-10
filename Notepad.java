package NotePad;

import java.awt.Font;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.print.PrinterException;

public class Notepad extends JFrame implements ActionListener{
	
	JMenuBar menuBar = new JMenuBar();
	JMenu file=new JMenu("file");
	JMenu edit=new JMenu("Edit");
	JMenu help=new JMenu("help");
	
	JMenuItem newFile=new JMenuItem("New");
	JMenuItem OpenFile=new JMenuItem("Open");
	JMenuItem SaveFile=new JMenuItem("Save");
	JMenuItem PrintFile=new JMenuItem("Print");
	JMenuItem ExitFile=new JMenuItem("Exit");
	
	JMenuItem cut=new JMenuItem("Cut");
	JMenuItem copy=new JMenuItem("Copy");
	JMenuItem paste=new JMenuItem("Paste");
	JMenuItem selectall=new JMenuItem("Select All");
	
	JMenuItem about =new JMenuItem("about");
	
	JTextArea textArea=new JTextArea();
	
	Notepad(){
	setTitle("notepad Application ");
	setBounds(100,100,800,600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	ImageIcon icon=new ImageIcon(getClass().getResource("notpad.jpg"));
    setIconImage(icon.getImage());	
    
    setJMenuBar(menuBar);
    
    menuBar.add(file);
    menuBar.add(edit);
    menuBar.add(help);
	
    file.add(newFile);
    file.add(OpenFile);
    file.add(SaveFile);
    file.add(PrintFile);
    file.add(ExitFile);
    
    edit.add(cut);
    edit.add(copy);
    edit.add(paste);
    edit.add(selectall);
    
    help.add(about);
    
    JScrollPane scrollPane=new JScrollPane(textArea);
    add(scrollPane);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    
    newFile.addActionListener(this);
    OpenFile.addActionListener(this);
    SaveFile.addActionListener(this);
    PrintFile.addActionListener(this);
    ExitFile.addActionListener(this);
    cut.addActionListener(this);
    copy.addActionListener(this);
    paste.addActionListener(this);
    selectall.addActionListener(this);
    about.addActionListener(this);
    
    newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
    OpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
    SaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
    PrintFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
    ExitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
    cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
    copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
    paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
    selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
    about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("New")) {
		textArea.setText(null);	
		}
		else if(e.getActionCommand().equalsIgnoreCase("Open"))
		{
			JFileChooser fileChooser=new JFileChooser();
			FileNameExtensionFilter textfilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textfilter);
			
			int action=fileChooser.showSaveDialog(null);
			
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			else {
				try {
					BufferedReader reader=new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
					
					textArea.read(reader,null);
					}
					catch (Exception a) {
					a.printStackTrace();
					}
			}
			
		}else if(e.getActionCommand().equalsIgnoreCase("Save"))
		{
			JFileChooser fileChooser=new JFileChooser();
			FileNameExtensionFilter textfilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textfilter);
			
			int action=fileChooser.showSaveDialog(null);
			
			if(action!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}else {
				String filename=fileChooser.getSelectedFile().getAbsolutePath().toString();
				if(filename.contains(".txt"))
				{
					filename=filename+".txt";
					
					try {
					BufferedWriter writer=new BufferedWriter(new FileWriter(filename));
					textArea.write(writer);
					}
					catch (Exception a) {
					a.printStackTrace();
					}
					}
			}
			
		}else if(e.getActionCommand().equalsIgnoreCase("Print"))
		{
			try {
				textArea.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
			}
		}else if(e.getActionCommand().equalsIgnoreCase("Exit"))
		{
			System.exit(0);
		}else if(e.getActionCommand().equalsIgnoreCase("Cut"))
		{
			textArea.cut();
		}else if(e.getActionCommand().equalsIgnoreCase("Copy"))
		{
			textArea.copy();
		}else if(e.getActionCommand().equalsIgnoreCase("Paste"))
		{
			textArea.paste();
		}else if(e.getActionCommand().equalsIgnoreCase("Select All"))
		{
			textArea.selectAll();
		}else if(e.getActionCommand().equalsIgnoreCase("About"))
		{
			new About().setVisible(true);
		}
	}
	
	public static void main(String args[])
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
		}
		
		new Notepad().setVisible(true);
	}

}
