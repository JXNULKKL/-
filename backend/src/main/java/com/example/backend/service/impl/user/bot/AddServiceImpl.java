package com.example.backend.service.impl.user.bot;

import com.example.backend.mapper.BotMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.Bot;
import com.example.backend.pojo.User;
import com.example.backend.service.impl.UserDetailsImpl;
import com.example.backend.service.user.bot.AddService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();


        String title = data.get("title");
        String description = data.get("description");
        String  content = data.get("content");

        Map<String,String> map = new HashMap<>();

        title = title.trim();
        if(title == null || title.length() == 0){
            map.put("error_message" , "标题不可以为空");
            return map;
        }

        if(title.length() > 100 ){
            map.put("error_message" , "标题过长，请100字符以内");
            return map;
        }

        description = description.trim();
        if(description == null || description.length() ==0){
            description = "这个用户很懒，什么也没有留下~";
        }

        if(description.length() > 1000 ){
            map.put("error_message" , "Bot描述过长，请1000字符以内");
            return map;
        }

        if(content == null || content.length() ==0 ){
            map.put("error_message" , "代码不能为空");
            return map;
        }

        if(content.length() > 3000){
            map.put("error_message" , "代码长度不能超过3000");
            return map;
        }

        Date now = new Date();
        Bot bot = new Bot((Integer) null, user.getId() , title ,description ,content ,1500,  now, now);
        botMapper.insert(bot);

        map.put("error_message" ,"success");
        return map;
    }


}
