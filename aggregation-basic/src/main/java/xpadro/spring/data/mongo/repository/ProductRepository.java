package xpadro.spring.data.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xpadro.spring.data.mongo.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom {

}
