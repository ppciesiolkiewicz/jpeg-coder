package DataObjects;

import java.awt.Image;


public class JpegInfo {
    public String comment;
    public int imageHeight;
    public int imageWidth;
    
    public int quality;
    
    public int precision = 8;
    public int numberOfComponents = 3;
    
    public JpegInfo(Image image, int quality_) {
    	quality = quality_;
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
        comment = "PCMSKRUSWKSH jpeg encoder";
    }
}
