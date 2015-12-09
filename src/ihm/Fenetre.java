package ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Observer {
	
	private JTextArea text = new JTextArea();
	

	private JScrollPane jsp = new JScrollPane(text);
	private JMenuBar menu = new JMenuBar();
	private JMenu tools = new JMenu("Tools");
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
	
	
	public JMenu getTools() {
		return tools;
	}
	public JTextArea getTextArea() {
		return text;
	}

	@Override
	public void update(Observable o, Object arg) {
		createMenuItem((Class<?>)arg);
	}
	
	private void createMenuItem(Class<?> cl) {
		try {
			final Object o = cl.newInstance();
			final Method transform = cl.getMethod("transform", String.class);
			Method getLabel = cl.getMethod("getLabel");
			String label = (String) getLabel.invoke(o);

			JMenuItem jmi = new JMenuItem(label);
			jmi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String newLabel = (String) transform.invoke(o, text.getText());
						text.setText(newLabel);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}
			});
			tools.add(jmi);

		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
}
