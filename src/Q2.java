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
		
	public static Mat q2o1(Mat img1, Mat img2) {
	      Point p1 = new Point( 0,0 );
	      Point p2 = new Point( img1.cols() - 1, 0 );
	      Point p3 = new Point( 0, img1.rows() - 1 );
	      Point p4 = new Point( 1219, 378 );
	      Point p5 = new Point( 1310, 378 );
	      Point p6 = new Point( 1219, 515 );
	      
	      MatOfPoint2f ma1 = new MatOfPoint2f(p1,p2,p3);
	      MatOfPoint2f ma2 = new MatOfPoint2f(p4,p5,p6);
	      
	      Mat tranformMatrix = Imgproc.getAffineTransform(ma1,ma2);

	      // Creating object of the class Size
	      Size size = new Size(img2.width(), img2.height());
	      
	      Mat result= new Mat();
	      // Applying Wrap Affine
	      Imgproc.warpAffine(img1, result, tranformMatrix, size);
	      
	      
	      for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = result.get(i, j);
				double[] rgb2 = img2.get(i, j);
				if(rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0) {
					img2.put(i, j, rgb2);
				}else {
					img2.put(i, j, rgb);
				}
			}
		}
	      return img2;
	}
	
	public static Mat q2o2(Mat img1, Mat img2) {
	      Point p1 = new Point( 0,0 );
	      Point p2 = new Point( img1.cols() - 1, 0 );
	      Point p3 = new Point( 0, img1.rows() - 1 );
	      Point p4 = new Point( 372, 94 );
	      Point p5 = new Point( 705, 128 );
	      Point p6 = new Point( 328, 526 );
	      
	      MatOfPoint2f ma1 = new MatOfPoint2f(p1,p2,p3);
	      MatOfPoint2f ma2 = new MatOfPoint2f(p4,p5,p6);
	      
	      Mat tranformMatrix = Imgproc.getAffineTransform(ma1,ma2);

	      Size size = new Size(img2.width(), img2.height());
	      
	      Mat result= new Mat();
	      
	      Imgproc.warpAffine(img1, result, tranformMatrix, size);
	      

	      for (int i = 0; i < result.rows(); i++) {
			for (int j = 0; j < result.cols(); j++) {
				double[] rgb = result.get(i, j);
				double[] rgb2 = img2.get(i, j);
				if(rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0) {
					img2.put(i, j, rgb2);
				}else {
					img2.put(i, j, rgb);
				}
			}
		}
	      return img2;
	}
	
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Q2 cv1 = new Q2();
		Mat img1 = cv1.loadImage("A1I/Q2I1.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q2I2.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img3 = cv1.loadImage("A1I/Q2I3.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);

		cv1.saveImage("output/outQ2-O1.jpg", q2o1(img1, img2));
		cv1.saveImage("output/outQ2-O2.jpg", q2o2(img1, img3));
	}
}
