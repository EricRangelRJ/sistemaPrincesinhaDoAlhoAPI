package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.pedidos.PedidoGetDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoPostDTO;
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.helpers.DateHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import br.com.princesinhadoalho.repositories.PedidoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PedidoService {

	private PedidoRepository pedidoRepository;
	private ClienteRepository clienteRepository;
	private ModelMapper mapper;

	public PedidoGetDTO cadastrar(PedidoPostDTO dto) {

		Optional<Pedido> result = pedidoRepository.findByNumeroPedido(dto.getNumeroPedido());

		if (result.isPresent()) {
			throw new BadRequestException("Pedido já cadastrado.");
		}

		Pedido pedido = new Pedido();
		pedido.setNumeroPedido(dto.getNumeroPedido());
		pedido.setDataPedido(DateHelper.toDate(dto.getDataPedido()));
		pedido.setDataEntrega(DateHelper.toDate(dto.getDataEntrega()));
		pedido.setTotal(dto.getTotal());
	
		Optional<Cliente> result2 = clienteRepository.findById(dto.getIdCliente());
		
		if (result2.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}
		
		Cliente cliente = result2.get();
		pedido.setCliente(cliente);

		pedidoRepository.save(pedido);

		PedidoGetDTO getDto = new PedidoGetDTO();
		mapper.map(pedido, getDto);
		getDto.setDataPedido(DateHelper.toString(pedido.getDataPedido()));
		getDto.setDataEntrega(DateHelper.toString(pedido.getDataEntrega()));
		
		return getDto;
	}

	public List<PedidoGetDTO> buscarPedidos() {

		List<Pedido> list = pedidoRepository.findAll();
		List<PedidoGetDTO> listaGetDto = new ArrayList<PedidoGetDTO>();

		for (Pedido pedido : list) {
			PedidoGetDTO getDto = new PedidoGetDTO();
			mapper.map(pedido, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}
	
	public PedidoGetDTO buscarId(Integer idPedido) {

		Optional<Pedido> result = pedidoRepository.findById(idPedido);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();

		PedidoGetDTO getDto = new PedidoGetDTO();
		mapper.map(pedido, getDto);

		return getDto;
	}
	
	public String excluir(Integer idPedido) {

		Optional<Pedido> result = pedidoRepository.findById(idPedido);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();

		pedidoRepository.delete(pedido);

		return "Pedido Nº " + result.get().getNumeroPedido() + " excluído com sucesso.";
	}

}
