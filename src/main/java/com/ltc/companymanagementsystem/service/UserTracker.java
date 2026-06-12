package com.ltc.companymanagementsystem.service;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserTracker {

    private final Set<String> users = ConcurrentHashMap.newKeySet();

    public void addUser(String user) {
        users.add(user);
    }

    public void removeUser(String user) {
        users.remove(user);
    }

    public int getOnlineCount() {
        return users.size();
    }
}
