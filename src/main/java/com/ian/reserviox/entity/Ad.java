package com.ian.reserviox.entity;

import com.ian.reserviox.dto.AdDto;
import jakarta.persistence.*;
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
    private String serviceName;
    private String description;
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
        adDto.setId(this.id);
        adDto.setServiceName(this.serviceName);
        adDto.setDescription(this.description);
        adDto.setPrice(this.price);
        adDto.setReturnedImg(this.image);
        adDto.setUserId(this.user.getId());
        adDto.setCompanyName(this.user.getFirstname());
        return adDto;
    }

}
