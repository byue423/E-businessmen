package com.byue.okgo.tools;

/**
 * @author:wjj
 * @date: 2017/6/27 10:03.
 * @description:
 */

public class WebConfig {

    //基础URL
//	public static final String BASE_URL = "http://192.168.9.51:8183/";
    public static final String VERIFY_URL = "http://123.57.136.110:8181/"; //外网认证服务器
    //	public static final String BASE_URL = "http://192.168.0.188:8081/";
    public static final String BASE_URL = "http://123.57.136.110:8180/";//公网地址
    //随机获取高清图片
    public static final String IMAGE_URL = "https://unsplash.it/600/320/?random";

    /** 登录*/
    public static final String USERLOGIN = BASE_URL + "c-mis/api/user/userLogin";

    /**
     * 处理响应结果的响应码
     */
    public static final String POST_ERROR = "服务器响应错误";
    public static final String POST_NULL_TYPE = "请添加接口调用方法类型！";
    public static final String POST_JSON_ERROR = "接口参数为空,请检查接口方法";
    public static final String NETWORK_ERROR = "网络连接错误";
    public static final String NO_MORE_DATA = "没有更多数据了";
    public static final String NO_USER = "请输入账户名";
    public static final String NO_PWD = "请输入密码";
    public static final String PWD_ERROR = "用户名密码错误";

    /**
     * 调用接口的方法类型
     */
    public static final String GET = "get";
    public static final String POST = "post";
    public static final String JSON = "json";


    /** 调用接口成功*/
    public static final int SUCESS_MSG = 0x01;
    /** 调用接口失败*/
    public static final int FAIL_MSG = 0x02;
    /** 调用接口是出现错误 */
    public static final int ERROR_MSG = 0x03;
    /** 登录 */
    public static final int GET_LOGIN = 0x04;
}
