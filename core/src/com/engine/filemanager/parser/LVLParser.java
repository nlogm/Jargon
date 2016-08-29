package com.engine.filemanager.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class LVLParser {

	private final String extension = ".lvl";
	private Section sections[];

	public LVLParser() {

	}

	public void load(String fileName) {
		String line;

		String levelName;

		if (!(fileName.charAt(fileName.length() - extension.length()) == '.')) {
			fileName += extension;
		}

		StringTokenizer beginToken, endToken;
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
		char sep = '-';
		int count = 0;
		for (int i = 0; i < streamData.length() - 1; i++) {
			if (streamData.substring(i, i + 1).equals(""+start)) {
				count++;
			}
		}

		sections = new Section[count];
		/*for (int k = 0; k < count; k++) {
			
			System.out.println("Current Data: \n" + streamData);
			
			int first = streamData.indexOf(start) < 0 ? -1 : streamData.indexOf(start);
			int last = streamData.indexOf(sep) < 0 ? -1 : streamData.indexOf(sep);
			String tmp  = streamData.substring(first, last);
			sections[k] = new Section();
			sections[k].setContents(tmp);
			streamData = streamData.substring(last, streamData.length() - 1);
			
			System.out.println("Data #" + k + "\n" + sections[k].getContents());
		}*/
		
		StringTokenizer token = new StringTokenizer(streamData, start + "");
		for(int i = 0; token.hasMoreTokens(); i++){
			sections[i] = new Section();
			sections[i].setContents(token.nextToken());
		}
		
		for(int i = 0; i < sections.length; i++){
			token = new StringTokenizer(sections[i].getContents(), sep+"");
			
			while(token.hasMoreTokens())
				sections[i].addSubSection(token.nextToken());
		}
		
		for(int i = 0; i < sections.length; i++)
			for(int k = 0; k < sections[i].getSubStreamSize(); k++){
				System.out.println("SubSection " + k + ":\n" + sections[i].getSubSection(k));
			}

	}

}
