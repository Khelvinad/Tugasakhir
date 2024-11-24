package utils;

import model.User;

public class ListUser {
    public User[] fill = new User[]{
            new User("1", "1", 0)
    };

    public void add(User newUser) {
        User[] newUsers = new User[fill.length + 1];
        for (int i = 0; i < fill.length; i++) {
            newUsers[i] = fill[i];
        }
        newUsers[newUsers.length - 1] = newUser;
        fill = newUsers;
    }
}
