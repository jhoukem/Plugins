package plugins;
import java.io.File;


public class PluginFinder {

	PluginFilter pf = new PluginFilter();
	File dPath;

	public PluginFinder(File f) {
		this.dPath = f;
	}
	
	File [] listClassFile(){
		return dPath.listFiles(pf);
	}
	

}