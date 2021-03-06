/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Grape;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for area
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class AreaDto extends AbstractKeyDto {

    private Long id;
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 50)
    private String weblink;

    private List<Long> primaryGrapes;

    /**
     * Constructor
     */
    public AreaDto() {
        this.primaryGrapes = new ArrayList<>();
    }

    /**
     * Constructor
     * @param a The area
     */
    public AreaDto(Area a) {
        super(toKey(a.getName()));
        this.id = a.getId();
        this.name = a.getName();
        this.description = a.getDescription();
        this.weblink = a.getWeblink();

        this.primaryGrapes = new ArrayList<>();
        for (Grape g : a.getPrimaryGrapes()) {
            this.primaryGrapes.add(g.getId());
        }
    }
}
