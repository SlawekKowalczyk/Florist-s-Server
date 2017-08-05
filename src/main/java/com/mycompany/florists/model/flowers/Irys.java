package com.mycompany.florists.model.flowers;

import com.mycompany.florists.model.ColourName;
import java.io.Serializable;
import com.mycompany.florists.model.Flower;

/**
 * Irys represents a flower.
 *
 * @author Sławomir Kowalczyk
 */
public class Irys extends ColourName implements Flower, Serializable {

    /**
     *
     * @param colour The colour of the flower being created.
     */
    public Irys(ColourName.Colours colour) {
        super("Irys", colour);
    }

    /**
     *
     * @return Returns flower catalogue description composed of a name and a
     * colour.
     */
    @Override
    public String getCatalogueDescription() {
        return name + " " + colour;
    }

    /**
     *
     * @return Returns catalogue description together with decorative
     * accessories.
     */
    @Override
    public String getDescription() {
        return getCatalogueDescription() + getAccessories();
    }

}
