package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.fornecedores.FornecedorGetDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorPostDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorPutDTO;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import br.com.princesinhadoalho.repositories.FornecedorRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class FornecedorService {

	private FornecedorRepository fornecedorRepository;
	private EnderecoRepository enderecoRepository;
	private ModelMapper mapper;

	public FornecedorGetDTO cadastrar(FornecedorPostDTO dto) {

		Optional<Fornecedor> result = fornecedorRepository.findByCpfCnpj(dto.getCpfCnpj());

		if (result.isPresent()) {
			throw new BadRequestException("Cpf/Cnpj já cadastrado.");
		}

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(dto.getNome());
		fornecedor.setCpfCnpj(dto.getCpfCnpj());
	
		Optional<Endereco> result2 = enderecoRepository.findById(dto.getIdEndereco());
		
		Endereco endereco = result2.get();
		
		fornecedor.setEndereco(endereco);

		fornecedorRepository.save(fornecedor);

		FornecedorGetDTO getDto = new FornecedorGetDTO();
		mapper.map(fornecedor, getDto);
		
		return getDto;
	}

	public List<FornecedorGetDTO> buscarFornecedores() {

		List<Fornecedor> list = fornecedorRepository.findAll();
		List<FornecedorGetDTO> listaGetDto = new ArrayList<FornecedorGetDTO>();

		for (Fornecedor fornecedor : list) {
			FornecedorGetDTO getDto = new FornecedorGetDTO();
			mapper.map(fornecedor, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}
	
	public FornecedorGetDTO buscarId(Integer idFornecedor) {

		Optional<Fornecedor> result = fornecedorRepository.findById(idFornecedor);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}

		Fornecedor fornecedor = result.get();

		FornecedorGetDTO getDto = new FornecedorGetDTO();
		mapper.map(fornecedor, getDto);

		return getDto;
	}
	
	public FornecedorGetDTO atualizar(FornecedorPutDTO dto) {
		
		Optional<Fornecedor> result = fornecedorRepository.findById(dto.getIdFornecedor());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}
		
		Fornecedor fornecedor = result.get();
		mapper.map(dto, fornecedor);

		fornecedorRepository.save(fornecedor);
		
		FornecedorGetDTO getDto = new FornecedorGetDTO();
		mapper.map(fornecedor, getDto);
		
		return getDto;
	}

	public String excluir(Integer idFornecedor) {

		Optional<Fornecedor> result = fornecedorRepository.findById(idFornecedor);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}

		Fornecedor fornecedor = result.get();

		fornecedorRepository.delete(fornecedor);

		return "Fornecedor " + result.get().getNome() + " excluído com sucesso.";
	}

}
