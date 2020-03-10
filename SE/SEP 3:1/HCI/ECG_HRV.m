clear;
close all;
load Lab_ECG_HRV.mat
fs = 100; % Hz
R_threshold = 150; %define threshold based on observing raw ECG 
t1 = 400; %select the start time of a segment (sec)
t2 = 800; %select the end time of a segment (sec)
t1_samp = t1*fs+1; % convert the start time to sample number
t2_samp = t2*fs; % convert the end time to sample number
ecg = val(1,t1_samp:t2_samp); 
t_ecg = (t1_samp:t2_samp)*(1/fs);

[pks,locs] = findpeaks(ecg,t_ecg,'MinPeakHeight',R_threshold);
hrv = 1000*(locs(2:end)-locs(1:end-1)); %make a milisec unit by x1000

figure; 
subplot(2,1,1); plot(t_ecg,ecg); hold on; plot(locs,pks,'*'); 
xlabel('Time(s)'); ylabel('Amplitude');
subplot(2,1,2); plot(locs(2:end),hrv); 
xlabel('Time(s)'); ylabel('HRV(ms)');
