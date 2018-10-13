import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Q1 extends CvAssignment1{
	
	
	public Mat applyBrightness1(Mat img) {
		Mat result = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
		for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = img.get(i, j);
					rgb[0] -= 50;
					rgb[1] -= 50;
					rgb[2] -= 50;
					result.put(i, j, rgb);
			}
		}
		return result;
	}
	
	
	public Mat applyContrast1(Mat img) {
		Mat result = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);
		for (int i = 0; i < result.rows(); i++) {
			double factor = 10 - 1/result.cols();
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = img.get(i, j);
				rgb[0] *= factor;
				rgb[1] *= factor;
				rgb[2] *= factor;
				factor -= 10/(double)result.cols();
				result.put(i, j, rgb);
			}
		}
		return result;
	}
	
	
	public Mat reflectImg(Mat img) {
		Mat result = new Mat();
		Core.flip(img, result, 1);
		return result;
	}
	
	public static Mat add2Images(Mat img1, Mat img2) {
		Mat result = new Mat(img1.rows(), img1.cols(), CvType.CV_8UC3);
		Mat resized = new Mat();
		Size oldSize = new Size(img1.cols(), img1.rows());
		Imgproc.resize(img2, resized, oldSize, 0, 0, Imgproc.INTER_AREA);
		for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double ratio1 = 0.0;
				double ratio2 = 1 - ratio1;
				double[] rgb1 = img1.get(i, j);
				double[] rgb2 = resized.get(i, j);
					ratio1 = 0.75;
					ratio2 = 0.25;
				rgb1[0] *= ratio1;
				rgb1[1] *= ratio1;
				rgb1[2] *= ratio1;
				rgb2[0] *= ratio2;
				rgb2[1] *= ratio2;
				rgb2[2] *= ratio2;
				rgb1[0] += rgb2[0];
				rgb1[1] += rgb2[1];
				rgb1[2] += rgb2[2];
				result.put(i, j, rgb1);
			}
		}
		
		return result;
	}
	
	
	public Mat translate(Mat img) {
		Mat out = new Mat();
		Mat trans = new Mat(2, 3, CvType.CV_32F);
		double [] data = {1, 0, 200, 0, 1, 0};
		trans.put(0, 0, data);
		Size size = new Size(img.width(), img.height());
		Imgproc.warpAffine(img, out, trans, size);
		return out;
	}
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q1 cv1 = new Q1();
		Mat img1 = cv1.loadImage("A1I/Q1I1.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q1I2.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img1Bright = cv1.applyBrightness1(cv1.applyContrast1(img1));
		Mat reflectedImg = cv1.reflectImg(img2);
		Mat translatedBat = cv1.translate(reflectedImg);
		Mat out = add2Images(img1Bright, cv1.applyBrightness1(translatedBat));
		cv1.saveImage("output/out1.jpg", out);
	}
}
