package plugins;
import java.io.File;
import java.io.FilenameFilter;


public class PluginFilter implements FilenameFilter{

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

}
