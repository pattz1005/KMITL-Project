function WebcamInput
cam = webcam(1) % Defaul input number of webcam
%preview(cam)
figure
hold on
for idx = 1:1000
   % ????????????????????????? snapshot
    InputImage = snapshot(cam);
    imshow(InputImage);
end
clear('cam');