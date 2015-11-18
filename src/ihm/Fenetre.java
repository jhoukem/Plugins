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
	
	private JTextArea text = new JTextArea();
	private JScrollPane jsp = new JScrollPane(text);
	private JMenuBar menu = new JMenuBar();
	private JMenu tools = new JMenu("Tools");
	
	public JMenu getTools() {
		return tools;
	}

	public Fenetre(){
		this.setTitle("Extendable Editor");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(500, 300));
		
		initMenu();
		
		this.setJMenuBar(menu);
		this.getContentPane().add(jsp);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initMenu(){
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem close = new JMenuItem("Close");
		
		menu.add(file);
		menu.add(tools);
		menu.add(help);
		file.add(close);
	}
	
}
