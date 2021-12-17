package br.com.lstcode.services;

import br.com.lstcode.model.User;
import br.com.lstcode.model.UserGroup;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class JiraUserGroupService {

    private final RestTemplate restTemplate;

    public JiraUserGroupService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserGroup> insertUserInGroup(List<UserGroup> userGroupList, String url, String username, String password){
        List<UserGroup> userFail = new ArrayList<>();
        userGroupList.forEach( userGroup -> {

            User user = new User(userGroup.getUser());
            try {
                System.out.println(url + "/rest/api/3/group/user?groupname=" + userGroup.getGroup());
                System.out.println(userGroup.getUser() + " " + userGroup.getUser() + " " + username + " " + password);
//                restTemplate.exchange(url + "/rest/api/3/group/user?groupname=" + userGroup.getGroup(),
//                        HttpMethod.POST,
//                        new HttpEntity<>(user,  createHeaders(username, password)),
//                        String.class);
            }catch (Exception e){
                e.printStackTrace();
                userFail.add(userGroup);
            }
        });
        return userFail;
    }

    private HttpHeaders createHeaders(String username, String password){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        return headers;
    }
}
