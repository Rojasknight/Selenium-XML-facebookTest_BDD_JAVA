package co.com.accenture.Facebook.gradle.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class LeerXML {
	public static NodeList listaEmpleados;

	public static NodeList main() {
		try {
			File archivo = new File("C:\\Users\\danny.rojas\\Desktop\\Retos\\7-gradle\\datos.xml");
			System.out.println("ajdajsdnjksd");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
			listaEmpleados = document.getElementsByTagName("empleado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}

}
