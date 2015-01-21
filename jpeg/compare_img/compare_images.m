j5 = imread('file5.jpg');
j25 = imread('file25.jpg');
j50 = imread('file50.jpg');
j75 = imread('file75.jpg');
j100 = imread('file100.jpg');

orig = imread('original.bmp');


figure;
subplot(2,2,1);
imshow((orig-j5).^2);
title('Quality: 5');

subplot(2,2,2);
imshow((orig-j50).^2);
title('Quality: 50');

subplot(2,2,3);
imshow((orig-j75).^2);
title('Quality: 75');

subplot(2,2,4);
imshow((orig-j100).^2);
title('Quality: 100');

suptitle('Differences between original and compressed image');


[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j5)
[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j25)
[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j50)
[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j75)
[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j100)