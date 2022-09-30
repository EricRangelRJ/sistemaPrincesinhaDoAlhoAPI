package br.com.princesinhadoalho.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoPostDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoGetDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoPostDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoPutDTO;
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.entities.Vendedor;
import br.com.princesinhadoalho.enums.SituacaoPedido;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.helpers.RandomHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import br.com.princesinhadoalho.repositories.PedidoRepository;
import br.com.princesinhadoalho.repositories.VendedorRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final VendedorRepository vendedorRepository;
	private final ItemPedidoService itemPedidoService;
	private final ModelMapper mapper;

	public PedidoGetDTO cadastrar(PedidoPostDTO dto) {

		// Gerando um número aleatório para o pedido
		String numeroPedido = RandomHelper.gerarNumeroPedidoAleatorio();
		
		Optional<Pedido> ped = pedidoRepository.findByNumeroPedido(numeroPedido);
		
		// Garantindo que o número do pedido não se repita
		if(ped.isPresent()) {
			String numero = ped.get().getNumeroPedido();
			
			 while (numero.contentEquals(numeroPedido)) {
					numeroPedido = RandomHelper.gerarNumeroPedidoAleatorio();
			}
		}	
		
		Optional<Cliente> cli = clienteRepository.findById(dto.getIdCliente());	
		if (cli.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}	
		
		Optional<Vendedor> vend = vendedorRepository.findById(dto.getIdVendedor());	
		if (vend.isEmpty()) {
			throw new EntityNotFoundException("Vendedor não encontrado.");
		}	
		
		Cliente cliente = cli.get();
		Vendedor vendedor = vend.get();
		Date dataPedido = Date.from(Instant.now());
		Double desconto = dto.getDesconto();
		SituacaoPedido situacao = SituacaoPedido.valueOf(dto.getSituacao());
		
		Pedido pedido = new Pedido(numeroPedido, dataPedido, situacao, desconto, cliente, vendedor);
		
		pedidoRepository.save(pedido);

		List<ItemPedidoPostDTO> lista = dto.getItens();

		if(lista != null && lista.size() > 0) {
			Set<ItemPedido> itens = new HashSet<ItemPedido>();
			
			itens = itemPedidoService.cadastrar(pedido, lista);
			
			// adicionando os ítens ao pedido
			pedido.setItens(itens);
		}
		
		return new PedidoGetDTO(pedido);
	}

	public List<PedidoGetDTO> buscarPedidos() {

		List<Pedido> lista = pedidoRepository.findAll();
		List<PedidoGetDTO> listaGetDto = null;
		
		if (lista != null) {
			listaGetDto = new ArrayList<PedidoGetDTO>();

			for (Pedido pedido : lista) {
				PedidoGetDTO getDto = new PedidoGetDTO(pedido);
				
				listaGetDto.add(getDto);
			}	
		}
		
		return listaGetDto;
	}
	
	public PedidoGetDTO buscarId(Integer idPedido) {

		Optional<Pedido> result = pedidoRepository.findById(idPedido);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();
		
		PedidoGetDTO getDto = new PedidoGetDTO(pedido);
		
		return getDto;
	}
	
	public PedidoGetDTO atualizar(PedidoPutDTO dto) {
		
		Optional<Pedido> result = pedidoRepository.findById(dto.getIdPedido());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();
		mapper.map(dto, pedido);
		
		return new PedidoGetDTO(pedido);	
	}
	
	public String cancelar(Integer idPedido) {

		Optional<Pedido> result = pedidoRepository.findById(idPedido);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();
		pedido.setSituacao(SituacaoPedido.CANCELADO);
		
		pedidoRepository.save(pedido);

		return "Pedido Nº " + result.get().getNumeroPedido() + " cancelado com sucesso.";
	}
	
}
