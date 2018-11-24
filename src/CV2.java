import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CV2 {
	
	public CV2() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public Mat loadImage(String imagePath, int i) {
        Mat image = Imgcodecs.imread(imagePath, i);
        if (image.empty()) {
            System.out.println("Empty image: " + imagePath);
        }else {
        	System.out.println("Image Loaded: "+ imagePath + ",  width: "+image.width()+",  height: "+image.height());
        }
        return image;
	}
	
	public void saveImage(String imagePath, Mat matrix) {
		  Imgcodecs.imwrite(imagePath, matrix); 
	      System.out.println("Image Saved ............"); 
	}
	
}
