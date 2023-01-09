package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.clientes.TipoEnderecoDTO;
import br.com.princesinhadoalho.dtos.clientes.TipoEnderecoPutDTO;
import br.com.princesinhadoalho.entities.TipoEnderecoEntity;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.TipoEnderecoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TipoEnderecoService {

	private final TipoEnderecoRepository repository;
	private final ModelMapper mapper;

	public TipoEnderecoDTO cadastrar(TipoEnderecoDTO dto) {
		TipoEnderecoEntity tipoEndereco = mapper.map(dto, TipoEnderecoEntity.class);
		repository.save(tipoEndereco);
		return new TipoEnderecoDTO(tipoEndereco);
	}

	public List<TipoEnderecoDTO> buscarTipoEndereco() {

		List<TipoEnderecoEntity> lista = repository.findAll();
		List<TipoEnderecoDTO> listaDto = new ArrayList<TipoEnderecoDTO>();

		lista.forEach(tipoEnderecoEntity -> {
			TipoEnderecoDTO tipoEnderecoDTO = new TipoEnderecoDTO(tipoEnderecoEntity);
			listaDto.add(tipoEnderecoDTO);
		});
		return listaDto;
	}

	public String excluir(Integer idTipoEndereco) throws Exception {

		Optional<TipoEnderecoEntity> result = repository.findById(idTipoEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Tipo Endereco não encontrado.");
		}

		TipoEnderecoEntity tipoEndereco = result.get();

		try {
			repository.delete(tipoEndereco);
		} catch (Exception e) {
			throw new Exception("Erro ao excluir o tipo Endereco " + tipoEndereco.getDescricao());
		}
		String retorno = "Tipo Endereco " + tipoEndereco.getDescricao() + " excluido com sucesso!";
		return retorno;

	}

	public TipoEnderecoDTO buscarId(Integer idTipoEndereco) {

		Optional<TipoEnderecoEntity> result = repository.findById(idTipoEndereco);
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}
		return new TipoEnderecoDTO(result.get());
	}


	public TipoEnderecoDTO atualizar(@Valid TipoEnderecoPutDTO dto) {
		Optional<TipoEnderecoEntity> result = repository.findById(dto.getIdTipoEndereco());
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Tipo Endereco não encontrado.");
		}
		TipoEnderecoEntity tipoEndereco = result.get();
		mapper.map(dto, tipoEndereco);
		repository.save(tipoEndereco);
		return new TipoEnderecoDTO(tipoEndereco);
	}
}
