import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Q1 extends CvAssignment1{
	public Mat applyBrightness1(Mat img) {
		Mat result = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
		for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = img.get(i, j);
				rgb[0] += 50;
				rgb[1] += 50;
				rgb[2] += 50;
				result.put(i, j, rgb);
			}
		}
		return result;
	}
	
	
	public Mat applyContrast1(Mat img) {
		Mat result = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
		for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = img.get(i, j);
				if(j > result.cols()/2) {
					rgb[0] /= 1.1;
					rgb[1] /= 1.1;
					rgb[2] /= 1.1;
				}else {
					rgb[0] *= 5;
					rgb[1] *= 5;
					rgb[2] *= 5;
				}
				result.put(i, j, rgb);
			}
		}
		return result;
	}
	
	
	public Mat reflectImg(Mat img) {
		Mat result = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
		for (int i = 0; i < result.rows(); i++) {
			int k = result.cols()-1;
			for (int j = 0; j < result.cols()/2; j++) {
				double[] rgb = img.get(i, j);
				double[] rgbLast = img.get(i, k-j);
				result.put(i, k-j, rgb);
				result.put(i, j, rgbLast);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q1 cv1 = new Q1();
		Mat img1 = cv1.loadImage("A1I/Q1I1.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q1I2.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img1Bright = cv1.applyBrightness1(img1);
		Mat reflectedImg = cv1.reflectImg(img2);
		//img1Bright = cv1.applyContrast1(img1Bright);
		Mat io = add2Images(img1Bright, reflectedImg);
		//Mat img1Cont = cv1.applyContrast1(img1Bright, 50.0);
		cv1.saveImage("output/out1.jpg", io);
	}
}
