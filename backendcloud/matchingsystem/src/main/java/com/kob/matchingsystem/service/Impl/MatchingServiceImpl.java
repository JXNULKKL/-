package com.kob.matchingsystem.service.Impl;


import com.kob.matchingsystem.service.MatchingService;
import com.kob.matchingsystem.service.utils.MatchingPool;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImpl implements MatchingService {

    public final static MatchingPool matchingpool = new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating) {
        System.out.println("add player: " + userId + " " + rating );
        return null;
    }

    @Override
    public String removePlayer(Integer userId) {
        System.out.println("remove player: " + userId);
        return null;
    }
}
