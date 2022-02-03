package com.example.nord;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
@Api("Api")
public class Controller {
    LinkService linkService;

    @Autowired
    public Controller(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/add")
    @ApiOperation("Adds a new link")
    private UUID addLink(@RequestBody LinkEntity linkToAdd) {
        return linkService.addLink(linkToAdd);
    }

    @GetMapping("/{uuid}")
    @ApiOperation("Redirects")
    private ModelAndView redirect(ModelMap model, @PathVariable UUID uuid) {
        LinkEntity linkEntity = linkService.findLinkEntityByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        linkEntity.incrementViews();
        return new ModelAndView("redirect:http://" + linkEntity.getUrl(), model);
    }


    @GetMapping("/all")
    @ApiOperation("Shows all links. Requires an authentication.")
    private List<LinkEntity> getAll(){
        return linkService.findAll();
    }
    @DeleteMapping("/delete/{uuid}")
    @ApiOperation("Deletes a link. Requires an authentication.")
    private String delete(@PathVariable UUID uuid) {
        return linkService.deleteLink(uuid);
    }
}