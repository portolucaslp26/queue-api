package com.weon.devel.producer.voice.validator;


public class VoiceValidator {

    public void validatePhone(String sourcePhone) {
        if (!sourcePhone.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
}
