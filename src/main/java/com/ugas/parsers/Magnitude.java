package com.ugas.parsers;

public enum Magnitude {
	NA("NA"),
	K("K"),
	CL("CL"),
	CA("CA"),
	LI("LI"),
	pH("pH"),
  CO2("CO2"),
	O2("O2");
	
	private String codigo;

	Magnitude(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
