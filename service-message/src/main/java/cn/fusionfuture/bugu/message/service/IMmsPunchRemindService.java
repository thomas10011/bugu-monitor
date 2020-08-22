package cn.fusionfuture.bugu.message.service;


import cn.fusionfuture.bugu.message.vo.PunchVO;
import cn.fusionfuture.bugu.pojo.entity.MmsPunchRemind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMmsPunchRemindService extends IService<MmsPunchRemind> {
    void addPunchRemind(MmsPunchRemind mmsPunchRemind);
    List<PunchVO> getPunchRemind(Long id);
}
