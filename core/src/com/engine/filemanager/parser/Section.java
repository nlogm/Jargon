package com.engine.filemanager.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.editor.box2D.constants.Scaler;
import com.editor.box2D.entity.BoxEntity;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Section {

	public static final char sep = '-';

	private String name, contents;
	private ArrayList<String> subSection = new ArrayList<String>();

	private static final String regex_x = "x:(.*?),";
	private static final String regex_y = "y:(.*?),";
	private static final String regex_width = "width:(.*?),";
	private static final String regex_height = "height:(.*?),";
	private static final String regex_col = "color:(.*?),";
	private static final String regex_type = "(?i)type:(.*?)(?i)\\n";

	public Section() {
		name = "";
		contents = "";
	}

	/**
	 * Assigns everything AFTER CONTENTS HAVE BEEN ASSIGNED
	 */
	public void create(World world, RayHandler handler) {
		String tmp = "";
		subSection.addAll(Arrays.asList("", "", "", ""));
		for (int i = 0, k = 0; i < contents.length(); i++) {

			if(i+3 < contents.length())
			if (contents.substring(i, i + 3).equals("---")) {
				i += 3;
				k++;
				tmp = "";
			}
			tmp += contents.substring(i, i + 1);
			subSection.set(k, tmp);

		}
		for (int i = 0; i < subSection.size(); i++) {
			if (subSection.get(i).isEmpty())
				subSection.remove(i);
		}
		if (this.name.equals("Lights")) {
			for (int i = 0; i < subSection.size(); i++) {
				System.out.println("\nSection " + i + ":\n");
				
				Pattern p = Pattern.compile(regex_x);
				Matcher m = p.matcher(subSection.get(i));
				m.find();
				
				
				float x = Float.parseFloat(m.group(1).replaceAll("\\s+", ""));
				System.out.println(x);
				p = Pattern.compile(regex_y);
				m = p.matcher(subSection.get(i));
				m.find();
				float y = Float.parseFloat(m.group(1).replaceAll("\\s+", ""));

				p = Pattern.compile(regex_col);
				m = p.matcher(subSection.get(i));
				m.find();

				p = Pattern.compile(regex_type, Pattern.DOTALL);
				m = p.matcher(subSection.get(i));
				m.find();
				decipherLightType(m.group(1));
				System.out.println("Creating Light at (" + x / Scaler.PPM + ", " + y / Scaler.PPM + ")");
				PointLight light = new PointLight(handler, 50, Color.PURPLE, 1, x / Scaler.PPM, y / Scaler.PPM);
			}

		}
		if (this.name.equals("Bodies")) {
			for (int i = 0; i < subSection.size(); i++) {
				System.out.println("\nSection " + i + ":\n");
				
				Pattern p = Pattern.compile(regex_x);
				Matcher m = p.matcher(subSection.get(i));
				float x = 0;
				if(m.find())
					x = Float.parseFloat(m.group(1).replaceAll("\\s+", ""));
				

				
				p = Pattern.compile(regex_y);
				m = p.matcher(subSection.get(i));
				m.find();
				
				float y = Float.parseFloat(m.group(1).replaceAll("\\s+", ""));

				p = Pattern.compile(regex_width);
				m = p.matcher(subSection.get(i));
				m.find();
				
				float w = Float.parseFloat(m.group(1).replaceAll("\\s+", ""));

				
				p = Pattern.compile(regex_height);
				m = p.matcher(subSection.get(i));
				m.find();
				
				float h = Float.parseFloat(m.group(1).replaceAll("\\s+", ""));


				p = Pattern.compile(regex_type, Pattern.DOTALL);
				m = p.matcher(subSection.get(i));
				m.find();
				System.out.println("Creating Body at (" + x / Scaler.PPM + ", " + y / Scaler.PPM + ")");
				BoxEntity entity = new BoxEntity(new Vector2(x / Scaler.PPM, y / Scaler.PPM), new Vector2(w / Scaler.PPM,
						h / Scaler.PPM), decipherBodyType(m.group(1)));
				entity.createBody(world);
				
			}
		}

	}
	
	private void decipherLightType(String type){
		type = type.replaceAll("\\s+", "");
		type = type.replaceAll("_light", "");
		System.out.println("Type:" + type);
		
	}
	
	private BodyType decipherBodyType(String type){
		type = type.replaceAll("\\s+", "");
		System.out.println("Type:" + type);
		switch(type){
			case "dynamic":{
				return BodyType.DynamicBody;
			}
			case "kinematic":{
				return BodyType.KinematicBody;
			}
			case "static":{
				return BodyType.StaticBody;
			}
			default: return null;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContents(String contents) {
		this.contents = contents;
		for (int i = 0; i < contents.length() - 1; i++) {
			if (!contents.substring(i, i + 1).contains("\n")) {
				this.name += contents.substring(i, i + 1);
			} else
				break;
		}
		name = name.replaceAll("\\s+", "");
		System.out.println("Section Name|-" + name + "-|");

	}

	public String getName() {
		return this.name;
	}

	public String getContents() {
		return this.contents;
	}

	public String getSubStream() {
		String tmp = "";
		for (int i = 0; i < subSection.size(); i++)
			tmp += subSection.get(i) + "\n";

		return tmp;

	}

	public String getSubSection(int i) {
		return this.subSection.get(i);
	}

	public int getSubStreamSize() {
		return subSection.size();
	}

}