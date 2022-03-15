package com.easy.rapidchat.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class GroupUser {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    private UUID id;
    @ManyToOne
    @ToString.Exclude
    private Group group;
    @ManyToOne
    @ToString.Exclude
    private UserDetail userDetail;

}
