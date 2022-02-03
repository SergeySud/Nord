package com.example.nord;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
@Api("Test")
public class Controller {
    LinkService linkService;

    @Autowired
    public Controller(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/red")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
//        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:http://localhost:8080/2", model);
    }

    @PostMapping("/add")
    private UUID addLink(@RequestBody LinkEntity linkToAdd) {
        return linkService.addLink(linkToAdd);
    }

    @GetMapping("/{uuid}")
    private ModelAndView redirect(ModelMap model, @PathVariable UUID uuid) {
        LinkEntity linkEntity = linkService.findLinkEntityByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        linkEntity.incrementViews();
        return new ModelAndView("redirect:http://" + linkEntity.getUrl(), model);
    }


    @GetMapping("/all")
    private List<LinkEntity> getAll(){
        return linkService.findAll();
    }
    @DeleteMapping("/delete/{uuid}")
    private String delete(@PathVariable UUID uuid) {
        return linkService.deleteLink(uuid);
    }
}