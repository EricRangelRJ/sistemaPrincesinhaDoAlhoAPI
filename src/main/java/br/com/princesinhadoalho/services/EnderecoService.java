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

		Endereco endereco = new Endereco();
		Condominio condominio = new Condominio();

		if (dto.getNomeCondominio().isBlank()) {
			// cadastrar endereço
			endereco.setLogradouro(dto.getLogradouro());
			endereco.setCep(dto.getCep());
			endereco.setNumero(dto.getNumero());
			endereco.setComplemento(dto.getComplemento());
			endereco.setObservacao(dto.getObservacao());
			endRepository.save(endereco);

		} else {
			// cadastrar endereço e condomínio
			endereco.setLogradouro(dto.getLogradouro());
			endereco.setCep(dto.getCep());
			endereco.setNumero(dto.getNumero());
			endereco.setComplemento(dto.getComplemento());
			endereco.setObservacao(dto.getObservacao());

			condominio.setNomeCondominio(dto.getNomeCondominio());
			condominio.setEndereco(endereco);

			endRepository.save(endereco);
			condRepository.save(condominio);
		}

		EnderecoGetDTO getDto = new EnderecoGetDTO();
		mapper.map(endereco, getDto);
		getDto.setNomeCondominio(dto.getNomeCondominio());

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

		Endereco endereco = result.get();

		EnderecoGetDTO getDto = new EnderecoGetDTO();
		mapper.map(endereco, getDto);

		return getDto;
	}

	public EnderecoGetDTO atualizar(EnderecoPutDTO dto) {

		Optional<Endereco> result = endRepository.findById(dto.getIdEndereco());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco endereco = result.get();
		EnderecoGetDTO getDto = new EnderecoGetDTO();

		// condominio inexistente
		if (endereco.getCondominio() == null) {

			// altera apenas o endereço
			if (dto.getNomeCondominio().isBlank()) {
				endereco.setLogradouro(dto.getLogradouro());
				endereco.setCep(dto.getCep());
				endereco.setNumero(dto.getNumero());
				endereco.setComplemento(dto.getComplemento());
				endereco.setObservacao(dto.getObservacao());

				endRepository.save(endereco);

				mapper.map(endereco, getDto);

				// altera endereço e insere novo condomínio
			} else {
				endereco.setLogradouro(dto.getLogradouro());
				endereco.setCep(dto.getCep());
				endereco.setNumero(dto.getNumero());
				endereco.setComplemento(dto.getComplemento());
				endereco.setObservacao(dto.getObservacao());

				Condominio condominio = new Condominio();
				condominio.setNomeCondominio(dto.getNomeCondominio());
				condominio.setEndereco(endereco);

				endRepository.save(endereco);
				condRepository.save(condominio);

				mapper.map(endereco, getDto);
				getDto.setNomeCondominio(condominio.getNomeCondominio());
			}

		}
		// condominio existente
		if (endereco.getCondominio() != null) {

			// altera endereço e exclui condomínio
			if (dto.getNomeCondominio().isBlank()) {

				endereco.setLogradouro(dto.getLogradouro());
				endereco.setCep(dto.getCep());
				endereco.setNumero(dto.getNumero());
				endereco.setComplemento(dto.getComplemento());
				endereco.setObservacao(dto.getObservacao());

				condRepository.delete(endereco.getCondominio());
				endereco.setCondominio(null);

				endRepository.save(endereco);

				mapper.map(endereco, getDto);

				// altera endereço e condomínio
			} else {
				endereco.setLogradouro(dto.getLogradouro());
				endereco.setCep(dto.getCep());
				endereco.setNumero(dto.getNumero());
				endereco.setComplemento(dto.getComplemento());
				endereco.setObservacao(dto.getObservacao());

				Condominio condominio = result.get().getCondominio();
				condominio.setNomeCondominio(dto.getNomeCondominio());
				condominio.setEndereco(endereco);

				endRepository.save(endereco);
				condRepository.save(condominio);

				mapper.map(endereco, getDto);
				getDto.setNomeCondominio(condominio.getNomeCondominio());
			}
		}

		return getDto;
	}

	public String excluir(Integer idEndereco) {

		Optional<Endereco> result = endRepository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		Endereco endereco = result.get();

		endRepository.delete(endereco);

		return "Endereço excluído com sucesso.";
	}

}
