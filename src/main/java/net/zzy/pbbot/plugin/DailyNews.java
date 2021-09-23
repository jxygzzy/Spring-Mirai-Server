package net.zzy.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhuZhaoYang
 * @date 2021/9/23 23:44
 */
public class DailyNews extends BotPlugin {

    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {

        return MESSAGE_IGNORE;
    }
}
