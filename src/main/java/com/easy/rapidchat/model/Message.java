package com.easy.rapidchat.model;

import com.easy.rapidchat.enums.MessageStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
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
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @ColumnDefault("uuid_generate_v4()")
    @Column(insertable = false, updatable = false)
    private UUID id;
    @ManyToOne
    private Room room;
    private boolean isDirectMessage;
    private String content;
    private LocalTime timeOfMsg;
    private MessageStatus status;
    @ManyToOne
    private UserDetail userDetail;

    @OneToMany(mappedBy = "userDetail")
    @ToString.Exclude
    private List<RoomUser> roomUsers;

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
