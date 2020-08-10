package com.ruike.community.controller;

import com.ruike.community.entity.DiscussPost;
import com.ruike.community.entity.Page;
import com.ruike.community.entity.User;
import com.ruike.community.service.DiscussPostService;
import com.ruike.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    DiscussPostService discussPostService;

    @GetMapping("index")
    public String getIndexPage(Model model, Page page){
        int offset = page.getOffSet();
        List<DiscussPost> postList = discussPostService.getAllDiscussPostExceptBlackList(Math.max(offset, 0), page.getCapacity());

        int rows = discussPostService.getNumberOfAllDiscussPostExceptBlackList();
        page.setRows(rows);

        List<Map<String, Object>> postsWithUser = new ArrayList<>();

        for (DiscussPost post : postList) {
            Map<String, Object> postWithUser = new HashMap<>();

            postWithUser.put("post", post);

            User user = userService.findUserById(post.getUserId());
            postWithUser.put("user", user);

            postsWithUser.add(postWithUser);
        }
        model.addAttribute("posts", postsWithUser);
        return  "index";
    }
}
