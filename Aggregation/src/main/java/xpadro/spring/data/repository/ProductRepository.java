package xpadro.spring.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xpadro.spring.data.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Override
    Product save(Product person);

    Product findById(String id);
}
