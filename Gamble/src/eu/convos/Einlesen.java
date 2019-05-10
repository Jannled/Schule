package eu.convos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Einlesen
{
	
	public static int[] read(String filePath) 
	{ 
		File file = new File(filePath);
		return read(file);
	}
	
	public static int[] read(File file)
	{
		ArrayList<String> output = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				output.add(text);
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				return null;
			}
		}
		int[] out = new int[output.size()];
		int i=0;
		try {
			for(; i<out.length; i++)
			{
				out[i] = Integer.parseInt(output.get(i).trim());
			}
		} catch (NumberFormatException e)
		{
			System.out.println("Zeile " + (i+1) + " enhält eine ungültige Zahl!"); 
			out = null;
		}
		
		return out;
	}
}
