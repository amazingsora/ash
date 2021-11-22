package org.ash.poi;

import java.io.File;

public class Testmain {
	public static void main(String[] args) throws Exception {
		File fileName1 = new File("D:\\compareData\\CLASS包反譯");
		File fileName2 = new File("D:\\compareData\\JAVA 包反譯");
new CompareFile();
		//		File fileName1 = new File("D:\\compareData\\CLASS\\com\\tradevan\\msweb\\action\\admin\\AdminAction.java");
//		File fileName2 = new File("D:\\compareData\\JAVA\\com\\tradevan\\msweb\\action\\admin\\\\AdminAction.java");
		CompareFile.gebExcel(fileName1, fileName2,"java");
//		new CompareFile().readFileContext(fileName1, fileName2);
	}
}
