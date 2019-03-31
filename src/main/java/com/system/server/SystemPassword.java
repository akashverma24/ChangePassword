package com.system.server;


/*****
 * System Password is a class which returns
 * the System Password Value
 * and has capabilities to update the password in the system with new Password.
 *
 */

public class SystemPassword {

    public static String sysPassword;

    public static String getSysPassword(){
        return sysPassword;
    }

    public static void setSysPassword(String password){
        SystemPassword.sysPassword = password;
    }
}
