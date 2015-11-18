package plugins;

public class ToUpperCase implements Plugin{

	
	@Override
	public String transform(String s) {
		return s.toUpperCase();
	}

	@Override
	public String getLabel() {
		return "To Upper Case";
	}

}
