package ch.helsana.microservice.storageservice.infrastructure.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductMetadata {

//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    private String productId;

    @Id
    private String productid;
    private String cmsproductdetail;
    private String sort;
    private String theme;

}
