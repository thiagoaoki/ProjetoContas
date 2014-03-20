package com.contas.Classes;

public class Conta {
	private int id;
	private String descricao;
	private String valor;
	private String tipo;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getdescricao() {
		return this.descricao;
	}

	public void setdescricao(String valor) {
		this.descricao = valor;
	}
	
	public String getvalor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.descricao = valor;
	}	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String valor) {
		this.tipo = valor;
	}
	
	
	@Override
	public String toString() {
		return descricao;
	}
}