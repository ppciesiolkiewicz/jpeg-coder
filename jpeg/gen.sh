
java -jar jpeg.jar encode -i example_bmp/example1.bmp -o "file5.jpg" -q 5 > /dev/null
for (( i=1; $i <= 4; i++ )) ; do
    java -jar jpeg.jar encode -i example_bmp/example1.bmp -o "file"$(($i*25))".jpg" -q $(($i*25)) > /dev/null
    #ls -lvA "filename"$i".jpg"
    #rm "filename"$i".jpg"
done
