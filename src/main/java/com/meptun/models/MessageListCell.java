/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import static javafx.scene.text.FontWeight.BOLD;
import javafx.scene.text.Text;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class MessageListCell extends ListCell<Message>{
    private VBox content;
    private Label sender;
    private Label sendDate;
    private Text recipient;
    private Label subject;
    
    public MessageListCell() {
            super();
            sender = new Label();
            sender.setStyle("-fx-font-size: 17px");
            sender.setStyle("-fx-font-weight: bold");
            subject = new Label();
            sendDate = new Label(LocalDate.now().getDayOfWeek().toString().toLowerCase()+", "+LocalTime.now().toString().substring(0, 5));
            sendDate.setStyle("-fx-font-size: 10px;");
            sendDate.setAlignment(Pos.BOTTOM_LEFT);
            content = new VBox(sender, subject,sendDate);
            content.setSpacing(5);
            
        }
    
        @Override
        protected void updateItem(Message message, boolean empty) {
            super.updateItem(message, empty);
            if (message != null && !empty) { // <== test for null message and empty parameter
                sender.setText(message.getMessageSender());
                subject.setText(message.getMessageSubject());
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }

