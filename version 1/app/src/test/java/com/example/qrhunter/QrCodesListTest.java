package com.example.qrhunter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QrCodesListTest {


    private QrCodesList mockQrCodesList() {
        QrCodesList qrcodeList = new QrCodesList();
        qrcodeList.add(mockQrCodes());
        return qrcodeList;
    }
    private QrCodes mockQrCodes() {
        return new QrCodes( "good",53.526,-113.527,"qrcode1",30,"http://www.google.com");
    }

    /**
     * This tests the functionality of adding qrcodes
     */
    @Test
    void testAdd() {
        QrCodesList qrcodeList = mockQrCodesList();
        assertEquals(1, qrcodeList.getQrCodes().size());

        QrCodes qrcode = new QrCodes("not good",23.526,113.527,"qrcode2",52,"http://www.ualberta.com");
        qrcodeList.add(qrcode);

        assertEquals(2, qrcodeList.getQrCodes().size());
        assertTrue(qrcodeList.getQrCodes().contains(qrcode));
    }

    /**
     * This tests the exception of adding
     */
    @Test
    void testAddException() {
        QrCodesList qrcodeList = mockQrCodesList();
        QrCodes qrcode = new QrCodes("bad",76.516,113.527,"qrcode3",10,"http://www.baidu.com");
        qrcodeList.add(qrcode);
        assertThrows(IllegalArgumentException.class, () -> {
            qrcodeList.add(qrcode);
        });
    }

    /**
     * This tests the functionality of getting qrcodes from the list
     */
    @Test
    void testGetQrCodes() {
        QrCodesList qrcodeList = mockQrCodesList();
        assertEquals(0, mockQrCodes().compareTo(qrcodeList.getQrCodes().get(0)));
        QrCodes qrcode = new QrCodes( "perfect",80.533,120.527,"qrcode4",100,"http://www.beartrack.com");
        qrcodeList.add(qrcode);
        assertEquals(9, qrcode.compareTo(qrcodeList.getQrCodes().get(0)));
    }

    /**
     * This tests if the qrcode exist in the list or not
     */
    @Test
    void testExistence(){
        QrCodesList qrcodeList = mockQrCodesList();
        QrCodes qrcode = new QrCodes("bad",20.516,114.527,"qrcode5",2, "http://www.gmail.com");
        assertFalse(qrcodeList.qrcodeExist(qrcode));
    }

    /**
     * This tests the functionality of counting qrcodes from the list
     */
    @Test
    void testCount(){
        QrCodesList qrcodeList = mockQrCodesList();
        assertEquals(qrcodeList.getQrCodes().size(),qrcodeList.countQrCodes());
    }

    /**
     * This tests the functionality of deleting qrcodes from the list
     */
    @Test
    void testDelete(){
        QrCodesList qrcodeList = mockQrCodesList();
        QrCodes qrcode = new QrCodes( "so so",41.216,45.527,"qrcode6",41,"http://www.android.com");
        qrcodeList.add(qrcode);
        assertEquals(2,qrcodeList.countQrCodes());
        qrcodeList.deleteQrCodes(qrcode);
        assertEquals(1,qrcodeList.countQrCodes());
        assertFalse(qrcodeList.getQrCodes().contains(qrcode));
    }

    /**
     * This tests the exception of deleting qrcodes from the list
     */
    @Test
    void testDeleteException() {
        QrCodesList qrcodeList = mockQrCodesList();
        QrCodes qrcode = new QrCodes("boring",76.016,2.527,"qrcode7", 30,"http://www.lenovo.com");
        assertThrows(IllegalArgumentException.class, () -> {
            qrcodeList.deleteQrCodes(qrcode);
        });
    }
}
