package eu.convos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main 
{
	private static String path = "src/Bsp05.txt";
	
	public static void main(String[] args)
	{
		//Setzte die Java Oberfläche auf Windows
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Could not set OS look and feel");
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Projekt Sichtlinien");
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		OutputPane mainPane = new OutputPane();
		frame.add(mainPane);
		frame.setVisible(true);
		
		//Lese die Datei ein
		mainPane.setSzene(leseDatei());
	}
	
	public static Szene leseDatei()
	{
		File f = new File(path);
		try(FileInputStream inputStream = new FileInputStream(f);
			Scanner scanner = new Scanner(inputStream);) 
		{
			
			int viewX, viewY;
			
			//Hole die Anzahl der Polygone
			Polygon[] polygone = new Polygon[scanner.nextInt()];
			
			//Für jedes Polygon tue:
			for(int p=0; p<polygone.length; p++)
			{
				//Gehe eine Zeile weiter
				scanner.nextLine();
				
				//Hole die Anzahl der Eckpunkte
				int[] eckpunkte = new int[scanner.nextInt() * Polygon.DIMENSION];
				
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
			
			return new Szene(polygone, viewX, viewY);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("File \"" + f.getAbsolutePath() + "\" not found!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
