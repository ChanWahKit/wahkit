package com.firstSpring.wahkit;

import com.firstSpring.wahkit.dao.LoginTicketMapper;
import com.firstSpring.wahkit.dao.QuestionMapper;
import com.firstSpring.wahkit.dao.UserMapper;
import com.firstSpring.wahkit.model.LoginTicket;
import com.firstSpring.wahkit.model.Question;
import com.firstSpring.wahkit.model.User;
import com.firstSpring.wahkit.model.ViewObject;
import com.firstSpring.wahkit.service.QuestionService;
import com.firstSpring.wahkit.service.UserService;
import com.firstSpring.wahkit.util.WendaUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WahkitApplication.class)
public class testDatabase {
    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Test //插入
    public void tdatabase(){
//        String username = "12221";
//        String password = "111111111";
//        User user = new User();
//        user.setName(username);
//        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
//        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
//        user.setHeadUrl(head);
//        user.setPassword(WendaUtil.MD5(password+user.getSalt()));
//        userMapper.addUser(user);
//        Integer a =userMapper.selectIdByName(username);
//
//        // 登陆
//        String ticket = addLoginTicket(user.getId());
//        System.out.print(ticket);
        Random random = new Random();
//
//        List<Question> questionList = questionService.getLastQuestion(0,0,9);
//        for (Question question:questionList){
//            System.out.println(question.getUserId());
//        }
        for (int i =1;i<11;i++){
//            Question question = questionService.selectq(i);
//            System.out.print(i);
//            System.out.println(userService.getUser(question.getUserId()));
//
//        }
//
//            System.out.println(vo.get(String.valueOf(i)));
//            i++;
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
            user.setName(String.format("USER%d",i));
            user.setPassword("");
            user.setSalt("");
            userMapper.addUser(user);
            user.setId(i+2);
            user.setPassword("123456");
            userMapper.updatePassword(user);

//            Question question = new Question();
//            question.setCommentCount(i);
//            Date date = new Date();
//            date.setTime(date.getTime()+1000*3600*i);
//            question.setCreatedDate(date);
//            question.setUserId(i+1);
//            question.setTitle(String.format("TILTE{%d}",i));
//            question.setContent(String.format("blabakbakbkab content %d",i));
//
//            questionMapper.addQuestion(question);


        }

//        Assert.assertNull(userMapper.selectById(49));
//        System.out.print(questionMapper.selectLastQuestions(0,0,10));

//    private String addLoginTicket(int userId){
//        LoginTicket loginTicket = new LoginTicket();
//        loginTicket.setUserId(userId);
//        Date now = new Date();
//        now.setTime(3600*24*100+now.getTime());
//        loginTicket.setExpired(now);
//        loginTicket.setStatus(0);
//        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
//        loginTicketMapper.addTicket(loginTicket);
//        return loginTicket.getTicket();
//    }

}
    public static void main(String args[]){
        new testDatabase();
    }
}

