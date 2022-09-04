package com.anky.xoriant.stringapi.controller;

import com.anky.xoriant.stringapi.dto.User;
import com.anky.xoriant.stringapi.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class StringController {

    @Autowired
    StringService stringService;

    @GetMapping(
            path = "/getUniqueUserIdsByEmail",
            produces = "application/json"
    )
    public Map<String, List<Integer>> getUniqueUserIdsByEmail() {
        return stringService.getUserForEachEmail();
    }

    @GetMapping(
            path = "/getDuplicateUserIdsByEmail",
            produces = "application/json"
    )
    public Map<String, List<Integer>> getDuplicateUserIdsByEmail() {
        return stringService.getDuplicateUserForEachEmail();
    }

    @GetMapping(
            path = "/getDuplicateEntries",
            produces = "application/json"
    )
    public List<User> getUsersEntries() {
        return stringService.getDuplicateUser();
    }

}
