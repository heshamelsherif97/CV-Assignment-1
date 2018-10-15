import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Q1 extends CvAssignment1{
	
	
	public Mat applyBrightness1(Mat img, double contrast, double brightness) {
		Mat result = new Mat(img.rows(), img.cols(), img.type());
		for (int i = 0; i < result.rows(); i++) {
			double newContrast;
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = img.get(i, j);
				newContrast = contrast  - (contrast * (double)((double)j/(double)(result.cols()-1)));
					rgb[0] = rgb[0]*newContrast + brightness;
					rgb[1] = rgb[1]*newContrast + brightness;
					rgb[2] = rgb[2]*newContrast + brightness;
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
		Mat result = new Mat();
		double alpha = 0.8;
		Core.addWeighted(img1, alpha, img2, 1.0 - alpha, 0, result);
		return result;
	}
	public Mat translate(Mat img, int tx, int ty) {
		Mat out = new Mat();
		Mat trans = new Mat(2, 3, CvType.CV_32F);
		double [] data = {1, 0, tx, 0, 1, ty};
		trans.put(0, 0, data);
		Size size = new Size(img.width(), img.height());
		Imgproc.warpAffine(img, out, trans, size);
		return out;
	}
	
	public static Mat scaleImg(Mat img, Mat src) {
		  Mat result = new Mat();
	      Size size = new Size(src.width(), src.height());
	      Imgproc.resize(img, result, size, 0, 0, Imgproc.INTER_AREA);
	      return result;
	}
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q1 cv1 = new Q1();
		Mat img1 = cv1.loadImage("A1I/Q1I1.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q1I2.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Imgproc.resize(img2, img2, new Size(img1.width(), img1.height()), 0, 0, Imgproc.INTER_AREA);
		img1 = cv1.applyBrightness1(img1, 5, 0);
		img2 = cv1.translate(scaleImg(cv1.reflectImg(img2), img1), 150, 0);
		cv1.saveImage("output/outQ1-O1.jpg", add2Images(img1, img2));
	}
}
