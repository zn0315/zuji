package com.zuji.util.examPaper;

import java.io.*;

public class Configuration {
	private File confFile;
	private String path;
	
	public Configuration() throws IOException {
		String fn = System.getProperty("java.io.tmpdir")+"/exampaper.conf";
		confFile = new File(fn);
		if (confFile.exists())
			path = getLastPath();
		else
			setDir("c:");
	}
	
	public String getDir() {
		return path;
	}
	
	public void setDir(String path) throws IOException {
		this.path = path;
		try (FileWriter out = new FileWriter(confFile);) {
			out.write(path);
		}
	}
	
	
	private String getLastPath() throws FileNotFoundException, IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(confFile))) {
			return in.readLine();
		}
	}
}
