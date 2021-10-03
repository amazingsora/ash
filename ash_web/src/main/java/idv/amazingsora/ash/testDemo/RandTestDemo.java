package idv.amazingsora.ash.testDemo;

import java.security.SecureRandom;

public class RandTestDemo {
	public static void main(String[] args) {
		for(int i =0;i<2000;i++) {
			String tmpR=String.valueOf((int)((Math.random()*1000000000)+1));
			System.out.println("原 ===	"+tmpR);
			SecureRandom rad = new SecureRandom();
			System.out.println("更改 ===	"+(int)((rad.nextInt(1000000000))+1));
			System.out.println("--------------------------------------------");

		}
	}
	
}
