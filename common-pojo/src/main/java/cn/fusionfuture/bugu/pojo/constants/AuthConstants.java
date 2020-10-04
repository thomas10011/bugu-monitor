package cn.fusionfuture.bugu.pojo.constants;

/**
 * @author thomas
 * @version 1.0
 * @class AuthConstants
 * @description TODO
 * @date 2020/8/22 6:59 下午
 */
public class AuthConstants {
    public static final String TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTA0MzI2NjksInVzZXJfbmFtZSI6IjEzMDUxODI3NDc5MzA3MTgyMDkiLCJhdXRob3JpdGllcyI6WyJlbXBsb3llZSIsInVzZXIiXSwianRpIjoiOGVmNWE1Y2ItMjA5MC00YjNkLWJhNzUtNTRkMmIzMjNiZDkxIiwiY2xpZW50X2lkIjoiY2xpZW50LWFwcCIsInNjb3BlIjpbImFsbCJdfQ.c5mORiBO1xMPKuS8bcRFyS2w9eAw7Ytq1jvRW0P5s-VXw_peEbjyzSxaUgYXsEsK6gEDY10dyXSyiglRpf7tiIaNjtB6qJJO61fAlu8rbR-DZ1HZGw3cB0XqAvTFJ9lU83jQ6dr8Ok59in6tm7cjUMgXXCySB9_Q4SbIzXwUyU1GpeCKbWCI6EXJawH8dmDaO3u_6l6d7wbdaW81OcXadqREstLPPb1DBgiiAq4U7k57XTV62LI1oG7BZkNPux9YUCHZycuCFUXaYde5Qu2mXXCqkdfQ0VB-XTrXI4dEyEC0rbjcMXvFddk5zvKKsYwsOgEeYvCKxxNh6cy_QA71mg";
    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static final String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    public static final String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台商城client_id
     */
    public static final String PORTAL_CLIENT_ID = "portal-app";

    /**
     * 后台管理接口路径匹配
     */
    public static final String ADMIN_URL_PATTERN = "/service-admin/**";

    /**
     * Redis缓存权限规则key
     */
    public static final String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    public static final String USER_TOKEN_HEADER = "user";
}
