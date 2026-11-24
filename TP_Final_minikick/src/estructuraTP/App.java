package estructuraTP;

import java.awt.EventQueue;

import estructuraTPvista.MarcoPrincipal;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarcoPrincipal frame = new MarcoPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
