package com.docu.components.util;

import java.util.ArrayList;
import java.util.List;

public class CrossUtil {

	public static <T> List<List<T>> cross(List<List<T>> crossArgs) {
		List<List<T>> rows = null;
		int counter = crossArgs.size() > 0 ? 1 : 0;
		for (List<T> data : crossArgs) {
			counter *= data.size();
		}
		if (counter > 0) {
			rows = new ArrayList<List<T>>();
			int[] indexes = new int[crossArgs.size()];
			for (int i = 0; i < counter; i++) {
				List<T> row = new ArrayList<T>();
				for (int j = 0; j < indexes.length; j++) {
					row.add(crossArgs.get(j).get(indexes[j]));
				}
				rows.add(row);
				crossIndex(crossArgs, indexes, crossArgs.size() - 1);
			}
		}
		return rows;
	}

	private static <T> void crossIndex(List<List<T>> sources, int[] indexes,
			int level) {
		indexes[level] = indexes[level] + 1;
		if (indexes[level] >= sources.get(level).size() && level > 0) {
			indexes[level] = 0;
			crossIndex(sources, indexes, level - 1);
		}
	}

	public static void main(String[] args) {
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> ysList = new ArrayList<String>();
		ysList.add("white");
		ysList.add("red");
		ysList.add("blue");
		ysList.add("green");

		List<String> sgList = new ArrayList<String>();
		sgList.add("150cm");
		sgList.add("160cm");
		sgList.add("170cm");
		sgList.add("180cm");
		sgList.add("190cm");
		sgList.add("200cm");
		
		List<String> ccList = new ArrayList<String>();
		ccList.add("S");
		ccList.add("M");
		ccList.add("L");
		ccList.add("XL");
		ccList.add("XXL");

		list.add(ysList);
		list.add(sgList);
		list.add(ccList);

		List<List<String>> result = cross(list);
		for (List<String> group : result) {
			for (String data : group) {
				System.out.print(data + "\t");
			}
			System.out.println();
		}
	}
}
