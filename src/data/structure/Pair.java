package data.structure;

import java.util.ArrayList;

public class Pair {
	
	
	int i; //i
	ArrayList<String> u = new ArrayList<String>(); //���׼��ϵ�ʵ��
	String first; //x>x'��ռ�ŵ�һ��
	String last; //x>x'�в�ռ�ŵ�һ��
	
	/**
	 * Pair�Ĺ��캯��
	 * @param ix
	 * @param ux
	 * @param firstx
	 * @param lastx
	 */
	public Pair(String ix, String ux, String firstx, String lastx) {
		// TODO Auto-generated constructor stub
		i = Integer.parseInt(ix);
		u=parseU(ux);
		first=firstx;
		last=lastx;
		
	}
	/**
	 * ��ȡ��Ԫ��ʵ���ļ���u
	 * @param u
	 * @return
	 */
	public ArrayList<String> parseU(String u){
		ArrayList<String> r = new ArrayList<String>();
		//���u��Ϊ�գ����u
		if(u.isEmpty()==false){

			if(u.contains(":")){//u�Ƕ����Ԫ��ʵ���ļ���
				String[] uset = u.split(":");
				for(int i=0;i<uset.length;i++){
					r.add(uset[i]);
				}
				return r;
			}
			else{//uֻ����һ����Ԫ��ʵ��
				r.add(u);
				return r;
			}
		}
		return r;
	}

	
	public String getLast() {
		// TODO Auto-generated method stub
		return last;
	}

	public String getFirst() {
		// TODO Auto-generated method stub
		return first;
	}
	/**
	 * ��ȡPair��i
	 * @return
	 */
	public String getI(){
		return i+"";
	}
	/**
	 * ��ȡPair�ĸ��׼��ϵ�ʵ��u
	 * @return
	 */
	public ArrayList<String> getu(){
		return u;
	}
	
	
}
