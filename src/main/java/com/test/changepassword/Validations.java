package com.test.changepassword;

import com.system.server.SystemPassword;
import org.apache.lucene.search.spell.JaroWinklerDistance;

import java.util.HashMap;
import java.util.Map;

public class Validations {

    /*****
     *
     * verifyPassword is a method with returns the value
     * upon comparing if old password matches with the System Password
     *
     * @param password
     * @return
     */

    public boolean verifyPassword(String password) {

        String abc = SystemPassword.getSysPassword();

        if (abc.equals(password)) {

            return true;
        } else {

            return false;
        }

    }

    /*****
     *
     * verifyPasswordRequirement method checks the password
     *  according to the System Requirement and
     * updates the changePassword functions if new Password
     * String is in proper format or not.
     * @param newPassword
     * @return
     */

    public boolean verifyPasswordRequirement(String newPassword) {

        boolean status;

        boolean passwordCountCheck = newPassword.matches("(?=.{18}).*"); //Should returns true
        System.out.println(">>>Passsword String should be atleast 18 characters>>> "+passwordCountCheck);

       /* boolean specialCharCheck = newPassword.matches(".*[!@#$&*]"); //Should returns false
        System.out.println(">>>Special Characters should only be !@#$&* in a password>>> "+specialCharCheck);*/

        boolean charCondition = newPassword.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@!#$*]).*");
        System.out.println(">>>Atleast 1 UpperCase,1 LowerCase, 1 Numeric, 1 Special Character>>> "+ charCondition);

        boolean duplicateChar = duplicateCharCheck(newPassword);
        boolean specCharCountCheck = specialCharCheck(newPassword);
        boolean numericCharCountCheck = numericCharCount(newPassword);

        System.out.println("Duplicate Chacter should not be more than 4 "+ duplicateChar);
        //System.out.println("Special Character should not be more than 4 "+specCharCountCheck);
        System.out.println("50 percent of password should not be number "+ numericCharCountCheck);

        if (passwordCountCheck  && charCondition && duplicateChar  && specCharCountCheck && numericCharCountCheck){
            System.out.println(">>>Password Requirement Fulfilled : VALID PASSWORD>>> ");
            status =true;
        }
        else {
            status = false;
        }
        return status;
    }

    /***
     *
     * returns the value to verifyPasswordRequirement if new passoword
     * contains no more than 4 duplicate Characters
     * @param newPass
     * @return
     */

    public boolean duplicateCharCheck(String newPass){

        boolean status = true;
        Map<Character,Integer> characterIntegerMap = new HashMap<Character, Integer>();

        char[] strArray =newPass.toCharArray();

        for (char c: strArray){
            if(characterIntegerMap.containsKey(c)){

                characterIntegerMap.put(c,characterIntegerMap.get(c)+1);
            }
            else {
                characterIntegerMap.put(c,1);
            }

            for (int count: characterIntegerMap.values()){
                if(count>4){
                    status = false;
                    break;
                }


            }

        }
        return status;
    }

    /*****
     *
     * specialCharCheck function returns the value to verifyPasswordRequirement
     * that new password should not contain special characters more than 4
     *
     * @param newPass
     * @return
     */

    public boolean specialCharCheck(String newPass) {
        boolean status = false;

        int specialCharcount = newPass.length() - newPass.replaceAll("[^a-zA-Z0-9]*", "").length();
        if (specialCharcount > 4) {

            status = false;
            System.out.println(">>>Special characters are more than 4");
        } else {
            System.out.println("Special Characters are not more than 4");
            status = true;
        }
        return status;
    }

    public boolean numericCharCount(String newPass){

        boolean status;
        double halfLength = newPass.length()*0.5;
        int numericCountCheck = newPass.length()-newPass.replaceAll("[0-9]*","").length();

        if(numericCountCheck>halfLength){
            status = false;
        }
        else{
            status  = true;
        }
        return status;
    }

    /*****
     *
     * OldNewMatch function returns value to the verifyPasswordRequirement
     * whether new Password is a similar match with the old Password.
     *  less than 80 percent.
     * @param oldPass
     * @param newPass
     * @return
     */

    public boolean OldNewMatch(String oldPass, String newPass){

        boolean status;
        JaroWinklerDistance distance = new JaroWinklerDistance();
        double dist = distance.getDistance(oldPass, newPass);

        if(dist>= 0.80){
            status = false;
        }
        else{
            status = true;
        }
            return status;
    }

}
