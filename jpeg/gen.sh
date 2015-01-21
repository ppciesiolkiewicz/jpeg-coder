
for (( i=1; $i <= 100; i++ )) ; do
    java -jar jpeg.jar encode -i example_bmp/example1_part.bmp -o "filename"$i".jpg" -q $i > /dev/null
    ls -lvA "filename"$i".jpg"
    rm "filename"$i".jpg"

done
