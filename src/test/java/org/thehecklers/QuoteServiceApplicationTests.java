package org.thehecklers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringApplicationConfiguration(classes = QuoteServiceApplication.class)
@WebAppConfiguration
public class QuoteServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
