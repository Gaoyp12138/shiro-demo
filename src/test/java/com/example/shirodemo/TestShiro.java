package com.example.shirodemo;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.ArrayList;

/**
 * @author gaoyp
 * @time 下午5:55
 */
@Slf4j
public class TestShiro {
    public static void main(String[] args) {
        log.info("running....");
        //获取安全管理器
        Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:static/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        //设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //获取subject对象
        Subject currentUser = SecurityUtils.getSubject();
        //获取session
        Session session = currentUser.getSession();
        session.setAttribute("name", "bob");
        String value = (String)session.getAttribute("name");
        if (null != value){
            log.info("value is ---------->" + value);
        }

        if(currentUser.isAuthenticated()){
            log.info("登录了");
        }else {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr","vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                log.info("登录成功");
            }catch (UnknownAccountException e){
                log.error("账户不存在");
            }catch (IncorrectCredentialsException e){
                log.error("密码错误");
            }catch (LockedAccountException e){
                log.error("用户被锁死");
            }
        }

        boolean b = currentUser.hasRole("goodguy");
        if (b){
            log.info("拥有角色");
        }else {
            log.info("未拥有");
        }

        boolean b1 = currentUser.isPermitted("winnebago:drive:eagle5");

        if (b1){
            log.info("拥有权限");
        }else {
            log.info("未拥有权限");
        }

        currentUser.logout();
        System.exit(0);
    }
}
