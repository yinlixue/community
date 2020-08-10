package com.ruike.community;

import com.ruike.community.dao.AlphaDao;
import com.ruike.community.dao.UserMapper;
import com.ruike.community.dao.impl.AlphaDaoMyBatisImpl;
import com.ruike.community.entity.User;
import com.ruike.community.service.AlphaService;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    AlphaDao alphaDao2;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /*Spring容器创建Bean*/
    @Test
    public void testApplicationContext() {

        //通过类型获取Bean：如果一个接口含有多个实现类，
        // 那么可以用@Primary注解来说明默认的是哪一个(Autowired默认是通过类型装配)
        AlphaDao alphaDao1 = applicationContext.getBean(AlphaDao.class);

        String method = alphaDao1.select();
        System.out.println(method);
        System.out.println(alphaDao2.select());

        //通过名称来获取：
        // 命名规则:（默认）为实现类的类名首字母小写
        // 可选：在@Component一类的注解后面加上（字符串）参数，参数即代表bean的名字
        AlphaDao alphaDao3 = (AlphaDaoMyBatisImpl)applicationContext.getBean("mybatis");
        System.out.println(alphaDao3.select());
        // 或者这样，不用强制类型转换
        AlphaDao alphaDao4 = applicationContext.getBean("hibernate", AlphaDao.class);
        System.out.println(alphaDao4.select());

    }

    /*Spring容器初始化Bean以及销毁Bean*/
    @Test
    public void testBeanManagement(){

        /*默认的作用范围是单例：在项目中一般也是使用默认的单例模式*/
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);

        AlphaService alphaService2 = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService2);

        /*使用@Scope注解调整为prototype的Bean*/
        AlphaService alphaService3 = applicationContext.getBean("alphaServiceMultipleThreadImpl", AlphaService.class);
        System.out.println(alphaService3);

        AlphaService alphaService4 = applicationContext.getBean("alphaServiceMultipleThreadImpl", AlphaService.class);
        System.out.println(alphaService4);

        /*执行结果显示：
        * 1.单例的Bean在Spring应用启动阶段就已经完成构造和初始化
        * 2.单例的Bean只有一个，并且在程序停止前自动销毁
        * 3.prototype的Bean不会在启动程序的时候自动创建，只有在用getBean方法获取的Bean的时候才会创建
        * 4.prototype的Bean同样也不会再程序终止前自动销毁*/
    }

    @Test
    public void testConfigurationBean(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        Date now = new Date();
        String formatDate = simpleDateFormat.format(now);
        System.out.println(formatDate);
    }

    /*1.@Autowired默认是通过类型来注入
    * 2.如果一个接口有多个实现类，可以通过@Qualifier来指定装配的Bean的名字，否则将自动装配用@Primary注解的Bean*/
    @Autowired
    @Qualifier("alphaServiceMultipleThreadImpl")
    AlphaService alphaService;



    @Test
    public void testDI(){
        System.out.println(alphaService);
    }

    @Autowired
    UserMapper userMapper;

    @Test
    public void mapperTest(){
        User user = userMapper.selectUserById(11);
        System.out.println(user);

        user = userMapper.selectUserByEmail("yin@sina.com");
        System.out.println(user);
        user = userMapper.selectUserByName("yinlixue");
        System.out.println(user);

        userMapper.updateHeader(150, "no header url");
        System.out.println(userMapper.selectUserById(150));

        userMapper.updateStatus(150, 20);
        System.out.println(userMapper.selectUserById(150));

        userMapper.updatePassword(150, "sdhusgsjabeka");
        System.out.println(userMapper.selectUserById(150));

        user.setUsername("wuyvxing");
        userMapper.insertUser(user);
        System.out.println(userMapper.selectUserByName("wuyvxing"));


    }
}
