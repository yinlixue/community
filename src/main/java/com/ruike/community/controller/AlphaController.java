package com.ruike.community.controller;


import com.ruike.community.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        //请求方式
        String method = request.getMethod();
        System.out.println(method);
        //请求路径
        System.out.println("ServletPath: " + request.getServletPath());
        System.out.println("ContextPath: " + request.getContextPath());

        //Headers内容
        Enumeration<String> names =  request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }

        //request参数获取
        if ("GET".equals(method)) {
            String para = request.getParameter("code");
            System.out.println("code = " + para);
        }

        //requestBody


        System.out.println("返回response");
        //response返回html
        response.setContentType("text/html;charset=utf-8");
        try (
            PrintWriter writer = response.getWriter();
        ){
            writer.print("<h>天街小雨润如酥 草色要看近却无 最是一年春好处 绝胜烟柳满皇都</h>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(path = "/httpget", method = RequestMethod.GET)
    public void httpGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("this is GET method");
        http(request, response);
    }

    @RequestMapping(path = "/httppost", method = RequestMethod.POST)
    public void httpPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("this is POST method");
        http(request, response);
    }

    /*获取请求参数*/
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "mav";
    }

    //通过连接拼接参数提交
    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@RequestParam(name = "id") int id, @RequestParam("name") String name){
        System.out.println(name + " " + id);
        return "success " + name;
    }

    //通过表单提交
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String puttStudent(@RequestParam(name = "id") int id, @RequestParam("name") String name){
        System.out.println(name + " " + id);
        return "success " + name;
    }


    /*返回数据*/
    //返回ModelAndView
    @RequestMapping(path = "/getview", method = RequestMethod.GET)
    public ModelAndView getStuRetMAV(){
        System.out.println("/getview");
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "zhangsan");
        mav.addObject("age", "13");
        mav.setViewName("/demo/view.html");
        return mav;
    }

    /*
    * 关于第二个参数：spring会自动注入，并且会自动set到model中，属性的名称就是类型首字母小写，和参数名称没关系
    * */
    @RequestMapping(path = "/getview2", method = RequestMethod.GET)
    public String getStuRetMAV(Model model, User u){
        System.out.println(u);
        System.out.println((User) model.getAttribute("user"));
        model.addAttribute("name", "lisi");
        model.addAttribute("age", "15");
        return "/demo/view";
    }

    /*响应JSON数据*/
    //一般在异步请求时使用
    @RequestMapping(path = "json", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getJSON(){
        List<Map> list = new ArrayList<>();

        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("address", "ShangHai");
        list.add(map);

        Map map2 = new HashMap();
        map2.put("name", "lisi");
        map2.put("sex", "male");
        list.add(map2);

        return list;
    }


}
