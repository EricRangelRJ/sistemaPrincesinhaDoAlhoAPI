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
import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.reflections.EnderecoReflection;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final EnderecoService enderecoService;
	private final ModelMapper mapper;

	public ClienteGetDTO cadastrar(ClientePostDTO dto) throws IllegalArgumentException, IllegalAccessException {

		// verificar se o CPF já está cadastrado
		Optional<Cliente> result = clienteRepository.findByCpf(dto.getCpf());
		if (result.isPresent()) {
			throw new BadRequestException("O CPF informado já encontra-se cadastrado. Tente outro.");
		}
		
		// convertendo o endereço do cliente(dto) para um enderecoDTO
		EnderecoDTO enderecoDTO = mapper.map(dto, EnderecoDTO.class);
				
		// verificando se existem campos preenchidos 
		EnderecoReflection endReflection = new EnderecoReflection();
		boolean result2 = endReflection.reflection(enderecoDTO);

		Endereco endereco = new Endereco();
		// caso exista(TRUE)
		if (result2) {
			// cadastrando um endereço
			endereco = enderecoService.cadastrar(enderecoDTO);
		}
		
		// caso a data esteja vazia
		if (dto.getDataNascimento().isBlank()) {
			dto.setDataNascimento(null);
		}

		// cadastrando novo Cliente com ou sem endereço
		Cliente cliente = mapper.map(dto, Cliente.class);
		if (endereco.getIdEndereco() != null) {
			cliente.setEndereco(endereco);
		}
		clienteRepository.save(cliente);

		// convertendo o cliente em dto e retornando ao cotroller
		return new ClienteGetDTO(cliente);
	}

	public List<ClienteGetDTO> buscarClientes() {

		List<Cliente> list = clienteRepository.findAll();
		List<ClienteGetDTO> listaGetDto = new ArrayList<ClienteGetDTO>();

		for (Cliente cliente : list) {
			ClienteGetDTO getDto = new ClienteGetDTO(cliente);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}

	public ClienteGetDTO buscarId(Integer idCliente) {

		Optional<Cliente> result = clienteRepository.findById(idCliente);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = result.get();

		return new ClienteGetDTO(cliente);
	}

	public ClienteGetDTO atualizar(ClientePutDTO dto) {

		Optional<Cliente> result = clienteRepository.findById(dto.getIdCliente());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}
		
		// convertendo o endereço do cliente(dto) para um EnderecoDTO
		EnderecoDTO enderecoDto = mapper.map(dto, EnderecoDTO.class);
		
		// verificando se existem campos preenchidos em enderecoDto
		EnderecoReflection endReflection = new EnderecoReflection();
		boolean result2 = endReflection.reflection(enderecoDto);
		
		// caso a data esteja vazia
		if (dto.getDataNascimento().isBlank()) {
			dto.setDataNascimento(null);
		}
		
		// atualizando os dados do cliente
		Cliente cliente = result.get();
		mapper.map(dto, cliente);

		// SITUAÇÃO 1: caso o cliente não possua endereço
		if(cliente.getEndereco() == null) {
			// se result2 = TRUE
			if (result2) {
				// cadastrar novo endereço para o cliente
				Endereco endereco = enderecoService.cadastrar(enderecoDto);
				cliente.setEndereco(endereco);
			}
		
			clienteRepository.save(cliente);
			
			return new ClienteGetDTO(cliente);
		}
		
		// SITUAÇÃO 2: caso o cliente já possua endereço
		if (!result2) {
			// se result2 = FALSE
			throw new BadRequestException("Prencha pelo menos um campo de endereço.");
		}
		
		// atualizando endereço do cliente
		enderecoDto.setIdEndereco(cliente.getEndereco().getIdEndereco());
		enderecoService.atualizar(enderecoDto);
		
		clienteRepository.save(cliente);

		return new ClienteGetDTO(cliente);
	}

	public String excluir(Integer idCliente) {

		Optional<Cliente> result = clienteRepository.findById(idCliente);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = result.get();

		// buscando uma lista de clientes associados ao endereço fornecido(ManyToOne)
		if (cliente.getEndereco() != null) {
			Optional<List<Cliente>> result2 = clienteRepository.findByIdEnderecoJoinEndereco(
					cliente.getEndereco().getIdEndereco());
			List<Cliente> lista = result2.get();

			// excluindo o endereço caso ele pertença apenas ao cliente
			if (lista.size() == 1) {
				enderecoService.excluir(cliente.getEndereco().getIdEndereco());
			}
		}
		clienteRepository.delete(cliente);
		
		return "Cliente " + cliente.getNome() + " excluído com sucesso.";
	}
	
}
