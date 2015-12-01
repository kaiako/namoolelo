package com.namoolelo.service.util;

import java.util.ArrayList;
import java.util.List;

import com.namoolelo.domain.Moolelo;

public class MooleloList {

    private List<Moolelo> moolelos = new ArrayList<Moolelo>();

    public MooleloList(List<Moolelo> list) {
        this.moolelos = list;
    }

    public List<Moolelo> getMoolelos() {
        return moolelos;
    }

    public void setMoolelos(List<Moolelo> moolelos) {
        this.moolelos = moolelos;
    }
}
