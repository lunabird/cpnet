package data.structure;

import java.util.ArrayList;

public class CPTable {

	String id;
	ArrayList<Pair> Pairs = new ArrayList<Pair>();
	ArrayList<String> parent = new ArrayList<String>();
	
	public CPTable(){
		
	}
	
	public CPTable(String id){
		this.id=id;
	}
	
	
	public ArrayList<Pair> getPairs() {
		// TODO Auto-generated method stub
		return Pairs;
	}
	
	public void setPairs(Pair p){
		Pairs.add(p);
	}
	
	/**
	 * ��ø��׼���ʵ��u
	 * @return
	 */
	public ArrayList<String> getParentu(){
		ArrayList<String> u = Pairs.get(0).getu();			
		if(u.isEmpty()||u.contains("null")){
			return parent;
		}else{
			for(int i=0;i<u.size();i++){
				parent.add(u.get(i));
			}
		}
		return parent;	
		
	}
	
	/**
	 * ��ӡCPTable�����ݣ���Ҫ�Ǵ�ӡPairs
	 */
	public void printCPT(){
		System.out.println("��ʼ��ӡcptable");
		for(int i=0;i<Pairs.size();i++){
			String a = Pairs.get(i).getFirst();
			String b = Pairs.get(i).getLast();
			ArrayList<String> u = Pairs.get(i).getu();
			String gg = Pairs.get(i).getI();
			for(int j=0;j<u.size();j++)
			{
				System.out.println("U="+u.get(j));
			}
			System.out.println("here is read pairs: i = "+gg+"\nfirst = "+a+"\nlast = "+b+"\n");
		}
		System.out.println("������ӡcptable");
	}
	
	
}
