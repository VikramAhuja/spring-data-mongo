package xpadro.spring.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xpadro.spring.data.domain.Product;
import xpadro.spring.data.repository.ProductRepository;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AggregationApplication.class)
@WebAppConfiguration
public class AggregationApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findById() {
		Product product = new Product("LN1", "London", 5.0f);
		productRepository.save(product);

		Product foundProduct = productRepository.findById("LN1");

		assertNotNull(foundProduct);
	}
}
