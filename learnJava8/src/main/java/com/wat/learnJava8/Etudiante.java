package com.wat.learnJava8;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Etudiante implements Comparable<Etudiante>, Comparator<Etudiante>{
	private String name;
	private int note;
	
	@Override
	public int compareTo(Etudiante o) {
		// TODO Auto-generated method stub
		return o.getName().compareTo(getName());
	}

	@Override
	public int compare(Etudiante o1, Etudiante o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
}
