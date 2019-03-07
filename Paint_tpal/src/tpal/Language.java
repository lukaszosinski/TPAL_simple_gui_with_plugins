package tpal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Language {
	
	private HashMap<String, String> en = new HashMap<String, String>();
	private HashMap<String, String> pl = new HashMap<String, String>();
	
	private Map<String, HashMap<String, String>> languages = new HashMap<String, HashMap<String, String>>();
	
	private ArrayList<String> availableLanguages = new ArrayList<String>();
	
	private String currentLanguage = "EN"; 
	
	Language() {
		
		availableLanguages.add("EN");
		availableLanguages.add("PL");
		
		en.put("Clear", "Clear");
		en.put("Black", "Black");
		en.put("Blue", "Blue");
		en.put("Green", "Green");
		en.put("Red", "Red");
		en.put("Magenta", "Magenta");
		en.put("Load image", "Load image");
		en.put("Undo", "Undo");
		en.put("Redo", "Redo");
		
		pl.put("Clear", "Wyczyść");
		pl.put("Black", "Czarny");
		pl.put("Blue", "Niebieski");
		pl.put("Green", "Zielony");
		pl.put("Red", "Czerwony");
		pl.put("Magenta", "Magenta");
		pl.put("Load image", "Załaduj obraz");
		pl.put("Undo", "Cofnij");
		pl.put("Redo", "Ponów");
		
		languages.put("EN", en);
		languages.put("PL", pl);
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
		availableLanguages.remove(availableLanguages.indexOf(currentLanguage));
		availableLanguages.add(0, currentLanguage);
	}
	
	public String getName(String name) {
		return languages.get(currentLanguage).get(name);		
	}
	
	public ArrayList<String> getAvailableLanguages() {
		return availableLanguages;
	}

}
