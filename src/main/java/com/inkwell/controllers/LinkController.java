package com.inkwell.controllers;

import com.inkwell.entities.Link;
import com.inkwell.entities.LinkRepository;
import com.inkwell.errors.ErrorResponse;
import com.inkwell.errors.LinkAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LinkController {
    private final LinkRepository repository;

    @Autowired
    public LinkController(LinkRepository repository) {
        this.repository = repository;
    }

    //all links - GET /v1/links, returns JSON of all links
    @RequestMapping(value = "/v1/links", method = RequestMethod.GET)
    public List<Link> allLinks(@RequestParam(name = "category", required = false) String category) {
        List<Link> allLinksList = new ArrayList<>();
        if (category != null) {
            allLinksList.addAll(repository.findByCategory(category));
        } else {
            for (Link link : repository.findAll()) {
                allLinksList.add(link);
            }
        }
        return allLinksList;
    }

    //one link - GET /v1/links/:linkId, returns JSON of link with id = :linkId
    @RequestMapping(value = "/v1/links/{linkId}", method = RequestMethod.GET)
    public Link getLink(@PathVariable("linkId") long linkId) {
        Link resultLink = repository.findOne(linkId);
        if (resultLink != null) {
            return resultLink;
        } else {
            throw new LinkAppException("Link not found");
        }
    }

    //post json - POST /v1/links/:linkId, returns saved object
    @RequestMapping(value = "/v1/links", method = RequestMethod.POST)
    public Link postLink(@RequestBody Link newLink) {
        Link res;
        try {
            res = repository.save(newLink);
        } catch (Exception e) {
            throw new LinkAppException(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "/v1/links/{linkId}", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateLink(@RequestBody Link updateLink, @PathVariable Long linkId) {
        try {
            Link updatingLink = repository.findOne(linkId);
            updatingLink.update(updateLink);
            repository.save(updatingLink);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            throw new LinkAppException(e.getMessage());
        }
    }

    @RequestMapping(value = "v1/links/{linkId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLink(@RequestParam(name = "linkId") Long linkId) {
        try {
            Link deletingLink = repository.findOne(linkId);
            repository.delete(deletingLink);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new LinkAppException(e.getMessage());
        }
    }

    @ExceptionHandler(LinkAppException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.I_AM_A_TEAPOT.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
