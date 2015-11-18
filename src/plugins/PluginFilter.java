package plugins;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;


public class PluginFilter implements FilenameFilter, ActionListener {

	public boolean accept(File dir, String name) {
		if(name.lastIndexOf('.')>0)
		{
			// get last index for '.' char
			int lastIndex = name.lastIndexOf('.');

			// get extension
			String str = name.substring(lastIndex);

			// match path name extension
			if(str.equals(".class"))
			{
				return true;
			}
		}
		return false;
	}


	public void actionPerformed(ActionEvent e) {
		System.out.println("action performed");
	}

}
