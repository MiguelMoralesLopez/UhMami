package uhmami.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de beans para la aplicación.
 * Esta clase define y configura los beans utilizados en la aplicación Spring.
 * Actualmente, configura el bean {@link ModelMapper}.
 */
@Configuration
public class BeanConfiguracion {
	
	/**
     * Crea y configura un bean de tipo {@link ModelMapper}.
     * 
     * @return una nueva instancia de {@link ModelMapper}
     */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
