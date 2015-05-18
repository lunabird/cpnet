package data.structure;

import java.util.ArrayList;

public class Xi {

	String name;	//�ڵ������
	CPTable CPT ;
	ArrayList<Xi> Parent;//Xi�ĸ��׽ڵ�ļ���
	
	public Xi(){
	}
	
	public Xi(String name){
		this.name = name;
	}
	
	/**
	 * ����Xi��CPTable
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
		System.out.println(name +"�ɹ���setCPT��cpTable.id ="+cpTable.id);
	}
	/**
	 * ��ӡXi����Ϣ
	 */
	public void printXiInfo() {
		// TODO Auto-generated method stub
		System.out.println("��ӡXi����Ϣ����ʼ��");
		Parent = new ArrayList<Xi>();
		System.out.println("Xi name is ="+name+"\nXi Parent is :\n");
		for(int i=0;i<Parent.size();i++){
			System.out.println(Parent.get(i).getName());
		}
		System.out.println("��ӡXi����Ϣ����ϣ�");
	}

	/**
	 * ����Xi�ĸ��׼���U,����������{A��B��C}�ȵĽڵ����Ƶļ���
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
		System.out.println(name+" setParentִ�����~");
	}
	/**
	 * ��ȡXi�Ľڵ����ƣ�����A
	 * @return
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/**
	 * ��ȡXi�ĸ��׽ڵ�ļ��ϣ�ArrayList<Xi>
	 * @return
	 */
	public ArrayList<Xi> getParent() {
		// TODO Auto-generated method stub		
		return Parent;
	}
	/**
	 * ��ȡXi��CPT
	 * @return
	 */
	public CPTable getCPT() {
		// TODO Auto-generated method stub
		return CPT;
	}

}
