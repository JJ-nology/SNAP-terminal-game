package org.example;

public class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        if(name.length() <= 1){
            return name.toUpperCase();
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }


}
