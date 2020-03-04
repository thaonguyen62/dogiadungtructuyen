package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document
public class Product implements Serializable {

    @Id
    private String id;

    private String name;
    private double price;
    private String description;
    private Category category;
    private String fileName;
    private List<Feedback> feedbacks;


}
