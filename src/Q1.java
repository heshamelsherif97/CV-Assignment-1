import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Q1 extends CvAssignment1{
	public Mat applyBrightness1(Mat img, double value) {
		Mat result = new Mat(img.rows(), img.cols(), CvType.);
		for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = img.get(i, j);
//				rgb[0] /= 2;
//				rgb[1] /= 2;
//				rgb[2] /= 2;
				result.put(i, j, rgb);
				System.out.println(result.get(i, j)[1]+ ", Old: "+ img.get(i, j)[1]);
			}
		}
		return result;
	}
	
	
	public Mat applyContrast1(Mat img, double value) {
		Mat result = new Mat();
		
		return result;
	}
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q1 cv1 = new Q1();
		Mat img1 = cv1.loadImage("A1I/Q1I1.png", CvType.CV_32F);
		Mat img2 = cv1.loadImage("A1I/Q1I2.jpg", CvType.CV_32F);
		Mat img1Bright = cv1.applyBrightness1(img1, 0.0);
		Mat io = add2Images(img1, img2);
		//Mat img1Cont = cv1.applyContrast1(img1Bright, 50.0);
		cv1.saveImage("output/out1.jpg", img1Bright);
	}
}
