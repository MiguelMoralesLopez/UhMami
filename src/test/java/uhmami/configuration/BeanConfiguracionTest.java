package uhmami.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class BeanConfiguracionTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testModelMapperBeanCreation() {
        // Verifica que el bean ModelMapper se haya creado y esté presente en el contexto de la aplicación
        ModelMapper modelMapper = applicationContext.getBean(ModelMapper.class);
        assertThat(modelMapper).isNotNull();
    }
}
