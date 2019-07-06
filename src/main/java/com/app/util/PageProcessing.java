package com.app.util;

import java.util.ArrayList;
import java.util.List;

public class PageProcessing {
	
	public static List<Integer> getListPage(int page, int size, int totalRecords) {
		int totalPages;
		List<Integer> list = new ArrayList<Integer>();
		if (totalRecords % size == 0) {
			totalPages = totalRecords / size;
		} else {
			totalPages = (totalRecords / size) + 1;
		}
		if (totalPages > 0 && totalPages < 6) {
			for (int i = 1; i < totalPages + 1; i++) {
				list.add(i);
			}
		}
		if (totalPages >= 6) {
			if (page == totalPages) {
				for (int i = page - 4; i < page+1; i++) {
					list.add(i);
				}
			} else {
				for (int i = page - 1; i < page + 4; i++) {
					if (i >= 1 && i <= totalPages)
						list.add(i);
				}
			}
		}
		return list;
	}
}
