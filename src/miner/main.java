package miner;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;

public class main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Setting up WebLookAndFeel style
					UIManager.setLookAndFeel(WebLookAndFeel.class
							.getCanonicalName());
				} catch (Throwable e) {
					// Something went wrong
				}
				try {
					TabbedPane.createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
}
