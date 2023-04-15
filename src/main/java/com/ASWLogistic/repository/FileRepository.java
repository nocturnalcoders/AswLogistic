package com.ASWLogistic.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();
}
