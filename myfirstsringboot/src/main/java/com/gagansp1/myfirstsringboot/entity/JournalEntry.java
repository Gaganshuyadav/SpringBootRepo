package com.gagansp1.myfirstsringboot.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

//POJO:- PLAIN OLD JAVA OBJECT
@Document(collection = "journal_enteries")
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Slf4j

//With @NoArgsConstructor: You can create a Route object without any parameters and set the values later using setter methods.
//@NoArgsConstructor is useful for compatibility with frameworks, when using JPA or Hibernate for ORM entities often require a no-argument constructor, serialization, and testing.

//With @AllArgsConstructor: You create a Route object by providing both startLocation and endLocation at the time of instantiation.
//@AllArgsConstructor is beneficial for creating immutable objects, DTOs, and configuration classes.

@Data //it gives all types of annotations
//@NoArgsConstructor
//Deserialization:-  object is converted into a format that can be easily stored or transmitted, such as JSON, XML, or binary format.
public class JournalEntry {

    //if not give id it automatically create id
    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;



//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
