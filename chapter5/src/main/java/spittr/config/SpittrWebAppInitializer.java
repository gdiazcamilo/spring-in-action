package spittr.config;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spittr.web.WebConfig;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{ WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        String UPLOADED_FILES_PATH = "/tmp/spittr/uploads";
        registration.setMultipartConfig(new MultipartConfigElement(UPLOADED_FILES_PATH, 2097152, 4194304, 0));

    }
}
