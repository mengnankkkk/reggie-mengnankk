package com.mengnankk.controller;

import com.mengnankk.comon.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Slf4j


public class SetmealController {
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{},ids");
        return null;
    }
}

