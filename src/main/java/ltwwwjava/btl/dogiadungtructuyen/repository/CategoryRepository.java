package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CategoryRepository extends MongoRepository<Category,String> {

    @Query("{ 'link' : ?0}")
    public Category getCategoriesByLink(String link);
}
