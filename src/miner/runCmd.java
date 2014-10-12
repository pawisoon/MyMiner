package miner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class runCmd extends TabbedPane {
	public runCmd() throws NoSuchAlgorithmException, IOException {

	}

	public static Process process;

	public static void runC(final String logIn, final String pswd,
			final String api) throws InterruptedException, IOException {
		String OS = System.getProperty("os.name").toLowerCase();
		String ar = System.getProperty("os.arch").toLowerCase();
		if (OS.indexOf("win") >= 0) {
			if(ar.equals("amd64")){
				
			
			
			File f = new File(
					"C:\\Users\\Public\\myMinerDir\\bfgminer-4.4.0-win64");
			try {
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
						"start", "bfgminer.exe", "-o", api, "-u", logIn, "-p",
						pswd, "-S", "auto", "-d", "all");
				builder.directory(f);

				builder.redirectErrorStream(true);

				

				process = builder.start();
				

			}

			catch (IOException e) {

			}
		}
			else if(ar.equals("x86")){
				File f = new File("C:\\Users\\Public\\myMinerDir\\bfgminer-4.4.0-win32");
				try{
					ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c","start","bfgminer.exe","-o",api,"-u", logIn,"-p",pswd,"-S","auto","-d","all");
					builder.directory(f);
					builder.redirectErrorStream(true);
					process = builder.start();
				}
				catch(IOException e){
					
				}
			}

		// LINUX RUN MINER
		else if (OS.indexOf("linux") >= 0) {
			System.out.println("Preparation to run on linux");

			final ProcessBuilder builder = new ProcessBuilder(
					"/usr/bin/x-terminal-emulator");

			builder.redirectErrorStream(true);

			process = builder.start();

		}

		else {
                System.out.println("error");
                int res1 = JOptionPane.showOptionDialog(null, "Sorry", "Oops!",
					JOptionPane.OK_OPTION, (Integer) null, null, null, null);
			System.out.println(res1);
		}
	}}

	public static void killMiner() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM bfgminer.exe");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("bfgminer killed");
	}

}