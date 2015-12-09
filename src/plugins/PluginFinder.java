package plugins;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JTextArea;


public class PluginFinder extends Observable implements ActionListener{

	PluginFilter pf = new PluginFilter();
	File dPath;
	ArrayList<Class<?>> plugins = new ArrayList<Class<?>>();
	
	public PluginFinder(File f, JMenu jMenu, JTextArea jta) {
		this.dPath = f;
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
				Class<?> cl = Class.forName("plugins."+pName); //cree la class associ� au nom
				if(!existPlugin(cl)){//test si le plugin n'existe aps deja
					plugins.add(cl);
					notifyObservers(cl);// on previens la barre de menu qu'il y a un nouveau plugin
					//createMenuItem(cl);
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	private boolean existPlugin(Class<?> cl) {
		return plugins.contains(cl);
	}
	
	@Override
	public void notifyObservers(Object o){
		setChanged();	
		super.notifyObservers(o);
	}
	
}
