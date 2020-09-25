package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsPunchRemindController
 * @description 打卡提醒
 * @date 2020/8/22 12:04
 */
@RestController
public class MmsPunchRemindController {
    @Autowired
    IMmsPunchRemindService iMmsPunchRemindService;

    /**
     * 发送打卡提醒
     * @author LiLan
     * @since 2020/8/22 12:10
     * @param mmsPunchRemind 打卡提醒对象
     * @return void
     **/
    @PostMapping(value="/punch-remind")
    @ApiOperation(value = "发送打卡提醒")
    public void addPunch(MmsPunchRemind mmsPunchRemind){
        iMmsPunchRemindService.addPunchRemind(mmsPunchRemind);
    }

    /**
     * 接收打卡提醒
     * @author LiLan
     * @since 2020/8/22 12:11
     * @param pn 当前所在页id
     * @param ps 页面size
     * @param id 接收者的id，即当前用户id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.PunchVO>
     **/
    @GetMapping(value="/punch-remind")
    @ApiOperation(value = "接收打卡提醒")
    public PageInfo<?> getPunch(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps,@RequestParam(name="id") Long id){
        PageInfo<PunchVO> mmsPunchVOList = iMmsPunchRemindService.getPunchRemind(pn, ps, id);
        return mmsPunchVOList;
    }
}
