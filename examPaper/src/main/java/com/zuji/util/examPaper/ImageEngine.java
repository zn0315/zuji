package com.zuji.util.examPaper;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageEngine {

	private static ImageEngine engine;
	
	private BufferedImage originalImage;
	private BufferedImage modifiedImage;
	
	static {
		engine = new ImageEngine();
	}
	
	public static ImageEngine getInstance() {
		return engine;
	}
	
	public void setImage(String fileName) throws IOException {
		originalImage = ImageIO.read(new File(fileName));
		int x = originalImage.getHeight();
        int y = originalImage.getWidth();
  	  
        modifiedImage =  new BufferedImage(y,x,BufferedImage.TYPE_BYTE_GRAY);
	}
	
	public BufferedImage erase(int depth) {
		byte clear[] = new byte[256];
   	  	for (int i=0; i<256; i++) {
   		  clear[i] = (byte)((i<depth)?i:255);
   	  	}
   	  	ByteLookupTable blut=new ByteLookupTable(0, clear);  
   	   	LookupOp lop = new LookupOp(blut, null);  
   	   	lop.filter(originalImage,modifiedImage);
   	   	return modifiedImage;
	}
}
