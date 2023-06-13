package com.anth0o0ny.model;


import com.anth0o0ny.sdes.LectureSde;
import com.anth0o0ny.sdes.MySde;
import com.anth0o0ny.sdes.SDE;
import com.anth0o0ny.sdes.SiteSde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SdeData {

    private Double x0;
    private Double xN;
    private Double yX0;
    private Double h;
    private Double eps;
    private String sde;
    private SDE sdeEquation;

    public SdeData() {
    }

    public SdeData(Double x0, Double xN, Double yX0, Double h, Double eps, String sde) {
        this.x0 = x0;
        this.xN = xN;
        this.yX0 = yX0;
        this.h = h;
        this.eps = eps;
        this.sde = sde;
    }

    public boolean isValidData() {

        if (x0 == null || xN == null || yX0 == null || h == null || eps == null || sde == null) {
            return false;
        }

        boolean firstCondition= xN > x0;
        boolean secondCondition= h <= (xN - x0);
        boolean thirdCondition= h > 0;
        boolean fourthCondition= eps > 0;

        return firstCondition && secondCondition && thirdCondition && fourthCondition;

    }

    public void validateData() {

        switch (sde) {
            case "lecture-sde":
                this.sdeEquation = new LectureSde(this.x0, this.yX0);
                break;
            case "site-sde":
                this.sdeEquation = new SiteSde(this.x0, this.yX0);
                break;
            case "my-sde":
                this.sdeEquation = new MySde(this.x0, this.yX0);
                break;
            default:
                this.sdeEquation = null;
                break;
        }

    }

    public Double getX0() {
        return x0;
    }

    public void setX0(Double x0) {
        this.x0 = x0;
    }

    public Double getxN() {
        return xN;
    }

    public void setxN(Double xN) {
        this.xN = xN;
    }

    public Double getyX0() {
        return yX0;
    }

    public void setyX0(Double yX0) {
        this.yX0 = yX0;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public String getSde() {
        return sde;
    }

    public void setSde(String sde) {
        this.sde = sde;
    }

    public SDE getSdeEquation() {
        return sdeEquation;
    }

    @Override
    public String toString() {
        return "SdeData{" +
                "x0=" + x0 +
                ", xN=" + xN +
                ", yX0=" + yX0 +
                ", h=" + h +
                ", eps=" + eps +
                ", sde='" + sde + '\'' +
                '}';
    }
}
