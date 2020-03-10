function EdgeDetection
%ImageInput = imread('images15.jpg');
%ImageInput = rgb2gray(ImageInput);
ImageInput = imread('coins.png');
imshow(ImageInput);
BWSobel = edge(ImageInput,'sobel');
BWCanny = edge(ImageInput,'Canny');
figure;
imshowpair(BWSobel,BWCanny,'montage')
title('Sobel Filter                                   Canny Filter');

[~, threshold] = edge(ImageInput, 'sobel');
fudgeFactor = .1;
BWs = edge(ImageInput,'sobel', threshold * fudgeFactor);
figure; imshow(BWs);
title('Threshold');    