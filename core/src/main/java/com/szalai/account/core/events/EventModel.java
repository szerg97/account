package com.szalai.account.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "eventStore")
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {

    @Id
    private String id;
    private Date timestamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private Integer version;
    private String eventType;
    private BaseEvent eventData;

    public EventModel(Date timestamp, String aggregateIdentifier, String aggregateType, Integer version, String eventType, BaseEvent eventData) {
        this.timestamp = timestamp;
        this.aggregateIdentifier = aggregateIdentifier;
        this.aggregateType = aggregateType;
        this.version = version;
        this.eventType = eventType;
        this.eventData = eventData;
    }
}
