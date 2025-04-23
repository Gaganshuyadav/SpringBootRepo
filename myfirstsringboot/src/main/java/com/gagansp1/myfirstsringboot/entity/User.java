package com.gagansp1.myfirstsringboot.entity;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data //it gives all types of lombok annotations
@Document(collection = "users")  //by default collection is made by mongodb
public class User {

    @id
    private ObjectId id;
    @Indexed(unique = true) //if we want to do indexing then, we need to do it by ourself in resource folder/application.properties
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEnteries = new ArrayList<JournalEntry>();

}
