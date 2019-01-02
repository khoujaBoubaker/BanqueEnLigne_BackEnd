package mbdemoapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mbdemoapp.util.logs.ILogger;

@Aspect
@Component
public class CreditAspect {
	
	@Autowired
	@Qualifier("file")
	private ILogger logger;
	
	
	
	@Before("execution "
			+ "(* mbdemoapp.services.BanqueMetier.IBanqueMetier."
			+ "AjouterCreditAunClient(..))")
	public void logBefore() {
		logger.log(""
				+ "La methode d'ajout credit "
				+ "est sur le point de "
				+ "s'executer");
	}

}
