package com.meetowin.meetowin.common;

import com.meetowin.meetowin.security.exception.BadRequestException;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptIds {


    public static String encryptId(Long id){
        try {
            id -= 8956372;
            id += 356;
            id *= 2589796;
            String stringId = id.toString();
            byte[] serialized = stringId.getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(serialized);
        }catch (Exception ex){
            throw new BadRequestException("Id is Missing or wrong value");
        }
    }

    public static Long decryptId(String stringId){
        try {
            byte[] serialized = stringId.getBytes(StandardCharsets.UTF_8);
            String s = new String(Base64.getDecoder().decode(serialized),StandardCharsets.UTF_8);
            Long id = Long.parseLong(s);
            id /= 2589796;
            id -= 356;
            id +=8956372;
            return id;
        }catch (Exception ex){
            throw new BadRequestException("Id is Missing or wrong value");
        }
    }
}
