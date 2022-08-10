package com.ps17931.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	
	private String name;
	private String from;
	private String to;
	private String subject;
	private String body;
	private String[] cc;
	private String[] bcc;
	private String[] attachments;
	
	public MailInfo(String to, String subject, String body) {
		this.from = "SpringLaptop <poly@gmail.com>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
