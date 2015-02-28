package me.tkb7.friends;

import java.util.UUID;

public class Friendship {
    public final UUID user1;
    public final UUID user2;
    
    public Friendship (UUID user1, UUID user2) {
        if (user1.compareTo(user2) > 0) {
            this.user1 = user1;
            this.user2 = user2;
        }
        else {
            this.user1 = user2;
            this.user2 = user1;
        }
    }
    
    @Override
    public int hashCode() {
        return user1.hashCode() + user2.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Friendship) {
            return (user1.equals(user2));
        }
        return false;
    }
}
