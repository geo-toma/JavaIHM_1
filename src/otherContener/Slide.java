package otherContener;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slide extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel("La valeur actuelle : 30");
	private JSlider slider = new JSlider();
	
	public Slide() {
		this.setSize(250, 150);
		this.setTitle("SLide");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		slider.setMaximum(100);
		slider.setMinimum(0);
		slider.setValue(30);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(20);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				label.setText("La valeur actuelle : "+((JSlider)e.getSource()).getValue());	
			}
		});
		
		this.getContentPane().add(slider ,BorderLayout.NORTH);
		this.getContentPane().add(label, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}
