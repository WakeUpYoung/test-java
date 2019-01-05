package com.wy.test.proxy;

public class UserManagerImpl implements UserManager {
    @Override
    public void addUser(Integer id) {
        System.out.println("addUser:"+id);
    }

    @Override
    public void deleteUser(Integer id) {
        System.out.println("deleteUser:"+id);
    }
}
