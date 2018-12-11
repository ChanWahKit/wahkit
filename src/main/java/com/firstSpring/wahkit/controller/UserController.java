//package com.firstSpring.wahkit.controller;
//
//import com.firstSpring.wahkit.dao.UserMapper;
//import com.firstSpring.wahkit.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////证明是controller层并返回json
//@RestController
//public class UserController {
//    //依赖注入
//    @Autowired
//    UserMapper userMapper;
//
//    @RequestMapping(value = "cs")
//    public User cs(){
//        //调用dao层
//        User user = userMapper.selectUserByName("a1");
//        return user;
//    }
//
//
//}
