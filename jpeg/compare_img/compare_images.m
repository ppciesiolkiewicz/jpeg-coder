j5 = imread('file5.jpg');
j25 = imread('file25.jpg');
j50 = imread('file50.jpg');
j75 = imread('file75.jpg');
j100 = imread('file100.jpg');

orig = imread('original.bmp');


figure;
subplot(2,2,1);
imshow(orig-j5);
title('Quality: 5');

subplot(2,2,2);
imshow(orig-j25);
title('Quality: 50');

subplot(2,2,3);
imshow(orig-j75);
title('Quality: 75');

subplot(2,2,4);
imshow(orig-j100);
title('Quality: 100');4

suptitle('Differences between original and compressed image');
