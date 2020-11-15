package com.fh.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;


/**
 * <pre>项目名称：movie-admin
 * 类名称：MD5Util
 * 类描述：MD5加密工具类(包含加盐算法)
 * 创建人：李川 lichuan@163.com
 * 创建时间：2019年10月30日 上午9:56:04
 * 修改人：李川 lichuan@163.com
 * 修改时间：2019年10月30日 上午9:56:04
 * 修改备注：
 * @version </pre>
 */
public class MD5Util {

	/**
	 * 盐：字符串 salt
     * 生成含有随机盐的密码
     * 原始密码：123456
     * 盐值：随机字符串，使密码更安全,skgjklfdsgfdgfdgfd
     * 加盐后密码：123456skgjkl
     */
    public static  String generate(String password) {
        Random r = new Random();
        //线程不安全，效率高，长度可变
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        //随机盐
        String salt = sb.toString();
        //String salt = new java.util.Date().getTime();
        //调用加密算法
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        //把密码和盐分开
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }


    /**
     * 校验密码是否正确
     * @param password 123456
     * @param md5 ba3944d5997e597140313f853c4f7712
     * @return
     */
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * md5加密算法
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //核心代码，加密方法
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] a){
    	String password = "1";
    	String passwordMd5 = generate(password);
    	System.out.println(passwordMd5);
    	//判断密码是否正确
    	System.out.println(verify(password, passwordMd5));


    }

}
