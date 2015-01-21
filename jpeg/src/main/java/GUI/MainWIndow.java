package GUI;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javax.swing.plaf.SliderUI;

import java.awt.ComponentOrientation;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import Application.Application;
import Application.ConsoleApplication;
import ArgParser.ArgInfo;
import ArgParser.ArgInfo.ActionType;


public class MainWIndow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmJpegEncoderdecoder;
	private JTextField textQuality;
	private JLabel photoLabel;
	private JTextField textImagePath;
	private JScrollPane scrollPane;
	private JComboBox cbType;
	private JSlider slider;
	
	private String currentDir;
	private File plikObrazek;

	/**
	 * Launch the application.
	 */
	public static void run() {
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
		
		textImagePath = new JTextField();
		textImagePath.setEditable(false);
		textImagePath.setColumns(10);
		

		
		
		JButton btnLoadImage = new JButton("Load");
		btnLoadImage.setAlignmentY(Component.TOP_ALIGNMENT);
		btnLoadImage.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnLoadImage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btnLoadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				
				if(plikObrazek != null)
				{
					fc.setCurrentDirectory(plikObrazek);
				}
				fc.addChoosableFileFilter(new MyFilter());
		   
				if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					plikObrazek = fc.getSelectedFile();
							//JOptionPane.showMessageDialog(null, "Wybrany plik to " + plikObrazek.getName());
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
		
		
		
		
		
		JLabel lblInputImage = new JLabel("Input image:");
		
		scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Conversion parameters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser saveFC = new JFileChooser();
				saveFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(plikObrazek != null)
				{
					saveFC.setCurrentDirectory(plikObrazek);
				}

				if (plikObrazek != null) {
					if( saveFC.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
						String outPath = (String)saveFC.getCurrentDirectory().getAbsolutePath();

						try {
							Application console;
							ArgInfo args;
								
							if ((String)cbType.getSelectedItem() == "BMP") {

							}
							else if ((String)cbType.getSelectedItem() == "JPEG") {
								args = new ArgInfo();
								args.action = ActionType.encode;
								args.input = plikObrazek.getAbsolutePath();
								System.out.print(args.input);
								args.quality = slider.getValue();
								//args.outp
								console = new ConsoleApplication();
								console.run(args);
							}
							else if ((String)cbType.getSelectedItem() == "JPEG2000") {
								
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				
			}
		});
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
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(441, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		panel.setLayout(null);
		
		JLabel lblQualitydefault = new JLabel("Image quality [%]:");
		lblQualitydefault.setBounds(12, 40, 98, 16);
		panel.add(lblQualitydefault);
		
		textQuality = new JTextField();
		textQuality.setBounds(128, 38, 46, 20);
		panel.add(textQuality);
		textQuality.setEditable(false);
		textQuality.setColumns(10);
		textQuality.setText("100");
		
		
		slider = new JSlider();
		slider.setBounds(12, 70, 229, 43);
		panel.add(slider);
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
		
		String[] conversionType = { "BMP", "JPEG", "JPEG2000" };
		
		cbType = new JComboBox(conversionType);
		cbType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				//selectedConvType = (String)cb.getSelectedItem();
			}
		});
		cbType.setBounds(311, 72, 182, 25);
		panel.add(cbType);
		
		JLabel lblConversionType = new JLabel("Conversion type:");
		lblConversionType.setBounds(311, 40, 157, 16);
		panel.add(lblConversionType);
		
		photoLabel = new JLabel("");
		scrollPane.setViewportView(photoLabel);
		photoLabel.setBorder(null);
		frmJpegEncoderdecoder.getContentPane().setLayout(groupLayout);
	}
}
