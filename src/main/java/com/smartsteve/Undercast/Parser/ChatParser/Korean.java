package com.smartsteve.Undercast.Parser.ChatParser;

import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.Parser.ChatParserBase;

/**
 * Created by JUN on 2015-11-22.
 */
public class Korean extends ChatParserBase {
    public Korean(ServerData serverData) {
        super(serverData);
    }

    @Override
    public void onChat(String message, String unformedMessage, String player) {
        /* Things You Have To Find
         * Server Join
         * Server Change
         * Map Change
         * Map Information
         * Next Map
         * Team
         * Kill
         * Death
         * RainDrop
         */

    }
}
