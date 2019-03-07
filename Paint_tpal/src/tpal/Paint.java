package tpal;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint {
 
  JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, loadBtn, undoBtn, redoBtn;
  DrawArea drawArea;
  List<Plugin> plugins; 
  Language language = new Language();
  JComboBox languageSet; 
  
  JFrame frame = new JFrame("TPAL Paint");
  JPanel controls = new JPanel();
  JPanel pluginsControls = new JPanel();
   
  ActionListener actionListener = new ActionListener() {
 
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      } else if (e.getSource() == blackBtn) {
        drawArea.black();
      } else if (e.getSource() == blueBtn) {
        drawArea.blue();
      } else if (e.getSource() == greenBtn) {
        drawArea.green();
      } else if (e.getSource() == redBtn) {
        drawArea.red();
      } else if (e.getSource() == loadBtn) {
          drawArea.open();
      } else if (e.getSource() == undoBtn) {
          drawArea.undo();
      } else if (e.getSource() == redoBtn) {
    	  drawArea.redo();
      } else if (e.getSource() == languageSet) {
    	  JComboBox cb = (JComboBox)e.getSource();
          String lan = (String)cb.getSelectedItem();
    	  setLanguage(lan);
  	  }   
    }
  };
  
  public Paint() {
		PluginLoader loader = new PluginLoader();
		try {
			plugins = loader.loadFromDirectory("plugins");
		} catch (InstantiationException | 
				IllegalAccessException  | 
				ClassNotFoundException  | 
				IOException e) {
			e.printStackTrace();
		}
  }
 
  

public static void main(String[] args) throws IOException {
    new Paint().show();    
  }
 
  public void show() throws IOException {
    
    Container content = frame.getContentPane();
    content.setLayout(new BorderLayout());
    drawArea = new DrawArea();    
    content.add(drawArea, BorderLayout.CENTER);
 
    
 
    clearBtn = new JButton(language.getName("Clear"));
    clearBtn.addActionListener(actionListener);
    blackBtn = new JButton(language.getName("Black"));
    blackBtn.addActionListener(actionListener);
    blueBtn = new JButton(language.getName("Blue"));
    blueBtn.addActionListener(actionListener);
    greenBtn = new JButton(language.getName("Green"));
    greenBtn.addActionListener(actionListener);
    redBtn = new JButton(language.getName("Red"));
    redBtn.addActionListener(actionListener);
    loadBtn = new JButton(language.getName("Load image"));
    loadBtn.addActionListener(actionListener);
    undoBtn = new JButton(language.getName("Undo"));
    undoBtn.addActionListener(actionListener);
    redoBtn = new JButton(language.getName("Redo"));
    redoBtn.addActionListener(actionListener);
    
    languageSet = new JComboBox(language.getAvailableLanguages().toArray());
    languageSet.addActionListener(actionListener);
    
    
	  
	controls.add(undoBtn);
	controls.add(redoBtn);
	controls.add(clearBtn);
	  
	controls.add(greenBtn);
	controls.add(blueBtn);
	controls.add(blackBtn);
	controls.add(redBtn);
	
	controls.add(loadBtn);
	  	  
	controls.add(languageSet);
    
    
       
    for (Plugin plugin : plugins) {
    	JButton button = new JButton(plugin.getOperationName(language.getCurrentLanguage()));
    	button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					drawArea.setImage(plugin.processImage(drawArea.getImage()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    		
    	});
		pluginsControls.add(button);
	}
 
    content.add(controls, BorderLayout.NORTH);
    content.add(pluginsControls, BorderLayout.SOUTH);
 
    frame.setSize(800, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
 
  }
  
  private void setLanguage(String lan) {
		language.setCurrentLanguage(lan);
		updateControls();
  }
  
  private void updateControls() {
	  
	  Container content = frame.getContentPane();
	  content.remove(this.controls);
	  
	  clearBtn.setText(language.getName("Clear"));
	  blackBtn.setText(language.getName("Black"));
	  blueBtn.setText(language.getName("Blue"));
	  greenBtn.setText(language.getName("Green"));
	  redBtn.setText(language.getName("Red"));
	  loadBtn.setText(language.getName("Load image"));
	  undoBtn.setText(language.getName("Undo"));
	  redoBtn.setText(language.getName("Redo"));
	    
	  languageSet = new JComboBox(language.getAvailableLanguages().toArray());
	  languageSet.addActionListener(actionListener);
	    
	  JPanel controls = new JPanel();
	  	  
	  controls.add(undoBtn);
	  controls.add(redoBtn);
	  controls.add(clearBtn);
	  
	  controls.add(greenBtn);
	  controls.add(blueBtn);
	  controls.add(blackBtn);
	  controls.add(redBtn);
	  
	  controls.add(loadBtn);
	  	  
	  controls.add(languageSet);
	  
	  this.controls = controls;
	    
	  content.add(controls, BorderLayout.NORTH);
	  
	  JPanel pluginsControls = new JPanel();
	  content.remove(this.pluginsControls);
	  
	  for (Plugin plugin : plugins) {
	    	JButton button = new JButton(plugin.getOperationName(language.getCurrentLanguage()));
	    	button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						drawArea.setImage(plugin.processImage(drawArea.getImage()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	    		
	    	});
			pluginsControls.add(button);
	  }
	  
	  content.add(pluginsControls, BorderLayout.SOUTH);	  
	  this.pluginsControls = pluginsControls;
	  
	  content.revalidate();
	  content.repaint();
  }

 
}