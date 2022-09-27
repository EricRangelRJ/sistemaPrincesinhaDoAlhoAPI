package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnderecoService {

	private final EnderecoRepository endRepository;
	private final ModelMapper mapper;

	public Endereco cadastrar(EnderecoDTO dto) {

		// buscando um endereço existente no banco
		Optional<Endereco> result = endRepository.findByLogradouroAndNumeroAndCepAndComplemento(
				dto.getLogradouro(), dto.getNumero(), dto.getCep(), dto.getComplemento());

		// caso exista
		if (result.isPresent()) {
			Endereco endereco = result.get();
			
			return endereco;
		}

		// transferindo o dto para o endereco
		Endereco endereco = mapper.map(dto, Endereco.class);
		
		// salvando no banco
		endRepository.save(endereco);
	
		return endereco;
	}

	public List<EnderecoDTO> buscarEnderecos() {

		List<Endereco> list = endRepository.findAll();
		List<EnderecoDTO> listaGetDto = new ArrayList<EnderecoDTO>();

		for (Endereco endereco : list) {
			EnderecoDTO getDto = new EnderecoDTO(endereco);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}

	public EnderecoDTO buscarId(Integer idEndereco) {

		Optional<Endereco> result = endRepository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco endereco = result.get();

		return new EnderecoDTO(endereco);
	}

	public Endereco atualizar(EnderecoDTO dto) {

		Optional<Endereco> result = endRepository.findById(dto.getIdEndereco());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}
		
		Endereco endereco = result.get();
		mapper.map(dto, endereco);

		endRepository.save(endereco);

		return endereco;
	}

	public String excluir(Integer idEndereco) {

		Optional<Endereco> result = endRepository.findById(idEndereco);
		
		if(result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco endereco = result.get();

		endRepository.delete(endereco);

		return "Endereço excluído com sucesso";
	}

}
