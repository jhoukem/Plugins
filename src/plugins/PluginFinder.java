package plugins;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class PluginFinder implements ActionListener{

	PluginFilter pf = new PluginFilter();
	File dPath;
	JMenu tools;

	public PluginFinder(File f, JMenu jMenu) {
		this.dPath = f;
		tools = jMenu;
	}

	File [] listClassFile(){
		return dPath.listFiles(pf);
	}

	public void actionPerformed(ActionEvent e) {
		File[] liste = listClassFile();
		for(int i = 0; i < liste.length; i++){
			System.out.println("File num :"+i+" "+liste[i].getName());
			if(isPlugin(liste[i]) && notExist(liste[i].getName())){
				createMenu(liste[i].getName());
			}
		}




	}

	private boolean notExist(String name) {
		System.out.println(tools.getComponent().getName());
		return false;
	}

	private boolean isPlugin(File file) {
		return true;	
	}

	private void createMenu(String name) {
		JMenuItem jmi = new JMenuItem(name);
		tools.add(jmi);

	}
}
