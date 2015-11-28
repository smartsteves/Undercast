package com.smartsteve.Undercast.GUI;

import com.smartsteve.Undercast.DataContainer.OptionData;
import com.smartsteve.Undercast.DataContainer.ServerData;
import com.smartsteve.Undercast.Util;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * This Class Indicate Ratio on Screen
 */
public class DisplayRatio {
    private final int x = 2;
    OptionData option;
    ServerData server;
    private int y = 2;
    public DisplayRatio(OptionData optionData, ServerData server) {
        option = optionData;
        this.server = server;
    }

    /*
     * FPS      *
     * Server   *
     * Team     *
     * Playing Time
     * Match Time
     * CurrentMap  *
     * NextMap     *
     * K/D         *
     * K/K         Removed
     * Kills       *
     * Deaths      *
     * Raindrops
     * Current KillStreak    *
     */
    @SubscribeEvent
    public void displayRatio(RenderGameOverlayEvent.Post e) {
        Minecraft mc = Minecraft.getMinecraft();
        if (server == null) return;
        if (option.enable && mc.inGameHasFocus && !mc.gameSettings.showDebugInfo && e.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            y = 2;

            if (option.fps) {
                drawString("FPS: " + Minecraft.getDebugFPS());
            }
            if (option.server) {
                drawString("Server: \u00A76" + server.getServer());
            }
            if (option.team && !server.isLobby()) {
                drawString("Team: " + server.getTeam());
            }
            if (option.playingTime) {
                drawString("Playing Time: \u00A7b" + Util.getTime(server.getPlaytime()));
            }
            if (option.matchTime && !server.isLobby()) {

            }
            if (option.currentMap && !server.isLobby()) {
                drawString("Current Map: \u00A7d" + server.getMap());
            }
            if (option.nextMap && !server.isLobby()) {
                drawString("Next Map: \u00A7d" + server.getNextMap());
            }
            if (option.kill && !server.isLobby()) {
                drawString("Kill: \u00A7a" + server.getCurrentKill() + "/" + server.getTotalKill());
            }
            if (option.death && !server.isLobby()) {
                drawString("Death: \u00A74" + server.getCurrentDeath() + "/" + server.getTotalDeath());
            }
            if (option.kd && !server.isLobby()) {
                drawString("KD: \u00A73" + String.format("%.3f", (float) server.getCurrentKd()) + "/" + String.format("%.3f", (float) server.getTotalKd()));
            }
            if (option.killstreak && !server.isLobby()) {
                drawString("KS: \u00A75" + server.getCurrentKillStreak() + "/" + server.getMaxKillStreak());
            }
            if (option.raindrop && server.isOvercast()) {
                drawString("RD: \u00A7b" + server.getRaindrop());
            }
        }
    }

    private void drawString(String string) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string, x, y, 16777215);
        y += Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
    }
}
