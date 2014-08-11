package images;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class ImageGenerator {
	public ImageGenerator(){
	}
	public BufferedImage getImage(int w, int h, String rEquation, String gEquation, String bEquation) {
		ClassLoader parent = getClass().getClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class rClass = loader.parseClass("public class RClass {public int getVal(x,y){${rEquation}}}");
		Class gClass = loader.parseClass("public class GClass {public int getVal(x,y){${gEquation}}}");
		Class bClass = loader.parseClass("public class BClass {public int getVal(x,y){${bEquation}}}");
		def r = rClass.newInstance();
		def g = gClass.newInstance();
		def b = bClass.newInstance();
		BufferedImage image = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
		int j = r.getVal(9,4);
		for (int x = 1; x <= w; x++){
			for (int y = 1; y <= h; y++){
				image.setRGB(x-1, y-1, getRGB(r.getVal(x,y), g.getVal(x,y), b.getVal(x,y)));
			}
		}
		return image;
	};
		
	private int getRGB(int r, int g, int b){
		return (r << 16) | (g << 8) | b;
	}
}

