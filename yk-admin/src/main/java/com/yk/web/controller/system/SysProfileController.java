package com.yk.web.controller.system;

import com.yk.common.annotation.ActionLog;
import com.yk.common.dto.Result;
import com.yk.common.enums.AttachmentAttrEnum;
import com.yk.common.enums.LogTypeEnum;
import com.yk.fileupload.attachment.service.ImageAttachmentService;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName SysProfileController
 * @Description 个人信息controller
 * @Author YuKai Fan
 * @Date 2020/6/13 15:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/system/profile")
public class SysProfileController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ImageAttachmentService imageAttachmentService;

    /**
     *  修改用户信息
     * @author YuKai Fan
     * @param sysUser
     * @return com.yk.common.dto.Result
     * @date 2020/6/13 15:19
     */
    @ActionLog(name = "个人信息", logType = LogTypeEnum.UPDATE)
    @PutMapping("updateUserInfo")
    public Result updateUserInfo(@RequestBody SysUser sysUser) {
        SysUser user = ShiroUtils.getCurrentSysUser();
        user.setUserName(sysUser.getUserName());
        user.setEmail(sysUser.getEmail());
        user.setPhone(sysUser.getPhone());
        user.setSex(sysUser.getSex());
        if (sysUserService.updateSysUser(user) > 0) {
            ShiroUtils.setSysUser(sysUserService.getSysUserById(user.getId()));
            return Result.success();
        }
        return Result.error();
    }

    /**
     *  更新用户头像
     * @author YuKai Fan
     * @param request
     * @return com.yk.common.dto.Result
     * @date 2020/6/13 15:22
     */
    @ActionLog(name = "个人信息", logType = LogTypeEnum.UPDATE)
    @PostMapping("/updateAvatar")
    public Result updateAvatar(HttpServletRequest request) throws IOException {
        SysUser sysUser = ShiroUtils.getCurrentSysUser();
        ImageAttachment attachment = imageAttachmentService.uploadFastDFSAttachment(request, sysUser.getId(), AttachmentAttrEnum.USER_AVATAR.getValue());
        sysUser.setAvatar(attachment.getId());

        if (sysUserService.updateSysUser(sysUser) > 0) {
            ShiroUtils.setSysUser(sysUserService.getSysUserById(sysUser.getId()));
            return Result.success();
        }
        return Result.error();
    }
}
