package br.com.princesinhadoalho.testes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.ClienteEntity;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.entities.EnderecoEntity;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.entities.Vendedor;
import br.com.princesinhadoalho.enums.Estado;
import br.com.princesinhadoalho.enums.SituacaoPedido;
import br.com.princesinhadoalho.helpers.DateHelper;
import br.com.princesinhadoalho.helpers.RandomHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import br.com.princesinhadoalho.repositories.ClienteRepository2;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import br.com.princesinhadoalho.repositories.FornecedorRepository;
import br.com.princesinhadoalho.repositories.ItemPedidoRepository;
import br.com.princesinhadoalho.repositories.PedidoRepository;
import br.com.princesinhadoalho.repositories.ProdutoRepository;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.repositories.VendedorRepository;
import lombok.AllArgsConstructor;

@Configuration
@Profile("test")
@AllArgsConstructor
public class Teste implements CommandLineRunner{
	
	private final UsuarioRepository usuarioRepository;
	private final EnderecoRepository enderecoRepository;
	private final ClienteRepository clienteRepository;
	private final ClienteRepository2 clienteRepository2;
	private final FornecedorRepository fornecedorRepository;
	private final ProdutoRepository produtoRepository;
	private final PedidoRepository pedidoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final VendedorRepository vendedorRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
//		// CADASTRANDO USUÁRIOS
//		Usuario usu1 = new Usuario("Teste", "t1@gmail.com", "1234");
//		usuarioRepository.saveAll(Arrays.asList(usu1));
//		
//		// CADASTRANDO ENDEREÇOS
//		Endereco endCliente1 = new Endereco("Rua Albert", "99", null, null, "Bnh", "Mesquita", Estado.RJ, "33333-666"); 
//		Endereco endCliente2 = new Endereco("Rua Alves", "40", null, null, "Barra Funda", "São Paulo", Estado.SP, "11111-111");
//		Endereco endFornecedor1 = new Endereco("Rua Kennedy", "10", null, null, "Pampulha", "Belo Horizonte", Estado.MG, "22222-222");
//		Endereco endFornecedor2 = new Endereco("Rua Rufino", "23", "Edificio Solar, Sala 99", "Palmeiras", "Abranches", "Curitiba", Estado.PR, "33333-333");
//		
//		enderecoRepository.saveAll(Arrays.asList(endCliente1, endCliente2, endFornecedor1, endFornecedor2));
		
		// CADASTRANDO CLIENTES
//		Cliente cli1 = new Cliente("Bia Souza", "111111111-11", DateHelper.toDate("1999-05-02"), "999999999", "3333-3333", "bia@bol.com", "Cliente chata a bessa kk", endCliente1);
//		Cliente cli2 = new Cliente("Edy Silva", "222222222-22", DateHelper.toDate("2002-08-10"), "888888888", null, "edy@bol.com", null, endCliente2);
//		Cliente cli3 = new Cliente("Tom Melo", "333333333-33", DateHelper.toDate("1995-01-15"), "777777777", null, "tom@bol.com", "Sem endereço", endCliente1);
//		Cliente cli4 = new Cliente("Ana Silva", "444444444-44", null, "666666666", null, "ana@bol.com", null, endCliente1);
//		Cliente cli5 = new Cliente("Tião Silva", "55555555-55", DateHelper.toDate("1989-09-02"), "555555555", null, "tiao@bol.com", null, endCliente2);
//		Cliente cli6 = new Cliente("Caio Silva", "66666666-66", DateHelper.toDate("1989-09-02"), "444444444", null, "caio@bol.com", null, null);

		ClienteEntity cli1 = new ClienteEntity();
		cli1.setCpf("023516513");
		cli1.setDataCadastro(new Date());
		cli1.setEmail("scas@oijojo");
		cli1.setEnderecos(null);
		cli1.setNome("nomesdasdsa");
		cli1.setObservacao("fcsfsfwefwefwef");
		cli1.setDataNascimento(DateHelper.toDate("1985-09-06"));
			
		clienteRepository2.save(cli1);
		
		
//		// CADASTRANDO FORNECEDORES
//		Fornecedor forn1 = new Fornecedor("Sadia", "05.565.279/0001-74", "3263-3666", "2690-0000", "sadia@bol.com", endFornecedor1);
//		Fornecedor forn2 = new Fornecedor("Lg", "15.565.279/0001-02", "3769-0001", null, "lg@bol.com", endFornecedor2);
//		Fornecedor forn3 = new Fornecedor("Consul", "28.565.279/0001-38", "2598-0322", null, "consul@bol.com", null);
//		
//		fornecedorRepository.saveAll(Arrays.asList(forn1, forn2, forn3));
//		
//		// CADASTRANDO PRODUTOS
//		String cod1 = RandomHelper.gerarCodigoProdutoAleatorio();
//		Date data1 = Date.from(Instant.now());		
//		Produto prod1 = new Produto("Lasanha", cod1, "Sabor bolonhesa", data1, true, 250.0, 5.00, 11.00, forn3); 
//		
//		String cod2 = RandomHelper.gerarCodigoProdutoAleatorio();
//		Date data2 = Date.from(Instant.now());	
//		Produto prod2 = new Produto("Pizza", cod2, "Calabresa", data2, true, 0.4, 14.5, 25.5, forn1);
//		
//		String cod3 = RandomHelper.gerarCodigoProdutoAleatorio();
//		Date data3 = Date.from(Instant.now());	
//		Produto prod3 = new Produto("Monitor", cod3, "22 pol", data3, true, 600.0 ,850.0, 1500.0, forn2);
//		
//		String cod4 = RandomHelper.gerarCodigoProdutoAleatorio();
//		Date data4 = Date.from(Instant.now());	
//		Produto prod4 = new Produto("Mouse", cod4, "Verde", data4, true, 25.0, 30.0, 45.0, forn3);
//be
//		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3,prod4));
//		
//		// CADASTRANDO VENDEDORES
//		Vendedor vend1 = new Vendedor(null, "Eric",  "dev", null);
//		Vendedor vend2 = new Vendedor(null, "Bruna", null , null);
//		
//		vendedorRepository.saveAll(Arrays.asList(vend1, vend2));
//		
//		// CADASTRANDO PEDIDOS	
//		Pedido ped1 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("2005-05-02"), SituacaoPedido.AGUARDANDO_PAGAMENTO, 20.0, cli4, vend1);
//		Pedido ped2 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, 12.5, cli2, vend1);
//		Pedido ped3 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, 50.0, cli3, vend2);
//		Pedido ped4 = new Pedido(RandomHelper.gerarNumeroPedidoAleatorio(),DateHelper.toDate("1999-05-02"), SituacaoPedido.PAGO, 10.0, cli1, vend2);
//		Pedido ped5 = new Pedido(null, RandomHelper.gerarNumeroPedidoAleatorio(), DateHelper.toDate("2022-11-09"), DateHelper.toDate("2022-11-11"), SituacaoPedido.PAGO, 5.0, null, cli6, null, vend2);
//		
//		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4, ped5));
//
//		// CADASTRANDO ITEM DE PEDIDO
//		ItemPedido item1 = new ItemPedido(ped1, prod1, 1, prod1.getValorVenda());
//		ItemPedido item2 = new ItemPedido(ped1, prod2, 2, prod2.getValorVenda());
//		ItemPedido item3 = new ItemPedido(ped1, prod3, 3, prod3.getValorVenda());
//		ItemPedido item4 = new ItemPedido(ped2, prod1, 2, prod1.getValorVenda());
//		ItemPedido item5 = new ItemPedido(ped3, prod2, 3, prod2.getValorVenda());
//		ItemPedido item6 = new ItemPedido(ped4, prod4,10, prod4.getValorVenda());
//		ItemPedido item7 = new ItemPedido(ped5, prod4,10, prod4.getValorVenda());
//	
//		itemPedidoRepository.saveAll(Arrays.asList(item1, item2,item3, item4, item5, item6, item7));
	
	}

}
