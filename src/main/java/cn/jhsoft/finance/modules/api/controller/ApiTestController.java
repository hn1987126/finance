package cn.jhsoft.finance.modules.api.controller;


import cn.jhsoft.finance.modules.api.entity.TokenEntity;
import cn.jhsoft.finance.modules.api.entity.UserEntity;
import cn.jhsoft.finance.common.utils.R;
import cn.jhsoft.finance.modules.api.annotation.AuthIgnore;
import cn.jhsoft.finance.modules.api.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

/**
 * API测试接口
 *
 * @author jhsoft
 * @email hn1987@126.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/api")
public class ApiTestController {

    /**
     * 获取用户信息
     */
    @GetMapping("userInfo")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("notToken")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }

    /**
     * 接收JSON数据
     */
    @PostMapping("jsonData")
    public R jsonData(@LoginUser UserEntity user, @RequestBody TokenEntity token){
        return R.ok().put("user", user).put("token", token);
    }
}
