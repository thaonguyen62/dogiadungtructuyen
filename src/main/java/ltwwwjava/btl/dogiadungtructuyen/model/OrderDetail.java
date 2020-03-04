package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class OrderDetail implements Serializable {

    @Id
    private String id;
    //private Order order;
    private Product product;
    private int quantity;
    private double price;


}
