package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Order implements Serializable {

    @Id
    private String id;
    private Customer customer;
    private int status;
    private Date billDate;
    private double totalPrice;
    private double tax;
    private List<OrderDetail> products;
    //private String serviceAddress;


}
