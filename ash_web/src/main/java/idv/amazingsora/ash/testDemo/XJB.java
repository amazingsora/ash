package idv.amazingsora.ash.testDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XJB {
	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		InputStream inputStream = new FileInputStream("D:/HelloWorld");
		JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		Response o = (Response)jaxbUnmarshaller.unmarshal(inputStream);
		System.out.println(o);
	}
}
