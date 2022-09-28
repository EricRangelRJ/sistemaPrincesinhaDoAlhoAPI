package br.com.princesinhadoalho.testes;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.entities.Produto;
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
		Endereco end1 = new Endereco("Rua Albert", "99", null, null, "Bnh", "Mesquita", Estado.RJ, "33333-666"); 
		Endereco end2 = new Endereco("Rua Alves", "40", null, null, "Barra Funda", "São Paulo", Estado.SP, "11111-111");
		Endereco end3 = new Endereco("Rua Kennedy", "10", null, null, "Pampulha", "Belo Horizonte", Estado.MG, "22222-222");
		Endereco end4 = new Endereco("Rua Rufino", "23", "Edificio Solar, Sala 99", "Palmeiras", "Abranches", "Curitiba", Estado.PR, "33333-333");
		
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		
		// CADASTRANDO CLIENTES
		Cliente cli1 = new Cliente("Bia Souza", "111111111-11", DateHelper.toDate("1999-05-02"), "999999999", null, "bia@bol.com", null, end2);
		Cliente cli2 = new Cliente("Edy Silva", "222222222-22", DateHelper.toDate("2002-08-10"), "888888888", null, "edy@bol.com", null, end1);
		Cliente cli3 = new Cliente("Tom Melo", "333333333-33", DateHelper.toDate("1995-01-15"), "777777777", null, "tom@bol.com", "Sem endereço", null);
		Cliente cli4 = new Cliente("Ana Silva", "444444444-44", DateHelper.toDate("1989-09-02"), "666666666", null, "ana@bol.com", null, end1);
				
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		
		// CADASTRANDO FORNECEDORES
		Fornecedor forn1 = new Fornecedor("Sadia", "05.565.279/0001-74", "3263-3666", null, "sadia@bol.com", end3);
		Fornecedor forn2 = new Fornecedor("Lg", "15.565.279/0001-02", "3769-0001", null, "lg@bol.com", end4);
		Fornecedor forn3 = new Fornecedor("Consul", "28.565.279/0001-38", "2598-0322", null, "consul@bol.com", null);
		
		fornecedorRepository.saveAll(Arrays.asList(forn1, forn2, forn3));
		
		// CADASTRANDO PRODUTOS
		String cod1 = RandomHelper.gerarCodigoProdutoAleatorio();
		Date data1 = Date.from(Instant.now());		
		Produto prod1 = new Produto("Lasanha", cod1, "Sabor bolonhesa", data1, true, 250.0, 5.00, 11.00, forn3); 
		
		String cod2 = RandomHelper.gerarCodigoProdutoAleatorio();
		Date data2 = Date.from(Instant.now());	
		Produto prod2 = new Produto("Pizza", cod2, "Calabresa", data2, true, 0.4, 14.5, 25.5, forn1);
		
		String cod3 = RandomHelper.gerarCodigoProdutoAleatorio();
		Date data3 = Date.from(Instant.now());	
		Produto prod3 = new Produto("Monitor", cod3, "22 pol", data3, true, 600.0 ,850.0, 1500.0, forn2);
		
		String cod4 = RandomHelper.gerarCodigoProdutoAleatorio();
		Date data4 = Date.from(Instant.now());	
		Produto prod4 = new Produto("Mouse", cod4, "Verde", data4, true, 25.0, 30.0, 45.0, forn3);

		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3,prod4));
		
		// CADASTRANDO PEDIDOS	
		Pedido ped1 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("2005-05-02"), SituacaoPedido.AGUARDANDO_PAGAMENTO, 20.0, cli4);
		Pedido ped2 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, 40.0, cli2);
		Pedido ped3 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, 50.0, cli3);
		Pedido ped4 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, 10.0, cli1);
	
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4));

		// CADASTRANDO ITEM DE PEDIDO
		ItemPedido item1 = new ItemPedido(ped1, prod1, 1, prod1.getValorVenda());
		ItemPedido item2 = new ItemPedido(ped1, prod2, 1, prod2.getValorVenda());
		ItemPedido item3 = new ItemPedido(ped1, prod3, 2, prod3.getValorVenda());
		ItemPedido item4 = new ItemPedido(ped2, prod1, 2, prod1.getValorVenda());
		ItemPedido item5 = new ItemPedido(ped3, prod2, 3, prod2.getValorVenda());
		ItemPedido item6 = new ItemPedido(ped4, prod4,10, prod4.getValorVenda());
	
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2,item3, item4, item5, item6));
	
	}

}
