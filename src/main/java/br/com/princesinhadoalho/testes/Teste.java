package br.com.princesinhadoalho.testes;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.enums.Ativo;
import br.com.princesinhadoalho.enums.Estado;
import br.com.princesinhadoalho.enums.SituacaoPedido;
import br.com.princesinhadoalho.helpers.DateHelper;
import br.com.princesinhadoalho.helpers.RandomHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import br.com.princesinhadoalho.repositories.FornecedorRepository;
import br.com.princesinhadoalho.repositories.ItemPedidoRepository;
import br.com.princesinhadoalho.repositories.PedidoRepository;
import br.com.princesinhadoalho.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;

@Configuration
@Profile("test")
@AllArgsConstructor
public class Teste implements CommandLineRunner{
	
	private final EnderecoRepository enderecoRepository;
	private final ClienteRepository clienteRepository;
	private final FornecedorRepository fornecedorRepository;
	private final ProdutoRepository produtoRepository;
	private final PedidoRepository pedidoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	

	@Override
	public void run(String... args) throws Exception {

		// CADASTRANDO ENDEREÇOS
		Endereco end1 = new Endereco(null, "Av Robert", "500", "Bloco A, Apto 20", "Estrela", "Bnh", "Mesquita", Estado.RJ, "00000-000");
		Endereco end2 = new Endereco(null, "Rua Alves", "40", null, null, "Barra Funda", "São Paulo", Estado.SP, "11111-111");
		Endereco end3 = new Endereco(null, "Rua Kennedy", "10", null, null, "Pampulha", "Belo Horizonte", Estado.MG, "22222-222");
		Endereco end4 = new Endereco(null, "Rua Rufino", "23", "Edificio Solar, Sala 99", null, "Abranches", "Curitiba", Estado.PR, "33333-333");
		
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		
		// CADASTRANDO CLIENTES
		Cliente cli1 = new Cliente(null, "Bia Souza", "111111111-11", DateHelper.toDate("1999-05-02"), "999999999", null, "bia@bol.com", null, end2);
		Cliente cli2 = new Cliente(null, "Edy Silva", "222222222-22", DateHelper.toDate("2002-08-10"), "888888888", null, "edy@bol.com", null, end1);
		Cliente cli3 = new Cliente(null, "Tom Melo", "333333333-33", DateHelper.toDate("1995-01-15"), "777777777", null, "tom@bol.com", null, null);
		Cliente cli4 = new Cliente(null, "Ana Silva", "444444444-44", DateHelper.toDate("1989-09-02"), "666666666", null, "ana@bol.com", null, end1);
				
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		
		// CADASTRANDO FORNECEDORES
		Fornecedor forn1 = new Fornecedor(null, "Sadia", "05.565.279/0001-74", "3263-3666", null, "sadia@bol.com", end3);
		Fornecedor forn2 = new Fornecedor(null, "Lg", "15.565.279/0001-02", "3769-0001", null, "lg@bol.com", end4);
		Fornecedor forn3 = new Fornecedor(null, "Consul", "28.565.279/0001-38", "2598-0322", null, "consul@bol.com", null);
		
		fornecedorRepository.saveAll(Arrays.asList(forn1, forn2, forn3));
		
		// CADASTRANDO PRODUTOS
		Produto prod1 = new Produto(null, "Geladeira", "00-1", "cor: cinza", DateHelper.toDate("2022-01-02"), Ativo.NÃO, 30.0, 1000.0, 1500.0, 500.0, forn3);
		Produto prod2 = new Produto(null, "Pizza", "00-2", "Calabresa", DateHelper.toDate("2022-02-01"), Ativo.SIM, 0.4, 9.0, 14.5, 5.5, forn1);
		Produto prod3 = new Produto(null, "Monitor", "00-3", "22 pol", DateHelper.toDate("2022-02-01"), Ativo.SIM, 2.0, 650.0, 950.0, 300.0, forn2);
		
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
	/*	// CADASTRANDO PEDIDOS	
		Pedido ped1 = new Pedido(null, RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), null, SituacaoPedido.PAGO, null, null, cli1, null);
		Pedido ped2 = new Pedido(null, RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, cli2);
		Pedido ped3 = new Pedido(null, RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, cli3);
		Pedido ped4 = new Pedido(null, RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, cli1);
	
		//ped1.setDesconto(100.0);
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4));

		// CADASTRANDO ITEM DE PEDIDO
	//	ItemPedido item1 = new ItemPedido(ped1, prod1, 1, prod1.getValorVenda());
	//	ItemPedido item2 = new ItemPedido(ped1, prod2, 1, prod2.getValorVenda());
		ItemPedido item3 = new ItemPedido(ped1, prod3, 2, prod3.getValorVenda());
		ItemPedido item4 = new ItemPedido(ped2, prod1, 2, prod1.getValorVenda());
		ItemPedido item5 = new ItemPedido(ped3, prod2, 3, prod2.getValorVenda());
		ItemPedido item6 = new ItemPedido(ped3, prod3, 1, prod3.getValorVenda());
	
		itemPedidoRepository.saveAll(Arrays.asList(item3, item4, item5, item6));
	*/
	}

}
