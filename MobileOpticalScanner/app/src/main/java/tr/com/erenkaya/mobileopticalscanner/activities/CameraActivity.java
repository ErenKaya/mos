package tr.com.erenkaya.mobileopticalscanner.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;

public class CameraActivity extends AppCompatActivity  implements CameraBridgeViewBase.CvCameraViewListener2 {
    JavaCameraView javaCameraView;
    Mat mCanny;
    int width, height;
    Size size;
    private static final String TAG = "OCVSample::Activity";

    private CameraBridgeViewBase mOpenCvCameraView;
    private boolean              mIsJavaCamera = true;
    private MenuItem mItemSwitchCamera = null;

    private Button CaptureBtn;
    private Button mSavedBtn;

    private Mat                    mRgba;
    private Mat                    mThresholdMat;
    private Mat                    mBlurMat;
    private Mat                    mIntermediateMat;
    private Mat                    mGray;
    private boolean              mIsTouch = false;
    private MatOfPoint points;
    Mat hierarchy;
    private MatOfPoint2f approxCurve;

    int duration = Toast.LENGTH_SHORT;
    String mMessage = "Detected ";
    Mat temp_contour;
    List<MatOfPoint> contours;

    private Mat                    touchedRegionRgba;
    private Mat                    touched_mThresholdMat;
    private Mat                    touched_mBlurMat;
    private Mat                    touched_mIntermediateMat;
    private Mat                    touched_mGray;
    private MatOfPoint touched_points;
    private MatOfPoint2f touched_approxCurve;
    Mat touched_hierarchy;
    List<MatOfPoint> touched_contours;
    BaseLoaderCallback mBaseCallBackLoader = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    javaCameraView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        javaCameraView = (JavaCameraView) findViewById(R.id.opticalCameraView);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (javaCameraView != null) {
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (OpenCVLoader.initDebug()) {
            Log.d(TAG, "OpenCvLoader successfully Loaded");
            mBaseCallBackLoader.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.d(TAG, "OpenCvLoader not Loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this, mBaseCallBackLoader);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (javaCameraView != null) {
            javaCameraView.disableView();
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mCanny = new Mat(height, width, CvType.CV_8UC1);
        mGray = new Mat(height, width, CvType.CV_8UC1);
        size = new Size();
        hierarchy = new Mat();
        size.height = height;
        size.width = width;
        touched_hierarchy = new Mat();
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();
        touched_mIntermediateMat.release();
        hierarchy.release();
        touched_hierarchy.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();

    contours = new ArrayList<MatOfPoint>();
        hierarchy = new Mat();
        approxCurve = new MatOfPoint2f();
        mThresholdMat = new Mat();
        mBlurMat = new Mat();

        Imgproc.GaussianBlur(mRgba, mThresholdMat, new Size(3, 3), 0);
        Imgproc.Canny(mThresholdMat, mIntermediateMat, 80, 100);
        Imgproc.findContours(mIntermediateMat, contours, hierarchy,
                Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));

        hierarchy.release();

        if (contours.size() > 0) {

            for(int i = 0; i < contours.size(); i++)
            {
                MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());
                double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
                Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);


                if (!contours.get(i).empty()){

                    temp_contour = contours.get(i);

                    double contourArea = Imgproc.contourArea(temp_contour);

                    MatOfPoint mat = new MatOfPoint();
                    approxCurve.convertTo(mat, CvType.CV_32S);

                    if (contourArea < 500 || !Imgproc.isContourConvex(mat)) {
                        continue;
                    }

                    points = new MatOfPoint(approxCurve.toArray());

                    if (points.toArray().length == 3) {

                        Log.v("POINTS", String.valueOf(points.toArray()));

                        Imgproc.drawContours(mRgba, contours, i, new Scalar(227, 43, 51), 3);
                    } else if (points.toArray().length == 4) {

                        Log.v("POINTS", String.valueOf(points.toArray()));

                        Imgproc.drawContours(mRgba, contours, i, new Scalar(227, 43, 51), 3);
                    } else if (points.toArray().length == 5) {

                        Log.v("POINTS", String.valueOf(points.toArray()));

                        Imgproc.drawContours(mRgba, contours, i, new Scalar(227, 43, 51), 3);
                    } else if (points.toArray().length > 6) {

                        Log.v("POINTS", String.valueOf(points.toArray()));

                        Imgproc.drawContours(mRgba, contours, i, new Scalar(227, 43, 51), 3);
                    }

                }

            }
        }

        return mRgba;
    }


}