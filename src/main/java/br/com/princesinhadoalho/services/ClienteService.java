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
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.helpers.DateHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

	private ClienteRepository clienteRepository;
	private EnderecoRepository enderecoRepository;
	private ModelMapper mapper;

	public ClienteGetDTO cadastrar(ClientePostDTO dto) {

		Optional<Cliente> result = clienteRepository.findByCpf(dto.getCpf());

		if (result.isPresent()) {
			throw new BadRequestException("Cpf já cadastrado.");
		}

		Cliente cliente = new Cliente();
		cliente.setNome(dto.getNome());
		cliente.setDataNascimento(DateHelper.toDate(dto.getDataNascimento()));
		cliente.setCpf(dto.getCpf());
	
		Optional<Endereco> result2 = enderecoRepository.findById(dto.getIdEndereco());
		
		if (result2.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}
		
		Endereco endereco = result2.get();
		cliente.setEndereco(endereco);

		clienteRepository.save(cliente);

		ClienteGetDTO getDto = new ClienteGetDTO();
		mapper.map(cliente, getDto);
		getDto.setDataNascimento(DateHelper.toString(cliente.getDataNascimento()));
		
		return getDto;
	}

	public List<ClienteGetDTO> buscarClientes() {

		List<Cliente> list = clienteRepository.findAll();
		List<ClienteGetDTO> listaGetDto = new ArrayList<ClienteGetDTO>();

		for (Cliente cliente : list) {
			ClienteGetDTO getDto = new ClienteGetDTO();
			mapper.map(cliente, getDto);

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

		ClienteGetDTO getDto = new ClienteGetDTO();
		mapper.map(cliente, getDto);

		return getDto;
	}
	
	public ClienteGetDTO atualizar(ClientePutDTO dto) {
		
		Optional<Cliente> result = clienteRepository.findById(dto.getIdCliente());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}
		
		Cliente cliente = result.get();
		mapper.map(dto, cliente);

		clienteRepository.save(cliente);
		
		ClienteGetDTO getDto = new ClienteGetDTO();
		mapper.map(cliente, getDto);
		
		return getDto;
	}

	public String excluir(Integer idCliente) {

		Optional<Cliente> result = clienteRepository.findById(idCliente);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = result.get();

		clienteRepository.delete(cliente);

		return "Cliente " + result.get().getNome() + " excluído com sucesso.";
	}

}
