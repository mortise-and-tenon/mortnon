package org.mt.mortnon.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.mt.mortnon.dal.sys.entity.SysUser;
import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.service.HelloService;
import org.mt.mortnon.utils.Asserts;
import org.mt.mortnon.utils.ModelConvertor;
import org.mt.mortnon.web.utils.ResultUtil;
import org.mt.mortnon.web.vo.HelloInput;
import org.mt.mortnon.web.vo.HelloOutput;
import org.mt.mortnon.web.vo.MortnonResult;
import org.mt.mortnon.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * hello world
 *
 * @date 2021-04-13 17:24:32
 * @author dongfangzan
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * Hello Mortnon
     *
     * @apiNote 你好 Mortnon
     * @return
     */
    @GetMapping("/hello")
    public MortnonResult<String> helloWorld() {
        return ResultUtil.success("Hello " + helloService.hello() + "!");
    }

    /**
     * Hello Mortnon with validate
     *
     * @param helloInput
     * @return 返回
     */
    @PostMapping("/hello")
    public MortnonResult<HelloOutput> hello(@Validated @RequestBody HelloInput helloInput) {
        HelloOutput helloOutput = new HelloOutput();
        helloOutput.setName(helloInput.getName());
        SysUser baseEntity = new SysUser().setUserName(helloInput.getName()).setSex("1").setUserName("123");
        helloService.saveUser(baseEntity);
        helloOutput.setId(baseEntity.getId());
        return ResultUtil.success(helloOutput);
    }

    /**
     * Hello Mortnon with data list by name convert.
     * @return
     */
    @GetMapping("/hello/all")
    public MortnonResult<List<UserVo>> helloUsers(){
        List<SysUser> users = helloService.getUsers();

        return ResultUtil.success(ModelConvertor.convert(users, UserVo.class));
    }

    /**
     * 分页查询用户信息
     *
     * @return
     */
    @GetMapping("/hello/page")
    public MortnonResult<IPage<SysUser>> pageUsers() {
        IPage<SysUser> byPage = helloService.getByPage();
        return ResultUtil.success(byPage);
    }

    /**
     * 调用异常接口
     *
     * @return
     */
    @GetMapping("/exception")
    public MortnonResult<Void> exception() {

        log.info("调用异常测试日志");
        log.error("错误：调用异常测试日志");

        Asserts.assertTrue(false, ErrorCodeEnum.SYSTEM_ERROR, "故意做的系统异常");

        return ResultUtil.success();
    }
}
