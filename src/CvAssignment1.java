import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CvAssignment1 {
	
	public CvAssignment1() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static Mat add2Images(Mat img1, Mat img2) {
		int x = Math.min(img1.rows(), img2.rows());
		int y = Math.min(img1.cols(), img2.cols());
		System.out.println(img1.rows() + " Woman"+ img2.rows()+"Batman");
		System.out.println(img1.cols() + " Woman"+ img2.cols()+"Batman");

		Mat result = new Mat(x, y, CvType.CV_8UC3);		
		for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
					double[] rgb1 = img1.get(i, j);
					double[] rgb2 = img2.get(i, j);
					rgb1[0] += rgb2[0];
					rgb1[1] += rgb2[1];
					rgb1[2] += rgb2[2];
				result.put(i, j, rgb1);
			}
		}
		
		return result;
	}
	
	public Mat loadImage(String imagePath, int i) {
        Mat image = Imgcodecs.imread(imagePath, i);
        if (image.empty()) {
            System.out.println("Empty image: " + imagePath);
        }else {
        	System.out.println("Image Loaded: "+ imagePath);
        }
        return image;
	}
	
	public void saveImage(String imagePath, Mat matrix) {
		  Imgcodecs.imwrite(imagePath, matrix); 
	      System.out.println("Image Saved ............"); 
	}
	
}
