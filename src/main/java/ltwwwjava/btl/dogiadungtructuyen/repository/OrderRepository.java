package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {


}
