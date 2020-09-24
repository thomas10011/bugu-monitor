package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkUserGrabTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/pk/pms-pk-user-grab-ticket")
public class PmsPkUserGrabTicketController {

    @Autowired
    IPmsPkUserGrabTicketService pkUserGrabTicketService;

    @PostMapping("/grab-ticket")
    public Long userGrabTicket(@RequestParam Long userId, @RequestParam Long planId){
        return pkUserGrabTicketService.userGrabTicket(userId,planId);
    }

}
