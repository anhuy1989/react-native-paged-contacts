package com.wix.pagedcontacts.contacts.Items;

import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Im;

import com.facebook.react.bridge.WritableMap;
import com.wix.pagedcontacts.contacts.query.QueryParams;

class InstantMessagingAddress extends ContactItem {
    private String data;
    private String protocol;

    InstantMessagingAddress(Cursor cursor) {
        super(cursor);
        fillFromCursor();
    }

    private void fillFromCursor() {
        data = getString(Im.DATA);
        final String protocolId = getString(Im.PROTOCOL);
        final String customProtocol = getString(Im.CUSTOM_PROTOCOL);
        protocol = getProtocolName(protocolId, customProtocol);
    }

    private String getProtocolName(String protocolId, String customProtocol) {
        if (protocolId == null) {
            throw new InvalidCursorTypeException();
        }
        switch (Integer.valueOf(protocolId)) {
            case Im.PROTOCOL_AIM:
                return "AIM";
            case Im.PROTOCOL_MSN:
                return "MSN";
            case Im.PROTOCOL_YAHOO:
                return "Yahoo";
            case Im.PROTOCOL_SKYPE:
                return "Skype";
            case Im.PROTOCOL_QQ:
                return "QQ";
            case Im.PROTOCOL_GOOGLE_TALK:
                return "Google Talk";
            case Im.PROTOCOL_ICQ:
                return "ICQ";
            case Im.PROTOCOL_JABBER:
                return "Jabber";
            case Im.PROTOCOL_NETMEETING:
                return "NetMeeting";
            case Im.PROTOCOL_CUSTOM:
                return customProtocol;
            default:
                return "Other";

        }
    }

    @Override
    protected void fillMap(WritableMap map, QueryParams params) {
        map.putString("service", protocol);
        map.putString("username", data);
    }
}
