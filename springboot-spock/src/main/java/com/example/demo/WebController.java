package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class WebController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RemoteFetcher remoteFetcher;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable long id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(userExample);
        if (0 == users.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }

        return "{\"id\":" + id + ",\"name\":\"" + users.get(0).getName() + "\"}";
    }

    @PostMapping("/user/add")
    public String addUser(@RequestBody Map<String, String> payload) {
        if (payload.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required");
        }

        String name = payload.get("name");
        if (null == name) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required");
        }

        User user = new User();
        String remoteFetchedName = remoteFetcher.fetch(name);
        user.setName(remoteFetchedName);
        long id = userMapper.insert(user);

        return "{\"id\":" + id + ",\"name\":\"" + remoteFetchedName + "\"}";
    }
}
