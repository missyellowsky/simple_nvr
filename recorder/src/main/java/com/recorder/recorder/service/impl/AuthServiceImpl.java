package com.recorder.recorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.recorder.recorder.beans.Result;
import com.recorder.recorder.beans.constant.Constant;
import com.recorder.recorder.service.AuthService;
import com.recorder.recorder.util.RSAEncrypt;
import com.recorder.recorder.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${rsa.privateKey}")
    private String privateKey;

    @Autowired
    private RedisUtils redisUtils;

    public static void main(String[] args) throws Exception {
        /*//生成公钥和私钥
        genKeyPair();
        //加密字符串
        String message = "df723820";
        System.out.println("随机生成的公钥为:" + keyMap.get(0));
        System.out.println("随机生成的私钥为:" + keyMap.get(1));
        String messageEn = encrypt(message,keyMap.get(0));
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn,keyMap.get(1));
        System.out.println("还原后的字符串为:" + messageDe);*/
        String messageEn = RSAEncrypt.encrypt("chinasoft-1610349284","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChDmbpLaGNYomR1rMdlyLDpzTSJkqi7nAgXUHdnuQ9Opw625FxoPDnwjEDmksa4z1qSnjhj9aE8zmADwffTctk6Xtvd8mflMQBFJ0szonQPhNxg/JgR9I4d65+wSU1NGPjQbsleKdu45gDl5/1Bxpic63u+nEdFHPbP5HK2GItdQIDAQAB");
        messageEn = java.net.URLEncoder.encode(messageEn,"UTF-8");
        System.out.println(messageEn);
        String sss = RSAEncrypt.decrypt(java.net.URLDecoder.decode(messageEn,"UTF-8"), "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEOZuktoY1iiZHWsx2XIsOnNNImSqLucCBdQd2e5D06nDrbkXGg8OfCMQOaSxrjPWpKeOGP1oTzOYAPB99Ny2Tpe293yZ+UxAEUnSzOidA+E3GD8mBH0jh3rn7BJTU0Y+NBuyV4p27jmAOXn/UHGmJzre76cR0Uc9s/kcrYYi11AgMBAAECgYAOsNueRXVJCkX2MZlhB5o6MpRU3v1L42gC677IUmgLVb9OdxzLOpz+5u3FoFtddoDxox1npdExi2YhUj2rltMImat9F1KgzJN1IrZXF/srz7SFEquG7x8GmmXtiV/84AtHV+J1GYctqcR08j/r+A8lyC9GZvgmMifvQQ1wFinPgQJBAPbGRCSz+26dJtsjwuoaFClHjL11gKce6paKU5W8FIUQA8167WK6arfsNCPxr/hgPzYW7mNsnDSCGts2bDVb+7ECQQCnE8bpPIUX1nEgvqAB+TUmKy+Uh5ivF0Q52Wa5bzKrYZpJb4o426odXOYIU9EXgD8wso49fhKiL98C8jtIEjMFAkBIWkM6e6a5fMwarjaEytakb7z9A4mP9kmTj8UH/tgJV3O1+FfHfZ6yhEeZTlBow1dlNX35mquF3augICnDz09RAkBRrnGIhXXr+YLFJ2zCijtZtbNFQiV7NDy+q3J1Jc/3yXfMfyNsOLgqMsijOZb3jlNWU3UGyyQA9rZoklQ5H+yhAkEAoRfWkkTziOZUw1dLbKib828nS0Cgxz7MOXBClof2w5hDyIFZWm36E0vO0cVA+G8xB4ur7FfsKgB49xCyb9Ag1A==");
        System.out.println(sss);

        System.out.println();


        System.out.println(timeToStamp("1610441385000"));
    }

    @Override
    public Boolean checkByPrivateKey(String pass) {
        try {
            //pass = java.net.URLDecoder.decode(java.net.URLDecoder.decode(pass,"UTF-8"),"UTF-8");
            System.out.println(pass);
            pass = java.net.URLDecoder.decode(pass,"UTF-8");
            String passStr = RSAEncrypt.decrypt(pass, privateKey);
            System.out.println(passStr);
            String[] array = passStr.split("-");
            Object obj = redisUtils.get(Constant.PASS_MEMBER);
            List<String> menbers = JSONArray.parseArray(JSON.toJSONString(obj), String.class);
            if (!menbers.contains(array[0])) {
                System.out.println(menbers.toString());
                return false;
            }
            Long paramDate = Long.parseLong(array[1]);
            Long thisDate = System.currentTimeMillis();
            if (thisDate - paramDate > 60000 || paramDate - thisDate > 60000) {
                System.out.println(thisDate+"-"+paramDate);
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Result addAuthMember(String member) {
        Object obj = redisUtils.get(Constant.PASS_MEMBER);
        List<String> members = JSONArray.parseArray(JSON.toJSONString(obj), String.class);
        if (members == null) members = new ArrayList<>();
        if(members.contains(member)){
            return Result.fail("this member has been added!");
        }
        members.add(member);
        redisUtils.set(Constant.PASS_MEMBER, members);
        return Result.success();
    }

    @Override
    public Result removeAuthMenber(String member) {
        Object obj = redisUtils.get(Constant.PASS_MEMBER);
        List<String> members = JSONArray.parseArray(JSON.toJSONString(obj), String.class);
        if (members == null) members = new ArrayList<>();
        if(members.contains(member)){
            members.remove(member);
        }else{
            return Result.fail("this member is not in storage!");
        }
        redisUtils.set(Constant.PASS_MEMBER, members);
        return Result.success();
    }

    /* //日期转换为时间戳 */
    private static long timeToStamp(String timers) throws ParseException {
        Date d = new Date();
        long timeStemp = 0;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d = sf.parse(timers);// 日期转换为时间戳
        timeStemp = d.getTime();
        return timeStemp;
    }

}
