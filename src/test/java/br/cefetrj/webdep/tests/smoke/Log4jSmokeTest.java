package br.cefetrj.webdep.tests.smoke;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jSmokeTest {

	@Test
	public void logError() {
		try{
			Logger logger = Logger.getLogger(Log4jSmokeTest.class);
			logger.error("Error Smoke Test");
		}catch(Exception e){
			fail("Não foi possível gerar os logs com log4j");
		}
		
	}

}
