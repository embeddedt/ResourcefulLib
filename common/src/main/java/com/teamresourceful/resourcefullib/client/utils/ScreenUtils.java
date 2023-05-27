package com.teamresourceful.resourcefullib.client.utils;

import com.teamresourceful.resourcefullib.common.exceptions.UtilityClassException;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public final class ScreenUtils {

    private ScreenUtils() throws UtilityClassException {
        throw new UtilityClassException();
    }

    public static void setTooltip(ItemStack stack) {
        setTooltip(stack.getTooltipLines(
            Minecraft.getInstance().player,
            Minecraft.getInstance().options.advancedItemTooltips ? TooltipFlag.ADVANCED : TooltipFlag.NORMAL
        ));
    }

    public static void setTooltip(Component component) {
        setTooltip(List.of(component));
    }

    public static void setTooltip(List<Component> component) {
        if (Minecraft.getInstance().screen != null) {
            List<FormattedCharSequence> formattedComponents = new ArrayList<>(component.size());
            for (Component comp : component) {
                formattedComponents.add(comp.getVisualOrderText());
            }
            Minecraft.getInstance().screen.setTooltipForNextRenderPass(formattedComponents);
        }
    }

    public static void sendCommand(String command) {
        if (Minecraft.getInstance().getConnection() != null) {
            Minecraft.getInstance().getConnection().sendUnsignedCommand(command);
        }
    }

    public static void sendClick(int containerId, int buttonId) {
        if (Minecraft.getInstance().gameMode != null) {
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(containerId, buttonId);
        }
    }
}