package com.aus.asx.service;

import java.util.ArrayList;
import java.util.List;

import com.aus.asx.model.Notification;

import org.springframework.stereotype.Service;

import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSvc {
    private final static List<Notification> NOTIFICATIONS = new ArrayList<Notification>() {
        private static final long serialVersionUID = -5980136744658136560L;
        {
            add(new Notification("Notification 1"));
            add(new Notification("Notification 2"));
            add(new Notification("Notification 3"));
            add(new Notification("Notification 4"));
            add(new Notification("Notification 5"));
            add(new Notification("Notification 6"));
            add(new Notification("Notification 7"));
            add(new Notification("Notification 8"));
        }
    };

    public DataFetcher<Notification[]> findAllDataFetcher() throws Exception {
        log.info("Fetching all notifications");
        return environment -> NOTIFICATIONS.toArray(new Notification[0]);
    }
}