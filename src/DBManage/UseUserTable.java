package DBManage;

import java.util.ArrayList;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import data.structure.Service;
public class UseUserTable {

	private DBConnectionManager connMgr;
	private Connection con;
	/*
	 * ���캯��
	 */
	public UseUserTable() {
		connMgr = DBConnectionManager.getInstance();
		con = connMgr.getConnection();
		if (con == null) {
            System.out.println("Can't get connection");
            return;
        }
	}
	
	/**
	 * ��ȡ���ݿ������е�Service��¼,����n��ʾqos���Եĸ���,����ArrayList<Service>
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Service> getAllRecord(int n) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Service> result = new ArrayList<Service>();
		Statement statement;statement = con.createStatement();
		ResultSet rs=null;
		String sqlm ="SELECT * FROM service1";
		rs=statement.executeQuery(sqlm);
		while(rs.next())
		{				
			String Servicename = rs.getString("serviceID");
			Service ser = new Service(Servicename);
			ArrayList<String> qosList = new ArrayList<String>();
			for(int i=0;i<n;i++)
			{
				String x = rs.getString(i+2);
				qosList.add(x);
			}
			ser.setQos(qosList);			
			result.add(ser);
		}		
		return result;
	}

	public ArrayList<String[]> searchUSet(ArrayList<String> s) throws SQLException {
		// TODO Auto-generated method stub
		String s1 = s.get(0);
		String s2 = s.get(1);
		Statement statement;
		statement = con.createStatement();
		ResultSet rs=null;
		String sqlm ="SELECT DISTINCT "+s1+","+s2+" FROM service";
		rs=statement.executeQuery(sqlm);
		ArrayList<String[]> ins = new ArrayList<String[]>();
		while(rs.next()){
			String a = rs.getString(s1);
			String b = rs.getString(s2);
			String[] rr ={a,b};
			ins.add(rr);
		}		
		return ins;
	}
	
	/**
	 * ��ȡ���е�serviceID�ļ���
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> queryServiceID() throws SQLException {
		// TODO Auto-generated method stub
		Statement statement;statement = con.createStatement();
		ResultSet rs=null;
		String sqlm ="SELECT serviceID FROM service1";
		rs=statement.executeQuery(sqlm);
		ArrayList<String> idList = new ArrayList<String>();
		while(rs.next())
		{		
			String id = rs.getString(1);
			idList.add(id);
		}		
			
		return idList;
		
	}
	
	/**
	 * ����ĳ�����ID����ȡ�÷����qosList,����n��ʾqos���Եĸ���
	 * @param id
	 * @param n
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> readRecord(String id,int n) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement;statement = con.createStatement();
		ResultSet rs=null;
		String sqlm ="SELECT * FROM service1 WHERE serviceID = "+"'"+id+"'";
		rs=statement.executeQuery(sqlm);
		ArrayList<String> qosList = new ArrayList<String>();
		//�����ֻ��һ����¼������ֻѭ��һ��
		while(rs.next())
		{		
//			String serviceID = rs.getString(1);
			for(int i=0;i<n;i++)
			{
				String x = rs.getString(i+2);
				qosList.add(x);
			}
		}		
			
		return qosList;
	}

	

}
