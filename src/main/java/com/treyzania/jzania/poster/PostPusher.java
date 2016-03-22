package com.treyzania.jzania.poster;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostPusher {
	
	public URL submissionAddress;
	
	public PostPusher(String address) {
		
		try {
			
			this.submissionAddress = new URL(address);
			
			System.out.println("Logging set up to log to " + this.submissionAddress.getHost());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String post(Throwable t) {
		
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		
		return this.post(sw.toString());
		
	}
	
	public String post(String... lines) {
		
		StringBuilder sb = new StringBuilder(lines[0]);
		for (int i = 1; i < lines.length; i++)
			sb.append("\n" + lines[i]);
			
		return this.post(sb.toString());
		
	}
	
	public String post(String data) {
		return this.post(data.getBytes());
	}
	
	public String post(byte[] data) {
		
		try {
			
			HttpURLConnection con = (HttpURLConnection) this.submissionAddress.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "PostPusher by Treyzania"); // Shhhh...
			con.setRequestProperty("Content-Type", "text/plain");
			con.setRequestProperty("Content-Length", Integer.toString(data.length));
			
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			con.connect();
			
			OutputStream os = con.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
			
			return readInputStreamToString(con.getInputStream());
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private static String readInputStreamToString(InputStream is) throws IOException {
		
		BufferedInputStream bis = new BufferedInputStream(is);
		
		byte[] buffer = new byte[1024]; // If you handle larger data use a bigger buffer size
		
		// I don't really know what this is for. But I need it.
		@SuppressWarnings("unused") int read;
		
		StringBuilder sb = new StringBuilder();
		
		while ((read = bis.read(buffer)) != -1) {
			sb.append((new String(buffer)).trim());
		}
		
		return sb.toString();
		
	}
	
}
