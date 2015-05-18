package xmlParse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import data.structure.CPTable;

public class XMLTest {
	
	public static void main(String[] args) throws Exception{
		XmlParser p = new XmlParser("Qos2CPT.xml");
//		ArrayList<String> V = XmlParser.getAllNodesInGraph();
//		
//		//¥Ú”°ArrayList
//		System.out.println("¥Ú”°ArrayList");
//		for(int i=0;i<V.size();i++){
//			System.out.println(V.get(i));
//		}
		ArrayList<String> V = XmlParser.getEachNodeParent("C");
		System.out.println("¥Ú”°ArrayList");
		for(int i=0;i<V.size();i++){
			System.out.println(V.get(i));
		}
//		
		ArrayList<CPTable> cptList = new ArrayList<CPTable>();
		cptList = p.getAllCPTable();
		for(int i=0;i<cptList.size();i++){
			CPTable t = cptList.get(i);
			t.printCPT();
		}
		
		
		
	}
	
	
}
