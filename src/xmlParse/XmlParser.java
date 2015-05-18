package xmlParse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import data.structure.CPTable;
import data.structure.Pair;


public class XmlParser {
	static Document doc;
	public XmlParser(String xmlPath){
		doc = loadxml(xmlPath);
	}
	/**
	 * 负责载入xml
	 */
	public static Document loadxml(String xmlPath) 
	{
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(xmlPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(xmlPath+" File not found!!!");
		}
		SAXReader sb = new SAXReader();
		Document doc = null;
		try {
			doc = sb.read(fileIn);
			System.out.println("file "+xmlPath+" load successfully~");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("DocumentException");
			e.printStackTrace();
		}		
		return doc;
	}	
	
	/**
	 * 获取CP-net中所有的节点的集合V
	 * @param doc
	 */
	public static ArrayList<String> getAllNodesInGraph(){
		ArrayList<String> V = new ArrayList<String>();		
		List list = doc.selectNodes("Perferences/QoSPerferences/node");
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Element nodeEle = (Element)iter.next();
			String eleString = nodeEle.attributeValue("name");
			V.add(eleString);
		}
		return V;
	}
	
	/**
	 * 获取CP-net中每个节点的父亲节点集合
	 * @param nodeName
	 */
	public static ArrayList<String> getEachNodeParent(String nodeName){
		ArrayList<String> parentList = new ArrayList<String>();	
		List list = doc.selectNodes("Perferences/QoSPerferences/node");
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Element nodeEle = (Element)iter.next();
			if(nodeEle.attributeValue("name").equals(nodeName)){
				
				Element preSet = nodeEle.element("previousSet");
				List list1 = preSet.selectNodes("previous");
				Iterator iter1 = list1.iterator();
				while(iter1.hasNext()){
					Element previousEle = (Element)iter1.next();
					String eleString = previousEle.getText();
					parentList.add(eleString);
				}
			}			
		}
		return parentList;
	}
	
	/**
	 * 获取某个cpt里的Pair中的实例u
	 * @return
	 */
	public  ArrayList<String> getu(){
		ArrayList<String> u = new ArrayList<String>();		
		return u;
	}
	/**
	 * 获取CP-net中所有的CPT
	 */
	public ArrayList<CPTable> getAllCPTable(){
		ArrayList<CPTable> cptableList = new ArrayList<CPTable>();
		List list = doc.selectNodes("Perferences/CPTables/CPT");
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Element cpt = (Element)iter.next();
			
			String cptid = cpt.attributeValue("id");
			CPTable cptable1 = new CPTable(cptid);
			
			List list1 = cpt.selectNodes("Pair");
			Iterator iter1 = list1.iterator();
			
			while(iter1.hasNext()){
				Element e = (Element)iter1.next();
				String pairId = e.attributeValue("id");
				String i = e.elementText("i");
				String u = e.elementText("u");
				String first = e.elementText("first");
				String last = e.elementText("last");
				
				Pair p = new Pair(i,u,first,last);
				cptable1.setPairs(p);
				
			}
			
			cptableList.add(cptable1);
		}
		return cptableList;
	}
	
}
