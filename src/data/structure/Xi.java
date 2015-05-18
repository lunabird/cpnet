package data.structure;

import java.util.ArrayList;

public class Xi {

	String name;	//节点的名称
	CPTable CPT ;
	ArrayList<Xi> Parent;//Xi的父亲节点的集合
	
	public Xi(){
	}
	
	public Xi(String name){
		this.name = name;
	}
	
	/**
	 * 设置Xi的CPTable
	 * @param cpTable
	 */
	public void setCPT(CPTable cpTable) {
		// TODO Auto-generated method stub
		Parent = new ArrayList<Xi>();
		CPT= cpTable;
		CPT.id = cpTable.id;
		CPT.Pairs = cpTable.getPairs(); //ArrayList<Pair>
		ArrayList<String> pl = cpTable.getParentu();
		
		for(int i=0;i<pl.size();i++){
			Xi xp = new Xi(pl.get(i).substring(0, 1).toUpperCase());
			Parent.add(xp);
		}
		System.out.println(name +"成功的setCPT，cpTable.id ="+cpTable.id);
	}
	/**
	 * 打印Xi的信息
	 */
	public void printXiInfo() {
		// TODO Auto-generated method stub
		System.out.println("打印Xi的信息，开始！");
		Parent = new ArrayList<Xi>();
		System.out.println("Xi name is ="+name+"\nXi Parent is :\n");
		for(int i=0;i<Parent.size();i++){
			System.out.println(Parent.get(i).getName());
		}
		System.out.println("打印Xi的信息，完毕！");
	}

	/**
	 * 设置Xi的父亲集合U,参数是例如{A，B，C}等的节点名称的集合
	 * @param pList
	 */
	public void setParent(ArrayList<String> pList) {
		// TODO Auto-generated method stub
		if(pList.isEmpty()||pList.contains("Null")){
			
		}else{
			for(int i=0;i<pList.size();i++){
				Xi xp = new Xi(pList.get(i));
				Parent.add(xp);
			}
		}
		System.out.println(name+" setParent执行完毕~");
	}
	/**
	 * 获取Xi的节点名称，例如A
	 * @return
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/**
	 * 获取Xi的父亲节点的集合，ArrayList<Xi>
	 * @return
	 */
	public ArrayList<Xi> getParent() {
		// TODO Auto-generated method stub		
		return Parent;
	}
	/**
	 * 获取Xi的CPT
	 * @return
	 */
	public CPTable getCPT() {
		// TODO Auto-generated method stub
		return CPT;
	}

}
