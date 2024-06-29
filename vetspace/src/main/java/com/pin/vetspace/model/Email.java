package com.pin.vetspace.model;

import lombok.Data;

@Data
public class Email {
    private String to;
    private String subject;
    private String body;

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

	public Email() {
		
	}

}
