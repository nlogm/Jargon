package com.engine.filemanager.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class LVLParser {

	private final String extension = ".lvl";
	public static final char start = '#';
	public static final char end_section= '*';
	public static final char end_sub_section = '-';
	public static final String NEW_LINE = System.getProperty("line.seperator");
	private Section sections[];

	public LVLParser() {

	}

	public void load(String fileName) {
		String tmp = fileName.substring(fileName.indexOf("desktop\\") + "desktop\\".length(), fileName.length());
		System.out.println(tmp);
		fileName = fileName.substring(0, fileName.indexOf(tmp)) + "bin\\" + tmp;
		if (!(fileName.charAt(fileName.length() - extension.length()) == '.')) {
			fileName += extension;
		}

		String stream = "";
		Scanner stackScan = null;
		try {
			stackScan = new Scanner(new File(fileName));

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find: " + fileName);
			e.printStackTrace();
		}

		while (stackScan.hasNextLine()) {
			stream += stackScan.nextLine() + "\n";
		}
		//System.out.println(stream);

		populateSections(stream);

	}

	private void populateSections(String streamData) {
		char start = '#';
		int count = 0;
		for (int i = 0; i < streamData.length() - 1; i++) {
			if (streamData.substring(i, i + 1).equals(""+start)) {
				count++;
			}
		}
		sections = new Section[count];
		Pattern p = Pattern.compile("(?i)#(.*?)(?i)\\*\\*\\*", Pattern.DOTALL);
		Matcher m = p.matcher(streamData);
		int hitCount = 0;
		while(m.find()){
			sections[hitCount] = new Section();
			sections[hitCount++].setContents(m.group(1));
		}

	}
	
	public void create(World world, RayHandler handler){
		for(int i = 0; i < sections.length; i++)
			if(sections[i] != null)
				sections[i].create(world, handler);
			
	}
	

}
