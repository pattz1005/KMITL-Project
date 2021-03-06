function FillHole
%ImageInput = imread('images15.jpg');
%ImageInput = rgb2gray(ImageInput);
ImageInput = imread('coins.png');
imshow(ImageInput);
BWSobel = edge(ImageInput,'sobel');
BWCanny = edge(ImageInput,'canny');
figure;
imshowpair(BWSobel,BWCanny,'montage')
title('Sobel Filter                                   Canny Filter');

[~, threshold] = edge(ImageInput, 'sobel');
fudgeFactor = .5;
BWs = edge(ImageInput,'sobel', threshold * fudgeFactor);
figure; imshow(BWs);
title('Threshold');    

se90 = strel('line', 3, 90);
se0 = strel('line', 3, 0);

BWsdil = imdilate(BWs, [se90 se0]);
figure, imshow(BWsdil), title('dilated gradient mask');
BWErode = imerode(BWsdil,[se90 se0]);
figure, imshow(BWErode), title('eroded gradient mask');

BWdfill = imfill(BWsdil, 'holes');
figure, imshow(BWdfill);
title('binary image with filled holes');
