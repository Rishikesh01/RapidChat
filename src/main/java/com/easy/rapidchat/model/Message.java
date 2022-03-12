package com.easy.rapidchat.model;

import com.easy.rapidchat.enums.MessageStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Rishikesh
 * @project RapidChat
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    private UUID id;
    private UUID senderId;
    @Column(nullable = false)
    private UUID receiverId;
    private String content;
    private Date dateOfMsg;
    private LocalTime timeOfMsg;
    private MessageStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Message message = (Message) o;
        return id != null && Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
