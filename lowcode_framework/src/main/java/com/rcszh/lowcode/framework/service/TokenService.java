package com.rcszh.lowcode.framework.service;

import cn.hutool.http.useragent.UserAgent;
import com.rcszh.lowcode.common.entity.LoginUser;
import com.rcszh.lowcode.common.utils.StringUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    private static final Logger log = LoggerFactory.getLogger(TokenService.class);
    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000L;
    protected static final long MILLIS_MINUTE = 60000L;
    private static final Long MILLIS_MINUTE_TEN = 1200000L;

    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = this.getToken(request);
        if (!token.isEmpty()) {
            try {
                Claims claims = this.parseToken(token);
                String uuid = (String)claims.get("login_user_key");
                String userKey = this.getTokenKey(uuid);
//                LoginUser user = (LoginUser)redisCache.getCacheObject(userKey);
//                return user;
                return null;
            } catch (Exception var7) {
                Exception e = var7;
                log.error("获取用户信息异常'{}'", e.getMessage());
            }
        }

        return null;
    }

    public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            this.refreshToken(loginUser);
        }

    }

    public void delLoginUser(String token) {
        if (token.isEmpty()) {
            return;
        }
        String userKey = this.getTokenKey(token);
//        this.redisCache.deleteObject(userKey);
    }

    public String createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        this.setUserAgent(loginUser);
        this.refreshToken(loginUser);
        Map<String, Object> claims = new HashMap();
        claims.put("login_user_key", token);
        return this.createToken((Map)claims);
    }

    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            this.refreshToken(loginUser);
        }
    }

    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + (long)this.expireTime * 60000L);
        String userKey = this.getTokenKey(loginUser.getToken());
//        this.redisCache.setCacheObject(userKey, loginUser, this.expireTime, TimeUnit.MINUTES);
    }

    public void setUserAgent(LoginUser loginUser) {
//        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//        String ip = IpUtils.getIpAddr();
//        loginUser.setIpaddr(ip);
//        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
//        loginUser.setBrowser(userAgent.getBrowser().getName());
//        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    private Claims parseToken(String token) {
        return (Claims)Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = this.parseToken(token);
        return claims.getSubject();
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(this.header);
        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }

        return token;
    }

    private String getTokenKey(String uuid) {
        return "login_tokens:" + uuid;
    }
}
