package com.pin.vetspace.enums;

public enum TipoPet {
    GATO(0),
    CACHORRO(1),
    PASSARO(2),
    REPTIL(3);
    
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
