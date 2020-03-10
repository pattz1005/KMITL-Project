function FourierTransformWK3
% Example Matlab script as provided with textbook:
%
%  Fundamentals of Digital Image Processing: A Practical Approach with Examples in Matlab
%  Chris J. Solomon and Toby P. Breckon, Wiley-Blackwell, 2010
%  ISBN: 0470844736, DOI:10.1002/9780470689776, http://www.fundipbook.com
%
A=imread('BBC_grey_testcard.png'); 		%Read in test card image
FA=fft2(A); 
FA=fftshift(FA);			%Take FFT and centre it
PSF=fspecial('gaussian',size(A),6);			%Define PSF
OTF=fft2(PSF); OTF=fftshift(OTF);		%Calculate corresponding OTF
figure;
Afilt=ifft2(OTF.*FA); Afilt=fftshift(Afilt);		%Calculate filtered image
subplot(2,2,1);imshow(A,[]);  colormap(gray);title('Original');	%Display Results
subplot(2,2,2); imagesc(log(1+(PSF))); axis image; axis off;title('Gaussian PSF'); 
subplot(2,2,3); imagesc(log(1+abs(OTF))); axis image; axis off;title('MTF PSF');
subplot(2,2,4); imagesc(abs(Afilt)); axis image; axis off;title('Result');

% Try to use imshow instread of imagesc
PSFImg = PSF/(max(max(PSF)));
PSFImgLog = log(1+(PSFImg));
PSFImgLog = (PSFImgLog/(max(max(PSFImgLog))))*255;
figure, 
subplot(1,2,1);imagesc(log(1+(PSF))); colormap(gray); axis image; axis off;title('Gaussian PSF');
subplot(1,2,2);imshow(uint8(PSFImgLog));title('Gaussian PSFImg');

%compare difference result from filter type
FilterImg = imread('Filter1.jpg');
FilterImg = rgb2gray(FilterImg);
FilterImg = imbinarize(FilterImg);
Afilt=ifft2(FilterImg.*FA);
result = abs(Afilt);
maxresult = max(max(result));
Filter1Img = uint8(result*255/maxresult);

FilterImg = imread('Filter2.jpg');
FilterImg = rgb2gray(FilterImg);
FilterImg = imbinarize(FilterImg);
Afilt=ifft2(FilterImg.*FA);
result = abs(Afilt);
maxresult = max(max(result));
Filter2Img = uint8(result*255/maxresult);

FilterImg = imread('Filter3.jpg');
FilterImg = rgb2gray(FilterImg);
FilterImg = imbinarize(FilterImg);
Afilt=ifft2(FilterImg.*FA);
result = abs(Afilt);
maxresult = max(max(result));
Filter3Img = uint8(result*255/maxresult);

FilterImg = imread('Filter4.jpg');
FilterImg = rgb2gray(FilterImg);
FilterImg = imbinarize(FilterImg);
Afilt=ifft2(FilterImg.*FA);
result = abs(Afilt);
maxresult = max(max(result));
Filter4Img = uint8(result*255/maxresult);

figure, subplot(2,2,1),imshow(Filter1Img), title('Filter 1');
subplot(2,2,2),imshow(Filter2Img), title('Filter 2');
subplot(2,2,3),imshow(Filter3Img), title('Filter 3');
subplot(2,2,4),imshow(Filter4Img), title('Filter 4');




