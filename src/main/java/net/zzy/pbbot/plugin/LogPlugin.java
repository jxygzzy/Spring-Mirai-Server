package net.zzy.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotBase;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static net.zzy.pbbot.finalfield.FinalStr.MESSAGE_TYPE_AT;

/**
 * @author jxygzzy
 */
@Slf4j
@Component
public class LogPlugin extends BotPlugin {
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        log.info("收到私聊消息 QQ：{} 内容：{}", event.getUserId(), event.getRawMessage());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        log.info("收到群消息 群号：{} QQ：{} 内容：{}", event.getGroupId(), event.getUserId(), event.getRawMessage());
        List<OnebotBase.Message> messageChain = event.getMessageList();
        //群内at则视为与bot对话
        if (messageChain.size() > 0) {
            OnebotBase.Message message = messageChain.get(0);
            String selfId = String.valueOf(bot.getSelfId());
            if (MESSAGE_TYPE_AT.equals(message.getType()) && selfId.equals(message.getDataMap().get(MESSAGE_TYPE_AT))) {
                return MESSAGE_IGNORE;
            }
        }
        return MESSAGE_BLOCK;
    }
}
