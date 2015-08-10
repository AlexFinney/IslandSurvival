package com.skeeter144.skills;

import java.util.ArrayList;

public class SkillsMain {
	
	private static ArrayList<Integer> expList = new ArrayList<Integer>();
	
	
	public static void init(){
		expList.add(50);
		for(int i = 1; i < 99; ++i){
			int res =  ((int) (50 + expList.get(i-1) + expList.get(i-1) * new Double(.15)));
			expList.add(res);
		}
	}

	
	public static int getLevelForExp(int exp){
		
		for(int i = 0; i < expList.size(); ++i){
			if(expList.get(i) > exp)
				return i + 1;
		}
		
		return 0;
	}
	
	public static int getExpToNextLevel(int level, int exp){	
		return expList.get(--level) - exp;
	}
		

}
