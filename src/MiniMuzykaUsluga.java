import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MiniMuzykaUsluga implements Usluga {

	MojPanelRysunkowy mojPanel;
	
	public JPanel pobierzPanelGUI() {
		JPanel panelGlowny = new JPanel();
		mojPanel = new MojPanelRysunkowy();
		JButton przyciskZagraj = new JButton("Zagraj!");
		przyciskZagraj.addActionListener(new ZagrajListener());
		panelGlowny.add(mojPanel);
		panelGlowny.add(przyciskZagraj);
		return panelGlowny;
		}
	public class ZagrajListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		try {
		Sequencer sekwenser = MidiSystem.getSequencer();
		sekwenser.open();
		
		sekwenser.addControllerEventListener(mojPanel, new int[] {127});
		Sequence sekwencja = new Sequence(Sequence.PPQ, 4);
		Track track = sekwencja.createTrack();
		
		for (int i = 0; i < 100; i+= 4) {
		int rNum = (int) ((Math.random() * 50) + 1);
		if (rNum < 38) { // dalsze czynnoosci wykonujemy, jezeli num <38 (75% czasu)
		track.add(twozZdarzenie(144,1,rNum,100,i));
		track.add(twozZdarzenie(176,1,127,0,i));
		track.add(twozZdarzenie(128,1,rNum,100,i + 2));
		}
	} // koniec petli
		
			sekwenser.setSequence(sekwencja);
			sekwenser.start();
			sekwenser.setTempoInBPM(220);
			} catch (Exception ex) {ex.printStackTrace();}
		} // koniec metody actionPerformed
	} // koniec klasy wewnetrznej
	
	public MidiEvent twozZdarzenie(int plc, int kanal, int jeden, int dwa, int takt) {
				MidiEvent zdarzenie = null;
				try {
					ShortMessage a = new ShortMessage();
					a.setMessage(plc, kanal, jeden, dwa);
					zdarzenie = new MidiEvent(a, takt);
					}catch(Exception e) { }
						return zdarzenie;
					}
		
		class MojPanelRysunkowy extends JPanel implements ControllerEventListener {
				// rysujemy tylko wtedy, jezeli odebralismy zdarzenie
				boolean kmk = false;
				
		public void controlChange(ShortMessage event) {
					kmk = true;
					repaint();
					}
		public Dimension getPreferredSize() {
					return new Dimension(300,300);
					}
					public void paintComponent(Graphics g) {
					if (kmk) {
					Graphics2D g2 = (Graphics2D) g;
					int r = (int) (Math.random() * 250);
					int gr = (int) (Math.random() * 250);
					int b = (int) (Math.random() * 250);
					g.setColor(new Color(r,gr,b));
					int wys = (int) ((Math.random() * 120) + 10);
					int szer = (int) ((Math.random() * 120) + 10);
					int x = (int) ((Math.random() * 40) + 10);
					int y = (int) ((Math.random() * 40) + 10);
					g.fillRect(x,y,wys, szer);
					kmk = false;
				} // koniec if
			} // koniec metody
		} // koniec klasy wewnÚtrznej
	} // koniec klasy
	

