import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Q2 extends CvAssignment1{
	
	
	public static Mat add2Images1(Mat img1, Mat img2) {	
		for (int i = 0; i < img1.rows(); i++) {
			for (int j = 0; j < img1.cols(); j++) {
				double [] rgb = img1.get(i, j);
				img2.put(376+i, 1218+j, rgb);
			}		
		}
		return img2;
	}
	
	public static Mat add2Images2(Mat img1, Mat img2) {	
		for (int i = 0; i < img1.rows(); i++) {
			for (int j = 0; j < img1.cols(); j++) {
				double [] rgb = img1.get(i, j);
				img2.put(i+95, j+370, rgb);
			}		
		}
		return img2;
	}
	
	public static Mat scale1(Mat img, int x, int y) {	
	    // Creating an empty matrix to store the result
	      Mat out = new Mat();
	      // Creating the Size object
	      Size size = new Size(x, y);
	      // Scaling the Image
	      Imgproc.resize(img, out, size, 0, 0, Imgproc.INTER_AREA);
	      return out;
	}
	
	public static Mat translate(Mat img, double tx, double ty) {
		Mat out = new Mat();
		Mat trans = new Mat(2, 3, CvType.CV_32F);
		double [] data = {1, 0, tx, 0, 1, ty};
		trans.put(0, 0, data);
		Size size = new Size(img.width(), img.height());
		Imgproc.warpAffine(img, out, trans, size);
		return out;
	}
		
	public static Mat rotate1(Mat img) {
        int newWidth = 2160;
        int newHeight = 3820;
        Mat out = new Mat(newHeight, newWidth, img.type());
        for (int i = 0; i < img.rows(); i++) {
			for (int j = 0; j < img.cols(); j++) {
				double [] rgb = img.get(i, j);
				out.put(i, j, rgb);
			}
		}
          out = translate(out, (double)newWidth/2-img.width()/2, (double)newHeight/2-img.height()/2);
	      // Creating a Point object
	      Point point = new Point((double)(newWidth)/2, (double)newHeight/2);

	      // Creating the transformation matrix M
	      Mat rotationMatrix = Imgproc.getRotationMatrix2D(point, 5, 1);
	      
	      
	      //Size
	      Size size = new Size(newWidth, newHeight);
	      
	      // Rotating the given image
	      Imgproc.warpAffine(out, out, rotationMatrix, size);
	      return out;
	}
	
	
	//370,95
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q2 cv1 = new Q2();
		Mat img1 = cv1.loadImage("A1I/Q2I1.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q2I2.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img3 = cv1.loadImage("A1I/Q2I3.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat out1 = add2Images1(scale1(img1, 92, 141), img2);
		
		cv1.saveImage("output/outQ2O1.jpg", out1);
		cv1.saveImage("output/outQ2O2.jpg", rotate1(img3));
	}
}
