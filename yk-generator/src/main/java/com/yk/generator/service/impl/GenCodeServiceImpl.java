package com.yk.generator.service.impl;

import com.yk.generator.service.GenCodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-03 15:54
 **/
@Service
@Transactional
public class GenCodeServiceImpl implements GenCodeService {
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        IOUtils.closeQuietly(zip);
        return new byte[0];
    }

    private void generatorCode(String tableName, ZipOutputStream zip) {

    }
}