package br.com.tita.service;

import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.tita.dao.ContatoDAO;
import br.com.tita.modelo.Contato;

public class ContatoService {
	public static final int LISTAR_CONTATOS = 1;
	private static final int CADASTRAR_CONTATO = 2;
	private static final int EDITAR_CONTATO = 3;
	private static final int REMOVER_CONTATO = 4;
	
	private Scanner scanner = new Scanner(System.in);
	private ContatoDAO dao = new ContatoDAO();
	
	public void menuOpcoes() {
		System.out.println("\n\n");
		System.out.println("Escolha uma opção");
		System.out.println("(1) Listar Contatos");
		System.out.println("(2) Cadastrar Novo");
		System.out.println("(3) Editar");
		System.out.println("(4) Remover");

		validaOpcaoMenu();
	}
	
	
	
	
	private void validaOpcaoMenu() {
		switch (scanner.nextInt()) {
		case ContatoService.LISTAR_CONTATOS:
			listaContatos();
			menuOpcoes();
			break;
		case ContatoService.CADASTRAR_CONTATO:
			cadastraNovoContato();
			break;
		case ContatoService.EDITAR_CONTATO:
			editar();
			break;
		case ContatoService.REMOVER_CONTATO:
			remover();
			break;
		default:
			break;
		}
		
	}


	private void editar() {
		listaContatos();
		System.out.println("Qual contato deseja editar?");
		Contato c = dao.getLista().get(scanner.nextInt());
		
		imprimePlaquinha(c);
		
		scanner = new Scanner(System.in);
		
		System.out.println("Insira um novo nome");
		c.setNome(scanner.nextLine());
		
		System.out.println("Insira um novo número do telefone");
		c.setTelefone(scanner.nextLine());
		
		dao.altera(c);
		System.out.println("Contato alterado com sucesso!! \n\n\n");
		menuOpcoes();
	}
	
	private void imprimePlaquinha(Contato c) {
		String nomeContato = "|   " +c.getNome() + " - " + c.getTelefone() + "  |";
		String tracinho = "-";
		for(int i = 0; i < nomeContato.length(); i++) {
			tracinho += "-";
		}
		System.out.println(tracinho);
		System.out.println(nomeContato);
		System.out.println(tracinho);
	}




	private void remover() {
		listaContatos();
		System.out.println("Qual contato deseja remover?");
		Contato c = dao.getLista().get(scanner.nextInt());
		
		dao.remove(c);
		System.out.println("Contato Removido com sucesso!! \n\n\n");
		menuOpcoes();
	}

	private void cadastraNovoContato() {
		scanner = new Scanner(System.in);
		Contato c = new Contato();
		System.out.println("Insira um nome");
		c.setNome(scanner.nextLine());
		
		System.out.println("Insira o número do telefone");
		c.setTelefone(scanner.nextLine());
		
		dao.adiciona(c);
		menuOpcoes();
	}

	public void listaContatos() {
		int position = 0;
		for (Contato contato : dao.getLista()) {
			System.out.println("Indice: " + position);
			System.out.println("Nome: " + contato.getNome());
			System.out.println("Tel.: " + contato.getTelefone());
			System.out.println("-----------------------------------------");
			position++;
		}
		position = 0;
	}

}
