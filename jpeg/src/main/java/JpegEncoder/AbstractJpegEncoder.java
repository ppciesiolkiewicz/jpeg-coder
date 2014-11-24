package JpegEncoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DataObjects.JpegInfo;
import DataObjects.Tile;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageToYCbCrArray;
import JpegMath.Quantiziers.JpegUniformQuantizier;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;

public abstract class AbstractJpegEncoder implements EncoderInterface {
	Integer quality;
	FileOutputStream fileOutput;
	HuffmanCoding encoder;
	JpegUniformQuantizier quant;

	public AbstractJpegEncoder(Integer quality_) {
		quality = quality_;
		try {
			fileOutput = new FileOutputStream("output.jpg");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		encoder = new HuffmanCoding();
		quant = new JpegUniformQuantizier(quality);
	}

	public BufferedImage encode(BufferedImage img) {
		JpegInfo info = new JpegInfo(img);
		WriteHeaders(fileOutput, info);

		// [color component][tile y position][tile x position]
		Tile[][][] tiles = preprocessing(img);
		System.out.println(tiles[0][0][0].toString() + "\n\n\n");
		List<List<Tile<Double>>> transformedTiles = transform(tiles);
		System.out
				.println(transformedTiles.get(0).get(0).toString() + "\n\n\n");
		List<List<Tile<Integer>>> quantiziedTiles = quantize(transformedTiles);
		System.out.println(quantiziedTiles.get(0).get(0).toString() + "\n\n\n");
		ByteArrayOutputStream os = entropyCoding(quantiziedTiles);

		try {
			os.writeTo(fileOutput);
			WriteEOI(fileOutput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Iterator<EncodedTile<Binary>> it = encodedTiles.iterator();
		// while(it.hasNext())
		// System.out.println(it.next().tile.toString()+"\n");
		// this image part probably will not be used
		// img = compose(tiles);
		return img;
	}

	protected Tile<Integer>[][][] preprocessing(BufferedImage image) {
		Integer[][][] subpixels = image2Array(image);
		Tile<Integer>[][][] tiles = tile(subpixels);
		return tiles;
	}

	private Integer[][][] image2Array(BufferedImage image) {
		ImageToArrayConverterInterface image2Array = new ImageToYCbCrArray();
		Integer[][][] subpixels = image2Array.convert(image);
		return subpixels;
	}

	protected Tile<Integer>[][][] tile(Integer[][][] subpixels) {
		TilerInterface<Integer> tiler = new JpegTiler();
		Tile<Integer>[][][] tiles = (Tile<Integer>[][][]) new Tile[3][][];
		int component = 0;
		// for Y[][], Cb[][], Cr[][]
		for (Integer[][] componentArray : subpixels)
			tiles[component++] = tiler.tile(componentArray);

		return tiles;
	}

	protected abstract List<List<Tile<Double>>> transform(
			Tile<Integer>[][][] tiles);

	protected List<List<Tile<Integer>>> quantize(List<List<Tile<Double>>> tiles) {
		List<List<Tile<Integer>>> out = new ArrayList<List<Tile<Integer>>>();
		for (List<Tile<Double>> t : tiles) {
			List<Tile<Integer>> comp = new ArrayList<Tile<Integer>>();
			
			Integer tableNo = 0;
			for (Tile<Double> component : t) {
				comp.add(quant.quantize(component, tableNo));
				tableNo = 1; //2nd. and 3rd. component should be quantizied with chrominance table
			}

			out.add(comp);
		}

		return out;

	}

	protected ByteArrayOutputStream entropyCoding(
			List<List<Tile<Integer>>> tiles) {
		return encoder.encodeImage(tiles);
	}

	// TODO ++JpegInfo?
	protected BufferedImage compose(Tile[][][] tiles) {
		/*
		 * WritableRaster raster = mergeToRaster(tiles); addHeader(raster);
		 * //dunnos dunnos dunnos
		 */
		return null;
	}

	public void setQuality(Integer quality_) {
		quality = quality_;
	}

	public Integer getQuality() {
		return quality;
	}

	/******** CHEATS ************/
	public void WriteHeaders(FileOutputStream fileOutput2, JpegInfo JpegObj) {
		int i, j, index, offset, length;
		int tempArray[];

		// the SOI marker
		byte[] SOI = { (byte) 0xFF, (byte) 0xD8 };
		WriteMarker(SOI, fileOutput2);

		// The order of the following headers is quiet inconsequential.
		// the JFIF header
		byte JFIF[] = new byte[18];
		JFIF[0] = (byte) 0xff;
		JFIF[1] = (byte) 0xe0;
		JFIF[2] = (byte) 0x00;
		JFIF[3] = (byte) 0x10;
		JFIF[4] = (byte) 0x4a;
		JFIF[5] = (byte) 0x46;
		JFIF[6] = (byte) 0x49;
		JFIF[7] = (byte) 0x46;
		JFIF[8] = (byte) 0x00;
		JFIF[9] = (byte) 0x01;
		JFIF[10] = (byte) 0x00;
		JFIF[11] = (byte) 0x00;
		JFIF[12] = (byte) 0x00;
		JFIF[13] = (byte) 0x01;
		JFIF[14] = (byte) 0x00;
		JFIF[15] = (byte) 0x01;
		JFIF[16] = (byte) 0x00;
		JFIF[17] = (byte) 0x00;
		WriteArray(JFIF, fileOutput2);

		// Comment Header
		String comment = "A";
		length = comment.length();
		byte COM[] = new byte[length + 4];
		COM[0] = (byte) 0xFF;
		COM[1] = (byte) 0xFE;
		COM[2] = (byte) ((length >> 8) & 0xFF);
		COM[3] = (byte) (length & 0xFF);
		//java.lang.System.arraycopy(JpegObj.Comment.getBytes(), 0, COM, 4,
		//		JpegObj.Comment.length());
		//WriteArray(COM, fileOutput2);

		// The DQT header
		// 0 is the luminance index and 1 is the chrominance index
		byte DQT[] = new byte[134];
		DQT[0] = (byte) 0xFF;
		DQT[1] = (byte) 0xDB;
		DQT[2] = (byte) 0x00;
		DQT[3] = (byte) 0x84;
		offset = 4;

		Tile<Integer> quantLum = quant.getQuantumLuminance();
		Iterator<Integer> it = quantLum.zigZagIterator();
		DQT[offset++] = (byte) ((0 << 4));
		for (j = 0; j < 64; j++) {
			DQT[offset++] = (byte) it.next().intValue();
		}

		Tile<Integer> quantChr = quant.getQuantumChrominance();
		it = quantChr.zigZagIterator();
		DQT[offset++] = (byte) ((0 << 4) + 1);
		for (j = 0; j < 64; j++) {
			DQT[offset++] = (byte) it.next().intValue();
		}
		

		WriteArray(DQT, fileOutput2);

		// Start of Frame Header
		byte SOF[] = new byte[19];
		SOF[0] = (byte) 0xFF;
		SOF[1] = (byte) 0xC0;
		SOF[2] = (byte) 0x00;
		SOF[3] = (byte) 17;
		SOF[4] = (byte) JpegObj.Precision;
		SOF[5] = (byte) ((JpegObj.imageHeight >> 8) & 0xFF);
		SOF[6] = (byte) ((JpegObj.imageHeight) & 0xFF);
		SOF[7] = (byte) ((JpegObj.imageWidth >> 8) & 0xFF);
		SOF[8] = (byte) ((JpegObj.imageWidth) & 0xFF);
		SOF[9] = (byte) JpegObj.NumberOfComponents;
		index = 10;
		for (i = 0; i < SOF[9]; i++) {
			SOF[index++] = (byte) JpegObj.CompID[i];
			SOF[index++] = (byte) ((JpegObj.HsampFactor[i] << 4) + JpegObj.VsampFactor[i]);
			SOF[index++] = (byte) JpegObj.QtableNumber[i];
		}
		WriteArray(SOF, fileOutput2);

		// The DHT Header
		byte DHT1[], DHT2[], DHT3[], DHT4[];
		int bytes, temp, oldindex, intermediateindex;
		length = 2;
		index = 4;
		oldindex = 4;
		DHT1 = new byte[17];
		DHT4 = new byte[4];
		DHT4[0] = (byte) 0xFF;
		DHT4[1] = (byte) 0xC4;
		for (i = 0; i < 4; i++) {
			bytes = 0;
			DHT1[index++ - oldindex] = (byte) ((int[]) encoder.bits
					.elementAt(i))[0];
			for (j = 1; j < 17; j++) {
				temp = ((int[]) encoder.bits.elementAt(i))[j];
				DHT1[index++ - oldindex] = (byte) temp;
				bytes += temp;
			}
			intermediateindex = index;
			DHT2 = new byte[bytes];
			for (j = 0; j < bytes; j++) {
				DHT2[index++ - intermediateindex] = (byte) ((int[]) encoder.val
						.elementAt(i))[j];
			}
			DHT3 = new byte[index];
			java.lang.System.arraycopy(DHT4, 0, DHT3, 0, oldindex);
			java.lang.System.arraycopy(DHT1, 0, DHT3, oldindex, 17);
			java.lang.System.arraycopy(DHT2, 0, DHT3, oldindex + 17, bytes);
			DHT4 = DHT3;
			oldindex = index;
		}
		DHT4[2] = (byte) (((index - 2) >> 8) & 0xFF);
		DHT4[3] = (byte) ((index - 2) & 0xFF);
		WriteArray(DHT4, fileOutput2);

		// Start of Scan Header
		byte SOS[] = new byte[14];
		SOS[0] = (byte) 0xFF;
		SOS[1] = (byte) 0xDA;
		SOS[2] = (byte) 0x00;
		SOS[3] = (byte) 12;
		SOS[4] = (byte) 3;// JpegObj.NumberOfComponents;
		index = 5;
		for (i = 0; i < SOS[4]; i++) {
			SOS[index++] = (byte) JpegObj.CompID[i];
			SOS[index++] = (byte) ((JpegObj.DCtableNumber[i] << 4) + JpegObj.ACtableNumber[i]);
		}
		SOS[index++] = (byte) 0;
		SOS[index++] = (byte) 63;
		SOS[index++] = (byte) ((0 << 4) + 0);
		WriteArray(SOS, fileOutput2);
	}

	void WriteMarker(byte[] data, FileOutputStream out) {
		try {
			out.write(data, 0, 2);
		} catch (IOException e) {
			// TODO
			System.out.println("IO Error: " + e.getMessage());
		}
	}

	void WriteArray(byte[] data, FileOutputStream out) {
		int length;
		try {
			length = ((data[2] & 0xFF) << 8) + (data[3] & 0xFF) + 2;
			out.write(data, 0, length);
		} catch (IOException e) {
			// TODO
			System.out.println("IO Error: " + e.getMessage());
		}
	}

	public void WriteEOI(FileOutputStream out) {
		byte[] EOI = { (byte) 0xFF, (byte) 0xD9 };
		WriteMarker(EOI, out);
	}

}
