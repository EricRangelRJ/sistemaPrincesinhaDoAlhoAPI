package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoGetDTO;
import br.com.princesinhadoalho.dtos.enderecos.EnderecoPostDTO;
import br.com.princesinhadoalho.dtos.enderecos.EnderecoPutDTO;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnderecoService {

	private EnderecoRepository repository;
	private ModelMapper mapper;

	public EnderecoGetDTO cadastrar(EnderecoPostDTO dto) {

		Endereco endereco = new Endereco();
		mapper.map(dto, endereco);

		repository.save(endereco);

		EnderecoGetDTO getDto = new EnderecoGetDTO();
		mapper.map(endereco, getDto);

		return getDto;
	}

	public List<EnderecoGetDTO> buscarEnderecos() {

		List<Endereco> list = repository.findAll();
		List<EnderecoGetDTO> listaGetDto = new ArrayList<EnderecoGetDTO>();

		for (Endereco endereco : list) {
			EnderecoGetDTO getDto = new EnderecoGetDTO();
			mapper.map(endereco, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}
	
	public EnderecoGetDTO buscarId(Integer idEndereco) {

		Optional<Endereco> result = repository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco Endereco = result.get();

		EnderecoGetDTO getDto = new EnderecoGetDTO();
		mapper.map(Endereco, getDto);

		return getDto;
	}
	
	public String atualizar(EnderecoPutDTO dto) {
		
		Optional<Endereco> result = repository.findById(dto.getIdEndereco());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Usuário não encontrado.");
		}
	
		Endereco Endereco = result.get();
		mapper.map(dto, Endereco);

		repository.save(Endereco);
		
		return "Endereço atualizado com sucesso.";
	}

	public String excluir(Integer idEndereco) {

		Optional<Endereco> result = repository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco Endereco = result.get();

		repository.delete(Endereco);

		return "Endereço excluído com sucesso.";
	}

}
