package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.pedidos.PedidoGetDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoPostDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoPutDTO;
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.enums.SituacaoPedido;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.helpers.RandomHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import br.com.princesinhadoalho.repositories.PedidoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
//	private final ItemPedidoService itemPedidoService;
	private final ModelMapper mapper;

	public PedidoGetDTO cadastrar(PedidoPostDTO dto) {

		// Gerando um número aleatório para o pedido
		String numeroPedido = RandomHelper.gerarNumeroPedidoAleatorio();
		
		Optional<Pedido> result = pedidoRepository.findByNumeroPedido(numeroPedido);
		
		// Garantindo que o número do pedido não se repita
		if(result.isPresent()) {
			String numero = result.get().getNumeroPedido();
			
			 while (numero.contentEquals(numeroPedido)) {
					numeroPedido = RandomHelper.gerarNumeroPedidoAleatorio();
			}
		}	
		
		Optional<Cliente> result2 = clienteRepository.findById(dto.getIdCliente());	
		if (result2.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}	
		Cliente cliente = result2.get();

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setNumeroPedido(numeroPedido);
		pedido.setDesconto(dto.getDesconto());
		pedido.setDataPedido(Calendar.getInstance().getTime());
		pedido.setSituacao(SituacaoPedido.valueOf(dto.getSituacao()));
			
	//	itemPedidoService.cadastrar(null);
		pedidoRepository.save(pedido);
		
		
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
