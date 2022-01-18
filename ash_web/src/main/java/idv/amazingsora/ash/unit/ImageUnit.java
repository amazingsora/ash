package idv.amazingsora.ash.unit;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang3.StringUtils;

public class ImageUnit {
	final static String path404 = "";
	/**
	 * 本地圖片讀取
	 * @return
	 */
	static public byte[] getPic(String Path) {
		File file = new File(Path);
		if (!file.exists()||StringUtils.isBlank(Path)) {
			return getDefaultPic();
		}
		byte[] bytes = null;
		try (FileInputStream inputStream = new FileInputStream(file);) {
			bytes = new byte[inputStream.available()];
			inputStream.read(bytes, 0, inputStream.available());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 預設404圖片
	 * @return
	 */
	private static byte[] getDefaultPic() {
		File file = new File(path404);
		byte[] bytes = null;
		try (FileInputStream inputStream = new FileInputStream(file);) {
			bytes = new byte[inputStream.available()];
			inputStream.read(bytes, 0, inputStream.available());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

}
