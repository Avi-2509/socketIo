package com.personal.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileChat extends SqlBase{

    @Column(name = "userIdOne")
    private String userIdOne;

    @Column(name = "profileIdOne")
    private String profileIdOne;

    @Column(name = "userIdTwo")
    private String userIdTwo;

    @Column(name = "profileIdTwo")
    private String profileIdTwo;

    @Column(name = "schoolBranchId")
    private String schoolBranchId;

}
