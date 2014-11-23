package DataObjects;

import java.awt.Image;

public class JpegInfo {
	public String Comment;
	public Image imageobj;
	public int imageHeight;
	public int imageWidth;
	public int BlockWidth[];
	public int BlockHeight[];

	// the following are set as the default
	public int Precision = 8;
	public int NumberOfComponents = 3;
	public float Components[][][];
	public int[] CompID = { 1, 2, 3 };
	public int[] HsampFactor = { 1, 1, 1 };
	public int[] VsampFactor = { 1, 1, 1 };
	public int[] QtableNumber = { 0, 1, 1 };
	public int[] DCtableNumber = { 0, 1, 1 };
	public int[] ACtableNumber = { 0, 1, 1 };
	public boolean[] lastColumnIsDummy = { false, false, false };
	public boolean[] lastRowIsDummy = { false, false, false };
	public int Ss = 0;
	public int Se = 63;
	public int Ah = 0;
	public int Al = 0;
	public int compWidth[], compHeight[];
	public int MaxHsampFactor;
	public int MaxVsampFactor;

	public JpegInfo(Image image) {
		Components = new float[NumberOfComponents][][];
		compWidth = new int[NumberOfComponents];
		compHeight = new int[NumberOfComponents];
		BlockWidth = new int[NumberOfComponents];
		BlockHeight = new int[NumberOfComponents];
		imageobj = image;
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		Comment = "JPEG Encoder Copyright 1998, James R. Weeks and BioElectroMech.  ";
	}

	public void setComment(String comment) {
		Comment.concat(comment);
	}

	public String getComment() {
		return Comment;
	}
}