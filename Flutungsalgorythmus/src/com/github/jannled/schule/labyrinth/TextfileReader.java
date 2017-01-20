package com.github.jannled.schule.labyrinth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class TextfileReader 
{
	public static String[] readTextFile(InputStream stream)
	{
		try {
			return reader(new InputStreamReader(stream));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[] readTextFile(File file)
	{
		try {
			return reader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String[] reader(Reader r)
	{
		ArrayList<String> strings = new ArrayList<String>();
		try 
		{
			BufferedReader reader = new BufferedReader(r);
			String line = "";
			while(line != null)
			{
				line = reader.readLine();
				if(line!=null)
				{
					strings.add(line);
				}
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		return strings.toArray(new String[strings.size()]);
	}
}
