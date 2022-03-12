package com.easy.rapidchat.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class GroupUser {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    private UUID id;
    @OneToMany
    @JoinColumn(name = "senderId")
    @ToString.Exclude
    private List<Message> message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupUser groupUser = (GroupUser) o;
        return id != null && Objects.equals(id, groupUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
