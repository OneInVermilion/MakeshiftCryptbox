package Classes;

import javax.swing.JLabel;

public class byteLoaderThread extends Thread {
	private CustomPanel infoPanel;
	private byte[] bytes;
	private boolean showAsSymbols;
	private JLabel byte_here;
	private int start_i;
	private int step_i;
	
	public byteLoaderThread(CustomPanel infoPanel, byte[] bytes, boolean showAsSymbols, int start_i, int step_i) {
		this.infoPanel = infoPanel;
		this.bytes = bytes;
		this.showAsSymbols = showAsSymbols;
		this.start_i = start_i;
		this.step_i = step_i;
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + start_i + " is here!");
		for (int i = start_i; i < bytes.length; i += step_i) {
			if (showAsSymbols) {byte_here = new JLabel(Hexer.getByteRepresentations(bytes[i])[4]);}
			else {byte_here = new JLabel(Hexer.getByteRepresentations(bytes[i])[3]);}
			byte_here.addMouseListener(new CustomMouseListener(byte_here, i));
			infoPanel.bytesList[i] = byte_here;
			System.out.println("Thread " + start_i + " / " + step_i + " adding byte_here to " + i);
		}
	}
}
