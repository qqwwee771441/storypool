package com.wudc.storypool.common.entity;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @Column(updatable = false, nullable = false, length = 26)
    private String id;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @PrePersist
    public void assignUlid() {
        if (this.id == null) {
            this.id = new ULID().nextULID();
        }
    }
}