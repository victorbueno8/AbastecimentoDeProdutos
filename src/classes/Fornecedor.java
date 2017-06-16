package classes;

import java.util.ArrayList;
import java.util.Iterator;

public class Fornecedor {
	private String nome;
	private String fabricacaoLocalidade;
	
	public Fornecedor(String nome, String fabricacaoLocalidade) {
		this.nome = nome;
		this.fabricacaoLocalidade = fabricacaoLocalidade;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	public String getNome() {
		return nome;
	}

	public String getFabricacaoLocalidade() {
		return fabricacaoLocalidade;
	}

	
}
