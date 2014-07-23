package miner;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//version 1.1
public class TabbedPane extends JPanel implements ActionListener, ItemListener {
	protected static double version = 1.1;
	static JLabel pps = new JLabel("");
	static JLabel pps1 = new JLabel("");
	static JLabel pps2 = new JLabel("");
	static JLabel btx = new JLabel("");
	static JLabel btce = new JLabel("");
	static JLabel btce1 = new JLabel("");
	JTextArea textArea;

	static Preferences preferences = Preferences
			.userNodeForPackage(TabbedPane.class);

	public void setCredentials(String username, String pswd, String api) {
		preferences.put("nick", username);
		preferences.put("pswd", pswd);
		preferences.put("apis", api);
	}

	public static String getUsername() {
		return preferences.get("nick", null);
	}

	public static String getPassword() {
		return preferences.get("pswd", null);
	}

	public static String getApi() {
		return preferences.get("apis", null);
	}

	public TabbedPane() throws NoSuchAlgorithmException, IOException {

		super(new GridLayout(4, 4));

		final JTabbedPane tabbedPane = new JTabbedPane();
		// panel 1 buttons
		final JComponent panel1 = makeGuiPanel();
		JButton btn = new JButton("Save changes");
		JButton nextStep1 = new JButton("Next step");

		// panel2 buttons

		// czytam login
		final String dd = getUsername();
		final JTextField nick = new JTextField(getUsername(), 10);

		// czytam pswd
		final String pp = getPassword();
		final JPasswordField pswd = new JPasswordField(getPassword(), 10);
		// czytam api
		final String aa = getApi();

		final JTextField api = new JTextField(getApi(), 10);

		// pr�ba zapisu
		ActionListener sv = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCredentials(nick.getText(), pswd.getText(), api.getText());

				/*
				 * File l = new File("src/l.txt"); File p = new
				 * File("src/p.txt"); File a = new File("src/a.txt"); // File a
				 * = new File("/bin/miner/a.txt");
				 * 
				 * try { FileWriter fw = new FileWriter(l.getAbsoluteFile());
				 * FileWriter fw1 = new FileWriter(p.getAbsoluteFile());
				 * 
				 * FileWriter fw2 = new FileWriter(a.getAbsoluteFile());
				 * 
				 * BufferedWriter bw = new BufferedWriter(fw); BufferedWriter
				 * bw1 = new BufferedWriter(fw1); BufferedWriter bw2 = new
				 * BufferedWriter(fw2);
				 * 
				 * bw.write(nick.getText()); bw1.write(pswd.getPassword());
				 * bw2.write(api.getText()); bw.close(); bw1.close();
				 * bw2.close(); System.out.println("Saved");
				 * 
				 * } catch (IOException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); }
				 */

			}
		};

		ActionListener nx = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);

			}
		};

		String p = pswd.getText();

		JLabel lg = new JLabel("Login");

		JLabel ps = new JLabel("Password");

		JLabel apiAdr = new JLabel("api:port");

		// String[] apiz = {"poolmine","btc","xyz","dupa","asasas"};
		//
		// JComboBox apis = new JComboBox(apiz);

		panel1.add(lg);
		panel1.add(nick);
		panel1.add(ps);
		panel1.add(pswd);
		panel1.add(apiAdr);
		panel1.add(api);

		byte[] bitsPswd = p.getBytes("UTF-8");
		savePswdDetails(bitsPswd);

		panel1.add(btn);
		// panel1.add(nextStep1);
		btn.addActionListener(sv);
		nextStep1.addActionListener(nx);

		tabbedPane.addTab("Main panel", panel1);

		JComponent panel2 = makeGuiPanel();

		tabbedPane.addTab("Mining details", panel2);
		JLabel kurs = new JLabel("Actual exchange rate");
		panel2.add(kurs);

		add(tabbedPane);
		add(kurs);
		try { // news feed !
			String a = getDetails();
			pps = new JLabel(a.substring(30, 57));
			add(pps);
			pps1 = new JLabel(a.substring(57, 81));
			add(pps1);
			pps2 = new JLabel(a.substring(81, 101));
			// add(pps2);

			// add(det);
		} catch (IOException e) {
			e.printStackTrace();
			String c = "can't reach host !";
			System.out.println(c);
			JLabel det = new JLabel(c);
			add(det);
		}
	}

	public static String getDetails() throws IOException {
		String url = "http://polmine.pl/index.php?cookie=ok";
		Document document = Jsoup.connect(url).get();

		String question = document.select(".border_r").text();

		return (question.substring(0, 180));

		// question.substring api(14, 30)+"\n"+question.substring(30,53)

	}

	public static String getUpdate() throws IOException {
		String url = "http://pawisoon.tk/version/version.html";
		Document document = Jsoup.connect(url).get();
		String question = document.text();
		return (question);
	}

	private void savePswdDetails(byte[] bitsPswd)
			throws NoSuchAlgorithmException {
		MessageDigest dig = java.security.MessageDigest.getInstance("MD5");
		dig.update(bitsPswd);
		byte[] hash = dig.digest();
	}

	private static JComponent makeGuiPanel() {

		JPanel panel = new JPanel(false);

		return panel;
	}

	public static void showGuiPanel(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		makeGuiPanel();
	}

	// url redirection
	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// wizyta na stronie
	static ActionListener wp = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			openWebpage("http://www.pawisoon.tk");
		}
	};
	static runCmd r;
	static ActionListener startMine = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(new File("C:\\Users\\Public\\myMinerDir\\").exists()){
				System.out.println("OK - dir is already downloaded");
			}
			else{
				JOptionPane.showConfirmDialog(null,"Download binaries fisrt!\nGo to Options.","Error",JOptionPane.DEFAULT_OPTION);
			}
			try {
				r = new runCmd();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String l = null;
			l = getUsername();
			String p = null;
			p = getPassword();
			String a = null;
			a = getApi();
			try {
				runCmd.runC(l, p, a);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	// stop kopania
	static ActionListener stopMine = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			runCmd.killMiner();

		}
	};

	// pobieranie zipa
	private static ActionListener dwnl = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				new downloadunzip();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	// Sprawdzanie aktualizacji
	private static ActionListener upd = new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			try {
				String a = getUpdate();
				double aD = Double.parseDouble(a);

				if (version < aD) {

					final String link = "http://pawisoon.tk/Miner";
					JLabel label = new JLabel("<html><u>" + link
							+ "</u></html>");
					label.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent me) {
							try {
								Runtime.getRuntime().exec(
										"rundll32 url.dll,FileProtocolHandler "
												+ link);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					int lel = JOptionPane.showConfirmDialog(null, label,
							"Update found!", JOptionPane.YES_OPTION);
					if (lel == JOptionPane.YES_OPTION) {
						openWebpage("http://pawisoon.tk/Miner");
					} else {
						System.out.println("Update aborted");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No updates found :(");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showConfirmDialog(null, e.getMessage(),
						"No internet connection", JOptionPane.DEFAULT_OPTION);
				e.printStackTrace();
			}

		}

	};

	static void createAndShowGUI() throws IOException {

		final JFrame frame = new JFrame("MyMiner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// addComponentsToPane(frame.getContentPane());
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		JMenu file = new JMenu("File");
		JMenu options = new JMenu("Options");

		JMenu more = new JMenu("About us");

		mb.add(file);
		mb.add(options);
		mb.add(more);
		JMenuItem dw = new JMenuItem("Download binaries");
		options.add(dw);
		JMenuItem up = new JMenuItem("Check for updates");
		options.add(up);
		up.addActionListener(upd);
		dw.addActionListener(dwnl);
		JMenuItem st = new JMenuItem("Start");
		st.addActionListener(startMine);
		file.add(st);
		JMenuItem ps = new JMenuItem("Stop");
		ps.addActionListener(stopMine);
		file.add(ps);

		JMenuItem webPage = new JMenuItem("Web");
		more.add(webPage);
		webPage.addActionListener(wp);

		// DOPISA� WY�WIETLANIE NOWEJ RAMKI Z JTABBEDPANE ON CLICK W OPTIONS
		// ->EDIT CONFIG

		try {
			frame.add(new TabbedPane());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		int width = 960;
		int height = 540;

		frame.setSize(width, height);
		// frame.setResizable(false);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

}
