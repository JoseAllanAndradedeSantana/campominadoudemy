package br.com.jaassoftwareeconsultoria.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.jaassoftwareeconsultoria.exception.ExplosaoException;
import br.com.jaassoftwareeconsultoria.exception.SairException;
import br.com.jaassoftwareeconsultoria.model.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	Scanner entradaUsuario = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		super();
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			while(continuar) {
				cicloJogo();
				System.out.println("Outra partida ? (S/n)");
				String respostaUsuario = entradaUsuario.nextLine();
				//n.equalsIgnoreCase(respostaUsuario)
				if(respostaUsuario.equalsIgnoreCase("n")) {
					continuar = false;
					System.out.println("Fim de Jogo");
				}else {
					tabuleiro.reiniciar();
				}
			}
		}catch (SairException e) {
			System.out.println("Saiu do Jogo!!! ");
		} finally {
			entradaUsuario.close();
		}
		
	}

	private void cicloJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (Linha,Coluna):");
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(elementos -> Integer.parseInt(elementos.trim())).iterator();
				/*
				 * System.out.println(xy.next()); System.out.println(xy.next());
				 */
				
				digitado = capturarValorDigitado("(1)- Abrir ou (2)- Marcar");
				System.out.println(digitado);
				if("1".equals(digitado)) {
					tabuleiro.abrirCampo(xy.next(), xy.next());
					
				}else if("2".equals(digitado)) {
					tabuleiro.marcar(xy.next(), xy.next());
				}
			}
			System.out.println("Você Ganhou!!");
		}catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você Perdeu!!");
			
		}
			
		
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entradaUsuario.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}

}
