package main;

import ihm.Fenetre;

import java.io.File;

import javax.swing.Timer;

import plugins.PluginFinder;

public class Main {

	public static void main(String[] args) {
		
		Fenetre fenetre = new Fenetre();
		
		PluginFinder pf = new PluginFinder(new File(System.getProperty("java.class.path")+"/plugins"), fenetre.getTools(), fenetre.getTextArea());
		pf.addObserver(fenetre);
		Timer timer = new Timer(1000, pf);
		timer.start();
		
		fenetre.setVisible(true);
		fenetre.pack();
		
		
	}

}
