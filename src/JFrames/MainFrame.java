package JFrames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Classes.CustomPanel;
import JPanels.MainMenuPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		switchPanel(new MainMenuPanel());
	}
	
	public void switchPanel(JPanel pan) {
		this.setContentPane(pan);
		pan.setLayout(null);
		this.revalidate();
	}
	
	public void addPanel(JPanel pan) {
		JPanel panel = pan;
		panel.setBounds(30, 70, 335, 245);
		getContentPane().add(panel);
		panel.revalidate();
	}
	public void addPanel(JScrollPane pan) {
		pan.setBounds(20, 70, 355, 235);
		getContentPane().add(pan);
		pan.revalidate();
		pan.repaint();
	}

}
