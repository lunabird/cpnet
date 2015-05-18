package data.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import xmlParse.XmlParser;

public class CPnet {
	
	/**
	 * 求两个集合的差，例如V-U
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
	 * 求一个集合和一个元素的并集
	 */
	public ArrayList<String> joinTwoSets(ArrayList<String> V1,String Xi){
		ArrayList<String> V = (ArrayList<String>) V1.clone();
		V.add(Xi);
		return V;
	}
	
	/**
	 * 求集合的实例的运算instantiation
	 */
	public ArrayList<ArrayList<String>> instantiation(ArrayList<String> nodeName1){
		ArrayList<String> nodeName = (ArrayList<String>) nodeName1.clone();
		int n = nodeName.size();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
		if(nodeName.contains("null")||n==0){
			resultList.clear();
			System.out.println("此集合的instantiation为空~");
			return resultList;
		}
		
		for(int i=0;i<n;i++){
//			char b = (char) (i+65);
			Node node = new Node(nodeName.get(i));
			list.add(node.nodeRank);
		}		
		
		System.out.println("开始打印instantiation~");
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
		System.out.println("结束打印instantiation~");
		
		System.out.println("开始打印resultList~");
		for(int p =0;p<resultList.size();p++){
			for(int p1=0;p1<resultList.get(p).size();p1++){
				System.out.print(resultList.get(p).get(p1));
			}
			System.out.println();
		}
		System.out.println("结束打印resultList~");
		return resultList;
	}
	
	
	/**
	 * 求u'ux或者u'ux'
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
	 * 构造一个Xi对象
	 * @param nodeName
	 * @param table
	 * @param pList
	 * @return
	 */
	public Xi gouzaoXI(String nodeName,CPTable table,ArrayList<String> pList){
		
			Xi x = new Xi(nodeName);
			x.setCPT(table);
			x.setParent(pList);
			System.out.println("成功的构造了"+nodeName);
			return x;
	}
	/**
	 * 计算Xi的V-Pa(Xi)U{Xi}集合
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
	 * 算法的实现
	 */
	public ArrayList<ArrayList<ArrayList<String>>> cpnetAlgorithm(int n,ArrayList<String> nodeName,ArrayList<CPTable> table){
		System.out.println("*开始打印所有的nodeName~");
		for(int i=0;i<nodeName.size();i++){
			System.out.println(nodeName.get(i) +",i = "+i);			
		}
		System.out.println("*结束打印所有的nodeName~");
		
		//r=空集
		ArrayList<ArrayList<ArrayList<String>>> r = new ArrayList<ArrayList<ArrayList<String>>>();
		//foreach node Xi in cp-net N do
		for(int i=0;i<nodeName.size();i++){
			//构造一个Xi对象
			new XmlParser("Qos2CPT.xml");
			ArrayList<String> pList = XmlParser.getEachNodeParent(nodeName.get(i));
			System.out.println("nodeName.get(i): "+nodeName.get(i)+",i="+i);
			Xi xi = gouzaoXI(nodeName.get(i),table.get(i),pList);
			
			//foreach Pair in cpt(Xi) do
			for(int j=0;j<xi.getCPT().getPairs().size();j++){
				ArrayList<ArrayList<String>> uplist = instantiation(jisuanUPSet(nodeName,pList,xi));
				
				System.out.println("开始打印upList");
				for(int gg=0;gg<uplist.size();gg++){
					
					for(int gg1 = 0;gg1<uplist.get(gg).size();gg1++){
						System.out.print(uplist.get(gg).get(gg1));
					}
					System.out.println();
				}
				System.out.println("结束打印upList");
				//foreach instantiation u' of V-Pa(Xi)U{Xi} do
				for(int k=0;k<uplist.size();k++){
					//每个Pair都包含一个u,x>x'
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
	 * 打印结果集r
	 */
	public static void printr(ArrayList<ArrayList<ArrayList<String>>> r){
		System.out.println("开始打印结果集r！");
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
		System.out.println("结束打印结果集r！");
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
		System.out.println("打印差集");
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
		
//		System.out.println("开始打印所有的nodeName~");
//		for(int i=0;i<nodeName.size();i++){
//			System.out.println(nodeName.get(i) +",i = "+i);			
//		}
//		System.out.println("结束打印所有的nodeName~");
		
		ArrayList<CPTable> tableList = x.getAllCPTable();
		
		
//		System.out.println("开始打印所有的CPTable~");
//		for(int i=0;i<tableList.size();i++){
//			CPTable t = tableList.get(i);
//			t.printCPT();
//		}
//		System.out.println("结束打印所有的CPTable~");
		
		ArrayList<ArrayList<ArrayList<String>>> r = c.cpnetAlgorithm(nodeName.size(), nodeName, tableList);
		printr(r);
	}
	
	
	
}

/**
 * 节点类，每个节点有一个节点名
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