package com.example.backend.service.impl.user.bot;

import com.example.backend.mapper.BotMapper;
import com.example.backend.pojo.Bot;
import com.example.backend.pojo.User;
import com.example.backend.service.impl.UserDetailsImpl;
import com.example.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class RemoveServiceImpl implements RemoveService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUer = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUer.getUser();


        int bot_id = Integer.parseInt(data.get("bot_id"));

        Bot bot = botMapper.selectById(bot_id);

        Map<String ,String> map = new HashMap<>();

        if(bot == null){
            map.put("error_message" , "Bot不存在或已经删除");
            return map;
        }


        if(!bot.getId().equals(user.getId())){
            map.put("error_message" , "没有权限删除改Bot");
            return map;
        }

        botMapper.deleteById(bot_id);
        map.put("error_message" , "success");

        return map;

    }
}
