package GUI;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

class MyFilter extends FileFilter {
    final static String jpg = "jpg";
    final static String jp2 = "jp2";
    final static String bmp = "bmp";
    final static String png = "png";
    // Accept all directories and (gif || 
    // jpg || tiff) files.
    public boolean accept(File f) {
       if(f.isDirectory()) {
          return true;
       }
           String s = f.getName();
    int i = s.lastIndexOf('.');
    if(i > 0 &&  i < s.length() - 1) {
       String extension = 
          s.substring(i+1).toLowerCase();
             if (bmp.equals(extension) 
                || jp2.equals(extension) 
                || jpg.equals(extension)
             	|| png.equals(extension))
             	{
                    return true;
             } else {
                 return false;
             }
          };
       return false;
       }
     
    // The description of this filter
    public String getDescription() {
    return "Image Files (*.bmp, *.jpg, *.jp2,*.png)";
    }
 }