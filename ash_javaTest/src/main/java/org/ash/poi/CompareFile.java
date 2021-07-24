package org.ash.poi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CompareFile {
	// 獲取階層
	public static Map<String, Object> getDirectoryINFO(File directory) throws IOException {
		Map<String, Object> directoryINFO = new LinkedHashMap<String, Object>();
		directoryINFO.put(replace(directory.getPath()), directory);

		analysisDirectory(directory, directoryINFO);
		return directoryINFO;
	}

	// 階成分析
	public static void analysisDirectory(File directory, Map<String, Object> directoryINFO) throws IOException {
		File[] files = directory.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				directoryINFO.put(replace(f.getPath()), f);

				analysisDirectory(f, directoryINFO);
			} else {
				setFileinfoMap(f, directoryINFO);
			}
		}
	}

	public static void setFileinfoMap(File file, Map<String, Object> directoryINFO) throws IOException {
		directoryINFO.put(replace(file.getPath()), file);

	}

	// 加密MD5
	public static String getFileMD5(File f1) {
		// MD5 碼
		String md5Code = "";

		try {

			if (f1.exists()) {
				// 取得檔案的 MD5
				InputStream fis = new FileInputStream(f1.getAbsolutePath());
				byte[] buffer = new byte[1024];
				java.security.MessageDigest complete = java.security.MessageDigest.getInstance("MD5");
				int numRead;
				do {
					numRead = fis.read(buffer);
					if (numRead > 0) {
						complete.update(buffer, 0, numRead);
					}
				} while (numRead != -1);

				fis.close();

				// byte[] 轉換為 HEX 字串
				byte[] b = complete.digest();
				for (int i = 0; i < b.length; i++) {
					md5Code += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
				}
			} else {
				md5Code = "Error: " + f1.getAbsolutePath() + " 檔案不存在";
			}
		} catch (Exception e) {
			md5Code = "Error: " + e.toString();
		}

		return md5Code;
	}

	// 比對路徑 依照後續規格處理 暫定寫死
	public static String replace(String name) {
		// 刪除不相同固定路徑
		name = name.replace("D:\\compareData\\JAVA", "");
		name = name.replace("D:\\compareData\\CLASS", "");
		name = name.replace("D:\\compareData\\JAVA 包反譯", "");
		name = name.replace("D:\\compareData\\CLASS包反譯", "");
		name = name.replace("class\\ptln", "");

		return name;
	}

	public static void gebExcel(File fileName1, File fileName2, String Filetype) throws IOException {
		if ("java".equals(Filetype)) {
			gebExcel_java(fileName1, fileName2);
		}
	}

	public static void gebExcel_java(File fileName1, File fileName2) throws IOException {
		Map<String, Object> compare1 = getDirectoryINFO(fileName1);
		Map<String, Object> compare2 = getDirectoryINFO(fileName2);

		Workbook  wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");

		FileOutputStream outputStream = new FileOutputStream("D:\\TestDemo\\Compare\\CompareLog.xlsx");
		CellStyle green = wb.createCellStyle();
		green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		green.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		CellStyle red = wb.createCellStyle();
		red.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		red.setFillForegroundColor(IndexedColors.RED.getIndex());
		CellStyle blue = wb.createCellStyle();
		blue.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		blue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		Font font = wb.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		red.setFont(font);
		green.setFont(font);
		blue.setFont(font);

		int count = 0;
		System.out.println(compare1);
		System.out.println(compare2);
		Set<String> list = new LinkedHashSet<String>();
		for (String key : compare1.keySet()) {
			list.add(key);
		}
//		for (String key : compare2.keySet()) {
//			list.add(key);
//		}
		int classcount = 0;
		int javacount = 0;
		int same = 0;
		int unsame = 0;
		int total = 0;
		for (String key : list) {
			String check = key + "X";
			if (check.indexOf(".classX") > -1) {
				total++;
				System.out.println(key);
				File file1 = (File) compare1.get(key);
				File file2 = (File) compare2.get(key);

				if (file2 != null && file1 != null) {

					List<String> message =  readFileContext(file1,file2);
					String value1 = "";
					String value2 = "";
					if(message.size()>2) {
						 value1 = message.get(1);
						 value2 = message.get(2);

					}

					Row row = sheet.createRow(count++);
					Cell cell = null;
					row.createCell(0).setCellValue(file1.getAbsolutePath());
					row.createCell(1).setCellValue(value1);
					row = sheet.createRow(count++);
					row.createCell(0).setCellValue(file2.getAbsolutePath());
					row.createCell(1).setCellValue(value2);
					row = sheet.createRow(count++);
					cell = row.createCell(2);
					cell.setCellValue(message.get(0));
					row = sheet.createRow(count++);
					if (value1.equals(value2)) {
						same++;
						cell.setCellStyle(green);
					} else {
						cell.setCellStyle(red);
						unsame++;

					}

				} else {
					Row row = sheet.createRow(count++);
					Cell cell;
					if (file2 == null) {
						javacount++;
						String file1MD5 = getFileMD5(file1);

						row.createCell(0).setCellValue(file1.getAbsolutePath());
						row.createCell(1).setCellValue(file1MD5);
						row = sheet.createRow(count++);
						row.createCell(0).setCellValue("JAVA無檔案比對");
						row = sheet.createRow(count++);
						cell = row.createCell(2);
						cell.setCellValue("無檔案比對");
						row = sheet.createRow(count++);
						cell.setCellStyle(blue);
					} else if (file1 == null) {
						classcount++;
						String file2MD5 = getFileMD5(file2);

						row.createCell(0).setCellValue("CLASS無檔案比對");
						row = sheet.createRow(count++);
						row.createCell(0).setCellValue(file2.getAbsolutePath());
						row.createCell(1).setCellValue(file2MD5);
						row = sheet.createRow(count++);
						cell = row.createCell(2);
						cell.setCellValue("無檔案比對");
						row = sheet.createRow(count++);
						cell.setCellStyle(blue);
					}
				}

			}
		}
		Row row = sheet.createRow(count++);
		Cell cell;
		row.createCell(0).setCellValue("總計	" + total + "	筆");
		row.createCell(1).setCellValue("不相同筆數::	" + unsame);
		row.createCell(2).setCellValue("相同筆數::	" + same);
		row.createCell(3).setCellValue("缺少JAVA檔筆數	" + javacount);
		row.createCell(4).setCellValue("缺少CLASS檔筆數	" + classcount);

		wb.write(outputStream);
		wb.close();
		outputStream.flush();
		outputStream.close();
	}

	public static void gebExcel(File fileName1, File fileName2) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("sheet1");

		Map<String, Object> compare1 = getDirectoryINFO(fileName1);
		Map<String, Object> compare2 = getDirectoryINFO(fileName2);
		FileOutputStream outputStream = new FileOutputStream("D:\\TestDemo\\Compare\\CCompareLog.xlsx");
		XSSFCellStyle green = wb.createCellStyle();
		green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		green.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		XSSFCellStyle red = wb.createCellStyle();
		red.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		red.setFillForegroundColor(IndexedColors.RED.getIndex());
		Font font = wb.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		red.setFont(font);
		green.setFont(font);

		int count = 0;
		for (String key : compare1.keySet()) {

			String Cgeckkey = key + "X";
			if (Cgeckkey.indexOf(".classX") > -1) {
				System.out.println(key);
				File file1 = (File) compare1.get(key);
				File file2 = (File) compare2.get(key);

				if (file2 != null && file1 != null) {

					String file1MD5 = getFileMD5(file1);
					String file2MD5 = getFileMD5(file2);

					if (file1MD5.equals(file2MD5)) {
//						cell.setCellStyle(red);
					} else {
						XSSFRow row = sheet.createRow(count++);
						XSSFCell cell;

						String filestr1[] = key.split("\\\\");
						String FileName = filestr1[filestr1.length - 1];
						String appendStr = "";

						for (int i = 2; i < filestr1.length; i++) {
							if (!filestr1[filestr1.length - i].equals("Compare")) {
								appendStr = filestr1[filestr1.length - i] + File.separator + appendStr;
							} else {
								break;
							}
						}
						row.createCell(0).setCellValue(appendStr);
						row.createCell(1).setCellValue(FileName);
						System.out.println("檔案名稱::" + appendStr + FileName);

					}

				}

			}
		}
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	public static List<String> readFileContext(File file1, File file2) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(file1.getAbsolutePath()));
		BufferedReader br2 = new BufferedReader(new FileReader(file2.getAbsolutePath()));
		String file1Path = file1.getAbsolutePath();
		String file2Path = file2.getAbsolutePath();
		List<String> data1 = new LinkedList<String>();
		List<String> data2 = new LinkedList<String>();
		List<String> message = new LinkedList<String>();
		Stream<String> stream = Files.lines(Paths.get(file1Path));
		stream.forEach(a -> data1.add(a));
		Stream<String> stream2 = Files.lines(Paths.get(file2Path));
		stream2.forEach(a -> data2.add(a));

		int minlength = data1.size();
		int maxlength = 0;
		// flag true = 最小為data1 反之DATA2
		boolean flag = true;
		if (minlength < data2.size()) {
			maxlength = data2.size();
		} else {
			maxlength = data1.size();
			minlength = data2.size();
			flag = false;
		}
		if (minlength > 0) {
			
			for (int i = 0; i < minlength; i++) {
				String stringword1 = data1.get(i);
				String stringword2 = data2.get(i);
				if (!StringUtils.equals(stringword1, stringword2)) {
					message.add("第_"+i+"_有差異");
					message.add(stringword1);
					message.add(stringword2);
					break;
				}

			}

		}
		if (maxlength > minlength && message.size() == 0) {
			String stringword = "";
			message.add("第"+minlength +1+"__有差異");
			if (flag) {
				stringword = data2.get(minlength +1);
				message.add("");
				message.add(stringword);
			} else {
				stringword = data1.get(minlength + 1);
				message.add(stringword);
				message.add("");
			}
		}
		if (message.size() == 0) {
			message.add("無差異");

		}
		return message;
	}
}
