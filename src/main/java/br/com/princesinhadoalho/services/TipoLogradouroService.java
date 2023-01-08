package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.clientes.TipoLogradouroDTO;
import br.com.princesinhadoalho.dtos.clientes.TipoLogradouroPutDTO;
import br.com.princesinhadoalho.entities.TipoLogradouroEntity;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.TipoLogradouroRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TipoLogradouroService {

	private final TipoLogradouroRepository repository;
	private final ModelMapper mapper;

	public TipoLogradouroDTO cadastrar(TipoLogradouroDTO dto) {
		TipoLogradouroEntity tipoLogradouro = mapper.map(dto, TipoLogradouroEntity.class);
		repository.save(tipoLogradouro);
		return new TipoLogradouroDTO(tipoLogradouro);
	}

	public List<TipoLogradouroDTO> buscarTipoLogradouro() {

		List<TipoLogradouroEntity> lista = repository.findAll();
		List<TipoLogradouroDTO> listaDto = new ArrayList<TipoLogradouroDTO>();

		lista.forEach(tipoLogradouroEntity -> {
			TipoLogradouroDTO tipoLogradouroDTO = new TipoLogradouroDTO(tipoLogradouroEntity);
			listaDto.add(tipoLogradouroDTO);
		});
		return listaDto;
	}

	public String excluir(Integer idTipoLogradouro) throws Exception {

		Optional<TipoLogradouroEntity> result = repository.findById(idTipoLogradouro);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Tipo logradouro não encontrado.");
		}

		TipoLogradouroEntity tipoLogradouro = result.get();

		try {
			repository.delete(tipoLogradouro);
		} catch (Exception e) {
			throw new Exception("Erro ao excluir o tipo logradouro " + tipoLogradouro.getDescricao());
		}
		String retorno = "Tipo Logradouro " + tipoLogradouro.getDescricao() + " excluido com sucesso!";
		return retorno;

	}

	public TipoLogradouroDTO buscarId(Integer idTipoLogradouro) {

		Optional<TipoLogradouroEntity> result = repository.findById(idTipoLogradouro);
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}
		return new TipoLogradouroDTO(result.get());
	}


	public TipoLogradouroDTO atualizar(@Valid TipoLogradouroPutDTO dto) {
		Optional<TipoLogradouroEntity> result = repository.findById(dto.getIdTipoLogradouro());
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Tipo Logradouro não encontrado.");
		}
		TipoLogradouroEntity tipoLogradouro = result.get();
		mapper.map(dto, tipoLogradouro);
		repository.save(tipoLogradouro);
		return new TipoLogradouroDTO(tipoLogradouro);
	}
}
