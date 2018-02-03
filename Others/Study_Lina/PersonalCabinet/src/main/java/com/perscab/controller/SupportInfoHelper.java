package com.perscab.controller;

import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.model.support.SupportServiceInfo;
import com.perscab.model.support.SupportSocialInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class SupportInfoHelper {

    private static final int INTERNET_CATEGORY_ID = 1;
    private static final int TV_CATEGORY_ID = 2;
    private static final int PHONE_CATEGORY_ID = 3;
    private static final int BILLING_CATEGORY_ID = 4;

    private static final int FACEBOOK_ATTR_ID = 102;
    private static final int VK_ATTR_ID = 103;

    public static void initSupportInfo(HttpServletRequest request, boolean isPaymentHistoryRequired) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();

            Collection<SupportServiceInfo>  supportServiceInfo = DBUtils.getSupportServiceInfo(conn);
            for (SupportServiceInfo infoRecord : supportServiceInfo) {
                System.out.println("SupportInfoHelper.initSupportInfo: [supportServiceInfo]:" + infoRecord);
                switch (infoRecord.getCategoryId().intValue()) {
                    case INTERNET_CATEGORY_ID:
                        request.setAttribute("internetSupportInfo", infoRecord); break;
                    case TV_CATEGORY_ID:
                        request.setAttribute("tvSupportInfo", infoRecord); break;
                    case PHONE_CATEGORY_ID:
                        request.setAttribute("phoneSupportInfo", infoRecord); break;
                    case BILLING_CATEGORY_ID:
                        request.setAttribute("billingSupportInfo", infoRecord); break;
                }
            }

            if (isPaymentHistoryRequired) {
                Collection<SupportSocialInfo> supportSocialInfo = DBUtils.getSupportSocialInfo(conn);
                for (SupportSocialInfo infoRecord : supportSocialInfo) {
                    System.out.println("SupportInfoHelper.initSupportInfo: [supportSocialInfo]:" + infoRecord);
                    switch (infoRecord.getAttrId().intValue()) {
                        case FACEBOOK_ATTR_ID :
                            request.setAttribute("facebookSupportInfo", infoRecord); break;
                        case VK_ATTR_ID :
                            request.setAttribute("vkSupportInfo", infoRecord); break;
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }
}
