import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Q3 extends CvAssignment1{
	
	public static Mat add2Images(Mat img1, Mat img2) {
		int x = Math.min(img1.rows(), img2.rows());
		int y = Math.min(img1.cols(), img2.cols());
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
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q3 cv1 = new Q3();
		Mat img1 = cv1.loadImage("A1I/Q2I1.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q3I1.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat out = add2Images(img1, img2);
		cv1.saveImage("output/out3.jpg", out);
	}
}
