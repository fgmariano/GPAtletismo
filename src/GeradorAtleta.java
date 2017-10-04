import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import persistence.GenericDAO;

public class GeradorAtleta {
	private String[] nomeM = { "John", "Till", "Walter", "Fern", "James", "Leonard", 
			"Ian", "Hank", "Richard", "Stephan" };
	private String[] nomeF = { "Christie", "June", "Anna", "Natalie", "Carla", "Lili",
			"Debbie", "Sue", "Florence", "Rose", "Heather", "Ella" };
	private String[] sobrenome = { "Lindemann", "Mariano", "Cohen", "Kobayashi",
			"Cash", "Alonso", "Kruspe", "Kakko", "Torvalds", "Murdock", "Gosling",
			"Grimes", "Underwood", "McGill", "White", "Pinkman", "Schrader", "Prohaska"};
	private ArrayList<String> pais = new ArrayList<String>();
	private ArrayList<String> nomesM = new ArrayList<String>();
	private ArrayList<String> nomesF = new ArrayList<String>();
	private Connection c = new GenericDAO().getConnection();
	
	public void getPais() {
		try {
			PreparedStatement ps = c.prepareStatement("SELECT codigo FROM paises");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pais.add(rs.getString("codigo"));
			}
			rs.close();
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void makeNamesM() {
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			nomesM.add(nomeM[r.nextInt(9)]+" "+sobrenome[r.nextInt(17)]);
		}
	}
	
	public void makeNamesF() {
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			nomesF.add(nomeF[r.nextInt(11)]+" "+sobrenome[r.nextInt(17)]);
		}
	}
	
	public void checkNamesM() {
		for (int i = 0; i < 100; i++) {
			for (int j = (i+1); j < 100; j++) {
				if ( nomesM.get(i).equals(nomesM.get(j)) ) {
					nomesM.set(j, "ERROR");
				}
			}
		}
	}
	
	public void checkNamesF() {
		for (int i = 0; i < 100; i++) {
			for (int j = (i+1); j < 100; j++) {
				if ( nomesF.get(i).equals(nomesF.get(j)) ) {
					nomesF.set(j, "ERROR");
				}
			}
		}
	}
	
	public void makeInserts() {
		Random r = new Random();
		for (int i = 0; i < nomesM.size(); i++) {
			System.out.println("('"+nomesM.get(i)+"', 'M', '"+pais.get(r.nextInt(205))+"'),");
			System.out.println("('"+nomesF.get(i)+"', 'F', '"+pais.get(r.nextInt(205))+"'),");
		}
	}
		
	public static void main(String[] args) {
		GeradorAtleta ga = new GeradorAtleta();
		ga.getPais();
		ga.makeNamesM();
		ga.makeNamesF();
		ga.checkNamesM();
		ga.checkNamesF();
		ga.makeInserts();
	}
}
