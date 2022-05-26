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
import br.com.princesinhadoalho.entities.Condominio;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.CondominioRepository;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnderecoService {

	private EnderecoRepository endRepository;
	private CondominioRepository condRepository;
	private ModelMapper mapper;

	public EnderecoGetDTO cadastrar(EnderecoPostDTO dto) {
		
		Optional<Endereco> result = endRepository.findByCepAndNumero(dto.getCep(), dto.getNumero());
		
		if(result.isPresent()) {
			throw new BadRequestException("Endereço já cadastrado.");
		}

		Endereco endereco = new Endereco();
		endereco.setLogradouro(dto.getLogradouro());
		endereco.setCep(dto.getCep());
		endereco.setNumero(dto.getNumero());
		endereco.setComplemento(dto.getComplemento());
		endereco.setObservacao(dto.getObservacao());

		Condominio condominio = new Condominio();
		condominio.setNomeCondominio(dto.getNomeCondominio());
		// associando o endereço ao condomínio
		condominio.setEndereco(endereco);

		endRepository.save(endereco);
		condRepository.save(condominio);

		EnderecoGetDTO getDto = new EnderecoGetDTO();
		mapper.map(endereco, getDto);

		return getDto;
	}

	public List<EnderecoGetDTO> buscarEnderecos() {

		List<Endereco> list = endRepository.findAll();
		List<EnderecoGetDTO> listaGetDto = new ArrayList<EnderecoGetDTO>();

		for (Endereco endereco : list) {
			EnderecoGetDTO getDto = new EnderecoGetDTO();
			mapper.map(endereco, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}

	public EnderecoGetDTO buscarId(Integer idEndereco) {

		Optional<Endereco> result = endRepository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco Endereco = result.get();

		EnderecoGetDTO getDto = new EnderecoGetDTO();
		mapper.map(Endereco, getDto);

		return getDto;
	}

	public String atualizar(EnderecoPutDTO dto) {

		Optional<Endereco> result = endRepository.findById(dto.getIdEndereco());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco Endereco = result.get();
		mapper.map(dto, Endereco);

		endRepository.save(Endereco);

		return "Endereço atualizado com sucesso.";
	}

	public String excluir(Integer idEndereco) {

		Optional<Endereco> result = endRepository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco Endereco = result.get();

		endRepository.delete(Endereco);

		return "Endereço excluído com sucesso.";
	}

}
