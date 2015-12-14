import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GraWKosciUsluga implements Usluga {

	JLabel etykieta;
	JComboBox iloscKosci;
	
	public JPanel pobierzPanelGUI() {
		
		JPanel panel = new JPanel();
		JButton przycisk = new JButton("Rzut kostakami");
		String[] opcje = {"1", "2", "3", "4", "5"};
		iloscKosci = new JComboBox(opcje);
		etykieta = new JLabel("wylosowane cyfry");
		przycisk.addActionListener(new RzutKoscmiListener());
		panel.add(iloscKosci);
		panel.add(przycisk);
		panel.add(etykieta);
		return panel;
	}
	
	public class RzutKoscmiListener implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
					// rzucamy kosci
				String wynikiRzutu = "";
				String wybor = (String) iloscKosci.getSelectedItem();
				int iloscUzywanychKosci = Integer.parseInt(wybor);
				for (int i = 0; i < iloscUzywanychKosci; i++) {
					int r = (int) ((Math.random() * 6) + 1);
					wynikiRzutu += (" " + r);
				}
				etykieta.setText(wynikiRzutu);
			}
		}
	}
