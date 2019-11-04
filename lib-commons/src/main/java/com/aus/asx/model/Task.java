package com.aus.asx.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private String id;
    private String name;
    private String owner;
    private String approver;
    private LocalDateTime created;
    private LocalDateTime updated;
}