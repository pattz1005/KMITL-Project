function ImageRegionprops

%ImageInput = imread('images15.jpg');
%ImageInput = rgb2gray(ImageInput);
ImageInput = imread('coins.png');
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

BWnobord = imclearborder(BWdfill, 4);
figure, imshow(BWnobord), title('cleared border image');

seD = strel('diamond',1);
BWfinal = imerode(BWnobord,seD);
BWfinal = imerode(BWfinal,seD);
figure, imshow(BWfinal), title('segmented image');

stats = regionprops(BWfinal,'Area','Centroid','PixelIdxList');
stats(2).Area

[L,n] = bwlabel(BWfinal);
ImageColor = label2rgb(L, @jet, [.5 .5 .5]);
%imshow(label2rgb(L, @jet, [.5 .5 .5]));
imshow(ImageColor);

%RGB = insertText(ImageColor,[2 50],['Total Coin:' num2str(n)],'FontSize',18);
RGB = insertText(ImageInput,[2 50],['Total Coin:' num2str(n)],'FontSize',18);
figure, imshow(RGB);

