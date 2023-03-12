package com.kob.matchingsystem.service.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor// 这三个帮我们实现了get函数
public class Player {
    private Integer userId;
    private Integer rating;
    private Integer waitingTime ;//等待时间

}
