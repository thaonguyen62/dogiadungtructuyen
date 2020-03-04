package ltwwwjava.btl.dogiadungtructuyen.repository;


import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

//    @Query("{'category.name': {'$in': ['name']} }")
//    List<Product> getByCategoryName(String name);

}
