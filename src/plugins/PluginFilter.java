package plugins;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;


public class PluginFilter implements FilenameFilter{

	public boolean accept(File dir, String name) {
		if(!name.toLowerCase().endsWith(".class"))
			return false;

		String pName = name.substring(0, name.length() - 6);
		Class<?> cl;
		try {
			cl = Class.forName("plugins."+pName);
			if(isPlugin(cl))
				return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isPlugin(Class<?> cl) {

		if(hasEmptyConstructor(cl) && belongsPluginPackage(cl) ){
			//	System.out.println("Plugin OK");
			return true;
		}
		else{
			//	System.out.println("Plugin KO");
			return false;
		}

	}

	private boolean belongsPluginPackage(Class<?> cl) {
		if(Plugin.class.isAssignableFrom(cl) && cl.getPackage().getName().equals("plugins") )
			return true;
		return false;
	}

	private boolean hasEmptyConstructor(Class<?> cl) {
		Constructor<?>[] constructors = cl.getConstructors();
		for(int i = 0; i < constructors.length; i++){
			Constructor<?> cons = constructors[i];
			if(cons.getParameterCount() == 0)
				return true;
		}
		return false;
	}



}
