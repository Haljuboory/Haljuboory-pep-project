package Util;

class UserNotFoundException extends Exception {

    public UserNotFoundException(String userName) {
        super(userName);
    }
}