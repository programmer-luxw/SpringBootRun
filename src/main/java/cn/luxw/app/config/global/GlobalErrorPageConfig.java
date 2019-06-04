//package cn.luxw.app.config.global;
//
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.server.ErrorPageRegistrar;
//import org.springframework.boot.web.server.ErrorPageRegistry;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
//import com.offerslook.api.common.constant.http.HttpErrorPage;
//
//
//@Configuration
//public class GlobalErrorPageConfig implements ErrorPageRegistrar/* extends ServletWebServerFactoryCustomizer */ {
//	@Override
//	public void registerErrorPages(ErrorPageRegistry registry) {
//		ErrorPage error401 = new ErrorPage(HttpStatus.UNAUTHORIZED, HttpErrorPage.PATH.concat(HttpErrorPage.ERROR_401));
//		ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, HttpErrorPage.PATH.concat(HttpErrorPage.ERROR_404));
//		ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, HttpErrorPage.PATH.concat(HttpErrorPage.ERROR_404));
//		ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, HttpErrorPage.PATH.concat(HttpErrorPage.ERROR_500));
//		registry.addErrorPages(error401, error403, error404, error500);
//		System.err.println("====AFFAPI===ErrorPageConfig====");
//	}
//
//}
