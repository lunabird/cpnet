package DBManage;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestDB {
	public static void main(String[] args) throws SQLException{
		UseUserTable u = new UseUserTable();
		ArrayList<String> s = new ArrayList<String>();
		s.add("A");s.add("C");
		ArrayList<String[]> r = u.searchUSet(s);
		for(int i = 0;i<r.size();i++){
			System.out.println(r.get(i)[0]+","+r.get(i)[1]);
		}
		
		
		
		
	}





}

