package model;

public class Resultado {
	private int cod_atleta;
	private String nome_atleta;
	private String nome_pais;
	private String resultado;
	private boolean recorde_mundial;
	private boolean recorde_evento;
	
	public int getCod_atleta() {
		return cod_atleta;
	}
	public void setCod_atleta(int cod_atleta) {
		this.cod_atleta = cod_atleta;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getNome_atleta() {
		return nome_atleta;
	}
	public void setNome_atleta(String nome_atleta) {
		this.nome_atleta = nome_atleta;
	}
	public String getNome_pais() {
		return nome_pais;
	}
	public void setNome_pais(String nome_pais) {
		this.nome_pais = nome_pais;
	}
	public boolean isRecorde_mundial() {
		return recorde_mundial;
	}
	public void setRecorde_mundial(boolean recorde_mundial) {
		this.recorde_mundial = recorde_mundial;
	}
	public boolean isRecorde_evento() {
		return recorde_evento;
	}
	public void setRecorde_evento(boolean recorde_evento) {
		this.recorde_evento = recorde_evento;
	}
}
