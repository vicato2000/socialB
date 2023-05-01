package com.cbd.socialb.resource;

import com.cbd.socialb.node.Tag;
import com.cbd.socialb.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TagResource {

    public TagService tagService;

    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/tags", produces = "application/json")
    public List<String> getAllTags(){
        return this.tagService.findAllTags().stream().map(Tag::getName).toList();
    }

    @GetMapping(value = "/tags/{name}", produces = "application/json")
    public String getTagByName(@PathVariable(value = "name") String name){
        return this.tagService.findTagByName(name).toString();
    }

    @GetMapping(value = "/tags/{name}/find-or-create", produces = "application/json")
    public String getOrCreateTag(@PathVariable(value = "name") String name){
        return this.tagService.findOrCreateTag(name).toString();
    }

}
