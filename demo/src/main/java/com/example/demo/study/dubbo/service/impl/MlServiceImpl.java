package com.example.demo.study.dubbo.service.impl;

import com.example.demo.study.dubbo.service.MlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 实现类
 * @ClassName: MlServiceImpl
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-22 10:08
 */
@Slf4j
public class MlServiceImpl implements MlService {

    private final static String say = "傻瓜";

    @Override
    public String Hello(String say) {
        log.info("method is Hello");
        return say + (StringUtils.isEmpty(say) ? "" : say);
    }
}
