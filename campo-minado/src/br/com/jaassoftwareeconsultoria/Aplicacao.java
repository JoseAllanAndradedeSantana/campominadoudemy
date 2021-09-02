package br.com.jaassoftwareeconsultoria;

import br.com.jaassoftwareeconsultoria.model.Tabuleiro;
import br.com.jaassoftwareeconsultoria.view.TabuleiroConsole;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(10,10,10);
		new TabuleiroConsole(tabuleiro);
		//tabuleiro.abrirCampo(3, 3);
		//System.out.println(tabuleiro);
		
		
		
		
		
	}

}
