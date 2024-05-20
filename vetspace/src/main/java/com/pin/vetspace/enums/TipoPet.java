package com.pin.vetspace.enums;

public enum TipoPet {
    GATO(1),
    CACHORRO(2),
    PASSARO(3),
    REPTIL(4);
    
    private final int value;
    
    TipoPet(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static TipoPet fromValue(int value) {
        for (TipoPet tipo : TipoPet.values()) {
            if (tipo.getValue() == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de pet inválido: " + value);
    }
}
