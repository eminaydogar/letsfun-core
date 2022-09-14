package com.easoft.letsfun.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReader {

	private DocumentBuilder documentBuilder;
	private DocumentBuilderFactory documentBuilderFactory;
	private String defaultPath;

	public HashMap<String, String> readXMLFile(String path, String baseTag) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {

			if (defaultPath != null) {
				path = defaultPath + path;
			}
			File fXmlFile = new File(path);
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(baseTag);

			if (nList != null) {
				callNodeExplorer(nList, result);
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		} finally {
			documentBuilder.reset();
		}

		return result;
	}

	public FileReader createResourceReader() {
		this.defaultPath = "Path.resource";
		return this;
	}

	private static void callNodeExplorer(NodeList nodeList, HashMap<String, String> map) {
		if (nodeList == null) {
			return;
		}
		if (nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node != null && node.getNodeName() != null && !node.getNodeName().equalsIgnoreCase("#text")
						&& node.getTextContent() != null && !node.getTextContent().isEmpty()
						&& node.getChildNodes() != null && node.getChildNodes().getLength() == 1) {

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						log.info(
								"key: [" + node.getNodeName() + "] || value: [" + node.getTextContent() + "] added map");
						map.put(node.getNodeName(), node.getTextContent().trim());
					}
				}
				callNodeExplorer(node.getChildNodes(), map);
			}
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		FileReader fr = new FileReader();
		fr.createResourceReader().readXMLFile("secure.xml", "securUtility");

	}

}
