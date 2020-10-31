package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class PageQuery {
    int number;
    int limit;

    @JsonCreator
    public PageQuery(@JsonProperty("number") int number, @JsonProperty("limit") int limit) {
        this.number = number;
        this.limit = limit;
    }

    public int offset() {
        return limit * (number - 1);
    }
}
