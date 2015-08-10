package com.skeeter144.skills;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) {
		
		list.add(50);
		list.add(57);
		

		for(int i = 2; i < 99; ++i){
			int res =  ((int) (list.get(i-1) + list.get(i-1) * new Double(.15)));
			list.add(res);
			
		}
		int count = 1;
			for(Integer num : list){
				System.out.println(count + ", " + num);
				++count;
			}
	}

}
