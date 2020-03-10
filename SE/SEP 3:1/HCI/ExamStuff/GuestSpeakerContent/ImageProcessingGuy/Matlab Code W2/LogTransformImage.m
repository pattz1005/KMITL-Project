function LogTransformImage
a1 = imread('mammography_800x600.jpg'); % Read the image
a = double(a1)/255; % Normalized Image
c = 2; % Constant
f = c*log(1 + (a)); % Log Transform
subplot(1,2,1),imshow(a1),title('Original Image');
subplot(1,2,2),imshow((f)),title('Log Transformation Image');
%imwrite(a1,'M:\1. SWU\5. Teaching\1. Bechalor Couse\1. First Semester\BME423 Pattern Recognition\1. Presentation 2018\Presentattion and MatlabCode Wk2\Input.jpg');
%imwrite(f,'M:\1. SWU\5. Teaching\1. Bechalor Couse\1. First Semester\BME423 Pattern Recognition\1. Presentation 2018\Presentattion and MatlabCode Wk2\LogTrandOutput.jpg');