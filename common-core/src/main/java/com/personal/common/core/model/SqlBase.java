package com.personal.common.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public class SqlBase {

    @Id
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    @Column(name = "id")
    private String id;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    @Column(name = "status")
    private Integer status;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    @Column(name = "createdAt")
    private Date createdAt;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    @Column(name = "updatedAt")
    private Date updatedAt;
}
