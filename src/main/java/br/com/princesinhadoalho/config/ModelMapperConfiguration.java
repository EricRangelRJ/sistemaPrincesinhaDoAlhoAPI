package br.com.princesinhadoalho.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	/*@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}*/
	
	

    @Bean
    public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration().setAmbiguityIgnored(true);
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
         modelMapper.getConfiguration().setSkipNullEnabled(true);
         return modelMapper;
    }
}
