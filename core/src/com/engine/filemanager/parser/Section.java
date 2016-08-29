package com.engine.filemanager.parser;

import java.util.ArrayList;

public class Section {
	
	
	private String name, contents;
	private ArrayList<String> subSection = new ArrayList<String>();
	
	public Section(){
		name = "NONAME";
		contents = "NULL";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setContents(String contents){
		this.contents = contents;
	}
	
	public void addSubSection(String sub){
		subSection.add(sub);
	}
	
	public void addContents(String newContent){
		contents += newContent;
	}
	
	public String getName(){ return this.name; }
	public String getContents(){ return this.contents;}
	public String getSubStream(){ 
		String tmp = "";
		for(int i = 0; i < subSection.size(); i++)
			tmp += subSection.get(i) + "\n";
		
		return tmp;
		
	}
	
	public String getSubSection(int i ){ return this.subSection.get(i);}
	
	public int getSubStreamSize(){ return subSection.size();}

}
