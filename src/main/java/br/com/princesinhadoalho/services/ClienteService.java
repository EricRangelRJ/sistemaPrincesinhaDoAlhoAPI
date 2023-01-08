package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.clientes.ClienteGetDTO;
import br.com.princesinhadoalho.dtos.clientes.ClientePostDTO;
import br.com.princesinhadoalho.dtos.clientes.ClientePutDTO;
import br.com.princesinhadoalho.entities.ClienteEntity;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final ModelMapper mapper;

	public ClienteGetDTO cadastrar(ClientePostDTO dto) throws IllegalArgumentException, IllegalAccessException {

		// verificar se o CPF já está cadastrado
		// Optional<Cliente> result = clienteRepository.findByCpf(dto.getCpf());
//		if (result.isPresent()) {
//			throw new BadRequestException("O CPF informado já encontra-se cadastrado. Tente outro.");
//		}

		// caso a data esteja vazia
		if (dto.getDataNascimento().isBlank()) {
			dto.setDataNascimento(null);
		}
		// cadastrando novo Cliente com ou sem endereço
		ClienteEntity cliente = mapper.map(dto, ClienteEntity.class);
		clienteRepository.save(cliente);
		// convertendo o cliente em dto e retornando ao cotroller
		return new ClienteGetDTO(cliente);
	}

	public List<ClienteGetDTO> buscarClientes() {

		List<ClienteEntity> list = clienteRepository.findAll();
		List<ClienteGetDTO> listaGetDto = new ArrayList<ClienteGetDTO>();

		for (ClienteEntity cliente : list) {
			ClienteGetDTO getDto = new ClienteGetDTO(cliente);
			listaGetDto.add(getDto);
		}
		return listaGetDto;
	}

	public ClienteGetDTO buscarId(Integer idCliente) {

		Optional<ClienteEntity> result = clienteRepository.findById(idCliente);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		ClienteEntity cliente = result.get();

		clienteRepository.delete(cliente);

		return new ClienteGetDTO(cliente);
	}

	public ClienteGetDTO atualizar(ClientePutDTO dto) {

		Optional<ClienteEntity> result = clienteRepository.findById(dto.getIdCliente());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}
		// caso a data esteja vazia
		if (dto.getDataNascimento().isBlank()) {
			dto.setDataNascimento(null);
		}
		// atualizando os dados do cliente
		ClienteEntity cliente = result.get();
		mapper.map(dto, cliente);
		return new ClienteGetDTO(cliente);
	}

	public String excluir(Integer idCliente) throws Exception {

		Optional<ClienteEntity> result = clienteRepository.findById(idCliente);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		ClienteEntity cliente = result.get();

		try {
			clienteRepository.delete(cliente);
		} catch (Exception e) {
			throw new Exception("Erro ao excluir o cliente " + cliente.getNome());
		}

		String retorno = "Cliente " + cliente.getNome() + " excluido com sucesso!";

		return retorno;

	}

}
