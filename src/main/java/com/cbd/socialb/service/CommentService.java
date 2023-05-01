package com.cbd.socialb.service;

import com.cbd.socialb.node.Comment;
import com.cbd.socialb.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(readOnly = false)
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
