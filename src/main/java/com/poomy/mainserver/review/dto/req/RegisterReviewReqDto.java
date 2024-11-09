package com.poomy.mainserver.review.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class RegisterReviewReqDto {

    @NotNull
    private Long poomShopId;

    @NotNull
    private Boolean isRecommend;

    @NotBlank
    @Length(min = 20, max = 500, message = "후기는 최소 20자 이상 500자 이하여야 합니다.")
    private String content;

    private List<Long> moodIds;

    private List<MultipartFile> multipartFiles;

}
