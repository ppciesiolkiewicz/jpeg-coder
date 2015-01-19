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


public class MainWIndow extends JPanel{

	private JFrame frame;
	private JTextField textField;
	private JLabel photoLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWIndow window = new MainWIndow();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLoadImage = new JButton("Load Image");
		btnLoadImage.setAlignmentY(Component.TOP_ALIGNMENT);
		btnLoadImage.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnLoadImage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btnLoadImage.setBounds(383, 11, 135, 23);
		btnLoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new MyFilter());
		   
				if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					File plikObrazek = fc.getSelectedFile();
							JOptionPane.showMessageDialog(null, "Wybrany plik to" + plikObrazek.getName());
				try {
					File file = new File(plikObrazek.getAbsolutePath());
					BufferedImage bi = ImageIO.read(file);
					photoLabel.setSize(bi.getWidth(),bi.getHeight());
					ImageIcon imgIcon = new ImageIcon(bi);
					photoLabel.setIcon(imgIcon);
				
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		JButton btnConvertToJpeg = new JButton("Convert to JPEG");
		btnConvertToJpeg.setAlignmentY(Component.TOP_ALIGNMENT);
		btnConvertToJpeg.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnConvertToJpeg.setBounds(383, 112, 135, 23);
		btnConvertToJpeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					File plikZapisuObrazka = fc.getSelectedFile();
							JOptionPane.showMessageDialog(null, "Zapisane do pliku" + plikZapisuObrazka.getName());
				}
			}
		});
		
		JButton btnConvertToBmp = new JButton("Convert to BMP");
		btnConvertToBmp.setBounds(383, 146, 135, 23);
		
		JButton btnConvertToJpeg_1 = new JButton("Convert to JPEG2000");
		btnConvertToJpeg_1.setBounds(383, 180, 135, 23);
		
		JLabel lblQualitydefault = new JLabel("% QUALITY ");
		lblQualitydefault.setBounds(301, 285, 60, 14);
		
		
		final JSlider slider = new JSlider();
		slider.setBounds(10, 247, 235, 65);
		slider.setValue(100);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
	         textField.setText(""+slider.getValue());
			}
		});
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(10);
		
		textField = new JTextField();
		textField.setBounds(251, 282, 46, 20);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setText("100");
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(slider);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(lblQualitydefault);
		frame.getContentPane().add(btnLoadImage);
		frame.getContentPane().add(btnConvertToJpeg);
		frame.getContentPane().add(btnConvertToBmp);
		frame.getContentPane().add(btnConvertToJpeg_1);
		
		photoLabel = new JLabel("");
		photoLabel.setBorder(new LineBorder(new Color(0, 0, 139), 3, true));
		photoLabel.setBounds(10, 11, 352, 231);
		frame.getContentPane().add(photoLabel);
	}
}
