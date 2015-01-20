package GUI;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.ComponentOrientation;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import javax.swing.border.BevelBorder;


public class MainWIndow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmJpegEncoderdecoder;
	private JTextField textQuality;
	private JLabel photoLabel;
	private JTextField textImagePath;
	private JButton btnConvertToBmp;
	private JButton btnConvertToJpeg;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWIndow window = new MainWIndow();
					window.frmJpegEncoderdecoder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWIndow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJpegEncoderdecoder = new JFrame();
		frmJpegEncoderdecoder.setTitle("JPEG 2000 Encoder/Decoder");
		frmJpegEncoderdecoder.setBounds(100, 100, 564, 583);
		frmJpegEncoderdecoder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textQuality = new JTextField();
		textQuality.setEditable(false);
		textQuality.setColumns(10);
		textQuality.setText("100");
		
		textImagePath = new JTextField();
		textImagePath.setColumns(10);
		
		JButton btnLoadImage = new JButton("Load");
		btnLoadImage.setAlignmentY(Component.TOP_ALIGNMENT);
		btnLoadImage.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnLoadImage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btnLoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new MyFilter());
		   
				if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					File plikObrazek = fc.getSelectedFile();
							JOptionPane.showMessageDialog(null, "Wybrany plik to " + plikObrazek.getName());
							textImagePath.setText(plikObrazek.getPath());
				try {
					File file = new File(plikObrazek.getAbsolutePath());
					BufferedImage bi = ImageIO.read(file);
					//photoLabel.setSize(bi.getWidth(),bi.getHeight());
					ImageIcon imgIcon = new ImageIcon(bi);
					photoLabel.setIcon(imgIcon);
				
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		btnConvertToJpeg = new JButton("Convert to JPEG");
		btnConvertToJpeg.setPreferredSize(new Dimension(100, 26));
		btnConvertToJpeg.setAlignmentY(Component.TOP_ALIGNMENT);
		btnConvertToJpeg.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnConvertToJpeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					File plikZapisuObrazka = fc.getSelectedFile();
							JOptionPane.showMessageDialog(null, "Zapisane do pliku" + plikZapisuObrazka.getName());
				}
			}
		});
		
		btnConvertToBmp = new JButton("Convert to BMP");
		btnConvertToBmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnConvertToBmp.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton btnConvertToJpeg_1 = new JButton("Convert to JPEG2000");
		
		JLabel lblQualitydefault = new JLabel("Image quality [%]:");
		
		
		final JSlider slider = new JSlider();
		slider.setValue(100);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
	         textQuality.setText(""+slider.getValue());
			}
		});
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(10);
		
		
		
		
		
		JLabel lblInputImage = new JLabel("Input image:");
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmJpegEncoderdecoder.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblInputImage, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(textImagePath, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(btnLoadImage)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQualitydefault, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textQuality, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnConvertToJpeg, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConvertToBmp, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConvertToJpeg_1))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblInputImage))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(textImagePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLoadImage))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnConvertToBmp)
							.addGap(18)
							.addComponent(btnConvertToJpeg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(btnConvertToJpeg_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQualitydefault)
								.addComponent(textQuality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(slider, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		photoLabel = new JLabel("");
		scrollPane.setViewportView(photoLabel);
		photoLabel.setBorder(null);
		frmJpegEncoderdecoder.getContentPane().setLayout(groupLayout);
	}
}
