package com.example.demo.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapperのBean登録用クラス
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}
}
