package xpadro.spring.data.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xpadro.spring.data.mongo.domain.Product;
import xpadro.spring.data.mongo.dto.WarehouseSummary;
import xpadro.spring.data.mongo.repository.ProductRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AggregationApplication.class)
@WebAppConfiguration
public class AggregationApplicationTests {

	@Autowired
	private ProductRepository productRepository;


	@Before
	public void setUp() {
		productRepository.deleteAll();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void findById() {
		Product product = new Product("LN1", "London", 5.0f);
		productRepository.save(product);

		Product foundProduct = productRepository.findOne("LN1");

		assertNotNull(foundProduct);
	}

	@Test
	public void aggregateProducts() {
		saveProducts();

		List<WarehouseSummary> warehouseSummaries = productRepository.aggregate(5.0f, 70.0f);

		assertEquals(3, warehouseSummaries.size());
		WarehouseSummary liverpoolProducts = getLiverpoolProducts(warehouseSummaries);
		assertEquals(39.1, liverpoolProducts.getTotalRevenue(), 0.01);
		assertEquals(19.55, liverpoolProducts.getAveragePrice(), 0.01);
	}


	private void saveProducts() {
		productRepository.save(new Product("NW1", "Norwich", 3.0f));
		productRepository.save(new Product("LN1", "London", 25.0f));
		productRepository.save(new Product("LN2", "London", 35.0f));
		productRepository.save(new Product("LV1", "Liverpool", 15.2f));
		productRepository.save(new Product("MN1", "Manchester", 45.5f));
		productRepository.save(new Product("LV2", "Liverpool", 23.9f));
		productRepository.save(new Product("LN3", "London", 55.5f));
		productRepository.save(new Product("LD1", "Leeds", 87.0f));
	}

	private WarehouseSummary getLiverpoolProducts(List<WarehouseSummary> warehouseSummaries) {
		return warehouseSummaries.stream().filter(product -> "Liverpool".equals(product.getWarehouse())).findAny().get();
	}
}
