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
	 * ��QosList����һ������
	 * @param qosList
	 */
	public Service(ArrayList<String> qosList){
		this.qosList.clear();
		this.qosList.addAll(qosList);
	}	
	/**
	 * ������������qos
	 */
	public void setQos(ArrayList<String> qosList) {
		// TODO Auto-generated method stub	
		this.qosList.clear();
		this.qosList.addAll(qosList);
	}
	
	/**
	 * ��Service��String������
	 */
	public String toString(){
		String s = null;
		for(int i=0;i<qosList.size();i++)
			s += qosList.get(i);
		return s;
	}
	
	
	/**
	 * �ж�����Service�����Ƿ����
	 */
	@Override
	//��Object��̳ж���
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
