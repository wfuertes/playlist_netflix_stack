package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Collection;

@Value
@Accessors(fluent = true)
public class Page<T> {
    int number;
    int records;
    Collection<T> content;

    @JsonCreator
    public Page(@JsonProperty("number") int number,
                @JsonProperty("records") int records,
                @JsonProperty("content") Collection<T> content) {

        this.number = number;
        this.records = records;
        this.content = content;
    }
}
