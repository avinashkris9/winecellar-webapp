/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.AreaDtoSorter;
import info.mywinecellar.model.Area;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Area converter
 */
@Component
public class AreaConverter {

    /**
     * Create a list of Dto objects
     *
     * @param areas The areas
     * @return The Dto objects
     */
    public List<AreaDto> toDto(List<Area> areas) {
        if (areas == null) {
            throw new IllegalStateException("Area list is null");
        }
        List<AreaDto> result = new ArrayList<>();
        areas.forEach(area -> result.add(toDto(area)));
        result.sort(new AreaDtoSorter());
        return result;
    }

    /**
     * Convert entity to dto
     *
     * @param a An area
     * @return dto object
     */
    public AreaDto toDto(Area a) {
        if (a == null) {
            throw new IllegalStateException("Area is null");
        }
        return new AreaDto(a);
    }

    /**
     * Create an Area entity
     *
     * @param a  An area
     * @param ui The Dto object
     * @return The area
     */
    public Area toEntity(Area a, AreaDto ui) {
        if (a == null) {
            a = new Area(ui.getName(), ui.getDescription(), ui.getWeblink());
        } else {
            a.setDescription(ui.getDescription());
            a.setWeblink(ui.getWeblink());
        }
        return a;
    }
}
