package com.zuji.util.examPaper;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;

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
		BufferedImage tmpImage = ImageIO.read(new File(fileName));
		int x = tmpImage.getHeight();
        int y = tmpImage.getWidth();

		originalImage = new BufferedImage(y,x,BufferedImage.TYPE_BYTE_GRAY);
		ColorConvertOp grayOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null);
		grayOp.filter(tmpImage, originalImage);
        modifiedImage =  new BufferedImage(y,x,BufferedImage.TYPE_BYTE_GRAY);
	}
	
	public javafx.scene.image.Image erase(int depth) {
		byte clear[] = new byte[256];
   	  	for (int i=0; i<256; i++) {
   		  clear[i] = (byte)((i<depth)?i:255);
   	  	}
   	  	ByteLookupTable blut=new ByteLookupTable(0, clear);  
   	   	LookupOp lop = new LookupOp(blut, null);  
   	   	lop.filter(originalImage,modifiedImage);
   	   	return SwingFXUtils.toFXImage(modifiedImage, null); // don't use null if have performance issue
   	   	//return SwingFXUtils.toFXImage(originalImage, null); // don't use null if have performance issue
	}
}
