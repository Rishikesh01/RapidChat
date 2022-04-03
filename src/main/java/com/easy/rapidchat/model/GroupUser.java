package com.easy.rapidchat.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @ColumnDefault("uuid_generate_v4()")
    private UUID id;
    @ManyToOne
    @ToString.Exclude
    private Group group;
    @ManyToOne
    @ToString.Exclude
    private UserDetail userDetail;

}
