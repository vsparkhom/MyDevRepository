package com.vlpa.jaxb.main;

import com.vlpa.jaxb.inventory.Inventory;

import javax.xml.bind.JAXBException;

public class ConvertTool {

    public static void main(String[] args) throws JAXBException {

        String inputXmlFileName = "generate/test_modem.xml";
        String outputXmlFileName = "generate/test_modem_out.xml";
        Inventory inventory = XMLHelper.fromXML(inputXmlFileName);

        System.out.println("XML file name: " + inputXmlFileName);
        System.out.println("Inventory Object: " + inventory);
        System.out.println("-------------------------------------------");

        XMLHelper.toXML(inventory, outputXmlFileName);
        System.out.println("Inventory Object has been marshalled to XML file: " + outputXmlFileName);

    }
}
