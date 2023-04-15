package com.ASWLogistic.dto;

public class PODWrapperDTO {
    private PODDTO holder;
    private PODDetailsDTO holderDetail;

    public PODDTO getHolder() {
        return holder;
    }

    public void setHolder(PODDTO holder) {
        this.holder = holder;
    }

    public PODDetailsDTO getHolderDetail() {
        return holderDetail;
    }

    public void setHolderDetail(PODDetailsDTO holderDetail) {
        this.holderDetail = holderDetail;
    }
}