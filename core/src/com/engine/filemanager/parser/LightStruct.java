package com.engine.filemanager.parser;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.editor.box2D.constants.Scaler;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LightStruct {

	public static final String directional_light = "directional";
	public static final String cone_light = "cone";
	public static final String point_light = "point";
	public static final String chain_light = "chain";

	public static final char color_type_name = '<';
	public static final char color_type_floats = '!';

	private float x, y;
	private long rgba8888;
	private String type;
	private float[] colorValues;
	private String color;
	private Color lightColor;
	
	
	private String streamLog = "";

	public LightStruct(float worldX, float worldY, String color, String type) {

		this.x = worldX / Scaler.PPM;
		this.y = worldY / Scaler.PPM;

		this.colorValues = new float[4];
		this.color = color;
		this.color = this.color.replaceAll("\\s+", "");
		this.determineColorType();

		this.type = type;
		this.type = this.type.replaceAll("\\s+", "");
		this.type = this.type.replaceAll("_light", "");

	}
	
	public void create(RayHandler handler){
		determineLight(handler);
		System.out.println(type + " placed at (" + x + ", " + y + ")");
	}

	private void determineLight(RayHandler handler) {

		streamLog += type + " is being analyzed..";
		switch (type) {
		case directional_light: {
			streamLog += "type is " + directional_light + "\n";
			PointLight p = new PointLight(handler, 50, lightColor, 1, x, y);
			p.setSoftnessLength(0);
			break;
		}
		case cone_light: {
			streamLog += "type is " + cone_light + "\n";
			ConeLight coneLight = new ConeLight(handler, 50, lightColor, 10, x, y, 0, 15);
			coneLight.setDirection(-90);
			coneLight.setSoftnessLength(0);
			break;
		}
		case point_light: {
			streamLog += "type is " + point_light + "\n";
			//to be stored in hashmap with id dont worry
			PointLight p = new PointLight(handler, 50, lightColor, 3, x, y);
			break;
		}
		case chain_light: {
			streamLog += "type is " + chain_light + "\n";
			PointLight p = new PointLight(handler, 50, lightColor, 1, x, y);
			break;
		}
		default: {
			try {
				throw new LVL_Exception("Light-Incompatible type");
			} catch (LVL_Exception e) {
				e.printStackTrace();
			}
		}
		}

	}

	private void determineColorType() {

		streamLog += color + " is being analyzed..";

		if (color.indexOf(color_type_name) != -1) {
			streamLog += "color is of type name\n";
			recieveValuesName(color = color.substring(0, color.length() - 1));
		} else {
			streamLog += "color is of type rbga values\n";
			recieveValuesRGBA(color = color.substring(0, color.length() - 1));
		}
	}

	private void recieveValuesRGBA(String cleaned) {
		streamLog += "Cleaned color string to: " + color + "\nExtracting float values from " + cleaned + "..\n";
		
		String[] stringRepresentation = new String[4];
		char key = ',';
		int indexOfLastKeyFound = 0;
		int timesKeyFound = 0;
		
		streamLog += "Extracing...";
		for(int i = 0; i < cleaned.length() - 1; i++){
			if(cleaned.substring(i, i+1).equals("" + key)){
				stringRepresentation[timesKeyFound++] = cleaned.substring(indexOfLastKeyFound, indexOfLastKeyFound = i + 1);
				stringRepresentation[timesKeyFound - 1] = stringRepresentation[timesKeyFound - 1].replaceAll(",", "");
			}
		}
		stringRepresentation[stringRepresentation.length - 1] = cleaned.substring(indexOfLastKeyFound, cleaned.length());
		streamLog += "Done!\n";
		
		streamLog += "Parsing from String values to floats...";
		
		System.out.println(streamLog);
		for(int i = 0; i < stringRepresentation.length; i++){
			this.colorValues[i] = Float.parseFloat(stringRepresentation[i]);
		}
		streamLog += "Done! \n";
		
		int i = 0;
		lightColor = new Color(colorValues[i++], colorValues[i++], colorValues[i++], colorValues[i]);
	}

	private void recieveValuesName(String cleaned) {
		streamLog += "\nCleaned color string to: " + color + "\nExtracting values from color name to Color Data..";
		cleaned = cleaned.toUpperCase();
		String stream = "";
		Scanner stackScan = null;
		
		String value = "";
		
		try {
			stackScan = new Scanner(Gdx.files.internal("ColorDictionary.txt").file());

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find: " + Gdx.files.internal("color").file().getAbsolutePath());
			e.printStackTrace();
		}

		while (stackScan.hasNextLine()) {
			stream += stackScan.nextLine() + "\n";
		}
		int startIndex = 0;
		if(stream.contains(cleaned.toUpperCase())){
			startIndex = stream.indexOf(cleaned.toUpperCase());
		}
		
		String tmp = stream.substring(startIndex, stream.length() - 1);
		
		Pattern p = Pattern.compile(cleaned + " = new Color\\((.*?)\\);");
		Matcher m = p.matcher(tmp);
		
		if(m.find())
			value = m.group(1);
		
		System.out.println(value);
		
		
		
		
		if(!value.contains("x"))
			recieveValuesRGBA(value);
		else{
			lightColor = Color.valueOf(value.substring("0x".length(), value.length() - 1));
		}
		
		
	}

}
