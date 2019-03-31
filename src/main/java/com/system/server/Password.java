package com.system.server;

import com.test.changepassword.Validations;

public class Password {

    /******
     *
     * main java class to run the test.
     * This method sets the mock value to the System Password
     * Also contains instance of changePassword to update with
     * the new password.
     *
     * @param args
     */

    public static void main(String args[]) {

        SystemPassword.setSysPassword("aAbBcCdD1234567890!@#$");
        System.out.println("Password present in the System>>> " + SystemPassword.getSysPassword());

        Password password = new Password();

        password.ChangePassword("aAbBcCdD1234567890!@#$", "e@j$8fr56*deK13#j"); //Positve Test case

        password.ChangePassword("jxfvkljflkGJGGSH21t623t$","ytuthgm657578!@#$475");
        //Negative TCs to check Incorrect password with the system

        password.ChangePassword("aAbBcCdD1234567890!@#$","fgfhdhj123*!A");
        //Negative TCs to check Atleast 18 Characters

        password.ChangePassword("aAbBcCdD1234567890!@#$","AhgAtyAweA11111A!@9");
        //Negative TCs to check No duplicate character more than 4

        password.ChangePassword("aAbBcCdD1234567890!@#$","jfjfUYT1234!@#$&*");
        //Negative TCs to check No more than 4 special Character

        password.ChangePassword("aAbBcCdD1234567890!@#$","789654123asdA!@#*3456ty84");
        //Negative TCs to check 50% of password should not be a number

        password.ChangePassword("aAbBcCdD1234567890!@#$","aAbBcCdD1234567890!@#9");
        //Negative TCs to check password is not similar to the old password >80%



    }

    /****
     *
     * Change password function verifies the password with the
     * System Password and verifies if both are equal.
     * Upon successful verification it checks the new Password
     * as per the requirement and return boolean to ChangePassword
     * whether to update the new Password or not.
     *
     * @param oldPassword
     * @param newPassword
     */

    public void ChangePassword(String oldPassword, String newPassword) {

        Validations validations = new Validations();

        boolean systemCheck = validations.verifyPassword(oldPassword);
        // Verify Old password with the System Password

        if (systemCheck) {

            System.out.println("Password Verifies with the System");

            boolean verifyPassReq = validations.verifyPasswordRequirement(newPassword);
            //Verify the Password Requirements
            if (verifyPassReq) {
                System.out.println("New Password is according to the System password requirements");

                boolean matchPass = validations.OldNewMatch(oldPassword, newPassword);
                //Change Password in the System
                if (matchPass) {
                    System.out.println("Password is not similar to old password >80 percent Match");

                    SystemPassword.setSysPassword(newPassword);
                    System.out.println("New Password Updated Successfully "+ SystemPassword.getSysPassword());
                } else{
                    System.out.println("Password is similar to Old password and matches equal to or greater than 80 percent");
                    System.out.println("System refuses to update the New Password");
                }


            } else{

                System.out.println("New Password is not according to System Password Requirements");
                System.out.println("System refuses to update the New Password");
            }

        } else{
            System.out.println("Old Password does not match with the System Password. Please input Correct Password");
            System.out.println("System refuses to update the New Password");
        }





    }
}
