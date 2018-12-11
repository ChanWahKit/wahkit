package com.firstSpring.wahkit.controller;

import com.firstSpring.wahkit.dao.CommentMapper;
import com.firstSpring.wahkit.model.Comment;
import com.firstSpring.wahkit.model.EntityType;
import com.firstSpring.wahkit.model.HostHolder;
import com.firstSpring.wahkit.service.QuestionService;
import com.firstSpring.wahkit.service.SensitiveService;
import com.firstSpring.wahkit.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    SensitiveService sensitiveService;

    @Autowired
    QuestionService questionService;


    public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(path = "/addComment",method = {RequestMethod.POST})
    public String addComment(@RequestParam("questionId") int questionId,
                             @RequestParam("content") String content){
        try {
            Comment comment = new Comment();
            comment.setContent(sensitiveService.filter(content));
            if(hostHolder.getUser()!=null){
                comment.setUserId(hostHolder.getUser().getId());
            }
            else {
                comment.setUserId(WendaUtil.ANONYMOUS_USERID);
            }
            comment.setCreatedDate(new Date());
            comment.setEntityType(EntityType.ENTITY_QUESTION);
            comment.setEntityId(questionId);
            commentMapper.addComment(comment);

            int count = commentMapper.getCommentCount(questionId,EntityType.ENTITY_QUESTION);
            questionService.updateCommentCount(comment.getEntityId(),count);
        }catch (Exception e){
            logger.error("增加评论失败"+e.getMessage());
        }
        return "redirect:/question/"+questionId;
    }
}
