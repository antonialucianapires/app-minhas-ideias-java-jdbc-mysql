package minhasideias;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String SUCCESS_ICON = "✔ ";
    public static final String FAILURE_ICON = "✖ ";
    
	public static void main(String[] args) {
		
		
		 Scanner scanner = new Scanner(System.in);
	        int opcao;

	        do {
	            exibirMenu();
	            System.out.print("Digite a opção desejada: ");
	            opcao = scanner.nextInt();
	            scanner.nextLine();  
	            switch (opcao) {
	                case 1:
	                    List<Ideia> ideias = IdeiaRepositorio.listarIdeias();
	                    if(ideias.isEmpty())
	                    	System.out.println(ANSI_RED + FAILURE_ICON + "Ainda não há ideias cadastradas!" + ANSI_RESET);
	                    System.out.println(ideias);
	                    break;
	                case 2:
	                	cadastrarNovaIdeia(scanner);
	                    break;
	                case 3:
	                	visualizarIdeiaPorId(scanner);
	                    break;
	                case 4:
	                    atualizarIdeia(scanner);
	                    break;
	                case 5:
	                    deletarIdeia(scanner);
	                    break;
	                case 6:
	                    adicionarComentario(scanner);
	                    break;
	                case 7:
	                    visualizarComentariosDeUmaIdeia(scanner);
	                    break;
	                case 8:
	                    deletarComentarios(scanner);
	                    break;
	                case 9:
	                    System.out.println("Saindo...");
	                    break;
	                default:
	                	System.out.println(ANSI_RED + FAILURE_ICON + "Opção inválida!" + ANSI_RESET);
	            }
	        } while (opcao != 9);

	        scanner.close();
	    }

		private static void exibirMenu() {
			System.out.println(ANSI_GREEN + "=========================================" + ANSI_RESET);
	        System.out.println(ANSI_GREEN + "            APP - Minhas Ideias          " + ANSI_RESET);
	        System.out.println(ANSI_GREEN + "=========================================" + ANSI_RESET);
	        System.out.println(ANSI_YELLOW + "1)" + ANSI_RESET + " Listar ideias");
	        System.out.println(ANSI_YELLOW + "2)" + ANSI_RESET + " Cadastrar nova ideia");
	        System.out.println(ANSI_YELLOW + "3)" + ANSI_RESET + " Visualizar uma ideia por ID");
	        System.out.println(ANSI_YELLOW + "4)" + ANSI_RESET + " Atualizar uma ideia");
	        System.out.println(ANSI_YELLOW + "5)" + ANSI_RESET + " Deletar uma ideia");
	        System.out.println(ANSI_YELLOW + "6)" + ANSI_RESET + " Adicionar comentário a uma ideia");
	        System.out.println(ANSI_YELLOW + "7)" + ANSI_RESET + " Visualizar comentários de uma ideia");
	        System.out.println(ANSI_YELLOW + "8)" + ANSI_RESET + " Deletar comentários de uma ideia");
	        System.out.println(ANSI_YELLOW + "9)" + ANSI_RESET + " Sair");
	        System.out.println();
	    }
	    
	    private static void cadastrarNovaIdeia(Scanner scanner) {
	        System.out.print("Digite o título da ideia: ");
	        String titulo = scanner.nextLine();
	        System.out.print("Digite a descrição da ideia: ");
	        String descricao = scanner.nextLine();

	        Ideia novaIdeia = new Ideia();
	        novaIdeia.setTitulo(titulo);
	        novaIdeia.setDescricao(descricao);
	        novaIdeia.setDataCriacao(LocalDateTime.now()); 

	        IdeiaRepositorio.cadastrarIdeia(novaIdeia);

	        System.out.println(ANSI_GREEN + SUCCESS_ICON + "Ideia cadastrada com sucesso!" + ANSI_RESET);
	        System.out.println();  
	    }
	    
	    private static void visualizarIdeiaPorId(Scanner scanner) { 
	        System.out.print("Digite o ID da ideia que deseja visualizar: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        Ideia ideia = IdeiaRepositorio.consultarPorId(id);

	        if (ideia != null) {
	            System.out.println("=========================================");
	            System.out.println("ID: " + ideia.getId());
	            System.out.println("Título: " + ideia.getTitulo());
	            System.out.println("Descrição: " + ideia.getDescricao());
	            System.out.println("Data de Criação: " + ideia.getDataCriacao());
	            System.out.println("=========================================");
	        } else {
	        	System.out.println(ANSI_RED + FAILURE_ICON + "Ideia com ID " + id + " não encontrada." + ANSI_RESET);
	        }
	        System.out.println();  
	    }
	    
	    private static void atualizarIdeia(Scanner scanner) {
	        System.out.print("Digite o ID da ideia que deseja atualizar: ");
	        int id = scanner.nextInt();
	        scanner.nextLine(); 

	        Ideia ideiaExistente = IdeiaRepositorio.consultarPorId(id);

	        if (ideiaExistente != null) {
	            System.out.println("Digite os novos dados da ideia ou pressione ENTER para manter o valor atual.");

	            System.out.print("Título [" + ideiaExistente.getTitulo() + "]: ");
	            String titulo = scanner.nextLine();
	            if (!titulo.isEmpty()) {
	                ideiaExistente.setTitulo(titulo);
	            }

	            System.out.print("Descrição [" + ideiaExistente.getDescricao() + "]: ");
	            String descricao = scanner.nextLine();
	            if (!descricao.isEmpty()) {
	                ideiaExistente.setDescricao(descricao);
	            }

	            IdeiaRepositorio.atualizarIdeia(ideiaExistente);
	            System.out.println(ANSI_GREEN + SUCCESS_ICON + "Ideia atualizada com sucesso!" + ANSI_RESET);

	        } else {
	        	System.out.println(ANSI_RED + FAILURE_ICON + "Ideia com ID " + id + " não encontrada." + ANSI_RESET);
	        }
	        System.out.println(); 
	    }
	    
	    private static void deletarIdeia(Scanner scanner) {
	        System.out.print("Digite o ID da ideia que deseja deletar: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();  

	        Ideia ideiaExistente = IdeiaRepositorio.consultarPorId(id);

	        if (ideiaExistente != null) {
	            List<Comentario> comentarios = ComentarioRepositorio.listarComentariosPorIdeiaId(id);
	            if (!comentarios.isEmpty()) {
	            	comentarios.forEach(comentario -> ComentarioRepositorio.deletarComentariosPorIdeiaId(comentario.getIdeiaID()));
	            	System.out.println(ANSI_GREEN + SUCCESS_ICON + "Todos os comentários associados à ideia foram deletados." + ANSI_RESET);
	            }

	            IdeiaRepositorio.deletarIdeiaPorId(id);
	            System.out.println(ANSI_GREEN + SUCCESS_ICON + "Ideia deletada com sucesso!" + ANSI_RESET);

	        } else {
	        	System.out.println(ANSI_RED + FAILURE_ICON + "Ideia com ID " + id + " não encontrada." + ANSI_RESET);
	        }
	        System.out.println();  
	    }
	
	    
	    private static void adicionarComentario(Scanner scanner) {
	        System.out.print("Digite o ID da ideia que deseja comentar: ");
	        int ideiaId = scanner.nextInt();
	        scanner.nextLine(); 

	        Ideia ideiaExistente = IdeiaRepositorio.consultarPorId(ideiaId);

	        if (ideiaExistente != null) {
	            System.out.print("Digite o texto do comentário: ");
	            String textoComentario = scanner.nextLine();

	            Comentario novoComentario = new Comentario();
	            novoComentario.setIdeiaID(ideiaId);
	            novoComentario.setTextoComentario(textoComentario);
	            novoComentario.setDataCriacao(LocalDateTime.now()); 

	            ComentarioRepositorio.cadastrarComentario(novoComentario);
	            System.out.println(ANSI_GREEN + SUCCESS_ICON + "Comentário adicionado com sucesso!" + ANSI_RESET);

	        } else {
	        	System.out.println(ANSI_RED + FAILURE_ICON + "Ideia com ID " + ideiaId + " não encontrada." + ANSI_RESET);
	        }
	        System.out.println();  
	    }
	    
	    private static void visualizarComentariosDeUmaIdeia(Scanner scanner) {
	        System.out.print("Digite o ID da ideia para visualizar seus comentários: ");
	        int ideiaId = scanner.nextInt();
	        scanner.nextLine();  

	        List<Comentario> comentarios = ComentarioRepositorio.listarComentariosPorIdeiaId(ideiaId);

	        if (comentarios != null && !comentarios.isEmpty()) {
	            System.out.println("Comentários para a ideia com ID " + ideiaId + ":");
	            for (Comentario comentario : comentarios) {
	                System.out.println(comentario);
	            }
	        } else {
	        	 System.out.println(ANSI_RED + FAILURE_ICON + "Nenhum comentário encontrado para a ideia com ID " + ideiaId + "." + ANSI_RESET);
	        }
	        System.out.println();  
	    }
	    
	    private static void deletarComentarios(Scanner scanner) {
	        System.out.print("Digite o ID da ideia associada aos comentários: ");
	        int ideiaId = scanner.nextInt();
	        scanner.nextLine(); 
	  

	        boolean deletado = ComentarioRepositorio.deletarComentariosPorIdeiaId(ideiaId);

	        if (deletado) {
	        	 System.out.println(ANSI_GREEN + SUCCESS_ICON + "Comentários da ideia com id " + ideiaId + " foram deletados com sucesso!" + ANSI_RESET);
	        } else {
	        	 System.out.println(ANSI_RED + FAILURE_ICON + "Erro ao deletar os comentários. Verifique se o ID fornecido está correto." + ANSI_RESET);
	        }
	        System.out.println(); 
	    }
}
