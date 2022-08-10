package com.ps17931.service;


import javax.mail.MessagingException;

import com.ps17931.domain.MailInfo;


public interface MailerService {
	
	void send(MailInfo mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	void queue(MailInfo mail) throws MessagingException;
	void queue(String to, String subject, String body);
}
