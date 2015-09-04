package soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {CompactDisc.class}) // por defecto busca en el mismo paquete (y subpaquetes) de esta clase
public class CDPlayerConfig {

	
	
}
