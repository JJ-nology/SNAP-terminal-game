package org.example;

public class Player {
    protected String name;

    public Player(String name) {
        // remove any space characters from a name
        this.name = name.replaceAll("\\s","");
    }

    public String getName() {
        if(name.length() <= 1){
            return name.toUpperCase();
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }


}
