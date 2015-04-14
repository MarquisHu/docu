package com.docu.user.constants;

public enum UserEnum {
    MANAGER(1),
    TL(2),
    STE(3),
    TE(4),
    ATE(5);
    
    private Integer type=1;
    UserEnum(Integer type) {
        this.type=type;
    }
    public Integer getType() {
        return type;
    }
    public static void main(String[] args) {
       System.out.println( UserEnum.MANAGER);
    }
}
