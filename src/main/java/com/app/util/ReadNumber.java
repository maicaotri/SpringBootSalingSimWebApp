package com.app.util;

import java.util.Scanner;

public class ReadNumber {
	public static void readNumber(int n) {
		String s = n + "";
		int nTi, nDuTrieu, nTrieu, nDuNghin, nNghin, nTram;
		if (s.length() == 1) {
			System.out.println(donVi(n));
		} else if (s.length() == 2) {
			System.out.println(hangChuc(n));
		} else if (s.length() == 3) {
			System.out.println(hangTram(n));
		} else if (s.length() >= 4 && s.length() <= 6) {
			nNghin = (n % 1000000) / 1000;
			nTram = (n % 1000000) % 1000;
			if (s.length() == 4) {
				System.out.println(donVi(nNghin) + " Nghìn " + hangTram(nTram));
			} else if (s.length() == 5) {
				System.out.println(hangChuc(nNghin) + " Nghìn " + hangTram(nTram));
			} else {
				System.out.println(hangTram(nNghin) + " Nghìn " + hangTram(nTram));
			}
		} else if (s.length() >= 7 && s.length() <= 9) {
			nTrieu = n / 1000000;
			nDuNghin = n % 1000000;
			nNghin = nDuNghin / 1000;
			nTram = nDuNghin % 1000;
			if (s.length() == 7) {
				System.out.println(donVi(nTrieu) + " Triệu " + hangTram(nNghin) + " Nghìn " + hangTram(nTram));
			} else if (s.length() == 8) {
				System.out.println(hangChuc(nTrieu) + " Triệu " + hangTram(nNghin) + " Nghìn " + hangTram(nTram));
			} else {
				System.out.println(hangTram(nTrieu) + " Triệu " + hangTram(nNghin) + " Nghìn " + hangTram(nTram));
			}
		} else {
			nTi = n / 1000000000;
			nDuTrieu = n % 1000000000;
			nTrieu = nDuTrieu / 1000000;
			nDuNghin = nDuTrieu % 1000000;
			nNghin = nDuNghin / 1000;
			nTram = nDuNghin % 1000;
			System.out.println(donVi(nTi) + " Tỉ " + hangTram(nTrieu) + " Triệu " + hangTram(nNghin) + " Nghìn "
					+ hangTram(nTram));
		}
	}

	public static String donVi(int n) {
		String[] donVi = { "", "Một", "Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Tám", "Chín" };
		String s = "";
		for (int i = 0; i < donVi.length; i++) {
			if (i == n) {
				s += donVi[i];
			}
		}
		return s;
	}

	public static String hangChuc(int n) {
		String s = "";
		String[] hangChuc = { "Linh ", "Mười ", "Hai Mươi ", "Ba Mươi ", "Bốn Mươi ", "Năm Mươi ", "Sáu Mươi ",
				"Bảy Mươi ", "Tám Mươi ", "Chín Mươi " };
		int donVi = n % 10;
		for (int i = 0; i < hangChuc.length; i++) {
			if (donVi == 1 && n / 10 == i) {
				s += hangChuc[i] + "Mốt";
			} else if (n % 10 == 5 && n / 10 == i) {
				s += hangChuc[i] + "Lăm";
			} else if (i == n / 10) {
				s += hangChuc[i] + donVi(n % 10);
			}
		}
		return s;
	}

	public static String hangTram(int n) {
		String s = "";
		String[] hangTram = { "Không Trăm ", "Một Trăm ", "Hai Trăm ", "Ba trăm ", "Bốn Trăm ", "Năm Trăm ",
				"Sáu Trăm ", "Bảy Trăm ", "Tám Trăm ", "Chín Trăm " };
		for (int i = 0; i < hangTram.length; i++) {
			if (n % 100 == 0 && i == n / 100) {
				s += hangTram[i];
			} else if ((i == n / 100) && (n % 100 < 10)) {
				s += hangTram[i] + "Linh " + donVi(n % 100);
			} else if (i == n / 100) {
				s += hangTram[i] + hangChuc(n % 100);
			}
		}
		return s;
	}

}
