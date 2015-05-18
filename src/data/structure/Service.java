package data.structure;

import java.util.ArrayList;

public class Service {
	
	String ServiceName;
	ArrayList<String> qosList = new ArrayList<String>();
	
	public Service(){
		
	}
	public Service(String ServiceName){
		this.ServiceName = ServiceName;
	}
	/**
	 * 用QosList构造一个服务
	 * @param qosList
	 */
	public Service(ArrayList<String> qosList){
		this.qosList.clear();
		this.qosList.addAll(qosList);
	}	
	/**
	 * 设置这个服务的qos
	 */
	public void setQos(ArrayList<String> qosList) {
		// TODO Auto-generated method stub	
		this.qosList.clear();
		this.qosList.addAll(qosList);
	}
	
	/**
	 * 将Service用String表达出来
	 */
	public String toString(){
		String s = null;
		for(int i=0;i<qosList.size();i++)
			s += qosList.get(i);
		return s;
	}
	
	
	/**
	 * 判断两个Service对象是否相等
	 */
	@Override
	//从Object类继承而来
	public boolean equals(Object obj) {
		Service otherService = (Service)obj;
		if((otherService.toString().contains("null"))||otherService.qosList.contains(null)){
			return false;
		}
		if(obj==this){
			return true;
		}
		if(otherService.qosList.equals(this.qosList)){
			return true;
		}
		return false;
	}
	
	
	
}
