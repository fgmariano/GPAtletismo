package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Resultado;

public class AtletismoDAO {
	
	private Connection con;
	
	public AtletismoDAO() {
		GenericDAO gen = new GenericDAO();
		con = gen.getConnection();
	}
	
	public ArrayList<String> getProvas() {
		ArrayList<String> lista = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT prova FROM provas");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				lista.add(rs.getString("prova"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void checarRecorde(ArrayList<Resultado> lista, int prova) {
		for (int i = 0; i < lista.size(); i++) {
			if (!lista.get(i).getResultado().equals("FAULT") & !lista.get(i).getResultado().equals("DNF")) {
				try {
					PreparedStatement ps = con.prepareStatement("SELECT dbo.fn_checkRecorde(?, ?) AS recorde");
					ps.setInt(1, prova);
					ps.setString(2, lista.get(i).getResultado());
					ResultSet rs = ps.executeQuery();
					rs.next();
					if (rs.getInt("recorde") == 1)
						lista.get(i).setRecorde_evento(true);
					if (rs.getInt("recorde") == 2)
						lista.get(i).setRecorde_mundial(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<Resultado> listarResultados(int prova, int fase) {
		if (prova == 1 | prova == 2 | prova == 3 | prova == 6 | prova == 9 | prova == 11) {
			return listarResultadosSalto(prova, fase);
		} else {
			return listarResultadosCorrida(prova, fase);
		}
	}
	
	public ArrayList<Resultado> listarResultadosSalto(int prova, int fase) {
		ArrayList<Resultado> lista = new ArrayList<Resultado>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * from fn_resultadoProva(?, ?) ORDER BY resultado DESC");
			ps.setInt(1, prova);
			ps.setInt(2, fase);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if (rs.getString("resultado")==null)
					return lista;
				Resultado res = new Resultado();
				res.setCod_atleta(rs.getInt("codAtleta"));
				res.setNome_atleta((rs.getString("nomeAtleta")));
				res.setNome_pais(rs.getString("nomePais"));
				res.setResultado(rs.getString("resultado"));
				if (res.getResultado().equals("0"))
					res.setResultado("FAULT");
				lista.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checarRecorde(lista, prova);
		return lista;
	}
	
	public ArrayList<Resultado> listarResultadosCorrida(int prova, int fase) {
		ArrayList<Resultado> lista = new ArrayList<Resultado>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * from fn_resultadoProva(?, ?) ORDER BY resultado ASC");
			ps.setInt(1, prova);
			ps.setInt(2, fase);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if (rs.getString("resultado")==null)
					return lista;
				Resultado res = new Resultado();
				res.setCod_atleta(rs.getInt("codAtleta"));
				res.setNome_atleta((rs.getString("nomeAtleta")));
				res.setNome_pais(rs.getString("nomePais"));
				res.setResultado(rs.getString("resultado"));
				if (res.getResultado().equals("0"))
					res.setResultado("DNF");
				lista.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checarRecorde(lista, prova);
		return lista;
	}
	
}
