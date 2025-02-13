package endergeticexpansion.core.registry.other;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import endergeticexpansion.core.registry.EEItems;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public enum EEArmorMaterials implements IArmorMaterial {
	BOOFLO("booflo_vest", new int[] {85, 85, 85, 85}, new int[] {3, 3, 3, 3}, 8, EEItems.BOOFLO_HIDE, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f);
	
	private int[] durabilities;
	private String name;
	private SoundEvent equipSound;
	private int enchantability;
	private Item repairItem;
	private int[] damageReductionAmounts;
	private float toughness;
	
	private EEArmorMaterials(String name, int[] durabilities, int[] damageReductionAmounts, int enchantability, Item repairItem, SoundEvent equipSound, float toughness) {
		this.name = name;
		this.equipSound = equipSound;
		this.durabilities = durabilities;
		this.enchantability = enchantability;
		this.repairItem = repairItem;
		this.damageReductionAmounts = damageReductionAmounts;
		this.toughness = toughness;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot) {
		return this.damageReductionAmounts[slot.getIndex()];
	}

	@Override
	public int getDurability(EquipmentSlotType slot) {
		return durabilities[slot.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairItem);
	}

	@Override
	public SoundEvent getSoundEvent() {
		return this.equipSound;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}
	
}
