package com.anky.xoriant.stringapi.service;

import com.anky.xoriant.stringapi.dto.User;
import com.anky.xoriant.stringapi.utils.Constants;
import com.anky.xoriant.stringapi.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StringService {

    private List<User> getUsersList() {
        try {
            List<User> usersList = new ArrayList<>();
            String[] splitStrings = Constants.INPUT_USER_STRING.split("\n");
            for (String userString : splitStrings) {
                String[] userAttributes = userString.split(",");
                User user = new User();
                user.setUserId(Integer.parseInt(userAttributes[0]));
                user.setUserName(userAttributes[1]);
                user.setEmailId(userAttributes[2]);
                user.setOrganization(userAttributes[3]);
                user.setLocation(userAttributes[4]);
                usersList.add(user);
            }
            return usersList;
        } catch (NullPointerException npe) {
            log.warn("StringService : Input User String is Null");
            return null;
        } catch (Exception ex) {
            log.error("StringService : Caught Generic Exception: " + ex);
            return null;
        }
    }

    //Number of Unique userId for each Email Id
    public Map<String, List<Integer>> getUserForEachEmail() {
        List<User> usersList = getUsersList();

        assert usersList != null;
        return usersList.stream()
                .filter(Utility.distinctByKey(User::getUserId))
                .collect(
                        Collectors.groupingBy(
                                User::getEmailId,
                                Collectors.mapping(
                                        User::getUserId, Collectors.toList())));
    }

    //Number of Duplicate UserId for each Email Id
    public Map<String, List<Integer>> getDuplicateUserForEachEmail() {
        List<User> usersList = getUsersList();

        assert usersList != null;
        return usersList.stream()
                .collect(Collectors.groupingBy(u -> u.getUserId() + u.getEmailId(), Collectors.toList()))
                .values()
                .stream()
                .filter(u -> u.size()>1)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(User::getEmailId, Collectors.mapping(User::getUserId, Collectors.toList())));
    }


    //Number of Duplicated Entries
    public List<User> getDuplicateUser() {
        List<User> usersList = getUsersList();

        assert usersList != null;
        return usersList
                .stream()
                .filter(u -> Collections.frequency(usersList, u) > 1)
                .toList();
    }

}
