package com.poomy.mainserver.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3ImageService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public String upload(MultipartFile image){
        if(image.isEmpty() || Objects.isNull(image.getOriginalFilename())){
            throw new CommonException(BError.NOT_EXIST, "Image file");
        }
        return this.uploadImage(image);
    }

    private String uploadImage(MultipartFile image){
        this.validateImageFileExtention(image.getOriginalFilename());
        try{
            return this.uploadImageToS3(image);
        } catch(IOException e){
            throw new CommonException(BError.FAIL, "Image File Upload");
        }
    }

    private void validateImageFileExtention(String filename){
        int lastDotIndex = filename.lastIndexOf(".");
        if(lastDotIndex == -1){
            throw new CommonException(BError.NOT_MATCH, "File format");
        }
        String extention = filename.substring(lastDotIndex+1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png");
        if(!allowedExtentionList.contains(extention)){
            throw new CommonException(BError.NOT_VALID, "File Extention");
        }
    }

    private String uploadImageToS3(MultipartFile image) throws IOException{
        String originalFilename = image.getOriginalFilename();
        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));

        String s3FileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename;

        InputStream is = image.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + extention);
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try{
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e){
            throw new CommonException(BError.FAIL, "Image File Upload to s3");
        } finally {
            byteArrayInputStream.close();
            is.close();
        }

        return amazonS3.getUrl(bucketName, s3FileName).toString();
    }

}
