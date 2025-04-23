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
@Data //it gives all types of annotations
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
