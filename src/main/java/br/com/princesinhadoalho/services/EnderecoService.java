package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.dtos.enderecos.EnderecoPostDTO;
import br.com.princesinhadoalho.entities.ClienteEntity;
import br.com.princesinhadoalho.entities.EnderecoEntity;
import br.com.princesinhadoalho.entities.TipoEnderecoEntity;
import br.com.princesinhadoalho.entities.TipoLogradouroEntity;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import br.com.princesinhadoalho.repositories.TipoEnderecoRepository;
import br.com.princesinhadoalho.repositories.TipoLogradouroRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnderecoService {

	private final EnderecoRepository endRepository;
	@Autowired
	private final TipoEnderecoRepository tipoEnderecoRepository;
	@Autowired
	private final TipoLogradouroRepository tipoLogradouroRepository;
	private final ModelMapper mapper;

	public EnderecoEntity cadastrar(EnderecoPostDTO dto) {


		// transferindo o dto para o endereco
		EnderecoEntity endereco = mapper.map(dto, EnderecoEntity.class);
		
		// salvando no banco
		endRepository.save(adicionarCampos(endereco,dto));
	
		return endereco;
	}
	
	public void cadastrarWithCliente(ClienteEntity cliente, EnderecoPostDTO dto) {
		// transferindo o dto para o endereco
		EnderecoEntity endereco = mapper.map(dto, EnderecoEntity.class);
		endereco.setCliente(cliente);
		// salvando no banco
		endRepository.save(adicionarCampos(endereco,dto));
	}


	private EnderecoEntity adicionarCampos(EnderecoEntity endereco, EnderecoPostDTO dto) {
		Optional<TipoEnderecoEntity> tipoEndereco = tipoEnderecoRepository.findById(dto.getTipoEndereco().getIdTipoEndereco());
		Optional<TipoLogradouroEntity> tipoLogradouro = tipoLogradouroRepository.findById(dto.getTipoLogradouro().getIdTipoLogradouro());
		endereco.setTipoEndereco(tipoEndereco.get());
		endereco.setTipoLogradouro(tipoLogradouro.get());
		return endereco;
	}

	public List<EnderecoDTO> buscarEnderecos() {

		List<EnderecoEntity> list = endRepository.findAll();
		List<EnderecoDTO> listaGetDto = new ArrayList<EnderecoDTO>();

		for (EnderecoEntity endereco : list) {
			EnderecoDTO getDto = new EnderecoDTO(endereco);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}

	public EnderecoDTO buscarId(Integer idEndereco) {

		Optional<EnderecoEntity> result = endRepository.findById(idEndereco);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		EnderecoEntity endereco = result.get();

		return new EnderecoDTO(endereco);
	}

	public EnderecoEntity atualizar(EnderecoDTO dto) {

		Optional<EnderecoEntity> result = endRepository.findById(dto.getIdEndereco());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}
		
		EnderecoEntity endereco = result.get();
		mapper.map(dto, endereco);

		endRepository.save(endereco);

		return endereco;
	}

	public String excluir(Integer idEndereco) {

		Optional<EnderecoEntity > result = endRepository.findById(idEndereco);
		
		if(result.isEmpty()) {
			throw new EntityNotFoundException("Endereço não encontrado.");
		}

		EnderecoEntity  endereco = result.get();

		endRepository.delete(endereco);

		return "Endereço excluído com sucesso";
	}

}
