package mod.flatcoloredblocks.fabric.registry.util.screen.screens;

import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import mod.flatcoloredblocks.fabric.registry.util.tags.FlatColoredBlocksItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class ColorerBlockMenu extends AbstractContainerMenu {
    private static final int CONTAINER_SIZE = 2;
    private final Container resultContainer = new SimpleContainer(1);
    private final Container container;
    private final CraftingContainer craftingContainer;
    private final Player player;

    public final DataSlot colorRed = DataSlot.standalone();
    public final DataSlot colorGreen = DataSlot.standalone();
    public final DataSlot colorBlue = DataSlot.standalone();

    // Client-side constructor
    public ColorerBlockMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(CONTAINER_SIZE));
    }

    // Server-side constructor
    public ColorerBlockMenu(final int containerId, final Inventory inventory, final Container container) {
        super(FlatColoredBlocksMenuTypes.COLORER, containerId);
        checkContainerSize(container, CONTAINER_SIZE);
        this.container = container;
        this.player = inventory.player;

        // Some containers do custom logic when opened by a player.
        container.startOpen(this.player);

        this.craftingContainer = new TransientCraftingContainer(this, 1, 1);

        this.addSlot(new Slot(this.craftingContainer, 0, 35, 35));
        this.addSlot(new Slot(this.container, 0, 134, 8));

        this.addSlot(new ResultSlot(this.player, this.craftingContainer, this.resultContainer, 0, 116, 44));

        // Add the player inventory slots.
        this.addStandardInventorySlots(inventory, 8, 84);

        this.addDataSlot(colorRed);
        this.addDataSlot(colorGreen);
        this.addDataSlot(colorBlue);

        colorRed.set(255);
        colorGreen.set(0);
        colorBlue.set(0);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();

        if (slotIndex < container.getContainerSize()) {
            if (!this.moveItemStackTo(stack, container.getContainerSize(), this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!this.moveItemStackTo(stack, 0, container.getContainerSize(), false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return clicked;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    @Override
    public void slotsChanged(Container container) {
        if (container == craftingContainer) {
            if (container.getItem(0).is(FlatColoredBlocksItemTags.COLORED_BLOCKS)) {
                ItemStack item = container.getItem(0).copy();
                item.set(FlatColoredBlocksComponents.COLOR_COMPONENT,
                        new FlatColoredBlocksUtil.Color(colorRed.get(), colorGreen.get(), colorBlue.get())
                                .getColorAsRgb()
                );
                resultContainer.setItem(0, item);
            }
        }
        if (container == this.container) {
            if (container.getItem(0).is(FlatColoredBlocksItemTags.COLORED_BLOCKS)) {
                ItemStack item = container.getItem(0).copy();
                Integer intColor = item.get(FlatColoredBlocksComponents.COLOR_COMPONENT);
                if (intColor != null) {
                    FlatColoredBlocksUtil.Color color = new FlatColoredBlocksUtil.Color(intColor);
                    colorRed.  set(color.red());
                    colorGreen.set(color.green());
                    colorBlue. set(color.blue());
                    System.out.println("Setting!");
                }
            }
        }
    }
}
