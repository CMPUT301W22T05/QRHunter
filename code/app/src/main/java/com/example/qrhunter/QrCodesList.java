package com.example.qrhunter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QrCodesList {


    private List<QrCodes> qrcodes = new ArrayList<>();

    /**
     * This adds a qrcode to the list if the qrcode does not exist
     *
     * @throws IllegalArgumentException
     *      If the qrcode already exist in the list this is thrown
     * @param qrcode
     * This is a candidate qrcode to add
     */

    public void add(QrCodes qrcode) {
        if (qrcodes.contains(qrcode)) {
            throw new IllegalArgumentException();
        }
        qrcodes.add(qrcode);
    }


    /**
     * This returns a sorted list of qrcodes
     * @return list
     * Return the sorted list
     */
    public List<QrCodes> getQrCodes() {
        List<QrCodes> list = qrcodes;
        Collections.sort(list);
        return list;
    }

    /**
     * This shows if the qrcode exists in the list or not
     * @return bool
     * @param qrcode
     * Return boolean
     */

    public boolean qrcodeExist(QrCodes qrcode){
        if(qrcodes.contains(qrcode)){
            return true;
        }
        return false;
    }

    /**
     * This delete a qrcode in the list if the qrcode exists
     *
     * @throws IllegalArgumentException
     *      If the qrcode does not exist in the list
     * @param qrcode
     * This is a qrcode to delete
     */

    public void deleteQrCodes(QrCodes qrcode) {
        if (qrcodes.contains(qrcode)) {
            qrcodes.remove(qrcode);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This returns the number of qrcodes in the list
     * @return qrcodes.size
     * This is an integer represents the size
     */
    public int countQrCodes(){
        return qrcodes.size();
    }
}
