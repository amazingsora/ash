package idv.amazingsora.ash.testDemo.poi;

import java.awt.Color;
import java.awt.image.BufferedImage;


import idv.amazingsora.ash.testDemo.poi.WatermarkUnit.Watermark;

public class poiMain {
	public static void main(String[] args) throws Exception {
		
		Watermark watermark = new Watermark();
		
        watermark.setText("測試浮水印12345678910111213");
        watermark.setColor(Color.gray);
        watermark.setShx(0.2F);
        watermark.setShy(-0.2F);
        watermark.setHeight(200);
        watermark.setWidth(300);
		BufferedImage image = WatermarkUnit.createWatermarkImage(watermark);
		
		WatermarkUnit.writeWatermark("D:\\cc.xlsx", image);
		
	}
}
