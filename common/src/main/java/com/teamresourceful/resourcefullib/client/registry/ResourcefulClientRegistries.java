package com.teamresourceful.resourcefullib.client.registry;

import com.teamresourceful.resourcefullib.client.fluid.registry.ResourcefulClientFluidRegistry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistryChild;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistryType;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import org.apache.commons.lang3.NotImplementedException;

public class ResourcefulClientRegistries {

    @SuppressWarnings("unchecked")
    public static <D, T extends ResourcefulRegistry<D>> T create(ResourcefulClientRegistryType<D, T> type, String id) {
        if (type == ResourcefulClientRegistryType.FLUID) {
            return (T) new ResourcefulClientFluidRegistry(id);
        }
        throw new NotImplementedException("Unknown registry type: " + type);
    }
}
