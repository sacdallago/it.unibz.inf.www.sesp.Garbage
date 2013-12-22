package textmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import controller.Log;

public class FTPBridge {

	FTPClient ftp = null;

	/**
	 * Creates an FTP connection to a specific host given the user name and the password
	 * 
	 * 
	 * @param host URL of the host
	 * @param user The user name 
	 * @param pwd The password of the user
	 */
	public FTPBridge(String host, String user, String pwd){
		Log.push("Trying FTP to host: "+host+" as "+user);
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		
		try {ftp.connect(host);}//Connection to the host.
		 catch (IOException e) {
			System.err.println("ERROR 2001 - Error during connection with the host");
		}
		
		int reply = ftp.getReplyCode();//Getting reply
		if (!FTPReply.isPositiveCompletion(reply)) {
			try {ftp.disconnect();} //Eventual negative reply generates error.
			catch (IOException e) {
				System.err.println("ERROR 2002 - Error during disconnection from the host");
			}	
		}
		
		try {ftp.login(user, pwd);} //Tries login.
		catch (IOException e) {
			System.err.println("ERROR 2003 - Error during login, check credential");
		}
		try {ftp.setFileType(FTP.BINARY_FILE_TYPE);} //Tries to set the file type to be transferred.
		catch (IOException e) {
			System.err.println("ERROR 2004 - Error during the setting of the file type to be transferred");
		}
		
		ftp.enterLocalPassiveMode();
	}

	
	
	/**
	 * Uploads a file into the host
	 * 
	 * 
	 * @param localFilePathName Path + name of the desired file to send.
	 * @param fileName The name of the file.
	 * @param hostDir Directory of the host where the file should be put
	 */
	public void uploadFile(String localFilePathName, String fileName, String hostDir) {
		try (InputStream input = new FileInputStream(new File(localFilePathName))) {
			this.ftp.storeFile(hostDir + fileName, input);
			//Stores a file on the server using the given name and taking input from the given InputStream.
					} catch (FileNotFoundException e) {
						System.err.println("ERROR 2011 - File to upload on the server not found");
					} catch (IOException e) {
						System.err.println("ERROR 2012 - Couldn't upload the file into the host");
					} 
	}

	
	
	/**
	 * If the current instance has an FTP connection, it is disconnected.
	 */
	public void disconnect() {
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				System.err.println("ERROR 2021 - Error during disconnection from the host");
			}
		}
	}
}
