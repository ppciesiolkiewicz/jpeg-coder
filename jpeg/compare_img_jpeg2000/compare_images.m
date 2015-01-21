
jpeg2000 = imread('output.jp2');
j85 = imread('out85.jpg');
j100 = imread('out100.jpg');

orig = imread('original.bmp');

norm(double(j100./orig),2);
figure;
title('JPEG2000');
subplot(1,3,1);
imshow((orig-jpeg2000).^2);

title('Quality: 85');
subplot(1,3,2);
imshow((orig-j85).^2);


subplot(1,3,3);
imshow((orig-j100).^2);
title('Quality: 100');

[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,jpeg2000)
[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j85)
[PSNR,MSE,MAXERR,L2RAT] = measerr(orig,j100)



suptitle('Differences between original and compressed image');
