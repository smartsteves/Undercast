package com.smartsteve.Undercast.Parser.ChatParser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.Parser.ChatParserBase;
import net.minecraft.client.Minecraft;

/**
 * Created by JUN on 2015-11-22.
 */
public class English extends ChatParserBase {
    public English(ServerData serverData) {
        super(serverData);
    }

    @Override
    public void onChat(String message, String unformedMessage, String player) {
        //if(!server.isOvercast()) return;

        //  Parse Server Join
        if (unformedMessage.contains("Welcome to the Overcast Network Lobby!")) {
            serverJoin();
            return;
        }
        // Parse Map Change
        if (unformedMessage.contains("Created by")) {
            mapChange();
            return;
        }
        // Parser Server Change
        if (unformedMessage.startsWith("Teleporting you to")) {
            serverChange(unformedMessage.replace("Teleporting you to [", "").replace("]", ""));
            return;
        }
        // Check Current Server
        if (unformedMessage.startsWith("You are currently on ")) {
            serverCheck(unformedMessage.replace("You are currently on ", "").replace("[", "").replace("]", ""));
            return;
        }
        // Parse Map Information
        if (unformedMessage.startsWith("---") && !unformedMessage.contains("Match Info") && !unformedMessage.contains("Overcast Network Servers") && !unformedMessage.contains("Current Rotation") && !unformedMessage.contains("Friends")) {
            String s = unformedMessage.replace("-", "").trim();
            char[] tc = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tc.length - 2; i++) {
                sb.append(tc[i]);
                if (tc[i] == ' ' && (tc[i + 2] == '.' || tc[i + 3] == '.')) break;
            }
            setMap(sb.toString());
            return;
        }
        // Parse Next Map
        if (unformedMessage.startsWith("Next map:")) {
            setNextMap(unformedMessage.replace("Next map: ", "").split(" by")[0]);
            return;
        }
        // Parse Team Information
        if (unformedMessage.startsWith("You joined")) {
            setTeam("\u00A7r" + String.valueOf(message.replace("You joined ", "")));
            return;
        }
        // Parse Kill
        if ((unformedMessage.contains("by " + player) || unformedMessage.contains(player + "'s") || unformedMessage.contains("took " + player) || unformedMessage.contains("fury of " + player)) && !unformedMessage.toLowerCase().contains(" destroyed by ")) {
            addKill();
            return;
        }
        // Parse Death
        if (unformedMessage.startsWith(player) && !unformedMessage.toLowerCase().endsWith(" team")) {
            addDeath();
            return;
        }
        // Parse Raindrop
        if (unformedMessage.startsWith("+") && unformedMessage.contains("Raindrops")) {
            addRaindrop(Integer.parseInt(unformedMessage.replace("+", "").split(" ")[0]));
        }
        System.out.println(message);
    }
}
