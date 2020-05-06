/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.time.LocalDate;


/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class Message {
    private String messageSender;
    private String messageRecipient;
    private String messageSubject;
    private LocalDate sendDate;
    private String messageBody;

    public Message(String messageSender, String messageRecipient, String messageSubject, LocalDate sendDate, String messageBody) {
        this.messageSender = messageSender;
        this.messageRecipient = messageRecipient;
        this.messageSubject = messageSubject;
        this.sendDate = sendDate;
        this.messageBody = messageBody;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(String messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
      
}
