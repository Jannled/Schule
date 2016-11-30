package com.github.jannled.schule.wettlauf;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class WindowManager implements ActionListener
{
	public static final String[] befehle = {"Auf die Plätze", "Fertig", "Los!"};
	
	public static WindowManager w = new WindowManager();
	JFrame window;
	JPanel root;
	JPanel pnlLaufer;
	JTextField lauferZahl;
	JTextField[] namen;
	JButton confirm;
	Wettlauf wettlauf;
	JTextArea message;
	
	public WindowManager()
	{
		window = new JFrame("Wettrennen");
		root = new JPanel(new BorderLayout());
		pnlLaufer = new JPanel(new GridLayout(0, 1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(root);
		
		message = new JTextArea("Bitte bereiten sie das rennen vor.                 ");
		message.setEditable(false);
		root.add(message, BorderLayout.NORTH);
		
		lauferZahl = new JTextField("8");
		lauferZahl.setBorder(new TitledBorder("Zahl der Läufer"));
		
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		
		pnlLaufer.add(lauferZahl);
		pnlLaufer.setBorder(new TitledBorder("Alle Läufer"));
		root.add(pnlLaufer, BorderLayout.CENTER);
		
		root.add(confirm, BorderLayout.SOUTH);
		
		window.pack();
		window.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		System.out.println(Arrays.toString(args));
	}
	
	public void setLaufer(Laufer[] laufer)
	{
		pnlLaufer.removeAll();
		namen = new JTextField[laufer.length];
		for(int i=0; i<laufer.length; i++)
		{
			namen[i] = new JTextField("Läufer " + (i+1));
			pnlLaufer.add(namen[i]);
		}
		window.revalidate();
		window.repaint();
		window.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==confirm)
		{
			if(wettlauf==null)
			{
				int anzahl = Integer.parseInt(lauferZahl.getText());
				System.out.println("Erstelle neues Wettrennen mit " + anzahl);
				wettlauf = new Wettlauf(anzahl);
				System.out.println("Dat wars");
			}
			else if(wettlauf.getLaufer()[0] == null)
			{
				String[] lauferNamen = new String[namen.length];
				for(int i=0; i<namen.length; i++)
				{
					lauferNamen[i] = namen[i].getText();
				}
				wettlauf.anDenStart(lauferNamen);
				message.setText("Alle Läufer stehen auf den Startpositionen");
			}
			else 
			{
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() 
					{
						confirm.setEnabled(false);
						for(int i=0; i<3; i++)
						{
							message.setText(befehle[i]);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						go();
						confirm.setEnabled(true);
					}
				});
				thread.start();
			}
		}
	}
	
	public void go()
	{
		wettlauf.rennenLaufen();
		Laufer[] laufer = wettlauf.zeitMessen();
		for(int i=0; i<laufer.length; i++)
		{
			namen[i].setText(laufer[i].getName() + ": " + laufer[i].getZeit());
		}
		Laufer[] platze = SortMinMax.minMax(laufer);
		message.append("\nBestzeit: " + platze[0].getName() + ": " + platze[0].getZeit());
		message.append("\nSchlechteste Zeit: " + platze[1].getName() + ": " + platze[1].getZeit());
	}
}
