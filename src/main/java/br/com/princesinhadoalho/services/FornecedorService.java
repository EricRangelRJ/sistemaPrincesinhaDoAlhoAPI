package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorGetDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorPostDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorPutDTO;
import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.reflections.EnderecoReflection;
import br.com.princesinhadoalho.repositories.EnderecoRepository;
import br.com.princesinhadoalho.repositories.FornecedorRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class FornecedorService {

	private final ModelMapper mapper;
	private final EnderecoService enderecoService;
	private final EnderecoRepository endRepository;
	private final FornecedorRepository fornecedorRepository;

	public FornecedorGetDTO cadastrar(FornecedorPostDTO dto) {

		List<Fornecedor> lista = fornecedorRepository.findAll();
		
		Fornecedor result = new Fornecedor();
		mapper.map(dto, result);

		for (Fornecedor result2 : lista) {
			if (result.equals(result2)) {
				if(result.getCpfCnpj().equalsIgnoreCase(result2.getCpfCnpj())) {
					throw new BadRequestException(" cpf ou cnpj já cadastrado.");
				}else if(result.getTelefone1().equalsIgnoreCase(result2.getTelefone1())) {
					throw new BadRequestException(" telefone1 já cadastrado.");
				}else if(result.getTelefone2().equalsIgnoreCase(result2.getTelefone2())) {
					throw new BadRequestException(" telefone2 já cadastrado.");
				}else if(result.getEmail().equalsIgnoreCase(result2.getEmail())) {
					throw new BadRequestException(" email já cadastrado.");
				}	
			}
		}

		// convertendo os camps referente à endereço para um enderecoDTO
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		mapper.map(dto, enderecoDTO);

		// verificando se existem campos NÃO nulos em enderecoDTO
		EnderecoReflection endReflection = new EnderecoReflection();
		boolean result3 = endReflection.reflection(enderecoDTO);

		Endereco endereco = new Endereco();
		// caso exista
		if (result3) {
			
			// buscando um endereço existente no banco
			Optional<Endereco> result4 = endRepository.findByLogradouroAndNumeroAndCepAndComplemento(
					dto.getLogradouro(), dto.getNumero(), dto.getCep(), dto.getComplemento());

			if(result4.isPresent()) {
				throw new BadRequestException(" Endereço já cadastrado.");	
			}
			// cadastrando um endereço
			endereco = enderecoService.cadastrar(enderecoDTO);
		}

		// Cadastrando novo Fornecedor
		Fornecedor fornecedor = new Fornecedor();
		mapper.map(dto, fornecedor);
		if (endereco.getIdEndereco() != null) {
			fornecedor.setEndereco(endereco);
		}
		fornecedorRepository.save(fornecedor);

		// convertendo o fornecedor em dto e retornando ao cotroller
		return getFornecedor(fornecedor);
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

		// convertendo o endereço do Fornecedor para um enderecoDTO
		EnderecoDTO enderecoDto = new EnderecoDTO();
		mapper.map(dto, enderecoDto);
		
		// verificando se existem campos NÃO nulos em enderecoDTO
		EnderecoReflection endReflection = new EnderecoReflection();
		boolean result2 = endReflection.reflection(enderecoDto);

		// atualizando os dados do Fornecedor encontrado
		Fornecedor fornecedor = result.get();
		mapper.map(dto, fornecedor);
		
		// SITUAÇÃO 1: caso o fornecedor não possua endereço
		if (fornecedor.getEndereco() == null) {
			// se result2 = TRUE
			if (result2) {
				// cadastrar novo endereço para o fornecedor
				Endereco endereco = enderecoService.cadastrar(enderecoDto);
				fornecedor.setEndereco(endereco);
			}

			fornecedorRepository.save(fornecedor);

			return getFornecedor(fornecedor);
		}
	
		// SITUAÇÃO 2: caso o fornecedor já possua endereço
		if (!result2) {
			// se result2 = FALSE
			throw new BadRequestException("Prencha pelo menos um campo de endereço.");
		}

		// atualizando endereço do fornecedor
		enderecoDto.setIdEndereco(fornecedor.getEndereco().getIdEndereco());
		enderecoService.atualizar(enderecoDto);

		fornecedorRepository.save(fornecedor);

		return getFornecedor(fornecedor);
	}

	public String excluir(Integer idFornecedor) {

		Optional<Fornecedor> result = fornecedorRepository.findById(idFornecedor);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}

		Fornecedor fornecedor = result.get();

		// Excluindo o endereço do Fornecedor no banco de dados(OneToOne)
		if (fornecedor.getEndereco() != null) {
			enderecoService.excluir(fornecedor.getEndereco().getIdEndereco());
		}

		fornecedorRepository.delete(fornecedor);

		return "Fornecedor " + result.get().getNome() + " excluído com sucesso.";
	}

	// Método para converter um Fornecedor em getDto
	public FornecedorGetDTO getFornecedor(Fornecedor fornecedor) {
		FornecedorGetDTO getDto = new FornecedorGetDTO();
		mapper.map(fornecedor, getDto);
		return getDto;
	}

}
