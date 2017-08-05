package com.mycompany.florists.model.accessories;

import com.mycompany.florists.model.FlowerAccessories;
import java.io.Serializable;
import com.mycompany.florists.model.Flower;

/**
 * Represents flower accessory.
 *
 * @author Sławomir Kowalczyk
 */
public class Siatka extends FlowerAccessories implements Serializable {

    /**
     * Equips a flower with Siatka accessory.
     *
     * @param flower A flower to be decorated.
     */
    public Siatka(Flower flower) {
        super(flower);
    }

    /**
     *
     * @return Returns String describing the flower object decoration.
     */
    @Override
    public String getAccessories() {
        return flower.getAccessories() + " Siatka";
    }

}
