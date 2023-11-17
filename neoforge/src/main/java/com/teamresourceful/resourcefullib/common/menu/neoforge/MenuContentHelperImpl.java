package com.teamresourceful.resourcefullib.common.menu.neoforge;

import com.teamresourceful.resourcefullib.common.menu.ContentMenuProvider;
import com.teamresourceful.resourcefullib.common.menu.MenuContent;
import com.teamresourceful.resourcefullib.common.menu.MenuContentHelper;
import com.teamresourceful.resourcefullib.common.menu.MenuContentSerializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.NetworkHooks;

import java.util.Optional;

public class MenuContentHelperImpl {
    public static <T extends AbstractContainerMenu, C extends MenuContent<C>> MenuType<T> create(MenuContentHelper.MenuFactory<T, C> factory, MenuContentSerializer<C> serializer) {
        return IMenuTypeExtension.create((id, inventory, data) -> {
            if (serializer != null) {
                return factory.create(id, inventory, Optional.ofNullable(serializer.from(data)));
            }
            return factory.create(id, inventory, Optional.empty());
        });
    }

    public static <C extends MenuContent<C>> void open(ServerPlayer player, ContentMenuProvider<C> provider) {
        NetworkHooks.openScreen(player, provider, buf -> {
            C content = provider.createContent();
            if (content != null) {
                content.serializer().to(buf, content);
            }
        });
    }
}
