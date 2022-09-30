package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.vendedores.VendedorGetDTO;
import br.com.princesinhadoalho.dtos.vendedores.VendedorPostDTO;
import br.com.princesinhadoalho.dtos.vendedores.VendedorPutDTO;
import br.com.princesinhadoalho.entities.Vendedor;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.VendedorRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class VendedorService {

	private final VendedorRepository vendedorRepository;
	private final ModelMapper mapper;

	public VendedorGetDTO cadastrar(VendedorPostDTO dto) {

		Vendedor vendedor = mapper.map(dto, Vendedor.class);
	
		vendedorRepository.save(vendedor);

		// convertendo o vendedor em dto e retornando ao cotroller
		return new VendedorGetDTO(vendedor);
	}

	public List<VendedorGetDTO> buscarVendedors() {

		List<Vendedor> list = vendedorRepository.findAll();
		List<VendedorGetDTO> listaGetDto = new ArrayList<VendedorGetDTO>();

		for (Vendedor vendedor : list) {
			VendedorGetDTO getDto = new VendedorGetDTO(vendedor);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}

	public VendedorGetDTO buscarId(Integer idVendedor) {

		Optional<Vendedor> result = vendedorRepository.findById(idVendedor);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Vendedor não encontrado.");
		}

		Vendedor vendedor = result.get();

		return new VendedorGetDTO(vendedor);
	}

	public VendedorGetDTO atualizar(VendedorPutDTO dto) {

		Optional<Vendedor> result = vendedorRepository.findById(dto.getIdVendedor());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Vendedor não encontrado.");
		}
		
		Vendedor vendedor = result.get();
		mapper.map(dto, vendedor);

		vendedorRepository.save(vendedor);
			
		return new VendedorGetDTO(vendedor);
	}

	public String excluir(Integer idVendedor) {

		Optional<Vendedor> result = vendedorRepository.findById(idVendedor);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Vendedor não encontrado.");
		}

		Vendedor vendedor = result.get();

		vendedorRepository.delete(vendedor);
		
		return "Vendedor " + vendedor.getNome() + " excluído com sucesso.";
	}
	
}
