package com.contas.Classes;

public class Debito {

	private int id;
	private String descricao;
	private String valor;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getdescricao() {
		return this.descricao;
	}

	public void setdescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getvalor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.descricao = valor;
	}	

	@Override
	public String toString() {
		return descricao;
	}

}
