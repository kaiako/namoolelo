/*
 * Copyright (c) 2010 Hawaii Information Consortium
 * 201 Merchant St #1805, Honolulu, HI 96813
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Hawaii Information Consortium. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with HIC.
 */

package com.namoolelo.domain;

import java.io.Serializable;

/**
 * Defines a set of methods required for identifiable entities.
 *
 * @param <I> determines an identifier type
 * @author <a href="mailto:warlock@ais.pl">Michal Jastak</a>, AIS.PL
 * @version $Revision$
 */
public interface Identifiable<I extends Serializable> {

    /**
     * Returns the identifier.
     *
     * @return the identifier
     */
    I getId();

    /**
     * Sets the identifier.
     *
     * @param identifier a new identifier to set
     */
    void setId(I identifier);
}
