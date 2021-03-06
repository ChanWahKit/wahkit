package com.firstSpring.wahkit.controller;

import com.firstSpring.wahkit.model.*;
import com.firstSpring.wahkit.service.MessageService;
import com.firstSpring.wahkit.service.SensitiveService;
import com.firstSpring.wahkit.service.UserService;
import com.firstSpring.wahkit.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    SensitiveService sensitiveService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;


    public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(path = "/msg/list",method = {RequestMethod.GET})
    public String getConversationList(Model model){
        if(hostHolder.getUser()==null){
            return "redirect:/relogin";
        }
        try {
            int localUserId = hostHolder.getUser().getId();
            List<Message> conversationList = messageService.getConversationList(localUserId,0,10);
            List<ViewObject> conversations = new ArrayList<ViewObject>();
            for (Message message : conversationList){
                ViewObject vo =new ViewObject();
                vo.set("conversation",message);
                int targetId = message.getFromId() == localUserId?message.getToId():message.getFromId();
                vo.set("user",userService.getUser(targetId));
                vo.set("unread",messageService.getConversationUnreadCount(localUserId,message.getConversationId()));
                conversations.add(vo);
            }
            model.addAttribute("conversations",conversations);
        }catch (Exception e){
            logger.error("获取消息列表失败"+e.getMessage());
        }
        return "letter";
    }

    @RequestMapping(path = "/msg/detail",method = {RequestMethod.GET})
    public String getConversationDetail(Model model,
                                        @RequestParam("conversationId") String conversationId){
        try {
            List<Message> messageList = messageService.getMessageByConversationId(conversationId,0,10);
            List<ViewObject> messages = new ArrayList<ViewObject>();
            for (Message message: messageList){
                ViewObject vo = new ViewObject();
                vo.set("message",message);
                vo.set("user",userService.getUser(message.getFromId()));
                messages.add(vo);
                messageService.updateHasRead(conversationId);
            }
            model.addAttribute("messages",messages);

        }catch (Exception e){
            logger.error("获取详情失败"+e.getMessage());
        }
        return "letterDetail";
    }

    @RequestMapping(path = "/msg/addMessage",method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content") String content){
        try {
            if(hostHolder.getUser()==null){
                return WendaUtil.getJSONString(999,"未登录");
            }
            User user = userService.selectByName(toName);
            if (user==null){
                return WendaUtil.getJSONString(1,"用户不存在");
            }

            Message message = new Message();
            message.setCreatedDate(new Date());
            message.setFromId(hostHolder.getUser().getId());
            message.setToId(user.getId());
            message.setContent(sensitiveService.filter(content));
            messageService.addMessage(message);
            return WendaUtil.getJSONString(0);
        }catch (Exception e){
            logger.error("发送消息失败"+e.getMessage());
            return WendaUtil.getJSONString(1,"发送失败");
        }

    }

}
