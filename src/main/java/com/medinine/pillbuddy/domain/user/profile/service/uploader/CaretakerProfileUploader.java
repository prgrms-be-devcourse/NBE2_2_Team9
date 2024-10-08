package com.medinine.pillbuddy.domain.user.profile.service.uploader;

import com.medinine.pillbuddy.domain.user.caretaker.entity.Caretaker;
import com.medinine.pillbuddy.domain.user.caretaker.repository.CaretakerRepository;
import com.medinine.pillbuddy.domain.user.profile.entity.Image;
import com.medinine.pillbuddy.domain.user.profile.repository.ImageRepository;
import com.medinine.pillbuddy.global.exception.ErrorCode;
import com.medinine.pillbuddy.global.exception.PillBuddyCustomException;
import com.medinine.pillbuddy.global.util.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
@Transactional
public class CaretakerProfileUploader implements ProfileUploader {

    private final CaretakerRepository caretakerRepository;
    private final ImageRepository imageRepository;
    private final UploadUtils uploadUtils;


    @Override
    public void upload(MultipartFile file, Long userId) {
        Caretaker caretaker = caretakerRepository.findById(userId)
                .orElseThrow(() -> new PillBuddyCustomException(ErrorCode.USER_NOT_FOUND));

        Image image = caretaker.getImage();
        String filename = uploadUtils.upload(file);

        if (image != null) {
            // 이미지가 원래 있었다면, 삭제 후 새로운 url 업데이트
            UploadUtils.deleteFile(image.getUrl());
            image.updateUrl(filename);
        } else {
            image = Image.builder()
                    .url(filename)
                    .build();
        }

        try {
            Image save = imageRepository.save(image);
            caretaker.updateImage(save);
        } catch (Exception e) {
            UploadUtils.deleteFile(filename);
        }
    }
}
