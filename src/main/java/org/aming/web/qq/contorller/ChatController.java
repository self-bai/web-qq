package org.aming.web.qq.contorller;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.logger.Logger;
import org.aming.web.qq.logger.LoggerManager;
import org.aming.web.qq.response.CommonResponse;
import org.aming.web.qq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daming
 * @version 2017/10/5.
 */
@RestController
@RequestMapping("/webqq")
public class ChatController {

    private static final Logger logger = LoggerManager.getLogger(ChatController.class);

    private MessageService messageService;

    @RequestMapping(path = "/chat", method = RequestMethod.POST)
    public CommonResponse chat(@RequestBody Message message){
        try{
            return CommonResponse.getSuccessCommonResponse(messageService.saveMessage(message));
        } catch (Exception ex){
            logger.error("异常信息 : {} - 异常原因 : {} ", ex.getMessage(), ex.getCause());
            throw CommonResponse.getErrorCommonResponse(ex);
        }
    }

    @Autowired
    public void setMessageService(MessageService  messageService){
        this.messageService = messageService;
    }
}
