package ihm;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	JTextArea text = new JTextArea();
	JScrollPane jsp = new JScrollPane(text);
	
	public Fenetre(){
		this.setTitle("Extendable Editor");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(500, 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(initMenu());
		this.add(jsp);
		this.setVisible(true);
		this.pack();
	}
	
	public JMenuBar initMenu(){
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu tools = new JMenu("Tools");
		JMenu help = new JMenu("Help");
		JMenuItem close = new JMenuItem("Close");
		
		menu.add(file);
		menu.add(tools);
		menu.add(help);
		file.add(close);
		
		return menu;
	}
	
}
