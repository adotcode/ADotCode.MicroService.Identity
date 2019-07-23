package com.adotcode.oauth2server.controller;

import com.adotcode.oauth2server.domain.exception.IllegalParameterException;
import com.adotcode.oauth2server.domain.exception.NullOrEmptyException;
import com.adotcode.oauth2server.domain.exception.UnauthorizedException;
import com.adotcode.oauth2server.domain.wrapper.ResultWrapper;
import com.adotcode.oauth2server.mapper.log.AuditLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 审计日志控制器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@RequestMapping("api/v1/auditlogs")
@RestController
@Slf4j
@Validated
public class AuditLogController {
    private final AuditLogMapper auditLogMapper;

    public AuditLogController(AuditLogMapper auditLogMapper) {
        this.auditLogMapper = auditLogMapper;
    }

    /**
     * 通过Id查找浏览器信息
     *
     * @param id 日志Id
     * @return 浏览器信息 @Min(value = 2, message = "id最小值为2。")
     */
    @GetMapping("{id}/browserInfo")
    public ResultWrapper<String> findBrowserInfoById(
            @Min(value = -2, message = "id最小值为-2。") @PathVariable("id") long id) {
        if (id == -1) {
            throw new NullOrEmptyException();
        }
        if (id == 0) {
            throw new IllegalParameterException("id");
        }
        if (id == 1) {
            throw new UnauthorizedException();
        }
        String result = auditLogMapper.findBrowserInfoById(id);
        log.error("This is an error message.[{}]", new Date());
        return ResultWrapper.success(result);
    }
}
