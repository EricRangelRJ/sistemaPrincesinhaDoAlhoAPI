package br.com.princesinhadoalho.reflections;

import java.lang.reflect.Field;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;

public class EnderecoReflection {

	public boolean reflection(EnderecoDTO enderecoDTO) {
		
		Class<EnderecoDTO> enderecoClass = EnderecoDTO.class;
		Field[] fields = enderecoClass.getDeclaredFields();

		Object result = null;
		Object result2 = "";

		// buscando os campos da entidade
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				result = field.get(enderecoDTO);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// comparando os valores dos campos(rejeitando valores nulos ou vazios)
			if (result != null && result != result2) {
				result2 = result;

				return true;
			}
		}

		return false;
	}

}
