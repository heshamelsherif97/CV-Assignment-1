import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Q3 extends CvAssignment1{
	
	public static Mat add2Images(Mat img1, Mat img2) {
	      Point p1 = new Point( 0,0 );
	      Point p2 = new Point( img1.cols() - 1, 0 );
	      Point p3 = new Point( 0, img1.rows() - 1 );
	      Point p4 = new Point( img1.cols() - 1, img1.rows() - 1 );
	      
	      
	      Point p5 = new Point( 163, 35 );
	      Point p6 = new Point( 470, 70 );
	      Point p7 = new Point( 158, 390 );
	      Point p8 = new Point( 465, 353 );
	      
	      MatOfPoint2f ma1 = new MatOfPoint2f(p1,p2,p3,p4);
	      MatOfPoint2f ma2 = new MatOfPoint2f(p5,p6,p7,p8);
	      Mat homography = Calib3d.findHomography(ma1, ma2);
	      
	      Mat result = new Mat();
	      // Warp source image to destination based on homography
	      Imgproc.warpPerspective(img1, result, homography, img2.size());
	      
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
		Q3 cv1 = new Q3();
		Mat img1 = cv1.loadImage("A1I/Q2I1.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = cv1.loadImage("A1I/Q3I1.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat out = add2Images(img1, img2);
		cv1.saveImage("output/outQ3-O1.jpg", out);
	}
}
