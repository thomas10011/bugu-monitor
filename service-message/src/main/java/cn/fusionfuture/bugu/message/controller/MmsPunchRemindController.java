package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
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
 * @description TODO
 * @date 2020/8/22 12:04
 */
@RestController
public class MmsPunchRemindController {
    @Autowired
    IMmsPunchRemindService iMmsPunchRemindService;

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 12:10
     * @param mmsPunchRemind
     * @return void
     **/
    @PostMapping(value="/punch-remind")
    public void addPunch(MmsPunchRemind mmsPunchRemind){
        iMmsPunchRemindService.addPunchRemind(mmsPunchRemind);
    }

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 12:11
     * @param id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.PunchVO>
     **/
    @GetMapping(value="/punch-remind")
    public List<PunchVO> getPunch(@RequestParam(name="id") Long id){
        List<PunchVO> mmsPunchVOList = iMmsPunchRemindService.getPunchRemind(id);
        return mmsPunchVOList;
    }
}
