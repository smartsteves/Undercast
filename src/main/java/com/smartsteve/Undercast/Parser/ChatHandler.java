package com.smartsteve.Undercast.Parser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.Parser.ChatParser.English;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by JUN on 2015-11-22.
 */
public class ChatHandler {
    ServerData server;
    ChatParserBase parser;

    public ChatHandler(ServerData server) {
        this.server = server;
        parser = new English(server);
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        String player = Minecraft.getMinecraft().thePlayer.getDisplayNameString();
        String message = e.message.getFormattedText();
        String unformedMessage = e.message.getUnformattedText();
        //filter useless chat
        if (unformedMessage.equals("Unknown command. Type \"/help\" for help.") || unformedMessage.startsWith("<") || unformedMessage.startsWith("[") || unformedMessage.startsWith("(From ") || unformedMessage.startsWith("(To ")) {
            return;
        }
        // Filter Server Change Using Portal
        if (unformedMessage.equals("                         ") && !server.isLobby() && !server.getServer().equals("")) {
            update();
            return;
        }
        parser.onChat(message, unformedMessage, player);
    }

    public void update() {
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/myteam");
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/mapnext");
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/map");
    }
}
