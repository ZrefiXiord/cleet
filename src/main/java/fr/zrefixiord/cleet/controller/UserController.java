package fr.zrefixiord.cleet.controller;

import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public HashMap<String, String> showUser(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if(user==null)
            return new HashMap<>();
        HashMap<String, String> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("display_name", user.getDisplayName());
        response.put("bio", user.getBio());
        response.put("followers", String.valueOf(user.getFollowers()));
        response.put("following", String.valueOf(user.getFollowing()));
        response.put("creation", String.valueOf(user.getCreation()));
        return response;
    }
}
