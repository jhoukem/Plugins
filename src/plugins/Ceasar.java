package plugins;

public class Ceasar implements Plugin{

	
	@Override
	public String transform(String s) {
		return s.toLowerCase();
	}

	@Override
	public String getLabel() {
		return "Caesar";
	}
}
