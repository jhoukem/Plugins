package plugins;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;


public class PluginFinder implements ActionListener{

	PluginFilter pf = new PluginFilter();
	File dPath;
	JMenu tools;
	JTextArea jTextArea;
	ArrayList<Class<?>> plugins = new ArrayList<Class<?>>();
	
	public PluginFinder(File f, JMenu jMenu, JTextArea jta) {
		this.dPath = f;
		tools = jMenu;
		jTextArea = jta;
	}

	File [] listPluginFile(){
		return dPath.listFiles(pf);
	}

	public void actionPerformed(ActionEvent e) {
		File[] liste = listPluginFile();
		for(int i = 0; i < liste.length; i++){
			File current = liste[i];// recup le fichier courant
			String pName = current.getName().substring(0, current.getName().length() - 6); //retire l'extension.class
			try {
				Class<?> cl = Class.forName("plugins."+pName); //cree la class associé au nom
				if(!existPlugin(cl)){//test si la classe est valide
					plugins.add(cl);
					createMenuItem(cl);
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	private boolean existPlugin(Class<?> cl) {
		return plugins.contains(cl);
	}


	private void createMenuItem(Class<?> cl) {

		try {
			Object o = cl.newInstance();
			Method transform = cl.getMethod("transform", String.class);
			Method getLabel = cl.getMethod("getLabel");
			String label = (String) getLabel.invoke(o);

			JMenuItem jmi = new JMenuItem(label);
			jmi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String newLabel = (String) transform.invoke(o, jTextArea.getText());
						jTextArea.setText(newLabel);
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
