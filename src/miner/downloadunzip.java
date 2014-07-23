package miner;

// This is class which work only with windows version 64 !!
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class downloadunzip extends TabbedPane {

	JButton ok = new JButton("Go to page");
	JButton no = new JButton("Cancel");
	static JLabel l = new JLabel("");
	static JProgressBar current = new JProgressBar(0, 100);
	static JButton btn = new JButton("Download");
	static JFrame frame1 = new JFrame("Downloading");
	static Thread t = null;
	private volatile Thread blinker;

	public downloadunzip() throws NoSuchAlgorithmException, IOException {

		frame1.setSize(320, 200);

		frame1.setLayout(new GridLayout(4, 4));
		frame1.setLocationRelativeTo(null);
		current.setSize(50, 50);
		current.setValue(0);
		current.setStringPainted(true);

		frame1.setResizable(false);
		frame1.setVisible(true);
		frame1.add(current);
		frame1.add(btn);
		btn.addActionListener(dwnl);

		frame1.addWindowListener(new WindowAdapter() {
			// WAT!?

			@Override
			public void windowClosing(WindowEvent e) {

				System.out.println("\nThread stopped. Binaries not downloaded");
			}

		});

	}

	private static ActionListener dwnl = new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			t = new Thread(new Runnable() {

				public void run() {

					String OS = System.getProperty("os.name").toLowerCase();
					String ar = System.getProperty("os.arch").toLowerCase();
					final String saveTo = "C:\\Users\\Public\\myMinerDir\\";
					boolean f;
					f = new File("C:\\Users\\Public\\myMinerDir\\").mkdir();
					// final URL url = null;

					if (OS.indexOf("win") >= 0) {
						System.out.println("tw�j system to: " + OS + " " + ar
								+ " / Your OS :" + OS + " " + ar);
						if (ar.equals("amd64")) {
							System.out.print(" " + ar);
							// pob i rozp 64 win

							Thread w64 = new Thread(new Runnable() {
								public void run() {

									URL url = null;
									try {
										//official download link from bfgminer.org
										url = new URL(
												"http://luke.dashjr.org/programs/bitcoin/files/bfgminer/4.4.0/bfgminer-4.4.0-win64.zip");
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									String filename = "bfgminer.zip";

									try {
										URLConnection conn = url
												.openConnection();
										int filesize = conn.getContentLength();
										float totalDataRead = 0;

										InputStream in = conn.getInputStream();
										FileOutputStream out = new FileOutputStream(
												saveTo + filename);
										// BufferedOutputStream bout = new
										// BufferedOutputStream(out,1024);

										byte[] b = new byte[1024];
										int count;

										while ((count = in.read(b)) >= 0) {
											out.write(b, 0, count);
											totalDataRead = totalDataRead
													+ count;
											float Percent = (totalDataRead * 100)
													/ filesize;
											current.setValue((int) Percent);

										}

										l.setText("Pobieranie zako�czone");
										out.close();
										in.close();
									} catch (IOException e) {
										JOptionPane.showConfirmDialog(null,
												e.getMessage(), "Error",
												JOptionPane.DEFAULT_OPTION);

									}
									String source = "C:\\Users\\Public\\myMinerDir\\bfgminer.zip";
									String destinantion = "C:\\Users\\Public\\myMinerDir\\";

									try {
										ZipFile zipFile = new ZipFile(source);
										zipFile.extractAll(destinantion);

									} catch (ZipException e) {
										e.printStackTrace();
									}

									System.out.println("DONE!");
									JOptionPane
											.showMessageDialog(
													null,
													"Done!",
													"Binaries succesfully downloaded and I am ready to mine !",
													JOptionPane.INFORMATION_MESSAGE);
									frame1.dispose();

								}
							});
							w64.start();

						}

						else {
							System.out.print(" " + ar);
							// pob i rozp 32 win
							Thread w32 = new Thread(new Runnable() {
								public void run() {
									URL url = null;
									try {
										//oficial download link from bfgminer.org
										url = new URL(
												"http://luke.dashjr.org/programs/bitcoin/files/bfgminer/4.4.0/bfgminer-4.4.0-win32.zip");
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									String filename = "bfgminer.zip";

									try {
										URLConnection conn = url
												.openConnection();
										int filesize = conn.getContentLength();
										float totalDataRead = 0;

										InputStream in = conn.getInputStream();
										FileOutputStream out = new FileOutputStream(
												saveTo + filename);
										// BufferedOutputStream bout = new
										// BufferedOutputStream(out,1024);

										byte[] b = new byte[1024];
										int count;

										while ((count = in.read(b)) >= 0) {
											out.write(b, 0, count);
											totalDataRead = totalDataRead
													+ count;
											float Percent = (totalDataRead * 100)
													/ filesize;
											current.setValue((int) Percent);

										}

										l.setText("Pobieranie zako�czone");
										out.close();
										in.close();
									} catch (IOException e) {
										JOptionPane.showConfirmDialog(null,
												e.getMessage(), "Error",
												JOptionPane.DEFAULT_OPTION);

									}
									String source = "C:\\Users\\Public\\myMinerDir\\bfgminer.zip";
									String destinantion = "C:\\Users\\Public\\myMinerDir\\";

									try {
										ZipFile zipFile = new ZipFile(source);
										zipFile.extractAll(destinantion);

									} catch (ZipException e) {
										e.printStackTrace();
									}

									System.out.println("DONE!");
									JOptionPane
											.showMessageDialog(
													null,
													"Done!",
													"Binaries succesfully downloaded and I am ready to mine !",
													JOptionPane.INFORMATION_MESSAGE);
									frame1.dispose();

								}
							});
							w32.start();

						}

					}

					/* LINUX */else if (OS.indexOf("linux") >= 0) {
						System.out.println("tw�j system to: " + OS + " " + ar
								+ " / Your OS :" + OS + " " + ar);
						if (ar.equals("amd64")) {
							System.out.print(" " + ar);
							// download linux 64
							int res = JOptionPane
									.showOptionDialog(
											null,
											"Something went wrong :( "
													+ "\n I can't install miner on linux automatically. Please click yes and download it yourself.",
											"Oops!", JOptionPane.OK_OPTION,
											JOptionPane.CANCEL_OPTION, null,
											null, null);

							System.out.println(res);
							if (res == 0) {
								TabbedPane.openWebpage("http://bfgminer.org/");
								frame1.dispose();

							} else {
								frame1.dispose();
							}

						}

						else {
							System.out.print(" " + ar);
							int res = JOptionPane
									.showOptionDialog(
											null,
											"Something went wrong :( "
													+ "\n I can't install miner automatically on linux. Please click yes and download it yourself.",
											"Oops!", JOptionPane.OK_OPTION,
											JOptionPane.CANCEL_OPTION, null,
											null, null);

							System.out.println(res);
							if (res == 0) {
								TabbedPane.openWebpage("http://bfgminer.org/");
								frame1.dispose();

							} else {
								frame1.dispose();
							}
						}

					}

					else {
						System.out.println("tw�j system to: " + OS + " " + ar
								+ " / Your OS :" + OS + " " + ar);
						System.out
								.println("We're sorry to inform that OSX or system you're using is not supported :(");
						JOptionPane
								.showMessageDialog(
										null,
										"We're sorry to inform that OSX or system you're using is not supported :(",
										"Error",
										
										JOptionPane.ERROR_MESSAGE);
					}

					// ok

				}
			});
			t.start();

		}

	};

}
