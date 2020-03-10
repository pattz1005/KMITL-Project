// Program to change between color and grayscale representations of an image using a GUI trackbar
// Author: Samarth Manoj Brahmbhatt, University of Pennsylvania

#include <iostream>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

using namespace std;
using namespace cv;

// Global variables
// Maximum slider value
const int slider_max = 1;
// Constantly updated slider value
int slider, i, j;
// Original image
Mat img;

// Callback function for trackbar event
void on_trackbar(int pos, void *)
{
	// Holds the image processed acording to value of slider
	Mat img_converted;
	// Convert color-spaces according to value of slider
	if (pos > 0) {
		cvtColor(img, img_converted, CV_BGR2GRAY);
		for (i = 0; i < 200; i++) {
			for (j = 0; j < 200; j++) {
				img.at<uchar>(i, j) = 255;
			}
		}
		imwrite("Fig1ResutGray.jpg", img_converted);

	}
	else {
		for (i = 0; i < 200; i++) {
			for (j = 0; j < 200; j++) {
				img.at<Vec3b>(i, j)[0] = 255;
				img.at<Vec3b>(i, j)[1] = 200;
				img.at<Vec3b>(i, j)[2] = 0;
			}
		}
		img_converted = img;
		imwrite("Fig1ResutRGB.jpg", img_converted);
	}
	imshow("Trackbar app", img_converted);
}

int main()
{
	img = imread("Fig1.jpg");


	namedWindow("Trackbar app");
	imshow("Trackbar app", img);

	// Initial value of slider
	slider = 0;

	// Create the trackbar
	createTrackbar("RGB <-> Grayscale", "Trackbar app", &slider, slider_max, on_trackbar);
	
	// Press 'q' to exit
	while(char(waitKey(1)) != 'q') {}
	
	return 0;
}
