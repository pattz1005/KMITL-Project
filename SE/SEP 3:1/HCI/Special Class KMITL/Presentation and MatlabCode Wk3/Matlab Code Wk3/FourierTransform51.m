function FourierTransform51
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
 
PSF=fspecial('gaussian',size(A),6);			%Define PSF
OTF=fft2(PSF); OTF=fftshift(OTF);		%Calculate corresponding OTF
rlow=(size(A,1)./2)-3; rhigh=(size(A,1)./2)+3;	%Define range to be altered
clow=(size(A,2)./2)-3; chigh=(size(A,2)./2)+3;
Fphase=angle(OTF); 				%Extract Fourer phase 
Fphase(rlow:rhigh,clow:chigh)=Fphase(rlow:rhigh,clow:chigh)+0.*pi.*rand;
						%Add random component to selected phase 
OTF=abs(OTF).*exp(i.*Fphase);			%Recombine phase and modulus
Afilt=ifft2(OTF.*FA); Afilt=fftshift(Afilt);		%Calculate filtered image
psfnew=abs(fftshift((otf2psf(OTF)))); 		%Calculate corresponding PSF
figure;
subplot(2,2,1);imshow(A,[]);  title('Original');
subplot(2,2,2); imagesc(log(1+psfnew)); axis image; axis off; colormap(gray);title('PSF');
subplot(2,2,3); imagesc(log(1+abs(OTF))); axis image; axis off;title('MTF*');
subplot(2,2,4); imagesc(abs(Afilt)); axis image; axis off;title('Result');
 
PSF=fspecial('motion',30,30);			%Define motion PSF
OTF=psf2otf(PSF,size(A)); OTF=fftshift(OTF);	%Calculate corresponding OTF
Afilt=ifft2(OTF.*FA); 				%Calculate filtered image
figure;
subplot(2,2,1);imshow(A,[]);  title('Original');
subplot(2,2,2); imshow(log(1+PSF),[]); title('Motion Blur PSF');
subplot(2,2,3); imshow(log(1+abs(OTF)),[]);title('MTF'); 
subplot(2,2,4); imshow(abs(Afilt),[]);title('Result');