package leet.code.solutions.optimizations;

//import org.openjdk.jol.info.GraphLayout;

/*
last optimization - using short mask for booleans
booleans stored as 1(true) and false(0) in byte array within short data type [10101100]
 */
public class BitMap {

    static final short IS_MALE = 0, //these are steps to shift << inside short byte array [1010110] etc ...
        IS_EXISTENT_USER = 1,
        IS_KNOWN_CUSTOMER = 2,
        IS_BANNED_USER = 3,
        IS_PREMIUM_USER = 4,
        IS_CACHED_USER = 5,
        IS_FOREIGN_CLIENT = 6,
        IS_EXTERNAL_USER = 7,
        IS_MASTER_CARD = 8;

    public static void main(String[] args) {
      // converting all booleans  to short using mask so short is [010110] of 1s and0s representing boolean true/false
      short resultValue = convertToShort(true, false,true,
               false,false,true,true,true,false);

       boolean isMaleValue = convertToBoolean(resultValue, IS_MALE);//first in short byte array - position 0
       boolean isBannedUserValue = convertToBoolean(resultValue, IS_BANNED_USER);//third in short byte array - position 3
       boolean isMasterCard = convertToBoolean(resultValue, IS_MASTER_CARD);//last in short byte array - position 8

       System.out.println(resultValue);
       System.out.println(Integer.toBinaryString(resultValue));
       System.out.println("is male? " + isMaleValue);
       System.out.println("is banned? "+isBannedUserValue);
        System.out.println("is mastercard? "+isMaleValue);


       // User user = new User(System.currentTimeMillis(), (short)13133, (short)10, (short)2331, (short) 229);

        //System.out.println(GraphLayout.parseInstance(user).totalSize());

    }

    public static short convertToShort(boolean isMale, boolean isExistentUser, boolean isKnownCustomer, boolean isBannedUser,
                                       boolean isPremiumUser, boolean isCachedUser, boolean isForeignClient, boolean isExternalUser,
                                       boolean isMasterCard){
        short isMaleShort = (short) (isMale ? 1 : 0);
        short isExistentUserShort = (short) (isExistentUser ? 1 : 0);
        short isKnownCustomerShort = (short) (isKnownCustomer ? 1 : 0);
        short isBannedUserShort = (short) (isBannedUser ? 1 : 0);
        short isPremiumUserShort = (short) (isPremiumUser ? 1 : 0);
        short isCachedUserShort = (short) (isCachedUser ? 1 : 0);
        short isForeignClientShort = (short) (isForeignClient ? 1 : 0);
        short isExternalUserShort = (short) (isExternalUser ? 1 : 0);
        short isMasterCardShort = (short) (isMasterCard ? 1 : 0);

        /*
        The transformation from boolean to short consists of the 3 next steps:

        1.Convert boolean to 1 (true) and 0 (false).
        2.Move that value to the left by N steps using the left shift operator (<<).
        3.Merge all values by using OR (|) operator.
         */
        //<< left shift
        return (short) (isMaleShort << IS_MALE | isExistentUserShort << IS_EXISTENT_USER | isKnownCustomerShort << IS_KNOWN_CUSTOMER
            |  isBannedUserShort << IS_BANNED_USER | isPremiumUserShort << IS_PREMIUM_USER | isCachedUserShort << IS_CACHED_USER
            | isForeignClientShort << IS_FOREIGN_CLIENT | isExternalUserShort << IS_EXTERNAL_USER | isMasterCardShort << IS_MASTER_CARD);
    }


    /*
    In order to identify what flag has what value, we need to make a backward transformation:

        1.Move results value to right >> by N steps (depending on flag order).
        2.Make a comparison in order to "cut" the right number.(&1)
        3.Compare this number with 1, and if it's 1, => flags value is true
     */
    public static boolean convertToBoolean(short value, short flagPosition){
        return (value >> flagPosition & 1) == 1;
    }





    static class User {
        long dateOfBirth;
        short fixedSalary;
        short bonusPercent;
        short cardNumber;
        short allFlagsValues;

        public User(long dateOfBirth, short fixedSalary, short bonusPercent, short cardNumber, short allFlagsValues) {
            this.dateOfBirth = dateOfBirth;
            this.fixedSalary = fixedSalary;
            this.bonusPercent = bonusPercent;
            this.cardNumber = cardNumber;
            this.allFlagsValues = allFlagsValues;
        }


    }



    /* Third optimization using narrow types: 40 bytes
    static class User {
        long dateOfBirth;
        boolean isMale, isExistentUser, isKnownCustomer,
                isBannedUser, isPremiumUser,
                isCachedUser, isForeignUser;
        short fixedSalary;
        short bonusPercent;
        boolean isExternalUser;
        boolean isMaterCard;
        short cardNumber;
        public User(long dateOfBirth, boolean isMale, boolean isExistentUser, boolean isKnownCustomer, boolean isBannedUser, boolean isPremiumUser, boolean isCachedUser, boolean isForeignUser, short fixedSalary, short bonusPercent, boolean isExternalUser, boolean isMaterCard, short cardNumber) {
            this.dateOfBirth = dateOfBirth;
            this.isMale = isMale;
            this.isExistentUser = isExistentUser;
            this.isKnownCustomer = isKnownCustomer;
            this.isBannedUser = isBannedUser;
            this.isPremiumUser = isPremiumUser;
            this.isCachedUser = isCachedUser;
            this.isForeignUser = isForeignUser;
            this.fixedSalary = fixedSalary;
            this.bonusPercent = bonusPercent;
            this.isExternalUser = isExternalUser;
            this.isMaterCard = isMaterCard;
            this.cardNumber = cardNumber;
        }
    }
     */

/* Second optimization - moving all fields in one single class total size - 64 shorts
 public static void main(String[] args) {
        User user = new User(new Date(System.currentTimeMillis()), false, false, false,
                false, false, false, false, 1231, 12, false,
                true, 3333);
        System.out.println(GraphLayout.parseInstance(user).totalSize());
    }
    static class User {
        java.sql.Date dateOfBirth;
        boolean isMale, isExistentUser, isKnownCustomer,
                isBannedUser, isPremiumUser,
                isCachedUser, isForeignUser;
        int fixedSalary;
        int bonusPercent;
        boolean isExternalUser;
        boolean isMaterCard;
        int cardNumber;
        public User(Date dateOfBirth, boolean isMale, boolean isExistentUser, boolean isKnownCustomer, boolean isBannedUser, boolean isPremiumUser, boolean isCachedUser, boolean isForeignUser, int fixedSalary, int bonusPercent, boolean isExternalUser, boolean isMaterCard, int cardNumber) {
            this.dateOfBirth = dateOfBirth;
            this.isMale = isMale;
            this.isExistentUser = isExistentUser;
            this.isKnownCustomer = isKnownCustomer;
            this.isBannedUser = isBannedUser;
            this.isPremiumUser = isPremiumUser;
            this.isCachedUser = isCachedUser;
            this.isForeignUser = isForeignUser;
            this.fixedSalary = fixedSalary;
            this.bonusPercent = bonusPercent;
            this.isExternalUser = isExternalUser;
            this.isMaterCard = isMaterCard;
            this.cardNumber = cardNumber;
        }
    }
* */

/*
 first opimization - from wrappers to primitives total size 112
 public static void main(String[] args) {
        UserPaymentDetails userPaymentDetails = new UserPaymentDetails(true, 3333);
        UserSalary userSalary = new UserSalary(1231, 12, false, userPaymentDetails);
        User user = new User(new Date(System.currentTimeMillis()), false, false, false,
                false, false, false, false, userSalary);
        System.out.println(GraphLayout.parseInstance(userPaymentDetails).totalSize());
        System.out.println(GraphLayout.parseInstance(userSalary).totalSize());
        System.out.println(GraphLayout.parseInstance(user).totalSize());
}
    static class User {
        java.sql.Date dateOfBirth;
        boolean isMale, isExistentUser, isKnownCustomer,
                isBannedUser, isPremiumUser,
                isCachedUser, isForeignUser;
        UserSalary salary;
        public User(Date dateOfBirth, boolean isMale, boolean isExistentUser, boolean isKnownCustomer, boolean isBannedUser, boolean isPremiumUser, boolean isCachedUser, boolean isForeignUser, UserSalary salary) {
            this.dateOfBirth = dateOfBirth;
            this.isMale = isMale;
            this.isExistentUser = isExistentUser;
            this.isKnownCustomer = isKnownCustomer;
            this.isBannedUser = isBannedUser;
            this.isPremiumUser = isPremiumUser;
            this.isCachedUser = isCachedUser;
            this.isForeignUser = isForeignUser;
            this.salary = salary;
        }
    }
    static class UserSalary {
        int fixedSalary;
        int bonusPercent;
        boolean isExternalUser;
        UserPaymentDetails userDetails;
        public UserSalary(int fixedSalary, int bonusPercent, boolean isExternalUser, UserPaymentDetails userDetails) {
            this.fixedSalary = fixedSalary;
            this.bonusPercent = bonusPercent;
            this.isExternalUser = isExternalUser;
            this.userDetails = userDetails;
        }
    }
    static class UserPaymentDetails {
        boolean isMaterCard;
        int cardNumber;
        public UserPaymentDetails(boolean isMaterCard, int cardNumber) {
            this.isMaterCard = isMaterCard;
            this.cardNumber = cardNumber;
        }
    }
 */

/* Initial version: total size: 208
package old.keke;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;
import java.sql.Date;
public class BitMap {
    public static void main(String[] args) {
        UserPaymentDetails userPaymentDetails = new UserPaymentDetails(true, 3333);
        UserSalary userSalary = new UserSalary(1231, 12, false, userPaymentDetails);
        User user = new User(new Date(System.currentTimeMillis()), false, false, false,
                false, false, false, false, userSalary);
        System.out.println(GraphLayout.parseInstance(userPaymentDetails).totalSize());
        System.out.println(GraphLayout.parseInstance(userSalary).totalSize());
        System.out.println(GraphLayout.parseInstance(user).totalSize());
}
    static class User {
        java.sql.Date dateOfBirth;
        Boolean isMale, isExistentUser, isKnownCustomer,
                isBannedUser, isPremiumUser, isCachedUser, isForeignUser;
        UserSalary salary;
        public User(Date dateOfBirth, Boolean isMale, Boolean isExistentUser, Boolean isKnownCustomer, Boolean isBannedUser, Boolean isPremiumUser, Boolean isCachedUser, Boolean isForeignUser, UserSalary salary) {
            this.dateOfBirth = dateOfBirth;
            this.isMale = isMale;
            this.isExistentUser = isExistentUser;
            this.isKnownCustomer = isKnownCustomer;
            this.isBannedUser = isBannedUser;
            this.isPremiumUser = isPremiumUser;
            this.isCachedUser = isCachedUser;
            this.isForeignUser = isForeignUser;
            this.salary = salary;
        }
    }
    static class UserSalary {
        Integer fixedSalary;
        Integer bonusPercent;
        Boolean isExternalUser;
        UserPaymentDetails userDetails;
        public UserSalary(Integer fixedSalary, Integer bonusPercent, Boolean isExternalUser, UserPaymentDetails userDetails) {
            this.fixedSalary = fixedSalary;
            this.bonusPercent = bonusPercent;
            this.isExternalUser = isExternalUser;
            this.userDetails = userDetails;
        }
    }
    static class UserPaymentDetails {
        Boolean isMaterCard;
        Integer cardNumber;
        public UserPaymentDetails(Boolean isMaterCard, Integer cardNumber) {
            this.isMaterCard = isMaterCard;
            this.cardNumber = cardNumber;
        }
    }
}
* */
}