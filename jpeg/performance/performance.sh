for (( i=10; $i >= 1; i )) ; do
	echo BMP to JP2 measurments $(($i-1))
	time -p (java -jar ../jpeg.jar encode -i A"$i".bmp -o jp2/bmp2jp2_"$i".jp2 > /dev/null);

	echo BMP to JPEG measurments $(($i-1))
	time -p (java -jar ../jpeg.jar encode -i A"$i".bmp -o JPEG/bmp2jpeg_"$i".jpeg > /dev/null);

	echo JPEG to bmp measurments $(($i-1))
	time -p (java -jar ../jpeg.jar decode -i JPEG/bmp2jpeg_"$i".jpeg -o JPEGtoBMP/A"$i".bmp  > /dev/null);

	echo jp2 to BMP measurments $(($i-1))
	time -p (java -jar ../jpeg.jar decode -i jp2/bmp2jp2_"$i".jp2 -o jp2toBMP/A"$i".bmp  > /dev/null);
done
