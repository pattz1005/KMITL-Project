function VDOInput
VideoDT= VideoReader('VD32.avi');
nFrames = VideoDT.NumberOfFrames;
VDHeight = VideoDT.Height;
VDWidth = VideoDT.Width;

% Preallocate movie structure.
mov(1:nFrames) = ...
    struct('cdata', zeros(VDHeight, VDWidth, 3, 'uint8'),...
           'colormap', []);

% Read one frame at a time.
for k = 1 :nFrames
    mov(k).cdata = read(VideoDT, k);
    imshow(mov(k).cdata);
end

% Size a figure based on the video's width and height.
hf = figure;
set(hf, 'position', [200 200 VDWidth VDHeight])

% Play back the movie once at the video's frame rate.
movie(hf, mov, 1, VideoDT.FrameRate);