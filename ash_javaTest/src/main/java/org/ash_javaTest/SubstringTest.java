package org.ash_javaTest;

public class SubstringTest {
	public static void main(String[] args) {
		String a= "##missid=ss##xxxid=xx##jssid=js.jpg";
		System.out.println(a.substring(2));
		String fileName = a.substring(a.lastIndexOf("/") + 1);
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		System.out.println("fileName ==="+fileName+"___fileExtension ==="+fileExtension);
	}

}
