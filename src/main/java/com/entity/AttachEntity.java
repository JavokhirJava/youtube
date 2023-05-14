package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "attach")
@Entity
public class AttachEntity {
    //    id(uuid),origin_name,size,type (extension),path,duration
    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "original_name")
    private String originalName;
    @Column
    private Long size;
    @Column
    private String extension;
    @Column
    private String path;
    @Column(name = "created_date")
    private LocalDateTime createdData;
}
