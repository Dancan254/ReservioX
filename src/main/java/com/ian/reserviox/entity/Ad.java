package com.ian.reserviox.entity;

import com.ian.reserviox.dto.AdDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ads")
@Data
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String serviceName;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @Lob
    @Column(name = "longlob")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    //convert to dto
    public AdDto toDto() {
        AdDto adDto = new AdDto();
        adDto.setServiceName(serviceName);
        adDto.setDescription(description);
        adDto.setPrice(price);
        adDto.setReturnedImg(image);
        adDto.setUserId(user.getId());
        adDto.setCompanyName(user.getFirstname());
        return adDto;
    }

}
