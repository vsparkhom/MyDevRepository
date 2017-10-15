package com.vlpa.jaxb.main;

import com.vlpa.jaxb.inventory.Inventory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import java.io.File;

public class XMLHelper {

    public static Inventory fromXML(String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.vlpa.jaxb.inventory");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent event) {
                return false;
            }
        });

        Inventory inventory = (Inventory) unmarshaller.unmarshal(new File(fileName));
        return inventory;
    }

    public static void toXML(Inventory inventory, String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.vlpa.jaxb.inventory");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(inventory, new File(fileName));
    }

}
