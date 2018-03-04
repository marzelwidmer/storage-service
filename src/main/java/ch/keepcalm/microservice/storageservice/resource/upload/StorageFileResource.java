package ch.keepcalm.microservice.storageservice.resource.upload;

import lombok.*;

import javax.persistence.Lob;

 @Data
@Builder
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StorageFileResource {

    private String id;
    private String filename;
    @Lob
    private byte[] data;

}
