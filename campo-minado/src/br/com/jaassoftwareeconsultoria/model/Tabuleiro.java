package br.com.jaassoftwareeconsultoria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.jaassoftwareeconsultoria.exception.ExplosaoException;

public class Tabuleiro {

	
	private int quantidadeLinhas;
	private int quantidadeColunas;
	private int quantidadeMinas;
	private List<Campo> campos = new ArrayList<>();

	public Tabuleiro() {
		super();
	}

	public Tabuleiro(int quantidadeLinhas, int quantidadeColunas, int quantidadeMinas) {
		super();
		this.quantidadeLinhas = quantidadeLinhas;
		this.quantidadeColunas = quantidadeColunas;
		this.quantidadeMinas = quantidadeMinas;

		gerarCampos();
		associarOsVizinhos();
		sortearMinas();
	}
	
	public void abrirCampo(int linha, int coluna) {
		try {
			campos.stream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna).findFirst().ifPresent(campo -> campo.abrirCampo());
		} catch (ExplosaoException e) {
			campos.forEach(campo -> campo.setAberto(true));
			throw e;
		}
	}
	public void marcar(int linha, int coluna) {
		campos.stream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna).findFirst().ifPresent(campo -> campo.alternarMarcacao());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		System.out.println("    JOGO CAMPO MINADO");
		sb.append("  ");
		for(int coluna = 0; coluna < quantidadeColunas; coluna++) {
			sb.append(" ");
			sb.append(coluna);
			sb.append(" ");
		}
		sb.append("\n");
		int i = 0;
		for (int l = 0; l < quantidadeLinhas; l++) {
			sb.append(l);
			sb.append(" ");
			for (int c = 0; c < quantidadeColunas; c++) {

				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;

			}

			sb.append("\n");
			
		}
		
		return sb.toString();
		
	}

	// @Override
	// public String toString() {
	// return "Tabuleiro [quantidadeLinhas=" + quantidadeLinhas + ",
	// quantidadeColunas=" + quantidadeColunas
	// + ", quantidadeMinas=" + quantidadeMinas + ", campos=" + campos + "]";
	// }

	public void reiniciar() {
		campos.stream().forEach(campo -> campo.reiniciar());
		sortearMinas();
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(campos -> campos.objetivoAlcancado());
	}

	private void sortearMinas() {

		long minasArmadas = 0;
		Predicate<Campo> minado = campo -> campo.isMinado();
		do {
			int valorAleatorio = (int) (Math.random() * campos.size());
			campos.get(valorAleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		} while (minasArmadas < quantidadeMinas);

	}

	private void associarOsVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}

	}

	private void gerarCampos() {
		for (int i = 0; i < quantidadeLinhas; i++) {
			for (int j = 0; j < quantidadeColunas; j++) {
				
				campos.add(new Campo(i, j));
			}
		}

	}

}
