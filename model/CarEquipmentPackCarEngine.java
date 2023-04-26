package mirosimo.car_showroom2.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.carEquipmentPack",
        joinColumns = @JoinColumn(name = "EQUIPMENT_PACK_ID")),
    @AssociationOverride(name = "primaryKey.carEngine",
        joinColumns = @JoinColumn(name = "ENGINE_ID")) })
public class CarEquipmentPackCarEngine {
	
	// Composite Key
	private CarEquipmentPackCarEngineId primaryKey = new CarEquipmentPackCarEngineId();
	
	// Extra fields in connection table
	private String marketingCarVariantName;
	private boolean active;
	
	@EmbeddedId
	public CarEquipmentPackCarEngineId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(CarEquipmentPackCarEngineId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public CarEngine getCarEngine() {
		return this.getPrimaryKey().getCarEngine();
	}
	
	public void setCarEngine(CarEngine carEngine) {
		this.primaryKey.setCarEngine(carEngine);
	}
	
	@Transient
	public CarEquipmentPack getCarEquipmentPack() {
		return this.getPrimaryKey().getCarEquipmentPack();
	}
	
	public void setCarEquipmentPack(CarEquipmentPack carEquipmentPack) {
		this.primaryKey.setCarEquipmentPack(carEquipmentPack);
	}
	
	public String getMarketingCarVariantName() {
		return marketingCarVariantName;
	}
	public void setMarketingCarVariantName(String marketingCarVariantName) {
		this.marketingCarVariantName = marketingCarVariantName;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
