package com.example.backend.service.user.bot;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface RemoveService {
    Map<String,String> remove(Map<String ,String> data);
}
