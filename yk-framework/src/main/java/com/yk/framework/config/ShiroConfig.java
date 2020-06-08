package com.yk.framework.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.google.common.collect.Maps;
import com.yk.common.util.StringUtils;
import com.yk.framework.shiro.realm.UserRealm;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: shiro权限配置加载
 * @author: YuKai Fan
 * @create: 2020-06-08 10:17
 **/
@Configuration
public class ShiroConfig {
    /**
     * Session超时时间, 单位毫秒(默认30分钟)
     */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /**
     * 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
     */
    @Value("${shiro.session.validationInterval}")
    public int validationInterval;

    /**
     * 同一个用户最大会话数
     */
    @Value("${shiro.session.maxSession}")
    public int maxSession;

    /**
     * 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户
     */
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * 验证码开关
     */
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    /**
     * 验证码类型
     */
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    /**
     * 设置Cookie的域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /**
     * 登录地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * 缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        CacheManager cacheManager = CacheManager.getCacheManager("YK");
        EhCacheManager ehCacheManager = new EhCacheManager();
        if (StringUtils.isBlank(cacheManager)) {
            ehCacheManager.setCacheManager(new CacheManager(getCacheManagerConfigFileInputStream()));
        } else {
            ehCacheManager.setCacheManager(cacheManager);
        }
        return ehCacheManager;
    }

    /**
     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     */
    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 自定义Realm
     * @param ehCacheManager
     * @return
     */
    @Bean
    public UserRealm userRealm(EhCacheManager ehCacheManager) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(ehCacheManager);
        return userRealm;
    }

    /**
     * 安全管理器
     * @param userRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(userRealm);
        // 记住我
        securityManager.setRememberMeManager(rememberMeManager());
        // 注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        //session 管理器 todo

        return securityManager;
    }

    /**
     * shiro过滤器配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心是安全管理器接口, 必须要有
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败, 则跳转到登录页面配置
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败, 跳转到未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // Shiro连接约束配置, 过滤链
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        // 对静态资源放行
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/request/**", "anon");
        //退出登录
        filterChainDefinitionMap.put("/logout", "logout");
        // 放行的接口
        filterChainDefinitionMap.put("/api/system/login", "anon");

        //过滤器配置 todo
        Map<String, Filter> filters = Maps.newLinkedHashMap();

        shiroFilterFactoryBean.setFilters(filters);
        //所有请求需要认证
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * thymeleaf模板引擎和shiro框架的整合
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 开启shiro注解通知
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 记住我
     * @return
     */
    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }

    /**
     * cookie 属性设置
     * @return
     */
    private SimpleCookie simpleCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

}