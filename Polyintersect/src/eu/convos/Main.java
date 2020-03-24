package eu.convos;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Main 
{
	static JFileChooser fc;
	
	private static String path = "Bsp05.txt";
	
	private static Border menuSpacing = new EmptyBorder(0, 10, 0, 10);
	
	static JLabel lblDistance = new JLabel("Click somewhere to start!");
	
	public static void main(String[] args)
	{
		//Setzte die Java Oberfläche auf Windows
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Could not set OS look and feel");
			e.printStackTrace();
		}
		
		//Dateien öffnen
		fc = new JFileChooser(".");
		
		//Fenster
		JFrame frame = new JFrame("Projekt Sichtlinien");
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Root pane
		JPanel proot = new JPanel(new BorderLayout());
		
		//Canvas auf dem Polygone und Sichtlinien gezeichnet werden
		OutputPane outputPane = new OutputPane();
		proot.add(outputPane, BorderLayout.CENTER);
		
		//Menu Bar
		JMenuBar jmb = new JMenuBar();
		proot.add(jmb, BorderLayout.NORTH);
		
		//File Menu
		JMenu jmu = new JMenu("File");
		jmb.add(jmu);
		
		//Button Open file
		JMenuItem miOpen = new JMenuItem("Open file");
		miOpen.addActionListener(e -> {
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)	
				outputPane.setSzene(leseDatei(fc.getSelectedFile()));
		});
		jmu.add(miOpen);
		
		jmu.addSeparator();
		
		//Examples
		for(int i=1; i<=5; i++)
		{
			final int a = i;
			JMenuItem miExample = new JMenuItem("Example " + i);
			miExample.addActionListener(e -> {
				outputPane.setSzene(leseDatei(Main.class.getClassLoader().getResourceAsStream(String.format("Bsp%02d.txt", a))));
			});
			jmu.add(miExample);
		}
		
		//Info text
		JLabel info = new JLabel("LMB um den Startpunkt zu setzen, RMB um das Ziel zu setzen.");
		info.setBorder(menuSpacing);
		jmb.add(info);
		
		//Distanz Text
		lblDistance.setBorder(menuSpacing);
		jmb.add(lblDistance);
		
		frame.add(proot);
		frame.setVisible(true);
		
		//Lese die Datei ein
		Szene s = leseDatei(Main.class.getClassLoader().getResourceAsStream(path));
		outputPane.setSzene(s);
	}
	
	public static Szene leseDatei(File f)
	{
		System.out.println("Opening file \"" + f.getAbsolutePath() + "\".");
		try (FileInputStream fis = new FileInputStream(f);) 
		{
			return leseDatei(fis);
		}
		catch (FileNotFoundException e) {
			System.err.println("File \"" + f.getAbsolutePath() + "\" not found!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Szene leseDatei(InputStream inputStream)
	{
		Scanner scanner = new Scanner(inputStream);
		int viewX, viewY;
		
		//Hole die Anzahl der Polygone
		Polygon[] polygone = new Polygon[scanner.nextInt()];
		
		//Für jedes Polygon tue:
		for(int p=0; p<polygone.length; p++)
		{
			//Gehe eine Zeile weiter
			scanner.nextLine();
			
			//Hole die Anzahl der Eckpunkte
			int[] eckpunkte = new int[scanner.nextInt() * Point.DIMENSION];
			
			//Für jeden Eckpunkt tue:
			for(int e=0; e<eckpunkte.length; e++)
			{
				eckpunkte[e] = scanner.nextInt();
			}
			
			polygone[p] = new Polygon(eckpunkte);
		}
		
		//Letzte Zeile mit der Betrachter Position
		scanner.nextLine();
		viewX = scanner.nextInt();
		viewY = scanner.nextInt();
		
		//Kein Ressource Leak
		scanner.close();
		
		return new Szene(polygone, new Point(viewX, viewY)); 
	}
}
