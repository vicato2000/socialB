package com.cbd.socialb.service;

import com.cbd.socialb.node.Tag;
import com.cbd.socialb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tag findTagByName(String name) {
        return tagRepository.findByName(name.toLowerCase().trim());
    }

    @Transactional
    public Tag findOrCreateTag(String name) {
        Tag findTag = findTagByName(name);

        if (findTag == null) {
            findTag = saveTag(name);
        }

        return findTag;
    }

    @Transactional(readOnly = false)
    public Tag saveTag(String name) {

        Tag tag = new Tag();
        tag.setName(name.toLowerCase().trim());

        return tagRepository.save(tag);
    }
    

}
