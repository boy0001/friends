package me.tkb7.friends;

public enum Message {
    PREFIX("&8<&dFRIENDS&8>"),
    NO_PERM("&cYOU SHALL NOT PASS");
    
    private String s;

    Message(String s) {
        this.s = s;
    }
    
    String s() {
        return this.s;
    }
    
}
