package data.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import xmlParse.XmlParser;

public class CPnet {
	
	/**
	 * ���������ϵĲ����V-U
	 */
	public ArrayList<String> setMinus(ArrayList<String> V,ArrayList<String> U){
		ArrayList<String> nodeNameList = (ArrayList<String>) V.clone();
		nodeNameList.removeAll(U);
		if(nodeNameList.size()==0){
			nodeNameList.add("null");
		}
		return nodeNameList;
	}
	
	/**
	 * ��һ�����Ϻ�һ��Ԫ�صĲ���
	 */
	public ArrayList<String> joinTwoSets(ArrayList<String> V1,String Xi){
		ArrayList<String> V = (ArrayList<String>) V1.clone();
		V.add(Xi);
		return V;
	}
	
	/**
	 * �󼯺ϵ�ʵ��������instantiation
	 */
	public ArrayList<ArrayList<String>> instantiation(ArrayList<String> nodeName1){
		ArrayList<String> nodeName = (ArrayList<String>) nodeName1.clone();
		int n = nodeName.size();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
		if(nodeName.contains("null")||n==0){
			resultList.clear();
			System.out.println("�˼��ϵ�instantiationΪ��~");
			return resultList;
		}
		
		for(int i=0;i<n;i++){
//			char b = (char) (i+65);
			Node node = new Node(nodeName.get(i));
			list.add(node.nodeRank);
		}		
		
		System.out.println("��ʼ��ӡinstantiation~");
		for(int i = 0;i<=Math.pow(2,n)-1;i++){
			ArrayList<Integer> index = new ArrayList<Integer>();
			for(int k=n-1;k>=0;k--){
				int aIdx = ((i & (int)Math.pow(2,k))>>k);
				index.add(aIdx);
			}
			ArrayList<String> tempList = new ArrayList<String>();
			for(int j=0;j<n;j++){	
				tempList.add(list.get(j).get(index.get(j)));
				System.out.print(list.get(j).get(index.get(j)));				
			}
			resultList.add(tempList);			
			System.out.println();
		}	
		System.out.println("������ӡinstantiation~");
		
		System.out.println("��ʼ��ӡresultList~");
		for(int p =0;p<resultList.size();p++){
			for(int p1=0;p1<resultList.get(p).size();p1++){
				System.out.print(resultList.get(p).get(p1));
			}
			System.out.println();
		}
		System.out.println("������ӡresultList~");
		return resultList;
	}
	
	
	/**
	 * ��u'ux����u'ux'
	 * @param up
	 * @param u
	 * @param x
	 */
	public ArrayList<String> instantiationHeBing(ArrayList<String> up,ArrayList<String> u,String x){
		ArrayList<String> result = (ArrayList<String>) up.clone();
		ArrayList<String> uclone = (ArrayList<String>) u.clone();
		if(result.contains("null")||result.isEmpty()){
			result.clear();
		}
		if(uclone.contains("null")||uclone.isEmpty()){
			uclone.clear();
		}
		
		for(int i=0;i<uclone.size();i++){
			result.add(uclone.get(i));
		}
		result.add(x);
		return result;
	}
	
	/**
	 * ����һ��Xi����
	 * @param nodeName
	 * @param table
	 * @param pList
	 * @return
	 */
	public Xi gouzaoXI(String nodeName,CPTable table,ArrayList<String> pList){
		
			Xi x = new Xi(nodeName);
			x.setCPT(table);
			x.setParent(pList);
			System.out.println("�ɹ��Ĺ�����"+nodeName);
			return x;
	}
	/**
	 * ����Xi��V-Pa(Xi)U{Xi}����
	 * @param nodeName
	 * @param pList
	 * @param x
	 */
	public ArrayList<String> jisuanUPSet(ArrayList<String> nodeName,ArrayList<String> pList,Xi x){
		if(pList.contains("null")||pList.isEmpty()){
			pList.clear();
		}
		pList.add(x.getName());
		ArrayList<String> result = setMinus(nodeName,pList);
		return result;
	}
   	/**
	 * �㷨��ʵ��
	 */
	public ArrayList<ArrayList<ArrayList<String>>> cpnetAlgorithm(int n,ArrayList<String> nodeName,ArrayList<CPTable> table){
		System.out.println("*��ʼ��ӡ���е�nodeName~");
		for(int i=0;i<nodeName.size();i++){
			System.out.println(nodeName.get(i) +",i = "+i);			
		}
		System.out.println("*������ӡ���е�nodeName~");
		
		//r=�ռ�
		ArrayList<ArrayList<ArrayList<String>>> r = new ArrayList<ArrayList<ArrayList<String>>>();
		//foreach node Xi in cp-net N do
		for(int i=0;i<nodeName.size();i++){
			//����һ��Xi����
			new XmlParser("Qos2CPT.xml");
			ArrayList<String> pList = XmlParser.getEachNodeParent(nodeName.get(i));
			System.out.println("nodeName.get(i): "+nodeName.get(i)+",i="+i);
			Xi xi = gouzaoXI(nodeName.get(i),table.get(i),pList);
			
			//foreach Pair in cpt(Xi) do
			for(int j=0;j<xi.getCPT().getPairs().size();j++){
				ArrayList<ArrayList<String>> uplist = instantiation(jisuanUPSet(nodeName,pList,xi));
				
				System.out.println("��ʼ��ӡupList");
				for(int gg=0;gg<uplist.size();gg++){
					
					for(int gg1 = 0;gg1<uplist.get(gg).size();gg1++){
						System.out.print(uplist.get(gg).get(gg1));
					}
					System.out.println();
				}
				System.out.println("������ӡupList");
				//foreach instantiation u' of V-Pa(Xi)U{Xi} do
				for(int k=0;k<uplist.size();k++){
					//ÿ��Pair������һ��u,x>x'
					//r = rU(u'ux,u'ux')
					for(int z=0;z<xi.getCPT().getPairs().size();z++){
						ArrayList<String> ulist = xi.getCPT().getPairs().get(z).u;
						ArrayList<String> x1 = instantiationHeBing(uplist.get(k),ulist,xi.getCPT().getPairs().get(z).first);
						ArrayList<String> x2 = instantiationHeBing(uplist.get(k),ulist,xi.getCPT().getPairs().get(z).last);
						
						ArrayList<ArrayList<String>> tempr = new ArrayList<ArrayList<String>>();
						tempr.add(x1);
						tempr.add(x2);
						System.out.println("tempr.add(x1);x1="+x1.get(0)+x1.get(1)+x1.get(2));
						r.add(tempr);
					}
			
				}
			}
		}
		return r;
	}
	
	/**
	 * ��ӡ�����r
	 */
	public static void printr(ArrayList<ArrayList<ArrayList<String>>> r){
		System.out.println("��ʼ��ӡ�����r��");
		for(int i=0;i<r.size();i++){
			for(int j=0;j<r.get(i).size();j++){
				for(int k=0;k<r.get(i).get(j).size();k++){
					Collections.sort(r.get(i).get(j));
					System.out.print(r.get(i).get(j).get(k));
				}
				if(j==0)
					System.out.print(">");
			}
			System.out.println();
		}
		System.out.println("������ӡ�����r��");
	}
	
	
	public static void main(String[] args){
		CPnet c = new CPnet();
		/*
		String[] V1 = {"A","B","C"};
		String[] U1 = {"A","B","C"};
		ArrayList<String> V = new ArrayList<String>();
		ArrayList<String> U = new ArrayList<String>();
		V.addAll(Arrays.asList(V1));
		U.addAll(Arrays.asList(U1));
		
		ArrayList<String> res = c.setMinus(V, U);
		System.out.println("��ӡ�");
		for(int i=0;i<res.size();i++){
			System.out.println(res.get(i));
		}
		
		
		String[] V1 = {"B","C","D"};
		ArrayList<String> V = new ArrayList<String>();
		V.addAll(Arrays.asList(V1));
		c.instantiation(V);	
		*/
		XmlParser x = new XmlParser("Qos2CPT.xml");
		ArrayList<String> nodeName = XmlParser.getAllNodesInGraph();
		
//		System.out.println("��ʼ��ӡ���е�nodeName~");
//		for(int i=0;i<nodeName.size();i++){
//			System.out.println(nodeName.get(i) +",i = "+i);			
//		}
//		System.out.println("������ӡ���е�nodeName~");
		
		ArrayList<CPTable> tableList = x.getAllCPTable();
		
		
//		System.out.println("��ʼ��ӡ���е�CPTable~");
//		for(int i=0;i<tableList.size();i++){
//			CPTable t = tableList.get(i);
//			t.printCPT();
//		}
//		System.out.println("������ӡ���е�CPTable~");
		
		ArrayList<ArrayList<ArrayList<String>>> r = c.cpnetAlgorithm(nodeName.size(), nodeName, tableList);
		printr(r);
	}
	
	
	
}

/**
 * �ڵ��࣬ÿ���ڵ���һ���ڵ���
 * @author Administrator
 *
 */
class Node{
	String nodeName;
	ArrayList<String> nodeRank = new ArrayList<String>();
	
	public Node(String nodeName){
		this.nodeName = nodeName;
		nodeRank.add(nodeName.toLowerCase()+"1");
		nodeRank.add(nodeName.toLowerCase()+"2");
	}
}