package idv.amazingsora.ash.testDemo.poi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Data;

public class WatermarkUnit {

    @Data
    public static class Watermark {
        private String text;
        private Color color;
        private Float shx;
        private Float shy;
        private int height;
        private int width;
    }
    public static BufferedImage createWatermarkImage() {
    	return createWatermarkImage(null);
    }

    public static BufferedImage createWatermarkImage(Watermark watermark) {
        if(watermark == null ) {
        	watermark = new Watermark();
            watermark.setText("測試浮水印");
            watermark.setColor(Color.gray);
            watermark.setShx(0.2F);
            watermark.setShy(0.2F);
            watermark.setHeight(200);
            watermark.setWidth(300);
        }
        String[] textArray = watermark.getText().split("\n");
        Font font = new Font("JhengHei", Font.PLAIN, 25);
        Integer width = watermark.getWidth();
        Integer height = watermark.getHeight();
        Float shx = 0F;
        Float shy = 0F;
        if(!shx.isNaN()) {
        	shx=watermark.getShx();
        }
        if(!shy.isNaN()) {
        	shy=watermark.getShy();
        }
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 背景透明
        Graphics2D g = image.createGraphics();
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        g = image.createGraphics();
        g.setColor(watermark.getColor());// 字體顏色
        g.setFont(font);// 字體
        g.shear(shx, shy);// 角度
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int y = 50;
        for (int i = 0; i < textArray.length; i++) {
            g.drawString(textArray[i], 0, y);
            y = y + font.getSize();
        }
        g.dispose();
        return image;

    }
    public static void writeWatermark(String inPath,BufferedImage image) throws Exception {
    	writeWatermark(inPath, inPath, image);
    }
    public static void writeWatermark(String inPath, String outPath,BufferedImage image) throws Exception {
    	 ByteArrayOutputStream os = new ByteArrayOutputStream();
         ImageIO.write(image, "png", os);
         FileInputStream fileInputStream = new FileInputStream(inPath); 
         XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
         int pictureIdx = workbook.addPicture(os.toByteArray(), Workbook.PICTURE_TYPE_PNG);
         POIXMLDocumentPart poixmlDocumentPart = workbook.getAllPictures().get(pictureIdx);
         for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
             XSSFSheet sheet = workbook.getSheetAt(i);
             PackagePartName ppn = poixmlDocumentPart.getPackagePart().getPartName();
             String relType = XSSFRelation.IMAGES.getRelation();
             PackageRelationship pr = sheet.getPackagePart().addRelationship(ppn, TargetMode.INTERNAL, relType, null);
             sheet.getCTWorksheet().addNewPicture().setId(pr.getId());
         }
         OutputStream o = new FileOutputStream(outPath);

         workbook.write(o);
 	}
    	
    
}