package data.structure;

import java.util.ArrayList;

public class Pair {
	
	
	int i; //i
	ArrayList<String> u = new ArrayList<String>(); //父亲集合的实例
	String first; //x>x'中占优的一方
	String last; //x>x'中不占优的一方
	
	/**
	 * Pair的构造函数
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
	 * 获取父元素实例的集合u
	 * @param u
	 * @return
	 */
	public ArrayList<String> parseU(String u){
		ArrayList<String> r = new ArrayList<String>();
		//如果u不为空，获得u
		if(u.isEmpty()==false){

			if(u.contains(":")){//u是多个父元素实例的集合
				String[] uset = u.split(":");
				for(int i=0;i<uset.length;i++){
					r.add(uset[i]);
				}
				return r;
			}
			else{//u只包含一个父元素实例
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
	 * 获取Pair的i
	 * @return
	 */
	public String getI(){
		return i+"";
	}
	/**
	 * 获取Pair的父亲集合的实例u
	 * @return
	 */
	public ArrayList<String> getu(){
		return u;
	}
	
	
}
