package com.vlpa.jaxb.inventory;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Inventory createInventory() {
        return new Inventory();
    }

    public Equipment createInventoryEquipment() {
        return new Equipment();
    }

    public Proprietor createInventoryEquipmentProprietor() {
        return new Proprietor();
    }

    public Components createInventoryEquipmentComponents() {
        return new Components();
    }

    public Specifications createInventoryEquipmentSpecifications() {
        return new Specifications();
    }

    public ProductLines createInventoryEquipmentProductLines() {
        return new ProductLines();
    }

    public Link createInventoryEquipmentLink() {
        return new Link();
    }

    public Identifier createInventoryEquipmentProprietorIdentifier() {
        return new Identifier();
    }
}
