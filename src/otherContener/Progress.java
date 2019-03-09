package otherContener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Progress extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton lunch;
	private JProgressBar bar;
	private Thread thread;
	
	public Progress() {
		this.setSize(250, 150);
		this.setTitle("ProgressBar");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		thread = new Thread(new Traitement());
		bar = new JProgressBar();
		bar.setMaximum(500);
		bar.setMinimum(0);
		bar.setStringPainted(true);
		lunch = new JButton("Lancer");
		lunch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread = new Thread(new Traitement());
				thread.start();
			}
		});
		
		this.getContentPane().add(bar, BorderLayout.NORTH);
		this.getContentPane().add(lunch, BorderLayout.SOUTH);
		thread.start();
		this.setVisible(true);
		
	}
	
	
	class Traitement implements Runnable{
		@Override
		public void run() {
			lunch.setEnabled(false);
			for (int i = 0; i <= 500; i++) {
				bar.setValue(i);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lunch.setEnabled(true);
		}
		
	}

}
